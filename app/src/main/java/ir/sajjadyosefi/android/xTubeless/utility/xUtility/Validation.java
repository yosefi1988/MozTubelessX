package ir.sajjadyosefi.android.xTubeless.utility.xUtility;

import android.content.Context;
import android.widget.Toast;

import java.util.Arrays;

public class Validation {


    public boolean validateMelliCode(Context context , String melliCode) {

        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};

        if (melliCode.trim().isEmpty()) {
            Toast.makeText(context, "Melli Code is empty", Toast.LENGTH_LONG).show();
            return false; // Melli Code is empty
        } else if (melliCode.length() != 10) {
            Toast.makeText(context, "Melli Code must be exactly 10 digits", Toast.LENGTH_LONG).show();
            return false; // Melli Code is less or more than 10 digits
        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
            Toast.makeText(context, "MelliCode is not valid (Fake MelliCode)", Toast.LENGTH_LONG).show();
            return false; // Fake Melli Code
        } else {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
            }

            int lastDigit;
            int divideRemaining = sum % 11;

            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }

            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
                Toast.makeText(context, "MelliCode is valid", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(context, "MelliCode is not valid", Toast.LENGTH_LONG).show();
                return false; // Invalid MelliCode
            }
        }
    }

    public static boolean isValidNationalCode(String nationalCode)
    {
        if (!nationalCode.matches("^\\d{10}$"))
            return false;
        if (nationalCode.matches("^(.)\1{9,}$"))
            return false;

        int sum = 0;

        for (int i = 0; i < 9; i++)
        {
            sum += Character.getNumericValue(nationalCode.charAt(i)) * (10 - i);
        }

        int lastDigit = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));
        int divideRemaining = sum % 11;

        return ((divideRemaining < 2 && lastDigit == divideRemaining) ||   (divideRemaining >= 2 && (11 - divideRemaining) == lastDigit ));
    }

    private boolean isValidNationalCode2(String nationalCode) {
        if (nationalCode.length() != 10) {
            return false;
        } else {
            //Check for equal numbers
            String[] allDigitEqual = {"0000000000", "1111111111", "2222222222", "3333333333",
                    "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};
            if (Arrays.asList(allDigitEqual).contains(nationalCode)) {
                return false;
            } else {
                int sum = 0;
                int lenght = 10;
                for (int i = 0; i < lenght - 1; i++) {
                    sum += Integer.parseInt(String.valueOf(nationalCode.charAt(i))) * (lenght - i);
                }

                int r = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));

                int c = sum % 11;

                return (((c < 2) && (r == c)) || ((c >= 2) && ((11 - c) == r)));
            }

        }
    }



    public static void main(String[] args) {
        System.out.println(validatePhoneNumber("09123678522"));
        System.out.println(validatePhoneNumber("09999816652"));
        System.out.println(validatePhoneNumber("09354226320"));
        System.out.println(validatePhoneNumber("09198166520"));
    }

    public static boolean validatePhoneNumber(String phoneNo) {
        String newString = changePersianNumbers(phoneNo.trim());

        //validate phone numbers of format "1234567890"
        if (newString.matches("^09[-.\\s]?\\d{2}[-.\\s]?\\d{3}[-.\\s]?\\d{4}$")) return true;
            //validating phone number with -, . or spaces
//        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
//            //validating phone number with extension length from 3 to 5
//        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
//            //validating phone number where area code is in braces ()
//        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
//            //return false if nothing matches the input
        else return false;

    }

    public static String changePersianNumbers(String old){
        old = old.replace("۰","0");
        old = old.replace("۹","9");
        old = old.replace("۸","8");
        old = old.replace("۷","7");
        old = old.replace("۶","6");
        old = old.replace("۵","5");
        old = old.replace("۴","4");
        old = old.replace("۳","3");
        old = old.replace("۲","2");
        old = old.replace("۱","1");
        return old;
    }

}
