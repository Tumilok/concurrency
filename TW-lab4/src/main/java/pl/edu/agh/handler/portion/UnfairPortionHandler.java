package pl.edu.agh.handler.portion;

import pl.edu.agh.configuration.PropertyReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnfairPortionHandler implements PortionHandler {
    private final Random rand = new Random();
    private List<Integer> portions = null;
    private final int maxPortion = PropertyReader.getInstance().getBuffSize() / 2;

    protected void initPortions() {
        portions = new ArrayList<>();
        for (int i = 1; i <= Math.log(maxPortion) / Math.log(2); i *= 2) {
            portions.add(i);
            for (int j = 0; j < 50 - i; j++) {
                portions.add(i);
            }
        }
    }

    @Override
    public int getPortion() {
        if (portions == null) {
            initPortions();
        }
        int index = rand.nextInt(portions.size());
        return portions.get(index);
    }
}
