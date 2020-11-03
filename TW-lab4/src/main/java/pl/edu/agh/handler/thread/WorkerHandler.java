package pl.edu.agh.handler.thread;

import pl.edu.agh.AccessCounter;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.thread.Worker;

import java.util.ArrayList;
import java.util.List;

public abstract class WorkerHandler {
    protected final Storage storage;
    protected final AccessCounter counter;

    protected final int workerNumber;
    protected final String workersName;

    protected final List<Worker> workers = new ArrayList<>();

    public WorkerHandler(Storage storage, int workerNumber) {
        workersName = this instanceof ProducerHandler ? "Producer" : "Consumer";
        counter = new AccessCounter(workersName);
        this.storage = storage;
        this.workerNumber = workerNumber;
    }

    public abstract void createAndRunWorkers();

    public void writeResults() {
        counter.writeResults();
    }
}
