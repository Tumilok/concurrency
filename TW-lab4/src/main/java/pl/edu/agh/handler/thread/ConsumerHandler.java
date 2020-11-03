package pl.edu.agh.handler.thread;

import pl.edu.agh.ConfigFileParser;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.handler.portion.FairPortionHandler;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.handler.portion.UnfairPortionHandler;
import pl.edu.agh.thread.Consumer;

public class ConsumerHandler extends WorkerHandler {
    public ConsumerHandler(Storage storage, int workerNumber) {
        super(storage, workerNumber);
    }

    @Override
    public void createAndRunWorkers() {
        String randomization = ConfigFileParser.RANDOMIZATION.getValue();
        PortionHandler portionHandler;
        assert randomization != null;
        if (randomization.equals("fair")) {
            portionHandler = new FairPortionHandler();
        } else {
            portionHandler = new UnfairPortionHandler();
        }
        for (int i = 0; i < workerNumber; i++) {
            workers.add(new Consumer(storage, portionHandler, counter));
            int finalI = i;
            new Thread(() -> workers.get(finalI).work()).start();
        }
    }
}
