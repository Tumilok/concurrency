package pl.edu.agh.tw;

import java.util.HashMap;
import java.util.Map;

public class Statistics {

    private static final Map<Integer, Integer> accessNumber = new HashMap<>();

    public static void increaseAccessNumber(int idx) {
        int value = accessNumber.getOrDefault(idx, 0);
        accessNumber.put(idx, value + 1);
    }

    public static void printStatistics() {
        accessNumber.forEach((key, value) -> System.out.println("Philosopher " + key + " have eaten " + value + " times"));
    }
}
