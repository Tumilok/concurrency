package pl.edu.agh.thread;

import pl.edu.agh.AccessCounter;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.handler.portion.PortionHandler;

public abstract class Worker {
    protected static boolean isFinished = false;

    protected final Storage storage;
    protected final PortionHandler portionHandler;
    protected final AccessCounter counter;

    public Worker(Storage storage, PortionHandler portionHandler, AccessCounter counter) {
        this.storage = storage;
        this.portionHandler = portionHandler;
        this.counter = counter;
    }

    public abstract void work();

    public static void finish() {
        isFinished = true;
    }
}
