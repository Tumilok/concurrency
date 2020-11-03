package pl.edu.agh.thread;

import pl.edu.agh.buffer.UnfairStorage;

public class Consumer extends Worker {
    public static final String CONSUMER_NAME = "CONSUMER";

    public Consumer(UnfairStorage unfairStorage, int portion) {
        super(unfairStorage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                unfairStorage.take(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
