package pl.edu.agh.handler.thread;

import pl.edu.agh.Storage;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.thread.Producer;

public class ProducerHandler extends WorkerHandler {
    private static final int PRODUCER_NUMBER = 8;

    public ProducerHandler(Storage storage, PortionHandler portionHandler) {
        super(storage, portionHandler);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < PRODUCER_NUMBER; i++) {
            workers.add(new Producer(storage, portionHandler.getPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
