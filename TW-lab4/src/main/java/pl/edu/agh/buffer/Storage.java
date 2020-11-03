package pl.edu.agh.buffer;

import pl.edu.agh.configuration.PropertyReader;

public interface Storage {
    void put(int portion);
    void take(int portion);

    static Storage getStorageInstance() {
        return PropertyReader.getInstance().isFair() ? new FairStorage() : new UnfairStorage();
    }
}
