package pl.edu.agh.handler;

import pl.edu.agh.Storage;
import pl.edu.agh.thread.Consumer;

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

    @Override
    public void printHistogram() {
        System.out.println("Consumers");
        super.printHistogram();
    }
}
