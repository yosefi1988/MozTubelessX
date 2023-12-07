package ir.sajjadyosefi.android.xTubeless.classes.model.bourseState;

import android.content.Context;

//import org.litepal.LitePal;
//import org.litepal.annotation.Column;
//import org.litepal.crud.LitePalSupport;

//import org.litepal.annotation.Column;
//import org.litepal.crud.LitePalSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BourseState
//        extends LitePalSupport
{

    private Context context;

    //_____________ ok ________________
//    @Column(unique = true, defaultValue = "unknown")
//    @Column(unique = true)
    public long BourseStateId;

//    @Column(ignore = true)
    private int DatabaseId = 101;

//    @Column(ignore = true)
//    @Column(nullable = true)
    String startDate = null;

//    @Column(ignore = true)
//    @Column(nullable = true)
    public String endDate = null;

//    @Column(nullable = true)
    public long lastPayedValue = 0;

//    @Column(nullable = true)
    public int lastPayedType = 0;

//    @Column(nullable = true)
    public long totalPayedValue = 0;


    public boolean firstRunApp(Context context){
        try {
            BourseState bourseState = new BourseState();
            bourseState.BourseStateId = DatabaseId ;
            bourseState.startDate = null;
            bourseState.endDate = null;
            bourseState.lastPayedValue = 0;
            bourseState.lastPayedType = 0;
            bourseState.totalPayedValue = 0;
//            bourseState.save();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean updateAfterPay(int dayCount , String serverDate){
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            Date d = new Date(System.currentTimeMillis());
            Date d = df.parse(serverDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(d);

            this.startDate = df.format(cal.getTime());

            cal.add(Calendar.DAY_OF_MONTH, dayCount);
            this.endDate = df.format(cal.getTime());

//            xxxxx.startDate = this.startDate;
//            xxxxx.endDate = this.endDate;
//            this.save();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public static boolean CheckDateIsValid(String EndTime , String serverDate) {
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date d1 = sdformat.parse(EndTime);
//            Date d2 = new Date(System.currentTimeMillis());
            Date d2 = sdformat.parse(serverDate);

            System.out.println("The date 1 is: " + sdformat.format(d1));
            System.out.println("The date 2 is: " + sdformat.format(d2));

            if (d1.compareTo(d2) > 0) {
                System.out.println("Date 1 occurs after Date 2");
                return true;
            } else if (d1.compareTo(d2) < 0) {
                System.out.println("Date 1 occurs before Date 2");
                return false;
            } else if (d1.compareTo(d2) == 0) {
                System.out.println("Both dates are equal");
                return true;
            }
            return false;
        }catch (Exception ex){
            return false;
        }
    }

    public BourseState loadUserBourseData() {
        return null;
        //todo fixxxxxxxxxxxxxx
//        BourseState userBourseState = LitePal.where("BourseStateId = ?", "" + DatabaseId).findFirst(BourseState.class);
//        return userBourseState;
    }

}
