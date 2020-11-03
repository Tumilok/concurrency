package pl.edu.agh.thread;

import pl.edu.agh.Storage;

public class Producer extends Worker {
    public Producer(Storage storage, int portion) {
        super(storage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                storage.put(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
