package pl.edu.agh;

import pl.edu.agh.csv.CsvData;
import pl.edu.agh.csv.CsvFileWriter;
import pl.edu.agh.handler.portion.FairPortionHandler;
import pl.edu.agh.handler.portion.PortionHandler;
import pl.edu.agh.handler.portion.UnfairPortionHandler;
import pl.edu.agh.handler.thread.ConsumerHandler;
import pl.edu.agh.handler.thread.ProducerHandler;
import pl.edu.agh.handler.thread.WorkerHandler;
import pl.edu.agh.thread.Worker;

import java.io.IOException;
import java.util.Objects;

public class Simulation {

    private void run() throws InterruptedException, IOException {
        Storage storage = new Storage();

        String randomization = ConfigFileParser.RANDOMIZATION.getValue();
        PortionHandler portionHandler;
        assert randomization != null;
        if (randomization.equals("fair")) {
            portionHandler = new FairPortionHandler();
        } else {
            portionHandler = new UnfairPortionHandler();
        }

        String pc_ratio = ConfigFileParser.PC_RATIO.getValue();
        int workerNumber;
        assert pc_ratio != null;
        if (pc_ratio.equals("100P+100C")) {
            workerNumber = 100;
        } else if(pc_ratio.equals("1000P+1000C")) {
            workerNumber = 1000;
        } else {
            throw new IOException();
        }

        WorkerHandler consumerHandler = new ConsumerHandler(storage, portionHandler, workerNumber);
        WorkerHandler producerHandler = new ProducerHandler(storage, portionHandler, workerNumber);

        consumerHandler.createAndRunWorkers();
        producerHandler.createAndRunWorkers();

        int timeSec = Integer.parseInt(Objects.requireNonNull(ConfigFileParser.SIMULATION_DURATION.getValue()));
        Thread.sleep(timeSec * 1000);

        Worker.finish();

        consumerHandler.writeResults();
        producerHandler.writeResults();

        System.exit(0);
    }

    private void testCsvWriter() {
        CsvData csvData = new CsvData(16, "Cons", 435, "100+100", false, "some randomization", 343);
        CsvFileWriter writer = CsvFileWriter.getInstance();
        try {
            for (int i = 0; i < 10; i++) {
                writer.writeDataToCSV(csvData.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Simulation().run();
//        new Main().testCsvWriter();
    }
}
