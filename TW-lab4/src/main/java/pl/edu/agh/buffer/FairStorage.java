package pl.edu.agh.buffer;

import pl.edu.agh.configuration.PropertyReader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairStorage implements Storage {
    private final int buffSize = PropertyReader.getInstance().getBuffSize();

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Lock consumerLock = new ReentrantLock(true);
    private final Lock producerLock = new ReentrantLock(true);

    public int numberOfStorageElements = 0;

    @Override
    public void put(int portion) {
        producerLock.lock();
        lock.lock();
        try {
            while (numberOfStorageElements + portion > buffSize) {
                notFull.await();
            }
            numberOfStorageElements += portion;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            producerLock.unlock();
        }
    }

    @Override
    public void take(int portion) {
        consumerLock.lock();
        lock.lock();
        try {
            while (numberOfStorageElements - portion < 0) {
                notEmpty.await();
            }
            numberOfStorageElements -= portion;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            consumerLock.unlock();
        }
    }
}
