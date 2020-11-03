package pl.edu.agh.handler.thread;

import pl.edu.agh.Storage;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.thread.Consumer;

public class ConsumerHandler extends WorkerHandler {
    private static final int CONSUMER_NUMBER = 8;

    public ConsumerHandler(Storage storage, PortionHandler portionHandler) {
        super(storage, portionHandler);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            workers.add(new Consumer(storage, portionHandler.getPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
