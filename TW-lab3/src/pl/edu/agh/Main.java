package pl.edu.agh;

import static pl.edu.agh.Worker.CONSUMER_NUMBER;
import static pl.edu.agh.Worker.PRODUCER_NUMBER;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        PortionHandler consumerPortionHandler = new PortionHandler();
        PortionHandler producerPortionHandler = new PortionHandler();

        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            new Thread(() -> new Worker(storage, consumerPortionHandler.getRandomPortion()).work(true)).start();
        }

        for (int i = 0; i < PRODUCER_NUMBER; i++) {
            new Thread(() -> new Worker(storage, producerPortionHandler.getRandomPortion()).work(false)).start();
        }

        Thread.sleep(10000);

        System.out.println("Consumers");
        Worker.printHistogram(true);

        System.out.println("Producers");
        Worker.printHistogram(false);

        System.exit(0);
    }
}
