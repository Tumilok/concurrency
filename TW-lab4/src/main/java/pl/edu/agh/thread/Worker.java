package main.java.agh.thread;

import main.java.agh.Storage;

public abstract class Worker {
    protected static boolean isFinished = false;

    protected final String workerName;
    protected final Storage storage;
    protected final int portion;
    protected int accessNumber = 0;

    public Worker(Storage storage, int portion) {
        workerName = this instanceof Consumer ? "Consumer" : "Producer";
        this.storage = storage;
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
