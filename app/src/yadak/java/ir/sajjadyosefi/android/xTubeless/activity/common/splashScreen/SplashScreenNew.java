package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.SplashScreenPresenterCompl;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.ISplashScreenView;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;

import static ir.sajjadyosefi.android.xTubeless.Global.user2;


//mvp
public class SplashScreenNew extends AppCompatActivity implements ISplashScreenView {

    Context context;
    private SplashScreenPresenterCompl peresenter;

    private String                  wantPermission              = Manifest.permission.READ_PHONE_STATE;
    private static final int        PERMISSION_REQUEST_CODE     = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (AndroidOs.isPermissionGranted(grantResults)) {
                    peresenter.registerDevice();
                } else {
                    Toast.makeText(this,this.getString(R.string.simcardPermissionError), Toast.LENGTH_LONG).show();
                }
                break;
//            case 9001:
//                if ((grantResults.length > 0) ){
//                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                        myOnStart();
//                        deviceRegister();
//                    }else {
//                        //Toast.makeText(mContext,mContext.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
//                        DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.WeNeedYourDeviceInfo));
//                    }
//                }else {
//                    Toast.makeText(context,context.getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        peresenter = new SplashScreenPresenterCompl(this,this);
        setContentView(R.layout.activity_splash_screen);


//        User2 albumToUpdate = LitePal.find(User2.class, user2.getUserCode());
//        albumToUpdate.se(20.99f); // raise the price
//        albumToUpdate.save();

    }

    @Override
    protected void onStart() {
        super.onStart();
        peresenter.registerDevice();
    }

    @Override
    public void onSplashScreenDone() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Go To Main

                context.startActivity(new Intent(context, MainActivity.class));
//                context.startActivity(new Intent(context, MainActivityRxJavaList.class));
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity)context).finish();
            }
        },1000);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Intent x;
//        if (requestCode == 1001) {
//            //login
//
//        }
//        if (requestCode == 661) {
//            if (ir.sajjadyosefi.accountauthenticator.activity.PaymentActivity.isPaymentSuccess()) {
//                x = ir.sajjadyosefi.accountauthenticator.activity.PaymentActivity.getPaymentIntent();
//
//                Toast.makeText(context,"pay success" ,Toast.LENGTH_LONG).show();
//
//            }else {
//                Toast.makeText(context,"pay not ok" ,Toast.LENGTH_LONG).show();
//            }
//
//            ir.sajjadyosefi.accountauthenticator.activity.PaymentActivity.PaymentDone();
//        }
//    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void onThrowException(Throwable t) {
        TubelessException sException = new TubelessException();
        sException.handleServerMessage(context,t);
    }

}
