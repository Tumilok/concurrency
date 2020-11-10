package pl.edu.agh;

import pl.edu.agh.csv.CsvData;
import pl.edu.agh.csv.CsvFileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final Mandelbrot mandelbrot;
    private final CsvFileWriter writer = CsvFileWriter.getInstance();
    private final List<Integer> threadNumList = new ArrayList<>();

    public Main(Mandelbrot mandelbrot) {
        this.mandelbrot = mandelbrot;
        threadNumList.add(1);
        threadNumList.add(3);
        for (int i = 5; i <= 100; i+=5) {
            threadNumList.add(i);
        }
    }

    public void run() {
        for (int numberOfThreads : threadNumList) {
            int numberOfTasks = numberOfThreads * 10;

            long startTimeMillis = System.currentTimeMillis();

            for (int j = 0; j < 500; j++) {
                ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
                List<Runnable> tasks = new ArrayList<>();
                for (int i = 0; i < numberOfTasks; i++) {
                    int yTo = mandelbrot.getHeight() / numberOfTasks * (i + 1);
                    int yFrom = yTo - (mandelbrot.getHeight() / numberOfTasks);
                    tasks.add(() -> mandelbrot.drawImage(yFrom, yTo));
                }

                CompletableFuture.allOf(tasks.stream()
                        .map(task -> CompletableFuture.runAsync(task, executor))
                        .toArray(CompletableFuture[]::new)).join();

                executor.shutdown();
            }

            long endTimeMillis = System.currentTimeMillis();

            writer.writeDataToCSV(new CsvData(numberOfThreads, (endTimeMillis - startTimeMillis)).toString());
//            mandelbrot.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Main(new Mandelbrot()).run();
    }
}
