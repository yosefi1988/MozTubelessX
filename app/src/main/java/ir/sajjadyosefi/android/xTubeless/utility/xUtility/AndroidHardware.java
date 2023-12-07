package ir.sajjadyosefi.android.xTubeless.utility.xUtility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class AndroidHardware {

    public synchronized static String getPhoneNumber(Context context) {
        TelephonyManager phoneMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        if(phoneMgr.getLine1Number().equals("")){
            return phoneMgr.getSubscriberId();
        }else {
            return phoneMgr.getLine1Number();
        }
    }
}
