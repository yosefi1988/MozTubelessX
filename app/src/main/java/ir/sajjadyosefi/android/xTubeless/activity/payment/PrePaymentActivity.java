package ir.sajjadyosefi.android.xTubeless.activity.payment;

import android.accounts.AccountManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

//import ir.moslem_deris.apps.zarinpal.ZarinPal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity;
import ir.sajjadyosefi.accountauthenticator.model.ATransaction;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ChargeRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;


public class PrePaymentActivity extends TubelessTransparentStatusBarActivity {


    public static final int GO_TO_LOGIN = 20;
    public Button btnPay ,buttonBack ;
    EditText editTextPhone, editTextAmount,editTextDiscription;
    public static String phone ,amount,discription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_prepayment);
        setRootActivity();
        btnPay = findViewById(R.id.btnPay);
        buttonBack = findViewById(R.id.buttonBack);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextDiscription = findViewById(R.id.editTextDiscription);

        buttonBack.setOnClickListener(v->{
            this.finish();
        });

        paymentinit(10000,"discription","09123678522");

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = editTextPhone.getText().toString();
                amount = editTextAmount.getText().toString();
                discription = editTextDiscription.getText().toString();
                payment();
            }
        });

////        if (Global.IDUser == NOT_LOGN_USER ){
//        if (Global.user == null || Global.user.getUserId() == NOT_LOGN_USER ){
//            Bundle bundle = new Bundle();
//            Intent intent = SignInActivity.getIntent(getContext(),bundle);
////            intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
////            intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);  //todo fix
//            intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//            //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
//        }

        Uri data2 = getIntent().getData();

        //defaultValue
        int defaultValue = getIntent().getIntExtra("defaultValue",0);
        if (getIntent().hasExtra("defaultValue"))
            editTextAmount.setText(String.valueOf(defaultValue));
    }

    Intent intent;
    ActivityResultLauncher<Intent> mGetNameActivity;
    private void paymentinit(long amount,String discription,String mobileNumber) {
        //payment = charge by method
        //done
        Bundle bundle = new Bundle();
        bundle.putInt("type", 2);
        bundle.putInt("amount", (int) amount);

        Gson gson = new Gson();
        HashMap<String,String> metaData = new HashMap<String,String>();
        metaData.put("amount", amount + "0");
        metaData.put("discription",discription);
        metaData.put("mobile",mobileNumber);

        bundle.putString("metaData", gson.toJson(metaData));
        intent = PaymentActivity.getIntent(this, bundle);
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);


        //new
        mGetNameActivity =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
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

                                    ReturnData returnData = new ReturnData();
                                    returnData = gson.fromJson(x.getStringExtra("ReturnData").toString(),ReturnData.class);
                                    Global.user2.getWallet().setAmount(returnData.wallet.getAmount());
                                    Wallet.savedToDataBase(Global.user2);


                                    //todo update database
                                    //user amount

                                }else {
                                    //Toast.makeText(getContext(),"pay not ok" ,Toast.LENGTH_LONG).show();
                                }

                                PaymentActivity.PaymentDone();
                                this.finish();
                            }
                        });
        mGetNameActivity.launch(intent);

        //old
        //startActivityForResult(intent,1000);


        //        Bundle bundle = new Bundle();
//        bundle.putInt("type" , 1);
//        Intent intent = SignInActivity.getIntent(context,bundle);
////        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
////                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//        //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        startActivityForResult(intent, 1000);
    }
    private void payment() {

        mGetNameActivity.launch(intent);

        //old
        //startActivityForResult(intent,1000);


        //        Bundle bundle = new Bundle();
//        bundle.putInt("type" , 1);
//        Intent intent = SignInActivity.getIntent(context,bundle);
////        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
////                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//        //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        startActivityForResult(intent, 1000);
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent x;
        if (requestCode == 1000) {
            if (PaymentActivity.isPaymentSuccess()) {
                x = PaymentActivity.getPaymentIntent();
//                Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();

                x.getIntExtra("amount",10);
                x.getStringExtra("ReturnData");
                x.getStringExtra("metaData");
                x.getStringExtra("item1");
                x.getIntExtra("type",10);

                Gson gson = new Gson();
                ReturnData returnData = new ReturnData();
                returnData = gson.fromJson(x.getStringExtra("ReturnData").toString(),ReturnData.class);
                Global.user2.getWallet().setAmount(returnData.wallet.getAmount());
                Wallet.savedToDataBase(Global.user2);


                //todo update database
                //user amount

            }else {
//                Toast.makeText(getContext(),"pay not ok" ,Toast.LENGTH_LONG).show();
            }

            PaymentActivity.PaymentDone();
            this.finish();
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GO_TO_LOGIN){
//            if (resultCode == Activity.RESULT_OK){
//
//            }else {
//                finish();
//            }
//        }
//    }

    private void chargeAccount(ChargeRequest chargeRequest) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted) , "ok" , new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.chargeAccount(chargeRequest ,ssssssss);
    }


    private class ReturnData{
        public AWallet getWallet() {
            return wallet;
        }

        public void setWallet(AWallet wallet) {
            this.wallet = wallet;
        }

        public TubelessException getTubelessException() {
            return tubelessException;
        }

        public void setTubelessException(TubelessException tubelessException) {
            this.tubelessException = tubelessException;
        }

        public List<ATransaction> getTransactionList() {
            return transactionList;
        }

        public void setTransactionList(List<ATransaction> transactionList) {
            this.transactionList = transactionList;
        }

        List<ATransaction> transactionList = new ArrayList<ATransaction>();
        AWallet wallet = new AWallet();
        TubelessException tubelessException = new TubelessException();
    }
}
