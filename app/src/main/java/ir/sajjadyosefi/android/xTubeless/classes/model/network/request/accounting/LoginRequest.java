package ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

public class LoginRequest {

    private int IDApplicationVersion  = StaticValue.IDApplicationVersion;
    private String Mail;
    private String PhoneNumber;
    private String UserName;
    private String Simcard;
    private String Password;
    private String IP;
    private String DeviceId;
    private String AvatarUrl;

//    private String UserName;
//    private String UserMoarefID;
//    private String ProfileImage;


    public int getIDApplicationVersion() {
        return IDApplicationVersion;
    }

    public void setIDApplicationVersion(int IDApplicationVersion) {
        this.IDApplicationVersion = IDApplicationVersion;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAndroidID() {
        return DeviceId;
    }

    public void setAndroidID(String androidID) {
        DeviceId = androidID;
    }

    public String getSimcard() {
        return Simcard;
    }

    public void setSimcard(String simcard) {
        Simcard = simcard;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getEmail() {
        return Mail;
    }

    public void setEmail(String email) {
        Mail = email;
    }

    public String getMobileNumber() {
        return PhoneNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        PhoneNumber = mobileNumber;
    }

    public String getUserImage() {
        return AvatarUrl;
    }

    public void setUserImage(String userImage) {
        AvatarUrl = userImage;
    }

    //manual
    public LoginRequest(Context context , String userName, String password, String androidID) {
        PhoneNumber = userName;
        UserName = userName;
        Password = password;
        DeviceId = androidID;
        IP = DeviceUtil.GetIP(context);
        Mail = "";
        AvatarUrl = "";
    }


    //google
    public LoginRequest(Context context,String email, String userImage) {
        Mail = email;
        AvatarUrl = userImage;

        IP = DeviceUtil.GetIP(context);
        Password = "";
        DeviceId = DeviceUtil.GetAndroidId(context);
        PhoneNumber = "";
    }

    //sim
    public LoginRequest(Context context, String mobileNumber) {
        Simcard = mobileNumber;

        IP =
        Mail = "";
        Password = "";
        DeviceId = DeviceUtil.GetAndroidId(context);
        IP = DeviceUtil.GetIP(context);
        PhoneNumber = "";
        AvatarUrl = "";
    }


}
