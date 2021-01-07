package pl.edu.agh.jcsp.onebuffer;

import org.jcsp.lang.*;

public class Consumer implements CSProcess {

    private final One2OneChannelInt in;

    public Consumer(One2OneChannelInt in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            int item = in.in().read();
            System.out.println("Got: " + item);
        }
    }
}
