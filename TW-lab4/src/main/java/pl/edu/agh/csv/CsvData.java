package main.java.agh.csv;

public class CsvData {

    public static final String TITLES = "Buff_size,Prod_or_cons,Size,PC_config,Is_fair,Randomization,Buff_access_num";

    private final int bufferSize;
    private final String workerName;
    private final int portionSize;
    private final String PC_Ratio;
    private final boolean isFair;
    private final String randomization;
    private final int bufferAccessNumber;

    public CsvData(int bufferSize, String workerName, int portionSize, String PC_Ratio, boolean isFair, String randomization, int bufferAccessNumber) {
        this.bufferSize = bufferSize;
        this.workerName = workerName;
        this.portionSize = portionSize;
        this.PC_Ratio = PC_Ratio;
        this.isFair = isFair;
        this.randomization = randomization;
        this.bufferAccessNumber = bufferAccessNumber;
    }

    @Override
    public String toString() {
        return bufferSize + ","
                + workerName + ","
                + portionSize + ","
                + PC_Ratio + ","
                + isFair + ","
                + randomization + ","
                + bufferAccessNumber;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public String getWorkerName() {
        return workerName;
    }

    public int getPortionSize() {
        return portionSize;
    }

    public String getPC_Ratio() {
        return PC_Ratio;
    }

    public boolean isFair() {
        return isFair;
    }

    public String getRandomization() {
        return randomization;
    }

    public int getBufferAccessNumber() {
        return bufferAccessNumber;
    }
}
