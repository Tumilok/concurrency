package main.java.agh.thread;

import main.java.agh.Storage;

public class Consumer extends Worker {
    public static final String CONSUMER_NAME = "CONSUMER";

    public Consumer(Storage storage, int portion) {
        super(storage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                storage.take(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
