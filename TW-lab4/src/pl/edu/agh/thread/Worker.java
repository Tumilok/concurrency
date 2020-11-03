package pl.edu.agh.thread;

import pl.edu.agh.Storage;

public abstract class Worker {
    protected static boolean isFinished = false;

    protected final Storage storage;
    protected final int portion;
    protected int accessNumber = 0;

    public Worker(Storage storage, int portion) {
        this.storage = storage;
        this.portion = portion;
    }

    public abstract void work();

    public void printAccessNumber(int min) {
        System.out.print(portion + ": ");
        for (int i = 0; i < accessNumber / min; i++) {
            System.out.print('*');
        }
        System.out.println(" " + accessNumber);
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public static void finish() {
        isFinished = true;
    }
}
