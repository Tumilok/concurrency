package pl.edu.agh;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final int buffSize = Integer.parseInt(Objects.requireNonNull(ConfigFileParser.BUFF_SIZE.getValue()));
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public int numberOfStorageElements = 0;

    public void put(int portion) throws InterruptedException {
        lock.lock();
        try {

            while (numberOfStorageElements + portion > buffSize) {
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
