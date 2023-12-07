package ir.sajjadyosefi.android.xTubeless;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

//import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Random;

import ir.adad.core.Adad;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.RetrofitHelperTubeless;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitPolice.RetrofitHelperEpolice;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.RetrofitHelperPost;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static ir.sajjadyosefi.android.xTubeless.widget.CustomEditText.FONT_IRANSANS_MOBILE_NORMAL_TTF;

//اس دی کی های تبلیغاتی عدد


/**
 * Created by Sajad on 2/11/2017.
 */
public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();


    }

}