package pl.edu.agh.jcsp.nbuffers.ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {

    private final One2OneChannelInt in;
    private final int numberOfProductsToRead;

    public Consumer(final One2OneChannelInt in, final int numberOfProductsToRead) {
        this.in = in;
        this.numberOfProductsToRead = numberOfProductsToRead;
    }

    @Override
    public void run() {
        final long start = System.nanoTime();

        for (int i = 0; i < numberOfProductsToRead; i++) {
            int item = in.in().read();
            System.out.println("Consumer got : " + item);
        }

        final long end = System.nanoTime();
        System.out.println("Consumer time: " + (end - start));
    }
}
