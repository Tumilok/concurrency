package main.java.agh.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PortionHandler {
    private final Random rand = new Random();
    private final List<Integer> portions = new ArrayList<>();

    public PortionHandler() {
        for (int i = 1; i <= 8192; i*=2) {
            portions.add(i);
        }
    }

    public int getRandomPortion() {
        int index = rand.nextInt(portions.size());
        int randomPortion = portions.get(index);
        portions.remove(index);
        return randomPortion;
    }
}
