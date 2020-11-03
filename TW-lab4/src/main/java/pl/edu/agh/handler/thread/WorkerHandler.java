package pl.edu.agh.handler.thread;

import pl.edu.agh.AccessCounter;
import pl.edu.agh.buffer.Storage;
import pl.edu.agh.configuration.PropertyReader;
import pl.edu.agh.handler.portion.FairPortionHandler;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.handler.portion.UnfairPortionHandler;
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

    protected PortionHandler getPortionHandlerInstance() {
        String randomization = PropertyReader.getInstance().getRandomization();
        assert randomization != null;
        return randomization.equals("fair") ? new FairPortionHandler() : new UnfairPortionHandler();
    }

    public static WorkerHandler getConsumerHandlerInstance(Storage storage) {
        return new ConsumerHandler(storage, PropertyReader.getInstance().getPC_Ratio());
    }

    public static WorkerHandler getProducerHandlerInstance(Storage storage) {
        return new ProducerHandler(storage, PropertyReader.getInstance().getPC_Ratio());
    }
}
