package pl.edu.agh.thread;

import pl.edu.agh.buffer.UnfairStorage;

public abstract class Worker {
    protected static boolean isFinished = false;

    protected final String workerName;
    protected final UnfairStorage unfairStorage;
    protected final int portion;
    protected int accessNumber = 0;

    public Worker(UnfairStorage unfairStorage, int portion) {
        workerName = this instanceof Consumer ? "Consumer" : "Producer";
        this.unfairStorage = unfairStorage;
        this.portion = portion;
    }

    public abstract void work();

    public String getWorkerName() {
        return workerName;
    }

    public int getPortion() {
        return portion;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public static void finish() {
        isFinished = true;
    }
}
