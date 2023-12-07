package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.ServerResponseStatus;

public class Configuration extends ServerResponseStatus {

//    ConfigurationObject configurationB = new ConfigurationObject();
    ConfigurationObject configurationB = new ConfigurationObject();

    public ConfigurationObject getConfiguration() {
        return configurationB;
    }

    public void setConfiguration(ConfigurationObject configuration) {
        this.configurationB = configuration;
    }

}
