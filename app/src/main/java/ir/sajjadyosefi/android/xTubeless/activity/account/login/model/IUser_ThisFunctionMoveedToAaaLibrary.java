package ir.sajjadyosefi.android.xTubeless.activity.account.login.model;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI_ThisFunctionMoveedToAaaLibrary;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;

import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;

public interface IUser_ThisFunctionMoveedToAaaLibrary {

    void CheckUserValidity(Context context , ILoginPresenterI_ThisFunctionMoveedToAaaLibrary presenter , LoginRequest request);
    IUser_ThisFunctionMoveedToAaaLibrary loadUserDatax(ISplashScreenPeresenter presenter , LoginRequest request);
}
