package ir.sajjadyosefi.android.xTubeless.activity.account.login.view;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.viewActivity;

public interface ILoginView extends viewActivity {
    void onLoginSuccessFinish(IUser user);

    void showProgressBar();
    void hideProgressBar();

    void OnPressGoToForgetPassword();
}
