package ir.sajjadyosefi.android.xTubeless.activity.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public abstract class TubelessTransparentStatusBarActivity extends TubelessPayActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //1
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //2
        //https://medium.com/@sandeeptengale/problem-solved-3-android-full-screen-view-translucent-scrollview-adjustresize-keyboard-b0547c7ced32
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getContext().getResources().getColor(R.color.tubelessColor_button_normal));
        }


    }


}
