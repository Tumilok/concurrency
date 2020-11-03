package pl.edu.agh.handler;

import pl.edu.agh.Storage;
import pl.edu.agh.thread.Producer;

public class ProducerHandler extends WorkerHandler {
    private static final int PRODUCER_NUMBER = 8;

    public ProducerHandler(Storage storage) {
        super(storage);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < PRODUCER_NUMBER; i++) {
            workers.add(new Producer(storage, portionHandler.getRandomPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }

    @Override
    public void printHistogram() {
        System.out.println("Producers");
        super.printHistogram();
    }
}
