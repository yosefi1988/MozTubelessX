package ir.sajjadyosefi.android.xTubeless.classes.model.network.request;

import ir.sajjadyosefi.android.xTubeless.Global;

public class TransactionRequest {

    private String Title;
    private String Text;
    private String StateCode;
    private String CityCode;

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    private String PageIndex;
    private String PageSize;
    private String TypeCode;
    private String Ui;
    private String Id;
    private String ip;


}
