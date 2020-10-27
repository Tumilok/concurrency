package pl.edu.agh;

import pl.edu.agh.handler.ConsumerHandler;
import pl.edu.agh.handler.ProducerHandler;
import pl.edu.agh.handler.WorkerHandler;
import pl.edu.agh.thread.Worker;

public class Main {

    private void run() throws InterruptedException {
        Storage storage = new Storage();

        WorkerHandler consumerHandler = new ConsumerHandler(storage);
        WorkerHandler producerHandler = new ProducerHandler(storage);

        consumerHandler.createAndRunWorkers();
        producerHandler.createAndRunWorkers();

        Thread.sleep(10000);

        Worker.finish();

        consumerHandler.printHistogram();
        producerHandler.printHistogram();

        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }
}
