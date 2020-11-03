package pl.edu.agh;

import pl.edu.agh.csv.CsvFileWriter;
import pl.edu.agh.csv.CsvData;
import pl.edu.agh.handler.ConsumerHandler;
import pl.edu.agh.handler.ProducerHandler;
import pl.edu.agh.handler.WorkerHandler;
import pl.edu.agh.thread.Worker;

import java.io.IOException;

public class Main {

    private void run() throws InterruptedException {
        Storage storage = new Storage();

        WorkerHandler consumerHandler = new ConsumerHandler(storage);
        WorkerHandler producerHandler = new ProducerHandler(storage);

        consumerHandler.createAndRunWorkers();
        producerHandler.createAndRunWorkers();

        Thread.sleep(10000);

        Worker.finish();

        consumerHandler.printHistogram();
        producerHandler.printHistogram();

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
//        new Main().run();
        new Main().testCsvWriter();
    }
}
