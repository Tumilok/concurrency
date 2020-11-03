package pl.edu.agh.buffer;

public interface Storage {
    void put(int portion);
    void take(int portion);
}
