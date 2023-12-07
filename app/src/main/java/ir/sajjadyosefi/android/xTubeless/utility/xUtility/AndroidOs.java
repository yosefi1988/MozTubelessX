package ir.sajjadyosefi.android.xTubeless.utility.xUtility;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class AndroidOs {

    public synchronized static boolean checkPermission(Context context , String permission){
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public synchronized static boolean checkPermission(Context context , String[] permissions){
        for (String mPermission : permissions) {
            return checkPermission(context ,mPermission);
        }
        return true;
    }

    public synchronized static boolean shouldShowRequestPermissionRationale(Activity activity , String permission){
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static void requestPermissions(
            final @NonNull Activity activity,
            final @NonNull String[] permissions,
            final @IntRange(from = 0) int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions,requestCode);
    }


    public static boolean isPermissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

}
