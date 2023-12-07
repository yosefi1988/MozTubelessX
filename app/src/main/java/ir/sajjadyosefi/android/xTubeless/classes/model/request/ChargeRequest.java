package ir.sajjadyosefi.android.xTubeless.classes.model.request;

//
//import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
//import com.zarinpal.ewallets.purchase.PaymentRequest;
//import com.zarinpal.ewallets.purchase.ZarinPal;

import ir.sajjadyosefi.android.xTubeless.Global;

public class ChargeRequest {


    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getUi() {
        return Ui;
    }

    public void setUi(String ui) {
        Ui = ui;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getRefrenceNo() {
        return RefrenceNo;
    }

    public void setRefrenceNo(String refrenceNo) {
        RefrenceNo = refrenceNo;
    }

    private String Ip;
    private String Ui;
    private String Amount;
    private String RefrenceNo;
    private String Discription;

    public ChargeRequest(String ip, String amount, String refrenceNo, String Desc) {
        Ip = ip;
        Ui = Global.userFixxxxxxxxx.getUserName();
        Amount = amount;
        RefrenceNo = refrenceNo;
        Discription = Desc;
    }

}
