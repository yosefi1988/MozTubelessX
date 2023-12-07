package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;
public class Configuration extends ServerResponseStatus {

    Data Data = new Data();

    public Data getData() {
        return Data;
    }

    public void setData(Data data) {
        this.Data = data;
    }

}
