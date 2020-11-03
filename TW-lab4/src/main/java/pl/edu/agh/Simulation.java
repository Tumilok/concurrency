package pl.edu.agh;

import pl.edu.agh.buffer.Storage;
import pl.edu.agh.configuration.PropertyReader;
import pl.edu.agh.handler.thread.WorkerHandler;
import pl.edu.agh.thread.Worker;

public class Simulation {
    public void simulate() throws InterruptedException {
        PropertyReader config = PropertyReader.getInstance();

        for (int i = 0; i < 16; i++) {
            printInfo(config);

            Storage storage = Storage.getStorageInstance();

            WorkerHandler consumerHandler = WorkerHandler.getConsumerHandlerInstance(storage);
            WorkerHandler producerHandler = WorkerHandler.getProducerHandlerInstance(storage);

            consumerHandler.createAndRunWorkers();
            producerHandler.createAndRunWorkers();

            Thread.sleep(config.getSimulationDuration() * 1000);

            Worker.finish();

            consumerHandler.writeResults();
            producerHandler.writeResults();

            config.switchPath(i + 1);
            Worker.start();
        }
        System.exit(0);
    }

    private void printInfo(PropertyReader config) {
        System.out.println("Buff size: " + config.getBuffSize());
        System.out.println("PC ratio: " + config.getPC_Ratio());
        System.out.println("Is fair: " + config.isFair());
        System.out.println("Randomization: " + config.getRandomization());
    }
}
