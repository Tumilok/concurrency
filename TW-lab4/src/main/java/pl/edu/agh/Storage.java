package main.java.agh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public int numberOfStorageElements = 0;

    public void put(int portion) throws InterruptedException {
        lock.lock();
        try {
            while (numberOfStorageElements + portion > Config.BUFF_SIZE) {
                notFull.await();
            }
            numberOfStorageElements += portion;
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
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
