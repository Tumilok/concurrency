package pl.edu.agh.tw;

import java.util.Arrays;

public class Main {

    private static final int PHILOSOPHERS_NUMBER = 5;
    private static final int PROGRAM_TIME = 10000;
    private static final boolean IS_ARBITRATOR = false;

    public static void main(String[] args) throws InterruptedException {
        Table table = IS_ARBITRATOR ? new ArbitratorTable(PHILOSOPHERS_NUMBER) :
                new StarvationTable(PHILOSOPHERS_NUMBER);

        Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_NUMBER];
        for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) philosophers[i] = new Philosopher(i, table);
        Arrays.stream(philosophers).map(Thread::new).forEach(Thread::start);

        Thread.sleep(PROGRAM_TIME);

        Arrays.stream(philosophers).forEach(Philosopher::interrupt);
        Statistics.printStatistics();
    }
}
