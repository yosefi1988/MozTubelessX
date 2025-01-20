package ir.sajjadyosefi.android.xTubeless;


import android.accounts.Account;
import android.content.SharedPreferences;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.gson.Gson;
//import com.ironsource.mediationsdk.IronSource;
//import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
//import com.ironsource.mediationsdk.impressionData.ImpressionData;
//import com.ironsource.mediationsdk.impressionData.ImpressionDataListener;
//import com.ironsource.mediationsdk.integration.IntegrationHelper;
//import com.ironsource.mediationsdk.logger.IronSourceError;
//import com.ironsource.mediationsdk.sdk.InitializationListener;
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener;
//import com.ironsource.mediationsdk.sdk.LevelPlayRewardedVideoListener;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;

//import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();



        //library
        AccountGeneral.setIDApplication(2); //todo read from system
        AccountGeneral.setIDApplicationVersion(1);  //todo read from system
//        CommonClass.GetAppVersion(context)    //todo fix
        //zarinpal
        AccountGeneral.setAppName(getString(R.string.app_name));
        AccountGeneral.setZarinpalpayment(this.getString(R.string.zarinpalpayment));
        AccountGeneral.setSchemezarinpalpayment(this.getString(R.string.schemezarinpalpayment));


    }

}