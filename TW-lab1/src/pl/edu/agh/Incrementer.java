package pl.edu.agh;

public class Incrementer extends Thread {

    private final Counter counter;

    public Incrementer(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < Counter.THREAD_REPEATS; i++) {
            counter.inc();
        }
        this.stop();
    }
}
