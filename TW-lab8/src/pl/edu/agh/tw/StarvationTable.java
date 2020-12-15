package pl.edu.agh.tw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvationTable implements Table {

    private final Lock lock = new ReentrantLock();
    private final int numberOfForks;
    private final List<Integer> freeForks;
    private final List<Condition> philosophers;

    public StarvationTable(int numberOfForks) {
        this.numberOfForks = numberOfForks;
        this.freeForks = new ArrayList<>(Collections.nCopies(numberOfForks, 2));
        this.philosophers = new ArrayList<>();
        for (int i = 0; i < numberOfForks; i++) {
            philosophers.add(lock.newCondition());
        }
    }

    public void put(int idx) {
        lock.lock();
        while (freeForks.get(idx) < 2) {
            try {
                philosophers.get(idx).await();
            } catch (InterruptedException e) {
                lock.unlock();
                Thread.currentThread().interrupt();
                return;
            }
        }
        freeForks.set((idx + numberOfForks - 1) % numberOfForks,
                freeForks.get((idx + numberOfForks - 1) % numberOfForks) - 1);
        freeForks.set((idx + 1) % numberOfForks,
                freeForks.get((idx + 1) % numberOfForks) - 1);
        lock.unlock();
    }

    public void take(int idx) {
        lock.lock();
        freeForks.set((idx + numberOfForks - 1) % numberOfForks,
                freeForks.get((idx + numberOfForks - 1) % numberOfForks) + 1);
        freeForks.set((idx + 1) % numberOfForks,
                freeForks.get((idx + 1) % numberOfForks) + 1);
        philosophers.get((idx + numberOfForks - 1) % numberOfForks).signal();
        philosophers.get((idx + 1) % numberOfForks).signal();
        lock.unlock();
    }
}
