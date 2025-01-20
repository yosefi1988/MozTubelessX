package ir.sajjadyosefi.android.xTubeless.activity.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.profile.MainActivityProfile;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class TubelessPayActivity extends TubelessActivity{

    protected Button buttonCharge;
    protected Intent intentPayment;
    protected ActivityResultLauncher<Intent> mGetNameActivity;
    protected ITubelessPayActivity iTubelessPayActivity;
    protected boolean isDirectPaymentInRegPostRequest = false;
    private ActivityResultCallback<ActivityResult> xxxxxxxxxxxx2 = new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                Intent x;
                if (PaymentActivity.isPaymentSuccess()) {
                    x = PaymentActivity.getPaymentIntent();
                    //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();
                    x.getIntExtra("amount",10);
                    x.getStringExtra("ReturnData");
                    x.getStringExtra("metaData");
                    x.getStringExtra("item1");
                    x.getIntExtra("type",10);

                    Gson gson = new Gson();
                    MainActivityProfile.ReturnData returnData = new MainActivityProfile.ReturnData();
                    try {
                        returnData = gson.fromJson(x.getStringExtra("ReturnData"), MainActivityProfile.ReturnData.class);
                        Global.user2.getWallet().setAmount(returnData.wallet.getAmount());

                        Wallet tmp = Global.user2.getWallet();
                        tmp.setUserCode(Global.user2.getUserCode());
                        Wallet.savedToDataBase(tmp);
                    }catch (Exception ex){ }

                    if (iTubelessPayActivity != null)
                        iTubelessPayActivity.payOk(isDirectPaymentInRegPostRequest);
                }else {
                    if (iTubelessPayActivity != null)
                        iTubelessPayActivity.payNotOk();
                }
                PaymentActivity.PaymentDone();
            }
        }
    };

    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }


    protected void initInterface(ITubelessPayActivity _iTubelessPayActivity,boolean _isDirectPaymentInRegPostRequest){
        iTubelessPayActivity = _iTubelessPayActivity;
        isDirectPaymentInRegPostRequest = _isDirectPaymentInRegPostRequest;
    }

    @Override
    protected void onStart() {
        super.onStart();

        buttonCharge = findViewById(R.id.buttonCharge);

        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        bundle.putInt("amount", 800);   //with type = 2
        bundle.putString("metaData", "meta Data 10 + 30");
        bundle.putString("tax", "10");
        bundle.putBoolean("isCharge", true);  //use in charge wallet

        bundle.putString("portService", "30");
        intentPayment = PaymentActivity.getIntent(getContext(), bundle);
        bundle.putParcelable(AccountManager.KEY_INTENT, intentPayment);
        mGetNameActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), xxxxxxxxxxxx2);

    }
}
