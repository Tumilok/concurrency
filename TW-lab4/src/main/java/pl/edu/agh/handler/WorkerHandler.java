package main.java.agh.handler;

import main.java.agh.Config;
import main.java.agh.Storage;
import main.java.agh.csv.CsvData;
import main.java.agh.csv.CsvFileWriter;
import main.java.agh.thread.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class WorkerHandler {
    protected final PortionHandler portionHandler = new PortionHandler();
    protected final List<Worker> workers = new ArrayList<>();
    protected final Storage storage;

    public WorkerHandler(Storage storage) {
        this.storage = storage;
    }

    public abstract void createAndRunWorkers();

    public void writeResults() {
        CsvFileWriter fileWriter = CsvFileWriter.getInstance();
        for (Worker worker : workers) {
            try {
                fileWriter.writeDataToCSV(new CsvData(
                        Config.BUFF_SIZE,
                        worker.getWorkerName(),
                        worker.getPortion(),
                        Config.PC_RATIO,
                        Config.IS_FAIR,
                        Config.RANDOMIZATION,
                        worker.getAccessNumber()
                ).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
