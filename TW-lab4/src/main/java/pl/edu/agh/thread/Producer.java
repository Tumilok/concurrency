package pl.edu.agh.thread;

import pl.edu.agh.buffer.Storage;

public class Producer extends Worker {
    public Producer(Storage storage, int portion) {
        super(storage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            storage.put(portion);
            ++accessNumber;
        }
    }
}
