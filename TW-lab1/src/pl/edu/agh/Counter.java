package pl.edu.agh;

public class Counter {

    public static final int THREAD_REPEATS = 100000000;

    private int number = 0;

    public void inc() {
        number++;
    }

    public void dec() {
        number--;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Decrementer decrementer = new Decrementer(counter);
        Incrementer incrementer = new Incrementer(counter);

        decrementer.start();
        incrementer.start();

        decrementer.join();
        incrementer.join();

        System.out.println("Final value: " + counter.getNumber());
    }
}
