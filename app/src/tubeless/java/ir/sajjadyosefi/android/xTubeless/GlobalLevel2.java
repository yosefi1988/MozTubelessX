package ir.sajjadyosefi.android.xTubeless;


import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.IDApplication;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.IDApplicationVersion;

public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();


        //library
        AccountGeneral.setIDApplication(IDApplication);
        AccountGeneral.setIDApplicationVersion(IDApplicationVersion);
//        CommonClass.GetAppVersion(context)    //todo fix
        //zarinpal
        AccountGeneral.setAppName(getString(R.string.app_name));
        AccountGeneral.setZarinpalpayment(this.getString(R.string.zarinpalpayment));
        AccountGeneral.setSchemezarinpalpayment(this.getString(R.string.schemezarinpalpayment));

    }

}