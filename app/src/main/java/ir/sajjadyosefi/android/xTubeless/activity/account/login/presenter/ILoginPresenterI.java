package ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;


public interface ILoginPresenterI {
    void tryToLoginByPhoneNumber(Context context,String phoneNumber, String password);
    void tryToLoginByMail(Context context,String email, String password);
    void tryToLoginBySimCard(Context context,String simId);

    String getAndroidId(Context context);

    void onSuccess(IUser user);

    void onThrowException(Throwable t);

    void goToForgetPassword();


}
