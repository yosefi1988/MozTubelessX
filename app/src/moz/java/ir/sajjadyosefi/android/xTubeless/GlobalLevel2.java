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

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
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



        IronSource.setConsent(true);
        IronSource.setMetaData("do_not_sell","false");
        IronSource.setMetaData("is_deviceid_optout","true");
        IronSource.setMetaData("is_child_directed","true");
        IronSource.setMetaData("Google_Family_Self_Certified_SDKS","true");

        IronSource.setLevelPlayRewardedVideoListener(new LevelPlayRewardedVideoListener() {
            @Override
            public void onAdAvailable(AdInfo adInfo) {

            }

            @Override
            public void onAdUnavailable() {

            }

            @Override
            public void onAdOpened(AdInfo adInfo) {

            }

            @Override
            public void onAdShowFailed(IronSourceError ironSourceError, AdInfo adInfo) {

            }

            @Override
            public void onAdClicked(com.ironsource.mediationsdk.model.Placement placement, AdInfo adInfo) {

            }

            @Override
            public void onAdRewarded(com.ironsource.mediationsdk.model.Placement placement, AdInfo adInfo) {

            }

            @Override
            public void onAdClosed(AdInfo adInfo) {

            }
        });
        IronSource.setLevelPlayInterstitialListener(new LevelPlayInterstitialListener() {
            @Override
            public void onAdReady(AdInfo adInfo) {

            }

            @Override
            public void onAdLoadFailed(IronSourceError ironSourceError) {

            }

            @Override
            public void onAdOpened(AdInfo adInfo) {

            }

            @Override
            public void onAdShowSucceeded(AdInfo adInfo) {

            }

            @Override
            public void onAdShowFailed(IronSourceError ironSourceError, AdInfo adInfo) {

            }

            @Override
            public void onAdClicked(AdInfo adInfo) {

            }

            @Override
            public void onAdClosed(AdInfo adInfo) {

            }
        });
        IronSource.addImpressionDataListener(new ImpressionDataListener() {
            @Override
            public void onImpressionSuccess(ImpressionData impressionData) {

            }
        });
//        IronSource.init(this, "1e5fb1a7d", IronSource.AD_UNIT.INTERSTITIAL, IronSource.AD_UNIT.REWARDED_VIDEO, IronSource.AD_UNIT.BANNER);
        IronSource.init(this, "1e5fb1a7d", new InitializationListener() {
            @Override
            public void onInitializationComplete() {
                // ironSource SDK is initialized
                int a = 5;
            }
        });

        IntegrationHelper.validateIntegration(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                    String advertisingId = adInfo.getId();
                    //"195fccc0-07a9-41e6-aef1-ecd8231504b5"
                    advertisingId = adInfo.getId();
                    // از advertisingId برای اهداف تبلیغاتی استفاده کنید
                } catch (Exception e) {
                    // رسیدگی به خطا
                }
            }
        }).start();


    }

}