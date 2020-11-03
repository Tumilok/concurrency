package pl.edu.agh.handler.thread;

import pl.edu.agh.buffer.UnfairStorage;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.thread.Producer;

public class ProducerHandler extends WorkerHandler {
    public ProducerHandler(UnfairStorage unfairStorage, PortionHandler portionHandler, int workerNumber) {
        super(unfairStorage, portionHandler, workerNumber);
    }

    @Override
    public void createAndRunWorkers() {
        for (int i = 0; i < workerNumber; i++) {
            workers.add(new Producer(unfairStorage, portionHandler.getPortion()));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
