package pl.edu.agh.tw.task1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int numberOfPhilosophers = 5;
        Table table = new Table(numberOfPhilosophers);
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, table);
        }

        Arrays.stream(philosophers).map(Thread::new).forEach(Thread::start);
        Thread.sleep(1000);
        Arrays.stream(philosophers).forEach(Philosopher::interrupt);

        Statistics.printStatistics();
    }
}
