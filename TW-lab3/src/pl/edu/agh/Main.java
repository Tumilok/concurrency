package pl.edu.agh;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.edu.agh.Worker.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        PortionHandler consumerPortionHandler = new PortionHandler();
        PortionHandler producerPortionHandler = new PortionHandler();

        Worker[] producers = new Worker[PRODUCER_NUMBER];
        Worker[] consumers = new Worker[CONSUMER_NUMBER];

        for (int i = 0; i < CONSUMER_NUMBER; i++) {
            consumers[i] = new Worker(storage, consumerPortionHandler.getRandomPortion());
            int finalI = i;
            new Thread(() -> consumers[finalI].work(true)).start();
        }

        for (int i = 0; i < PRODUCER_NUMBER; i++) {
            producers[i] = new Worker(storage, producerPortionHandler.getRandomPortion());
            int finalI = i;
            new Thread(() -> producers[finalI].work(false)).start();
        }

        Thread.sleep(10000);
        isFinished = true;

        Map<Integer, Integer> consumerAccessMap = Arrays.stream(consumers).collect(Collectors.toMap(Worker::getPortion, Worker::getAccessNumber));
        Map<Integer, Integer> producerAccessMap = Arrays.stream(producers).collect(Collectors.toMap(Worker::getPortion, Worker::getAccessNumber));

        System.out.println("Consumers");
        Worker.printHistogram(consumerAccessMap);
        System.out.println("Producers");
        Worker.printHistogram(producerAccessMap);

        System.exit(0);
    }
}
