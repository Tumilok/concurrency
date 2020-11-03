package pl.edu.agh.thread;

import pl.edu.agh.AccessNumberCounter;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.handler.portion.PortionHandler;

public abstract class Worker {
    protected static boolean isFinished = false;

    protected final Storage storage;
    protected final PortionHandler portionHandler;
    protected final AccessNumberCounter counter;

    public Worker(Storage storage, PortionHandler portionHandler, AccessNumberCounter counter) {
        this.storage = storage;
        this.portionHandler = portionHandler;
        this.counter = counter;
    }

    public abstract void work();

    public static void finish() {
        isFinished = true;
    }
}
