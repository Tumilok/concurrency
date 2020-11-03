package pl.edu.agh.csv;

import pl.edu.agh.ConfigFileParser;

import java.io.*;
import java.nio.file.Files;

public class CsvFileWriter {
    private static final String CSV_FILEPATH = ConfigFileParser.OUTPUT_FILEPATH.getValue();
    private static final String CSV_FILENAME = ConfigFileParser.OUTPUT_FILENAME.getValue();

    private static final CsvFileWriter instance = new CsvFileWriter();

    public static CsvFileWriter getInstance() {
        return instance;
    }

    private final File csvOutputFile = new File(CSV_FILEPATH + CSV_FILENAME);

    private CsvFileWriter() {
        String value = ConfigFileParser.NEW_OUTPUT_FILE.getValue();
        if (Boolean.parseBoolean(value)) {
            createNewCsvFile();
        }
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
