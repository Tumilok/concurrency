public class CycleNumberPrinter {
    public static final int THREAD_NUM = 3;
    private static final int ITER_NUM = 10;

    private int numberToPrint = 1;

    private void printNumber(int numberToPrint) {
        synchronized (this) {
            for (int i = 0; i < ITER_NUM; i++) {
                while (this.numberToPrint != numberToPrint) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(numberToPrint);
                this.numberToPrint = numberToPrint % THREAD_NUM + 1;
                notifyAll();
            }
        }
    }


    public static void main(String[] args) {
        CycleNumberPrinter cycleNumberPrinter = new CycleNumberPrinter();
        Thread[] threads = new Thread[THREAD_NUM];

        for (int i = 0; i < THREAD_NUM; i++) {
            int numberToPrint = i + 1;
            threads[i] = new Thread(() -> cycleNumberPrinter.printNumber(numberToPrint));
            threads[i].start();
        }
    }
}
