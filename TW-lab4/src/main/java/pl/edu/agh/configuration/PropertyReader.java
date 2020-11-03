package pl.edu.agh.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String FILE_PATH = "src/main/resources/config";
    private static final String FILE_EXTENSION  = ".properties";

    private static final PropertyReader instance = new PropertyReader();

    public static PropertyReader getInstance() {
        return instance;
    }

    private String configFilePath;

    private PropertyReader() {
        configFilePath = FILE_PATH + FILE_EXTENSION;
    }

    public void switchPath(int i) {
        configFilePath = FILE_PATH + i + FILE_EXTENSION;
        System.out.println(configFilePath);
    }

    private String getValue(Property arg) {
        String result = null;
        try (InputStream input = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(input);
            result = prop.getProperty(arg.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getBuffSize() {
        String result = getValue(Property.BUFF_SIZE);
        return Integer.parseInt(result);
    }

    public int getSimulationDuration() {
        String result = getValue(Property.SIMULATION_DURATION);
        return Integer.parseInt(result);
    }

    public int getPC_Ratio() {
        String result = getValue(Property.PC_RATIO);
        return Integer.parseInt(result);
    }

    public boolean isFair() {
        String result = getValue(Property.IS_FAIR);
        return Boolean.parseBoolean(result);
    }

    public boolean isNewOutputFile() {
        String result = getValue(Property.NEW_OUTPUT_FILE);
        return Boolean.parseBoolean(result);
    }

    public String getRandomization() {
        return getValue(Property.RANDOMIZATION);
    }

    public String getFileName() {
        return getValue(Property.OUTPUT_FILENAME);
    }

    public String getFilePath() {
        return getValue(Property.OUTPUT_FILEPATH);
    }
}
