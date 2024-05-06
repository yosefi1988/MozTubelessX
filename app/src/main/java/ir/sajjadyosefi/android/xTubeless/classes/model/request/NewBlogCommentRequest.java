package ir.sajjadyosefi.android.xTubeless.classes.model.request;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

public class NewBlogCommentRequest {

    private int ApplicationId = AccountGeneral.getIDApplication();
    private int PostId;
    private int UserID;


    private String Message;
    private String Ui;
    private String CreatorUi;
    private String IP;

    public String getUi() {
        return Ui;
    }

    public void setUi(String ui) {
        Ui = ui;
    }

    public String getCreatorUi() {
        return CreatorUi;
    }

    public void setCreatorUi(String creatorUi) {
        CreatorUi = creatorUi;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getApplicationId() {
        return ApplicationId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }


    public int getPostId() {
        return PostId;
    }

    public void setPostId(int postId) {
        PostId = postId;
    }

}
