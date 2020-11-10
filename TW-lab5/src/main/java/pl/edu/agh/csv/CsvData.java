package pl.edu.agh.csv;

public class CsvData {

    public static final String TITLES = "THREAD_NUM,TIME";

    private final int threadNum;
    private final long time;

    public CsvData(int threadNum, long time) {
        this.threadNum = threadNum;
        this.time = time;
    }

    @Override
    public String toString() {
        return threadNum + "," + time;
    }
}
