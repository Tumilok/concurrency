package pl.edu.agh.jcsp.nbuffers.ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {

    private final One2OneChannelInt out;
    private final int numberOfProductsToWrite;

    public Producer(final One2OneChannelInt out, final int numberOfProductsToWrite) {
        this.out = out;
        this.numberOfProductsToWrite = numberOfProductsToWrite;
    }

    @Override
    public void run() {
        final long start = System.nanoTime();

        for (int i = 0; i < numberOfProductsToWrite; i++) {
            int item = (int) (Math.random() * 100) + 1;
            System.out.println("Producer put: " + item);
            out.out().write(item);
        }

        final long end = System.nanoTime();
        System.out.println("Producer time: " + (end - start));
    }
}
