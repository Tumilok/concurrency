package pl.edu.agh.thread;

import pl.edu.agh.Storage;

public class Consumer extends Worker {
    public Consumer(Storage storage, int portion) {
        super(storage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                storage.take(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
