package main.java.agh.handler;

import main.java.agh.Storage;
import main.java.agh.thread.Consumer;

public class ConsumerHandler extends WorkerHandler {
    private static final int CONSUMER_NUMBER = 8;

    public ConsumerHandler(Storage storage) {
        super(storage);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            workers.add(new Consumer(storage, portionHandler.getRandomPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
