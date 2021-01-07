package pl.edu.agh.jcsp.nbuffers.unordered;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess {
    private final One2OneChannelInt in, out, additional;

    public Buffer(final One2OneChannelInt in, final One2OneChannelInt out, final One2OneChannelInt additional) {
        this.in = in;
        this.out = out;
        this.additional = additional;
    }

    public void run() {
        while (true) {
            additional.out().write(0);
            out.out().write(in.in().read());
        }
    }
}
