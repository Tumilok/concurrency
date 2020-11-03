package pl.edu.agh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigFileParser {
    SIMULATION_DURATION ("simulation_duration"),
    BUFF_SIZE ("buff_size"),
    PC_RATIO ("pc_ratio"),
    IS_FAIR ("is_fair"),
    RANDOMIZATION ("randomization"),
    NEW_OUTPUT_FILE ("new_output_file"),
    OUTPUT_FILEPATH ("output_filepath"),
    OUTPUT_FILENAME ("output_filename");

    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    private final String key;

    ConfigFileParser(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(getKey());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
