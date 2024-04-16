package ir.sajjadyosefi.android.xTubeless.classes.model.request;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;

public class NewBlogRequest {


    public NewBlogRequest() {
        this.Store = AccountGeneral.getStore();
    }

    public String getIDApplication() {
        return IDApplication;
    }

    public void setIDApplication(String IDApplication) {
        this.IDApplication = IDApplication;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTtc() {
        return ttc;
    }

    public void setTtc(String ttc) {
        this.ttc = ttc;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getReciveMessage() {
        return ReciveMessage;
    }

    public void setReciveMessage(String reciveMessage) {
        ReciveMessage = reciveMessage;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public List<Amounts> getItems() {
        return Items;
    }

    public void setItems(List<Amounts> items) {
        Items = items;
    }

    private String IDApplication;
    private String UserCode;
    private String Title;
    private String Text;
    private String ttc;
    private String StateCode;
    private String CityCode;
    private String Amount;
    private String ReciveMessage;
    private String PublishDate;
    private String ExpireDate;
    private boolean DirectPay;
    private String IP;
    private String Store;
    private List<Amounts> Items = new ArrayList<>();


    public void setDirectPay(boolean isdirect) {
        DirectPay = isdirect;
    }

    public boolean isDirectPay() {
        return DirectPay;
    }
}
