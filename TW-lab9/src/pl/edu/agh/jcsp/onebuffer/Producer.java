package pl.edu.agh.jcsp.onebuffer;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Producer implements CSProcess {

    private final One2OneChannelInt out;

    public Producer(One2OneChannelInt out) {
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            int item = (int) (Math.random() * 100) + 1;
            out.out().write(item);
            System.out.println("Put: " + item);
        }
    }
}
