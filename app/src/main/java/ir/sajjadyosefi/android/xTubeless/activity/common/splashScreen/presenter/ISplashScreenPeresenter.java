package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter;


import android.content.Context;

public interface ISplashScreenPeresenter {

    void registerDevice();
    boolean isFirstRun();

    void onSuccessDeviceRegister();
    void onSuccessUserDataLoad();
    void onFailUserDataLoad();

    void onThrowException(Throwable t);

    void goToMainPage();

}
