package pl.edu.agh.jcsp.nbuffers.unordered;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Guard;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {

    private final One2OneChannelInt[] out;
    private final One2OneChannelInt[] additional;
    private final int numberOfProductsToWrite;

    public Producer(final One2OneChannelInt[] out, final One2OneChannelInt[] additional,
                    final int numberOfProductsToWrite) {
        this.out = out;
        this.additional = additional;
        this.numberOfProductsToWrite = numberOfProductsToWrite;
    }

    @Override
    public void run() {
        final long start = System.nanoTime();

        final Guard[] guards = new Guard[additional.length];
        for (int i = 0; i < additional.length; i++) {
            guards[i] = additional[i].in();
        }

        final Alternative alternative = new Alternative(guards);

        for (int i = 0; i < numberOfProductsToWrite; i++) {
            int index = alternative.select();
            additional[index].in().read();
            int item = (int) (Math.random() * 100) + 1;
            System.out.println("Producer put: " + item + " to index: " + index);
            out[index].out().write(item);
        }

        final long end = System.nanoTime();
        System.out.println("Producer time: " + (end - start));
    }
}
