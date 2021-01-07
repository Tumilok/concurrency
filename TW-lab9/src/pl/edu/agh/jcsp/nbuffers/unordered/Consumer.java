package pl.edu.agh.jcsp.nbuffers.unordered;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Consumer implements CSProcess {

    private final One2OneChannelInt[] in;
    private final int numberOfProductsToRead;

    public Consumer(final One2OneChannelInt[] in, final int numberOfProductsToRead) {
        this.in = in;
        this.numberOfProductsToRead = numberOfProductsToRead;
    }

    @Override
    public void run() {
        final long start = System.nanoTime();

        final Guard[] guards = new Guard[in.length];
        for (int i = 0; i < in.length; i++) {
            guards[i] = in[i].in();
        }

        final Alternative alternative = new Alternative(guards);

        for (int i = 0; i < numberOfProductsToRead; i++) {
            int index = alternative.select();
            int item = in[index].in().read();
            System.out.println("Consumer got : " + item + " from index: " + index);
        }

        final long end = System.nanoTime();
        System.out.println("Consumer time: " + (end - start));

        System.exit(0);
    }
}
