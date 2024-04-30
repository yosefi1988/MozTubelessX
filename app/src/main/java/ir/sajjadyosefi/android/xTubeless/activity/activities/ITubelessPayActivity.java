package ir.sajjadyosefi.android.xTubeless.activity.activities;

import retrofit2.Call;

public interface ITubelessPayActivity {
    void payOk(boolean isDirectPaymentInRegPostRequest);
    void payNotOk();
}
