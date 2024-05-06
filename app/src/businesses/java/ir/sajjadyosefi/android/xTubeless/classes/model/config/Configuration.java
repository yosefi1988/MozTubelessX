package ir.sajjadyosefi.android.xTubeless.classes.model.config;


import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;

public class Configuration extends ServerResponseStatus {

//    ConfigurationObject configurationB = new ConfigurationObject();
    ConfigurationObject confSokhtInYafte = new ConfigurationObject();

    public ConfigurationObject getConfiguration() {
        return confSokhtInYafte;
    }

    public void setConfiguration(ConfigurationObject configuration) {
        this.confSokhtInYafte = configuration;
    }

}
