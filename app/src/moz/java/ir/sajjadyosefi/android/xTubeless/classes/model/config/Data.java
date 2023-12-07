package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;

public class Data {
    public ConfigurationObject Config = new ConfigurationObject();
    public String Token = new String();
    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public ConfigurationObject getConfiguration() {
        return Config;
    }

    public void setConfiguration(ConfigurationObject configuration) {
        this.Config = configuration;
    }
}
