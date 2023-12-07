package ir.sajjadyosefi.android.xTubeless.classes.model.request;


import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.Device;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.NOT_LOGN_USER;
//import ir.sajjadyosefi.android.xTubeless.classes.model.Device;

public class DeviceRequest {

    private String DeviceId;

    private int IDUser = 0;
    private int ApplicationVersionId = StaticValue.IDApplicationVersion;
    private String SERIAL;
    private String MODEL;
    private String BuildID;
    private String AndroidVersion;
    private String AndroidAPI;
    private String MANUFACTURER;
    private String BRAND;
    private String BOARD;
    private String DISPLAY;
    private String IP;



    public DeviceRequest( String androidID, String SERIAL, String MODEL, String buildID, String androidVersion, int androidAPI, String MANUFACTURER, String BRAND, String BOARD, String DISPLAY, String Ip) {
        DeviceId = androidID;
        this.SERIAL = SERIAL;
        this.MODEL = MODEL;
        BuildID = buildID;
        AndroidVersion = androidVersion;
        AndroidAPI = androidAPI + "";
        this.MANUFACTURER = MANUFACTURER;
        this.BRAND = BRAND;
        this.BOARD = BOARD;
        this.DISPLAY = DISPLAY;
        this.IP = Ip;
    }

//    public DeviceRequest(IRegisterDeviceModel device) {
//        if (Global.user == null)
//            IDUser = NOT_LOGN_USER;
//        else
//            IDUser = (int) Global.user.getUserId();
//
//        DeviceId = ((Device)device).getAndroidID();
//        this.SERIAL = ((Device)device).getSERIAL();
//        this.IP = ((Device)device).getIP();
//        this.MODEL = ((Device)device).getMODEL();
//        BuildID = ((Device)device).getBuildID();
//        AndroidVersion = ((Device)device).getAndroidVersion();
//        AndroidAPI = ((Device) device).getAndroidAPI() + "";
//        this.MANUFACTURER = ((Device)device).getMANUFACTURER();
//        this.BRAND = ((Device)device).getBRAND();
//        this.BOARD = ((Device)device).getBOARD();
//        this.DISPLAY = ((Device)device).getDISPLAY();
//    }


    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public int getIDApplicationVersion() {
        return ApplicationVersionId;
    }

    public void setIDApplicationVersion(int IDApplicationVersion) {
        this.ApplicationVersionId = IDApplicationVersion;
    }

    public String getAndroidID() {
        return DeviceId;
    }

    public void setAndroidID(String androidID) {
        DeviceId = androidID;
    }

    public String getSERIAL() {
        return SERIAL;
    }

    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getBuildID() {
        return BuildID;
    }

    public void setBuildID(String buildID) {
        BuildID = buildID;
    }

    public String getAndroidVersion() {
        return AndroidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        AndroidVersion = androidVersion;
    }

    public int getAndroidAPI() {
        return Integer.parseInt(AndroidAPI);
    }

    public void setAndroidAPI(int androidAPI) {
        AndroidAPI = androidAPI + "";
    }

    public String getMANUFACTURER() {
        return MANUFACTURER;
    }

    public void setMANUFACTURER(String MANUFACTURER) {
        this.MANUFACTURER = MANUFACTURER;
    }

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getBOARD() {
        return BOARD;
    }

    public void setBOARD(String BOARD) {
        this.BOARD = BOARD;
    }

    public String getDISPLAY() {
        return DISPLAY;
    }

    public void setDISPLAY(String DISPLAY) {
        this.DISPLAY = DISPLAY;
    }

}
