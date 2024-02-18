package ir.sajjadyosefi.android.xTubeless.activity.account.login.model;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;

import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

public interface IUser {

    void CheckUserValidity(ILoginPresenterI presenter , LoginRequest request);
    IUser loadUserDatax(ISplashScreenPeresenter presenter , LoginRequest request);
}
