package pl.edu.agh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private static final int STORAGE_CAPACITY = 16384;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int numberOfStorageElements = 0;

    public void put(int portion) throws InterruptedException {
        lock.lock();
        try {
            while (numberOfStorageElements + portion > STORAGE_CAPACITY) {
                notFull.await();
            }
            numberOfStorageElements += portion;
            Worker.incrementAccessMap(false, portion);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public void take(int portion) throws InterruptedException {
        lock.lock();
        try {
            while (numberOfStorageElements - portion < 0) {
                notEmpty.await();
            }
            numberOfStorageElements -= portion;
            Worker.incrementAccessMap(true, portion);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
