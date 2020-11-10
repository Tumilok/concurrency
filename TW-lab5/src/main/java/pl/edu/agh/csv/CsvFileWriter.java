package pl.edu.agh.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class CsvFileWriter {
    private static final String CSV_FILEPATH = "src/main/resources/result.csv";

    private static final CsvFileWriter instance = new CsvFileWriter();

    public static CsvFileWriter getInstance() {
        return instance;
    }

    private final File csvOutputFile = new File(CSV_FILEPATH);

    private CsvFileWriter() {
        createNewCsvFile();
    }

    private void createNewCsvFile() {
        try {
            Files.deleteIfExists(csvOutputFile.toPath());
            writeDataToCSV(CsvData.TITLES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToCSV(String line) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvOutputFile, true))) {
            bw.append(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
