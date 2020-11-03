package main.java.agh.csv;

import main.java.agh.Config;

import java.io.*;
import java.nio.file.Files;

public class CsvFileWriter {
    private static final String CSV_FILE_NAME = "result.csv";

    private static final CsvFileWriter instance = new CsvFileWriter();

    public static CsvFileWriter getInstance() {
        return instance;
    }

    private final File csvOutputFile = new File(CSV_FILE_NAME);

    private CsvFileWriter() {
        if (Config.CREATE_NEW_OUTPUT_FILE) {
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

    public void writeDataToCSV(String line) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvOutputFile, true))) {
            bw.append(line);
            bw.newLine();
        }
    }
}
