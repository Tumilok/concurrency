package pl.edu.agh.handler;

import pl.edu.agh.Storage;
import pl.edu.agh.thread.Worker;

import java.util.ArrayList;
import java.util.List;

public abstract class WorkerHandler {
    protected final PortionHandler portionHandler = new PortionHandler();
    protected final List<Worker> workers = new ArrayList<>();
    protected final Storage storage;

    public WorkerHandler(Storage storage) {
        this.storage = storage;
    }

    public abstract void createAndRunWorkers();

    private int getMinAccessNumber() {
        return workers.stream().map(Worker::getAccessNumber).min(Integer::compareTo).orElse(-1);
    }

    public void printHistogram() {
        workers.forEach(c -> c.printAccessNumber(getMinAccessNumber()));
    }
}
