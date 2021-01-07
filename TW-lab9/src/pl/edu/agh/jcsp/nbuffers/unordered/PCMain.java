package pl.edu.agh.jcsp.nbuffers.unordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.lang.StandardChannelIntFactory;

public class PCMain {

    private static final int BUFFER_SIZE = 20;
    private static final int PRODUCTS_NUM = 100000;

    public static void main(String[] args) {
        StandardChannelIntFactory factory = new StandardChannelIntFactory();

        One2OneChannelInt[] out = factory.createOne2One(BUFFER_SIZE);
        One2OneChannelInt[] buffer = factory.createOne2One(BUFFER_SIZE);
        One2OneChannelInt[] in = factory.createOne2One(BUFFER_SIZE);

        CSProcess[] procList = new CSProcess[BUFFER_SIZE + 2];

        procList[0] = new Producer(out, buffer, PRODUCTS_NUM);
        procList[1] = new Consumer(in, PRODUCTS_NUM);
        for (int i = 0; i < BUFFER_SIZE; i++) {
            procList[2 + i] = new Buffer(out[i], in[i], buffer[i]);
        }

        Parallel parallel = new Parallel(procList);
        parallel.run();
    }
}
