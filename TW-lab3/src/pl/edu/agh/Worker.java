package pl.edu.agh;

import java.util.Map;

public class Worker {
    public static boolean isFinished = false;

    public static final int CONSUMER_NUMBER = 8;
    public static final int PRODUCER_NUMBER = 8;

    private final Storage storage;
    private final int portion;
    private int accessNumber = 0;

    public Worker(Storage storage, int portion) {
        this.storage = storage;
        this.portion = portion;
    }

    public void work(boolean doConsume) {
        while (!isFinished) {
            try {
                if (doConsume) {
                    storage.take(portion);
                } else {
                    storage.put(portion);
                }
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(storage.numberOfStorageElements);
        }
    }

    public int getPortion() {
        return portion;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public static void printHistogram(Map<Integer, Integer> mapToPrint) {
        for (int key : mapToPrint.keySet()) {
            System.out.print(key + ": ");
            int min = mapToPrint.values().stream().min(Integer::compareTo).orElse(-1);
            for (int i = 0; i < mapToPrint.get(key) / min; i++) {
                System.out.print('*');
            }
            System.out.println(" " + mapToPrint.get(key));
        }
    }
}
