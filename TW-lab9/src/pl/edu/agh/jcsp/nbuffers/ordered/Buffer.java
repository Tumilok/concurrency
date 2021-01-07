package pl.edu.agh.jcsp.nbuffers.ordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess {
    private final One2OneChannelInt in, out;

    public Buffer(final One2OneChannelInt in, final One2OneChannelInt out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        while (true) {
            out.out().write(in.in().read());
        }
    }
}
