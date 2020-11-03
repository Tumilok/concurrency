package pl.edu.agh.thread;

import pl.edu.agh.buffer.UnfairStorage;

public class Producer extends Worker {
    public static final String WORKER_NAME = "Producer";

    public Producer(UnfairStorage unfairStorage, int portion) {
        super(unfairStorage, portion);
    }

    @Override
    public void work() {
        while (!isFinished) {
            try {
                unfairStorage.put(portion);
                ++accessNumber;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
