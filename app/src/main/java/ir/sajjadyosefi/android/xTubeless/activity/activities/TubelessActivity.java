package ir.sajjadyosefi.android.xTubeless.activity.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.NOT_LOGN_USER;


public abstract class TubelessActivity extends AppCompatActivity {

    private Context context;
    private AppCompatActivity activity;
    private ViewGroup rootActivity;
    public Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        activity = this;
        progressDialog = new Dialog(getContext());
        progressDialog.setContentView(R.layout.x_main_progress);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


//        Crashlytics.setUserIdentifier((Global.user == null ? NOT_LOGN_USER : Global.user.getUserId()) + "");
////        Crashlytics.setUserName(<USER_NAME>);
////        Crashlytics.setUserEmail(<USER_EMAIL>);
//        Fabric.with(this, new Crashlytics());




        int width  = Resources.getSystem().getDisplayMetrics().widthPixels;

//        progressDialog.getWindow().setLayout(convertPixelsToDp(width ,getContext()), ViewGroup.LayoutParams.WRAP_CONTENT);
//        progressDialog.getWindow().setLayout(200, ViewGroup.LayoutParams.WRAP_CONTENT);
//        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        progressDialog.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL); // set text and message direction to RTL

        progressDialog.setCancelable(false);

//        getSupportActionBar().hide();


//        if (rootActivity != null)
//            overrideFonts(this,rootActivity);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public Context getContext(){
        return context;
    }
    public AppCompatActivity getActivity(){
        return activity;
    }
    public View getRootActivity(){
        return rootActivity;
    }


    private void setProgressDialog() {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.x_main_progress);
        dialog.setCancelable(true);
        //there are a lot of settings, for progressDialog, check them all out!

//        progressDialog.getWindow().getDecorView().getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x00ffffff));

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
    }

    protected void setRootActivity(ViewGroup childAt) {
        rootActivity = childAt;
    }
    protected void setRootActivity() {
        rootActivity = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
    }
    @Override
    protected void onStart() {
        super.onStart();
        rootActivity = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        //font 3 ok
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//        fontChanger.replaceFonts(rootActivity);

    }




    protected void retCancel(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    protected void retData(Intent returnIntent){
//        Intent autoActivityIntent =  new Intent(getContext(), MainActivity.class);
////                Bundle bundleAuto = new Bundle();
////                bundleAuto.putString("type","NEW_Auto");
////                bundleAuto.putString("BankNumber" , phoneNumber );
////                bundleAuto.putString("Message" , message );
////                autoActivityIntent.putExtras(bundleAuto);
//        getContext().startActivity(autoActivityIntent);
//
//
//        returnIntent.putExtra("result",returnIntent);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    protected SystemBarTintManager mSystemBarTint;
    protected boolean mTranslucentStatus;
    protected boolean mTranslucentStatusSet;
    protected boolean mTranslucentNavigation;
    protected boolean mTranslucentNavigationSet;

    public abstract SystemBarTintManager getSystemBarTint() ;

    public abstract boolean hasTranslucentNavigation();

    public abstract boolean hasTranslucentStatusBar();

    public abstract BottomNavigation getBottomNavigation();

    public abstract int getNavigationBarHeight();

    public abstract boolean hasManagedToolbarScroll();

    public abstract boolean hasAppBarLayout();

    public abstract Toolbar getToolbar();



}
