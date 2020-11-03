package main.java.agh.thread;

import main.java.agh.Storage;

public class Producer extends Worker {
    public static final String WORKER_NAME = "Producer";

    public Producer(Storage storage, int portion) {
        super(storage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                storage.put(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}