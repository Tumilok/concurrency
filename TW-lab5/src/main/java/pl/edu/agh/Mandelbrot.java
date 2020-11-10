package pl.edu.agh;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Mandelbrot extends JFrame {

    private final int MAX_ITER = 1570;
    private final double ZOOM = 200;
    private final BufferedImage I;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void drawImage(int yFrom, int yTo) {
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
}
