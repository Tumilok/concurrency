package pl.edu.agh.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ArbitratorTable implements Table {

    private final Semaphore arbiter;
    private final List<Semaphore> forks = new ArrayList<>();
    private final int numberOfForks;

    public ArbitratorTable(int numberOfForks) {
        this.numberOfForks = numberOfForks;
        arbiter = new Semaphore(numberOfForks - 1);
        for (int i = 0; i < numberOfForks; i++) {
            forks.add(new Semaphore(1));
        }
    }

    public void put(int idx) {
        try {
            arbiter.acquire();
            forks.get(idx).acquire();
            forks.get((idx + 1) % numberOfForks).acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take(int idx){
        forks.get(idx).release();
        forks.get((idx + 1) % numberOfForks).release();
        arbiter.release();
    }
}
