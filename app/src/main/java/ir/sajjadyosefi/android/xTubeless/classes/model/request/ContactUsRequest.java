package ir.sajjadyosefi.android.xTubeless.classes.model.request;


import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

public class ContactUsRequest {

    private int ApplicationId = AccountGeneral.getIDApplication();
    private String MetaData;
    private String text;                            //ok
    private String title;                           //ok
    private String TypeCode;
    private String PhoneNumber;                     //ok
    private String SenderUserID;
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public String getMetaData() {
        return MetaData;
    }

    public void setMetaData(String metaData) {
        MetaData = metaData;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getSenderUserID() {
        return SenderUserID;
    }

    public void setSenderUserID(String senderUserID) {
        SenderUserID = senderUserID;
    }


}
