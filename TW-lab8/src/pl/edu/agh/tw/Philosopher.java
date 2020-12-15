package pl.edu.agh.tw;

public class Philosopher implements Runnable {

    private final int idx;
    private final Table table;
    private boolean isInterrupted = false;

    public Philosopher(int idx, Table table) {
        this.idx = idx;
        this.table = table;
    }

    private void think() {
        try {
            System.out.println(idx + ": I'm thinking...");
            Thread.sleep(3);
        } catch (InterruptedException e) {
            isInterrupted = true;
        }
    }

    private void eat() {
        try {
            System.out.println(idx + ": I'm eating...");
            Statistics.increaseAccessNumber(idx);
            Thread.sleep(3);
        } catch (InterruptedException e) {
            isInterrupted = true;
        }
    }

    @Override
    public void run() {
        while (!isInterrupted) {
            think();
            table.put(idx);
            eat();
            table.take(idx);
        }
    }

    public void interrupt() {
        isInterrupted = true;
    }
}
