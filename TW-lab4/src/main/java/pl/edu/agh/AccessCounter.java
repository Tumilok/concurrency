package pl.edu.agh;

import pl.edu.agh.configuration.PropertyReader;
import pl.edu.agh.csv.CsvData;
import pl.edu.agh.csv.CsvFileWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccessCounter {
    private final Map<Integer, Integer> portionsAccessNumber = new HashMap<>();

    private final String workersName;

    public AccessCounter(String workersName) {
        this.workersName = workersName;
    }

    public void incrementPortionsAccessNumber(int portion) {
        int accessNumber = Optional.ofNullable(portionsAccessNumber.get(portion)).orElse(0);
        portionsAccessNumber.put(portion, accessNumber + 1);
    }

    public void writeResults() {
        CsvFileWriter fileWriter = CsvFileWriter.getInstance();

        int buffSize = PropertyReader.getInstance().getBuffSize();
        int PC_ratio = PropertyReader.getInstance().getPC_Ratio();
        boolean isFair = PropertyReader.getInstance().isFair();
        String randomization = PropertyReader.getInstance().getRandomization();

        portionsAccessNumber.keySet().stream()
                .map(key -> new CsvData(buffSize, workersName, key, PC_ratio, isFair, randomization, portionsAccessNumber.get(key)).toString())
                .forEach(fileWriter::writeDataToCSV);
    }
}
