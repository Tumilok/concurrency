package pl.edu.agh.handler.thread;

import pl.edu.agh.ConfigFileParser;
import pl.edu.agh.Storage;
import pl.edu.agh.csv.CsvData;
import pl.edu.agh.csv.CsvFileWriter;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.thread.Worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class WorkerHandler {
    protected final PortionHandler portionHandler;
    protected final List<Worker> workers = new ArrayList<>();
    protected final Storage storage;
    protected final int workerNumber;

    public WorkerHandler(Storage storage, PortionHandler portionHandler, int workerNumber) {
        this.storage = storage;
        this.portionHandler = portionHandler;
        this.workerNumber = workerNumber;
    }

    public abstract void createAndRunWorkers();

    public void writeResults() {
        CsvFileWriter fileWriter = CsvFileWriter.getInstance();
        for (Worker worker : workers) {
            try {
                fileWriter.writeDataToCSV(new CsvData(
                        Integer.parseInt(Objects.requireNonNull(ConfigFileParser.BUFF_SIZE.getValue())),
                        worker.getWorkerName(),
                        worker.getPortion(),
                        ConfigFileParser.PC_RATIO.getValue(),
                        Boolean.parseBoolean(ConfigFileParser.IS_FAIR.getValue()),
                        ConfigFileParser.RANDOMIZATION.getValue(),
                        worker.getAccessNumber()
                ).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
