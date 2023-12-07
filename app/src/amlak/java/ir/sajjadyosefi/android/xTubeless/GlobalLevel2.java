package ir.sajjadyosefi.android.xTubeless;


import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();

        //library
        AccountGeneral.setIDApplication(1008); //todo read from system
        AccountGeneral.setIDApplicationVersion(1003);  //todo read from system
//        CommonClass.GetAppVersion(context)    //todo fix
        //zarinpal
        AccountGeneral.setAppName(getString(R.string.app_name));
        AccountGeneral.setZarinpalpayment(this.getString(R.string.zarinpalpayment));
        AccountGeneral.setSchemezarinpalpayment(this.getString(R.string.schemezarinpalpayment));


    }

}