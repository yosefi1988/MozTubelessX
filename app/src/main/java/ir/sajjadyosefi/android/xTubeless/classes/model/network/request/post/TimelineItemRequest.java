package ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.Global;

public class TimelineItemRequest {

    public TimelineItemRequest() {
        this.IDApplication = AccountGeneral.getIDApplication() + "";
        this.IP = AccountGeneral.getIP();
        this.Store = AccountGeneral.getStore();
        if ((Global.user2 != null))
            this.UserCode = Global.user2.getUserCodeAsString();
        else
            this.UserCode = "";
    }
    public void setIDPost(String IDPost) {
        this.IDPost = IDPost;
    }

    public void setUserCode(String userCode) {
        this.UserCode = userCode;
    }

    public void setIDApplication(String IDApplication) {
        this.IDApplication = IDApplication;
    }

    private String IDApplication;
    private String UserCode;
    private String IDPost;

    public void setID(String ID) {
        this.ID = ID;
    }

    private String ID;
    private String Store;
    private String IP;

}
