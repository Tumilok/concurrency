package pl.edu.agh.thread;

import pl.edu.agh.AccessCounter;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.handler.portion.PortionHandler;

public class Producer extends Worker {
    public Producer(Storage storage, PortionHandler portionHandler, AccessCounter counter) {
        super(storage, portionHandler, counter);
    }

    @Override
    public void work() {
        while (!isFinished) {
            int portion = portionHandler.getPortion();
            storage.put(portion);
            counter.incrementPortionsAccessNumber(portion);
        }
    }
}
