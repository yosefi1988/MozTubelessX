package ir.sajjadyosefi.android.xTubeless.activity.account.login.view;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser_ThisFunctionMoveedToAaaLibrary;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.viewActivity;

public interface ILoginView_ThisFunctionMoveedToAaaLibrary extends viewActivity {
    void onLoginSuccessFinish(IUser_ThisFunctionMoveedToAaaLibrary user);

    void showProgressBar();
    void hideProgressBar();

    void OnPressGoToForgetPassword();
}
