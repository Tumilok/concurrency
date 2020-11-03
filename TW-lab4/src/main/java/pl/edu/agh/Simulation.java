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

    private void run() throws InterruptedException {
        Storage storage = new Storage();

        String randomization = ConfigFileParser.RANDOMIZATION.getValue();
        PortionHandler portionHandler = null;
        assert randomization != null;
        if (randomization.equals("fair")) {
            portionHandler = new FairPortionHandler();
        } else {
            portionHandler = new UnfairPortionHandler();
        }

        WorkerHandler consumerHandler = new ConsumerHandler(storage, portionHandler);
        WorkerHandler producerHandler = new ProducerHandler(storage, portionHandler);

        consumerHandler.createAndRunWorkers();
        producerHandler.createAndRunWorkers();

        int time = Integer.parseInt(Objects.requireNonNull(ConfigFileParser.SIMULATION_DURATION.getValue()));
        Thread.sleep(time);

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

    public static void main(String[] args) throws InterruptedException {
        new Simulation().run();
//        new Main().testCsvWriter();
    }
}
