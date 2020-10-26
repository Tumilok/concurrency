package pl.edu.agh;

import java.util.HashMap;
import java.util.Map;

public class Worker {
    public static final int CONSUMER_NUMBER = 8;
    private static final Map<Integer, Integer> consumerAccess = new HashMap<>();
    public static final int PRODUCER_NUMBER = 8;
    private static final Map<Integer, Integer> producerAccess = new HashMap<>();

    private final Storage storage;
    private final int portion;

    public Worker(Storage storage, int portion) {
        this.storage = storage;
        this.portion = portion;
    }

    public void work(boolean doConsume) {
        while (true) {
            try {
                if (doConsume) {
                    storage.take(portion);
                } else {
                    storage.put(portion);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void incrementAccessMap(boolean isConsume, int portion) {
        Map<Integer, Integer> map = isConsume ? consumerAccess : producerAccess;
        int value = map.getOrDefault(portion, 1);
        map.put(portion, value + 1);
    }

    public static void printHistogram(boolean isConsumer) {
        Map<Integer, Integer> mapToPrint = isConsumer ? consumerAccess : producerAccess;
        for (int key : mapToPrint.keySet()) {
            System.out.print(key + ": ");
            int min = mapToPrint.values().stream().min(Integer::compareTo).orElse(-1);
            for (int i = 0; i < mapToPrint.get(key) / min; i++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
}
