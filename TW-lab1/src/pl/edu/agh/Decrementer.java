package pl.edu.agh;

public class Decrementer extends Thread {

    private final Counter counter;

    public Decrementer(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < Counter.THREAD_REPEATS; i++) {
            counter.dec();
        }
        this.stop();
    }
}
