package pl.edu.agh;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 150;
    private final BufferedImage I;

    int numberOfThreads = 1;
    int numberOfTasks = numberOfThreads * 10;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void run() {
        long startTimeMillis = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Runnable> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            int yTo = getHeight() / numberOfTasks * (i + 1);
            int yFrom = yTo -  (getHeight() / numberOfTasks);
            tasks.add(() -> drawImage(yFrom, yTo));
        }

        CompletableFuture<?>[] futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, executor))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        executor.shutdown();

        long endTimeMillis = System.currentTimeMillis();

        System.out.println("Number of threads: " + numberOfThreads +
                ", number of tasks: " + numberOfTasks +
                ", time: " + (endTimeMillis - startTimeMillis) + " milliseconds");

        setVisible(true);
    }

    private void drawImage(int yFrom, int yTo) {
        for (int y = yFrom; y < yTo; y++) {
            for (int x = 0; x < getWidth(); x++) {
                double zx, zy, cX, cY, tmp;
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                synchronized (this) {
                    I.setRGB(x, y, iter | (iter << 8));
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) {
        Mandelbrot mandelbrot = new Mandelbrot();
        mandelbrot.run();
    }
}
