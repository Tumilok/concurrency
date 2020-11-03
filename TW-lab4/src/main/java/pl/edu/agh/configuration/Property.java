package pl.edu.agh.configuration;

public enum Property {
    SIMULATION_DURATION ("simulation_duration"),
    BUFF_SIZE ("buff_size"),
    PC_RATIO ("pc_ratio"),
    IS_FAIR ("is_fair"),
    RANDOMIZATION ("randomization"),
    NEW_OUTPUT_FILE ("new_output_file"),
    OUTPUT_FILEPATH ("output_filepath"),
    OUTPUT_FILENAME ("output_filename");

    private final String key;

    Property(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
