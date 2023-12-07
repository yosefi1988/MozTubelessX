package ir.sajjadyosefi.android.xTubeless.utility;

import android.annotation.TargetApi;
import android.os.Build;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Created by s.yousefi on 26/02/2018.
 */

public class SamanString {

    public static String clearPhoneNumber(String phoneNo) {
        String clear = phoneNo.replace(" " , "");
        clear = clear.replace("-","");
        return clear;
    }

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase(Locale.ROOT);

    public static final String digits = "0123456789";

    public static final String alphanum = upper + lower + digits;

    private final Random random;

    private final char[] symbols;

    private final char[] buf;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public SamanString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public SamanString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public SamanString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public SamanString() {
        this(21);
    }


    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    public static String persianDigitToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }


    public static String ArabicNumberToEnglishNumbers(String str){
        char[] arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {

                if (((str.charAt(i))+ "").equals("۰"))
                    builder.append("0");
                if (((str.charAt(i))+ "").equals( "۱" ))
                    builder.append("1");
                if (((str.charAt(i))+ "").equals( "۲" ))
                    builder.append("2");
                if (((str.charAt(i))+ "").equals( "۳" ))
                    builder.append("3");
                if (((str.charAt(i))+ "").equals( "٤" ) || ((str.charAt(i))+ "").equals( "۴" ))
                    builder.append("4");
                if (((str.charAt(i))+ "").equals( "٥" ) || ((str.charAt(i))+ "").equals( "۵" ))
                    builder.append("5");
                if (((str.charAt(i))+ "").equals( "٦" ) || ((str.charAt(i))+ "").equals( "۶" ))
                    builder.append("6");
                if (((str.charAt(i))+ "").equals( "۷" ))
                    builder.append("7");
                if (((str.charAt(i))+ "").equals( "۸" ))
                    builder.append("8");
                if (((str.charAt(i))+ "").equals( "۹" ))
                    builder.append("9");

            }
            else
            {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }

    public static String doubleToStringNoDecimal(int d) {
        return doubleToStringNoDecimal(d+ "");
    }
    public static String doubleToStringNoDecimal(String d) {
        Double d2 = Double.parseDouble(d);
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter .applyPattern("#,###");
        return formatter.format(d2);
    }


    public static String EnglishNumbersToArabicNumber (String str){
        char[] arabicChars = {'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {

                if (((str.charAt(i))+ "").equals("0"))
                    builder.append("۰");
                if (((str.charAt(i))+ "").equals( "1" ))
                    builder.append("۱");
                if (((str.charAt(i))+ "").equals( "2" ))
                    builder.append("۲");
                if (((str.charAt(i))+ "").equals( "3" ))
                    builder.append("۳");
                if (((str.charAt(i))+ "").equals( "4" ) || ((str.charAt(i))+ "").equals( "۴" ))
                    builder.append("٤");
                if (((str.charAt(i))+ "").equals( "5" ))
                    builder.append("۵");
                if (((str.charAt(i))+ "").equals( "6" ))
                    builder.append("۶");
                if (((str.charAt(i))+ "").equals( "7" ))
                    builder.append("۷");
                if (((str.charAt(i))+ "").equals( "8" ))
                    builder.append("۸");
                if (((str.charAt(i))+ "").equals( "9" ))
                    builder.append("۹");

            }
            else
            {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }

//    public static String addSprator(String s) {
//        StringBuilder stringBuilder = new StringBuilder();
//        if (s.length() < 3){
//            stringBuilder.append(s.substring(0,s.length()));
//            stringBuilder.append("+");
//        }
//
//        if (s.length() == 4){
//            stringBuilder.append(s.substring(0,1));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(1,s.length()));
//            stringBuilder.append("+");
//        }
//        if (s.length() == 5){
//            stringBuilder.append(s.substring(0,2));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(2,s.length()));
//            stringBuilder.append("+");
//        }
//        if (s.length() == 6){
//            stringBuilder.append(s.substring(0,3));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(3,s.length()));
//            stringBuilder.append("+");
//        }
//        if (s.length() == 7){
//            stringBuilder.append(s.substring(0,1));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(1,s.length()));
//
//            stringBuilder.append(s.substring(0,4));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(4,s.length()));
//
//            stringBuilder.append("+");
//        }
//        if (s.length() == 8){
//            stringBuilder.append(s.substring(0,2));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(2,s.length()));
//
//            stringBuilder.append(s.substring(0,5));
//            stringBuilder.append(",");
//            stringBuilder.append(s.substring(5,s.length()));
//
//            stringBuilder.append("+");
//        }
//
//        return stringBuilder.toString();
//    }

    public static String addSpratorX( float s ) {
        return addSpratorX((int) s);
    }

    public static String addSpratorX( int s ) {
        //1
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.ROOT);
        formatter.applyPattern("#,###,###");
        return formatter.format(s);


        //2
        //return NumberFormat.getNumberInstance(Locale.US).format(s);



        //3
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(s);
//        String tmp = stringBuilder.reverse().toString();
//
//        boolean xxxxx = false;
//        try {
//            if (tmp.length() >= 4) {
//                if (tmp.substring(2, 3).equals("4")) {
//                    xxxxx = true;
//                }
//                if (tmp.length() >= 7) {
//                    if (tmp.substring(5, 6).equals("4")) {
//                        xxxxx = true;
//                    }
//                    if (tmp.length() >= 10) {
//                        if (tmp.substring(8, 9).equals("4")) {
//                            xxxxx = true;
//                        }
//
//                    }
//                }
//            }
//        }catch (Exception e) {
//            xxxxx = false;
//        }
//
//
//
//        if (xxxxx){
//            StringBuilder tmpRet = new StringBuilder();
//            if (tmp.length() > 3) {
//                tmpRet.append(tmp.substring(0, 3));
//                tmpRet.append(",");
//
//                if (tmp.length() == 4) {
//                    tmpRet.append(tmp.substring(3, 4));
//                }
//                if (tmp.length() == 5) {
//                    tmpRet.append(tmp.substring(3, 5));
//                }
//                if (tmp.length() == 6) {
//                    tmpRet.append(tmp.substring(3, 6));
//                }
//            }
//
//
//            if (tmp.length() > 6) {
//                tmpRet.append(tmp.substring(3, 6));
//                tmpRet.append(",");
//
//                if (tmp.length() == 7) {
//                    tmpRet.append(tmp.substring(6, 7));
//                }
//                if (tmp.length() == 8) {
//                    tmpRet.append(tmp.substring(6, 8));
//                }
//
//                if (tmp.length() == 9) {
//                    tmpRet.append(tmp.substring(6, 9));
//                }
//            }
//
//
//            if (tmp.length() > 9) {
//                tmpRet.append(tmp.substring(6, 9));
//                tmpRet.append(",");
//
//                if (tmp.length() == 10) {
//                    tmpRet.append(tmp.substring(9, 10));
//                }
//                if (tmp.length() == 11) {
//                    tmpRet.append(tmp.substring(9, 11));
//                }
//                if (tmp.length() == 12) {
//                    tmpRet.append(tmp.substring(9, 12));
//                }
//            }
//            return tmpRet.reverse().toString();
//        } else {
//            return NumberFormat.getNumberInstance(Locale.US).format(s);
//        }
    }
//    public static String addSpratorNew(String s) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        StringBuilder stringBuilder0 = new StringBuilder();
//        stringBuilder0.append(s);
//        stringBuilder0.reverse();
//        String tmp = stringBuilder0.toString().replace(" ","");
//
//        int c = 0 ;
//        for (int i = tmp.length() ; i > 0 ; i -- ) {
//            stringBuilder.append(tmp.charAt(i-1));
//            c++;
//            if ((c) % 3 == 0){
//                if (tmp.length() > c)
//                    stringBuilder.append(",");
//            }
//        }
//        return stringBuilder.reverse().toString();
//    }

    public static String prepareDate(String date) {
        return  date;
    }

    public static String prepareString(String String) {
        try {
//            if (DiamondMainActivity.language.equals(LanguageDialog.PERSIAN_LANGUAGE) ||
//                DiamondMainActivity.language.equals(LanguageDialog.ARABIC_LANGUAGE) ){
//                return EnglishNumbersToArabicNumber(String);
//            }else {
                return  String;
//            }
        }catch (Exception ex){
            return  "";
        }
    }
    public static String prepareString(int String) {
        return prepareString(String + "");
    }
}
