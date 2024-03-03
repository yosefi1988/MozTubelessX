package ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post;

import java.io.Serializable;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.Global;

public class TimelineRequest implements Serializable {
    private String pageSize = null;
    private String pageIndex = null;
    private String IDApplication = AccountGeneral.getIDApplication() + "";

    private String UserCode = null;
    private Boolean IsActive;
    private Boolean Visited = null;
    private Boolean Faved = null;


    //filter
    private String Search = null;
    private int StateCode = 0;
    private String ttc = null;


    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public String getTtc() {
        return ttc;
    }

    public void setFaved(Boolean faved) {
        Faved = faved;
    }

    public void setVisited(Boolean visited) {
        Visited = visited;
    }

    public TimelineRequest(int current_page) {
        this.pageIndex = current_page + "";
    }

    public TimelineRequest(String Search, int StateCode) {
        this.Search = Search;
        this.StateCode = StateCode;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

}
