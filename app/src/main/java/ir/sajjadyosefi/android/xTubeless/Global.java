package ir.sajjadyosefi.android.xTubeless;

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

//import org.litepal.LitePal;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Random;

import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.classes.SAccounts;
import ir.sajjadyosefi.accountauthenticator.model.AConfig;

import ir.sajjadyosefi.android.xTubeless.classes.model.test.Food;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitLottery.RetrofitHelperLottery;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitNerkhRoz.RetrofitHelperNerkhRoz;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitPolice.RetrofitHelperEpolice;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.RetrofitHelperTubeless;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.RetrofitHelperPost;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.getAppDownloadedStore;
import static ir.sajjadyosefi.android.xTubeless.widget.CustomEditText.FONT_IRANSANS_MOBILE_NORMAL_TTF;


/**
 * Created by Sajad on 2/11/2017.
 */
public class Global extends MultiDexApplication {

    //_____________ ok ________________
    public static User userFixxxxxxxxx = null;
    public static User2 user2 = null;

    public static AConfig aConfig = null;
    public static SAccounts sAccountHelper = null;
    //_________________________________

    public static final String REST_API_IP_ADDRESS2                       = "http://test.sajjadyosefi.ir";   //internet Dns
//    public static final String REST_API_IP_ADDRESS2                         = "http://192.168.1.5:80";   //local Dns

    public static RetrofitHelperPost apiManagerPost;
    public static RetrofitHelperLottery apiManagerLottery;
    public static RetrofitHelperTubeless apiManagerTubeless;
    public static RetrofitHelperEpolice retrofitHelperEpolice;
    public static RetrofitHelperNerkhRoz retrofitHelperNerkhRoz;

    public static void ShowSelectSturceDialog(final Context context) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.version_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(false);

        final Button btnCamera = (Button) dialoglayout.findViewById(R.id.btnCamera);
        final Button btnBrowse = (Button) dialoglayout.findViewById(R.id.btnBrowse);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();

                String url = context.getString(R.string.direct_download_address);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                ((Activity)context).startActivity(i);

            }//end click
        });
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
//                openGallery();
                Toast.makeText(context,"سایت درحال آماده سازی - دانلود شروع شد",Toast.LENGTH_SHORT).show();

                String url = context.getString(R.string.direct_download_address);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                ((Activity)context).startActivity(i);

//                Bundle bundle = new Bundle();
//                bundle.putString("address" , context.getString(R.string.site_address));
//                bundle.putBoolean("isOnline" , true);
//                ((Activity)context).startActivity(WebViewActivity.getIntent(context,bundle));
            }
        });
        alertDialog1.show();


    }

    private FirebaseAnalytics firebaseAnalytics;


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        //user
        sAccountHelper = new SAccounts(this);

        AccountGeneral.setIP(DeviceUtil.GetIP(this));
        AccountGeneral.setAndroidID(DeviceUtil.GetAndroidId(this));
        AccountGeneral.setStore(getAppDownloadedStore());


        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Food food = new Food();
        food.setId(1);
        // choose random food name from the list
        food.setName("آش");
        Bundle bundle = new Bundle();
        bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, food.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, food.getName());
        //Logs an app event.
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        //Sets whether analytics collection is enabled for this app on this device.
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);
        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds just for the fun
        firebaseAnalytics.setMinimumSessionDuration(20000);
        //Sets the duration of inactivity that terminates the current session. The default value is 1800000 (30 minutes).
        firebaseAnalytics.setSessionTimeoutDuration(500);
        //Sets the user ID property.
        firebaseAnalytics.setUserId(String.valueOf(food.getId()));
        //Sets a user property to a given value.
        firebaseAnalytics.setUserProperty("Food", food.getName());





//        MobileAds.initialize(this, "ca-app-pub-6595298957852131/3747952719");

        apiManagerPost = RetrofitHelperPost.getInstance();
        apiManagerLottery = RetrofitHelperLottery.getInstance();
        retrofitHelperEpolice = RetrofitHelperEpolice.getInstance(this);
        retrofitHelperNerkhRoz = RetrofitHelperNerkhRoz.getInstance(this);

        //font 1
//        FontChanger.overrideDefaultFont(this, "DEFAULT", FONT_IRANSANS_MOBILE_NORMAL_TTF);

        //font 6 ok
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_IRANSANS_MOBILE_NORMAL_TTF)
                .setFontAttrId(R.attr.fontPath)
                .build());


//        Adad.setEnabled(true);
//        Adad.setTestMode(false);
//        Adad.initialize("357418d6-115d-47d3-8fb4-a228d2c1b882");// جایگاه وارد کردن توکن یا شناسه ی اپلیکیشن مورد نظر


        apiManagerTubeless = RetrofitHelperTubeless.getInstance(getApplicationContext());
        LitePal.initialize(this);



        //picasso cache
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);


        //throw new RuntimeException("Test Crash"); // Force a crash

    }

    public static void copy(File src, File dst) {
        try {
            FileInputStream inStream = new FileInputStream(src);
            FileOutputStream outStream = new FileOutputStream(dst);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inStream.close();
            outStream.close();
        }catch (Exception ex ){

        }

    }

    public static void StartDownload(String Url,Context context) {
        // show download is started to user
        Toast.makeText(context,context.getString(R.string.startDownloadText), Toast.LENGTH_SHORT);
        /////////////////////////////// Start Download //////////////////////////////////
        //DownloadManager.Request r = new DownloadManager.Request(Uri.parse(Url));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, context.getString(R.string.downloadNotifText));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
        //////////////////////////////////////////////////////////////////////////////
    }

    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static  String getFileType(String word){
        if (word.length() == 3) {
            return word;
        } else if (word.length() > 3) {
            return word.substring(word.length() - 3);
        } else {
            // whatever is appropriate in this case
            throw new IllegalArgumentException("word has less than 3 characters!");
        }
    }

//    public static int getIndexByTabType(int type){
//        int index = 0 ;
//        switch (type){
//            case 16 :
//            {
//                return FragmentYaftehaAdapter.mTab1;
//            }
//            case 17 :
//            {
//                return FragmentYaftehaAdapter.mTab2;
//            }
//            case 18 :
//            {
//                return FragmentYaftehaAdapter.mTab3;
//            }
//            case 2 :
//            {
//                return FragmentPoliceAdapter.mTab1;
//            }
//            case 3 :
//            {
//                return FragmentPoliceAdapter.mTab2;
//            }
//            case 6:
//            {
//                return FragmentTamirgahAdapter.mTab1;
//            }
//            case 5:
//            {
//                return FragmentTamirgahAdapter.mTab2;

//            }
//            case 12:
//            {
//                return FragmentTimelineAdapter.mTab2;
//            }
//            case 0 :
//            {
//                return FragmentTimelineAdapter.mTab1;
//            }
//            case 9:
//            {
//                return FragmentMashinbazAdapter.mTab1;
//            }
//            case 8:
//            {
//                return FragmentMashinbazAdapter.mTab2;
//            }
//            case 14: {
//                return FragmentGashtogozarAdapter.mTab1;
//            }
//            default :
//                return 10000000;
//        }
//
//    }

//    public static void resetListIndex(int type){
//        switch (type){
//            case 16 :
//            {
//                FragmentYaftehaAdapter.mTab1 = 0;
//                break;
//            }
//            case 17 :
//            {
//                FragmentYaftehaAdapter.mTab2 = 0 ;
//                break;
//            }
//            case 18 :
//            {
//                FragmentYaftehaAdapter.mTab3 = 0;
//                break;
//            }
//            case 2 :
//            {
//                FragmentPoliceAdapter.mTab1 = 0;
//                break;
//            }
//            case 3 :
//            {
//                FragmentPoliceAdapter.mTab2 = 0;
//                break;
//            }
//            case 6:
//            {
//                FragmentTamirgahAdapter.mTab1 = 0;
//                break;
//            }
//            case 5:
//            {
//                FragmentTamirgahAdapter.mTab2 = 0;
//                break;
//            }
//            case 12:
//            {
//                FragmentTimelineAdapter.mTab2 = 0;
//                break;
//            }
//            case 0 :
//            {
//                FragmentTimelineAdapter.mTab1 = 0;
//                break;
//            }
//            case 9:
//            {
//                FragmentMashinbazAdapter.mTab1 = 0;
//                break;
//            }
//            case 8:
//            {
//                FragmentMashinbazAdapter.mTab2 = 0;
//                break;
//            }
//            case 14: {
//                FragmentGashtogozarAdapter.mTab1 = 0;
//                break;
//            }
//        }
//    }

//    public static void increamentIndex(int type){
//        switch (type){
//            case 16 :
//            {
//                FragmentYaftehaAdapter.mTab1 ++;
//                break;
//            }
//            case 17 :
//            {
//                FragmentYaftehaAdapter.mTab2 ++;
//                break;
//            }
//            case 18 :
//            {
//                FragmentYaftehaAdapter.mTab3 ++;
//                break;
//            }
//            case 2 :
//            {
//                FragmentPoliceAdapter.mTab1 ++;
//                break;
//            }
//            case 3 :
//            {
//                FragmentPoliceAdapter.mTab2++;
//                break;
//            }
//            case 6:
//            {
//                FragmentTamirgahAdapter.mTab1++;
//                break;
//            }
//            case 5:
//            {
//                FragmentTamirgahAdapter.mTab2++;
//                break;
//            }
//            case 12:
//            {
//                FragmentTimelineAdapter.mTab2++;
//                break;
//            }
//            case 0 :
//            {
//                FragmentTimelineAdapter.mTab1++;
//                break;
//            }
//            case 9:
//            {
//                FragmentMashinbazAdapter.mTab1++;
//                break;
//            }
//            case 8:
//            {
//                FragmentMashinbazAdapter.mTab2++;
//                break;
//            }
//            case 14: {
//                FragmentGashtogozarAdapter.mTab1++;
//                break;
//            }
//        }
//    }

    //public static class CheckForSDCard {
        //Check If SD Card is present or not method
        public static boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }
    //}

    public static  String getFileFullName(String word){
        return word.toString().substring( word.toString().lastIndexOf('/')+1, word.toString().length() );
    }

    public static final String downloadDirectory = "/DCIM/TubelessImages/";
    public static File getFileLocalPath(String fileName){
        return  new File(Environment.getExternalStorageDirectory() + "/"  + downloadDirectory + fileName);
    }
    public static File getFileStoragePath(){
        return  new File(Environment.getExternalStorageDirectory() + "/"  + downloadDirectory );
    }


    public static final boolean SaveLogedInUser(Context context,User user){
        try {
            SharedPreferences prefs = null;
            prefs = context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = gson.toJson(user);
            prefs.edit().putString("LogedInUser", json).commit();
            return true;
        }catch (Exception ex){
            return false;
        }
    }


    public static final void ClearLogedInUser(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        prefs.edit().putString("LogedInUser", "").commit();
    }



    public static final boolean avalableSendAppInMenu(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        int count = prefs.getInt("avalableSendAppInMenu", 0);
        if (count == 10) {
            return true;
        }else {
            prefs.edit().putInt("avalableSendAppInMenu", count + 1).commit();
            return false;
        }
    }
    public static final boolean avalableBazarAd(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        int count = prefs.getInt("avalableBazarAd", 0);
        if (count == 13) {
            return true;
        }else {
            prefs.edit().putInt("avalableBazarAd", count + 1).commit();
            return false;
        }
    }

}