package ir.sajjadyosefi.android.xTubeless.classes;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;

import ir.sajjadyosefi.android.xTubeless.R;

/**
 * Created by s.yousefi on 26/03/2018.
 */

public class Validator {

    public static String message = "";

    //CheckBox
    public String checkBoxCheck(Context context , CheckBox checkBox){
        String message = "";

//        if (!checkBox.isChecked())
//            message += context.getString(R.string.checkbox_must_checked) + "\n";
        return message;
    }
//    public String checkBoxRason(Context context, CheckBox checkBox){
//        message = "";
//        if (!checkBox.isChecked())
//            message += context.getString(R.string.checkbox_must_checked) + "\n";
//        return message;
//    }


    //phone EditText
    public boolean phoneFieldValid(EditText editText){
        if (editText.getText().toString().isEmpty())
            return false;
        else if (!this.isIranianMobileNumber(editText.getText().toString()))
            return false;
        else return true;
    }

    //phone EditText
    public boolean samaniPointIsEnugh(EditText editText){
        try {
            if (editText.getText().toString().length() >= 1) {
                try {
                    if (Integer.parseInt(editText.getText().toString()) > 0) {
//                        if (Integer.parseInt(editText.getText().toString()) <= customerNew.getScores().getSamanScoreAsInt()) {
//                            return true;
//                        } else {
                            return false;
//                        }
                    } else {
                        return false;
                    }
                }catch (Exception e){
                    return false;
                }
            }else {
                return false;
            }
        }catch (Exception ex){
            return false;
        }
    }

    //CardNumber String
    public String CardNumberValid(Context context ,  String cardNumber) {
        String message = null ;

//        if (cardNumber == "") {
//            message += context.getString(R.string.card_number_not_valid) + "\n";
//        } else
        if (cardNumber.length() <= 18) {
//            message += context.getString(R.string.card_number_not_valid) + "\n";
        } else {
//            if (onlySamanCards){
            if (true){
                if (cardNumber.substring(0, 7).equals("6219-86")) {
//                    if (cardNumber.substring(0, 8).equals("6219-861") || cardNumber.substring(0, 8).equals("6219-866") || cardNumber.substring(0, 8).equals("6219-864")) {
//                    } else {
//                        message += context.getString(R.string.card_saman_not_valid) + "\n";
//                        return false;
//                    }
                } else{
//                    message += context.getString(R.string.card_number_not_valid) + "\n";
                }
            }else {
                if (cardNumber.substring(0, 7).equals("6219-86")) {
//                    if (cardNumber.substring(0, 8).equals("6219-861") || cardNumber.substring(0, 8).equals("6219-866") || cardNumber.substring(0, 8).equals("6219-864")) {
//                    } else {
//                        message += context.getString(R.string.card_saman_not_valid) + "\n";
//                        return false;
//                    }
                }
            }
        }
        return message ;
    }

    //CardNumber String
    public boolean CardNumberValid2(Context context ,  String cardNumber) {

        if (cardNumber.length() >= 8) {
//            if (onlySamanCards) {
            if (false) {
                if (cardNumber.substring(0, 7).equals("6219-86")) {
//                    if (cardNumber.substring(0, 8).equals("6219-861") || cardNumber.substring(0, 8).equals("6219-866") || cardNumber.substring(0, 8).equals("6219-864")) {
                        return true;
//                    } else {
//                        message += context.getString(R.string.card_saman_not_valid) + "\n";
//                        return false;
//                    }
                } else {
//                    message += context.getString(R.string.card_number_not_valid) + "\n";
                    return false;
                }
            } else {
                if (cardNumber.substring(0, 7).equals("6219-86")) {
//                    if (cardNumber.substring(0, 8).equals("6219-861") || cardNumber.substring(0, 8).equals("6219-866") || cardNumber.substring(0, 8).equals("6219-864")) {
                        return true;
//                    } else {
//                        message += context.getString(R.string.card_saman_not_valid) + "\n";
//                        return false;
//                    }
                } else
                    return true;
            }
        }else
            return true;
    }

    //phone String
    public boolean phoneFieldValid(String phone){
        if (phone == null || phone.length() <= 10)
            return false;
        else if (!this.isIranianMobileNumber(phone))
            return false;
        else return true;
    }
    //phone String
    public boolean phoneFieldCheck2(String phone){

        if (phone == null)
            return false;
        if (this.isIranianMobileNumber(phone.replace(" " , "").replace("+98","0")))
            return true;
        else
            return false;
    }

//    public String phoneFailedRason(Context context, EditText editText){
//        message = "";
//        if (!this.isIranianMobileNumber(editText.getText().toString()))
//            message += context.getString(R.string.phone_number_is_not_correct) + "\n";
//        if (editText.getText().toString().isEmpty())
//            message += context.getString(R.string.phone_should_not_be_empty) + "\n";
//        return message;
//    }

//
//    public String CardNumberFailedRason(Context context, EditText editText) {
//        message = "";
//        if (editText.getText().toString() == "") {
//            message += context.getString(R.string.card_number_not_valid) + "\n";
//        } else if (editText.getText().toString().length() <= 15) {
//            message += context.getString(R.string.card_number_not_valid) + "\n";
//        } else {
//            if (editText.getText().toString().substring(0, 7).equals("6219-86")) {
//                if (editText.getText().toString().substring(0, 8).equals("6219-861") || editText.getText().toString().substring(0, 8).equals("6219-866")) {
//                    message += context.getString(R.string.card_saman_not_valid) + "\n";
//                } else
//                    message += context.getString(R.string.card_saman_not_valid) + "\n";
//            }
//        }
//        return message;
//    }



    //UserName EditText
    public boolean userNameFieldCheck(EditText editText){
        if (editText.getText().toString().isEmpty())
            return false;
        else return true;
    }
//    public String userNameFailedRason(Context context, EditText editText){
//        message = "";
//        if (editText.getText().toString().isEmpty())
//            message += context.getString(R.string.userName_should_not_be_empty) + "\n";
//        return message;
//    }
//
//    public String friendNameFailedRason(Context context, EditText editText){
//        message = "";
//        if (editText.getText().toString().isEmpty())
//            message += context.getString(R.string.friend_name_should_not_be_empty) + "\n";
//        return message;
//    }



    //email EditText
    public boolean emailFieldCheck(EditText editText){
        if (editText.getText().toString().isEmpty())
            return false;
        else if (!this.isEmail(editText.getText().toString()))
            return false;
        else return true;
    }

    public boolean emailFieldCheck(String editText){
        if (editText.toString().isEmpty())
            return false;
        else if (!this.isEmail(editText.toString()))
            return false;
        else return true;
    }

//    public String emailFailedRason(Context context, EditText editText){
//        message = "";
//        if (!this.isEmail(editText.getText().toString()))
//            message += context.getString(R.string.email_is_not_correct) + "\n";
//
//        if (editText.getText().toString().isEmpty())
//            message += context.getString(R.string.email_should_not_be_empty) + "\n";
//
//        return message;
//    }



    //password EditText
    public boolean passwordFieldCheck(EditText editTextPassword) {
        if (editTextPassword.getText().toString().isEmpty())
            return false;
        else if (!this.isPassword(editTextPassword.getText().toString()))
            return false;
        else return true;
    }
    public boolean passwordFieldCheckForLogin(EditText editTextPassword) {
        if (editTextPassword.getText().toString().isEmpty())
            return false;
        else if (!this.isPasswordForlogin(editTextPassword.getText().toString()))
            return false;
        else return true;
    }


    //password 2 BankCard EditText
//    public String cvv2BankCardFieldCheck(Context context , EditText editTextPassword) {
//        String message = null ;
//
////        if (!this.isCvv2(editTextPassword.getText().toString()))
////            message += context.getString(R.string.entered_bank_card_cvv2_is_not_correct) + "\n";
//
//        if (editTextPassword.getText().toString().isEmpty())
//            message += context.getString(R.string.entered_bank_card_cvv2_is_not_correct) + "\n";
//
//        return message;
//    }

    //password 2 BankCard EditText
//    public String passwordBankCardFieldIsValid(Context context , EditText editTextPassword) {
//        String message = null;
//        if (!this.isBankCardPassword(editTextPassword.getText().toString()))
//            message += context.getString(R.string.entered_bank_card_password_is_not_correct) + "\n";
//
////        if (editTextPassword.getText().toString().isEmpty())
////            message += context.getString(R.string.password_bank_card_should_not_be_empty) + "\n";
//
//        return message;
//    }

//    public String passwordFailedRason(Context context, EditText editTextPassword) {
//        message = "";
//        if (!this.isEmail(editTextPassword.getText().toString()))
//            message += context.getString(R.string.entered_password_is_not_correct) + "\n";
//
//        if (editTextPassword.getText().toString().isEmpty())
//            message += context.getString(R.string.password_should_not_be_empty) + "\n";
//
//        return message;
//    }


//    public String cvv2BankCardFailedRason(Context context, EditText editTextPassword) {
//        message = "";
//        if (!this.isCvv2(editTextPassword.getText().toString()))
//            message += context.getString(R.string.entered_bank_card_cvv2_is_not_correct) + "\n";
//
//        if (editTextPassword.getText().toString().isEmpty())
//            message += context.getString(R.string.cvv2_bank_card_should_not_be_empty) + "\n";
//
//        return message;
//    }

//    public String passwordBankCardFailedRason(Context context, EditText editTextPassword) {
//        message = "";
//        if (!this.isBankCardPassword(editTextPassword.getText().toString()))
//            message += context.getString(R.string.entered_bank_card_password_is_not_correct) + "\n";
//
//        if (editTextPassword.getText().toString().isEmpty())
//            message += context.getString(R.string.password_bank_card_should_not_be_empty) + "\n";
//
//        return message;
//    }


    //confirm password EditText
    public boolean passwordConfirmFieldCheck( EditText editTextPassword,EditText passwordConfirmEditText) {
        if (passwordConfirmEditText.getText().toString().isEmpty())
            return false;
        else if (!this.isPassword(passwordConfirmEditText.getText().toString()))
            return false;
        else if (!editTextPassword.getText().toString().equals(passwordConfirmEditText.getText().toString()))
            return false;
        else return true;
    }

//    public String passwordConfirmFailedRason(Context context, EditText editTextPassword,EditText passwordConfirmEditText) {
//        message = "";
//        if (!this.isEmail(editTextPassword.getText().toString()))
//            message += context.getString(R.string.confirm_entered_password_is_not_correct) + "\n";
//
//        if (editTextPassword.getText().toString().isEmpty())
//            message += context.getString(R.string.confirm_password_should_not_be_empty) + "\n";
//
//        if (!editTextPassword.getText().toString().equals(passwordConfirmEditText.getText().toString()))
//            message += context.getString(R.string.confirm_password_is_not_equals) + "\n";
//
//        return message;
//    }


    //confirm password EditText
    public boolean verifyCodeCheck(EditText EditTextVerifyCode) {
        if (EditTextVerifyCode.getText().toString().isEmpty())
            return false;
        else if (!this.isVerifyCode(EditTextVerifyCode.getText().toString()))
            return false;
        else return true;
    }

//    public String verifyCodeCheckFailedRason(Context context, EditText EditTextVerifyCode) {
//        message = "";
//        if (!this.isVerifyCode(EditTextVerifyCode.getText().toString()))
//            message += context.getString(R.string.entered_verifyCode_is_not_correct) + "\n";
//
//        if (EditTextVerifyCode.getText().toString().isEmpty())
//            message += context.getString(R.string.entered_verifyCode_should_not_be_empty) + "\n";
//        return message;
//    }

    ////////////////////////////////////////////////////////////////////////

    private boolean isAlphaNumeric(String s){
        String regexPattern= "^[a-zA-Z0-9]*$";
        return s.matches(regexPattern);
    }
    private boolean isNumeric(String s){
        String regexPattern = "[0-9]+";
        return s.matches(regexPattern);
    }

    private boolean isAlpha(String s){
        String regexPattern = "[a-zA-Z]+\\.?";
        return s.matches(regexPattern);
    }
    private boolean isEmail(String s){
        String regexPattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return s.matches(regexPattern);
    }

    public boolean isIranianMobileNumber(String s){
        if (s == null){
            return false;
        }else {
            String regexPattern = "^[0][9][0-9]{9,9}$";
            return s.matches(regexPattern);
        }
    }

    private boolean isEmpty(String s){
        String regexPattern = "/^$|\\s+/";
        return s.matches(regexPattern);
    }

    private boolean isPassword(String s){
        String regexPattern ;

        //regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        regexPattern = ".{8,}";

//        Explanations:
//            (?=.*[0-9]) a digit must occur at least once
//            (?=.*[a-z]) a lower case letter must occur at least once
//            (?=.*[A-Z]) an upper case letter must occur at least once
//            (?=.*[@#$%^&+=]) a special character must occur at least once
//            (?=\\S+$) no whitespace allowed in the entire string
//            .{8,} at least 8 characters

        return s.matches(regexPattern);
    }
    private boolean isPasswordForlogin(String s){
        String regexPattern ;

        //regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        regexPattern = ".{5,}";

//        Explanations:
//            (?=.*[0-9]) a digit must occur at least once
//            (?=.*[a-z]) a lower case letter must occur at least once
//            (?=.*[A-Z]) an upper case letter must occur at least once
//            (?=.*[@#$%^&+=]) a special character must occur at least once
//            (?=\\S+$) no whitespace allowed in the entire string
//            .{8,} at least 8 characters

        return s.matches(regexPattern);
    }


    private boolean isCvv2(String s){
        String regexPattern ;

        //regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        regexPattern = ".{2,}";

//        Explanations:
//            (?=.*[0-9]) a digit must occur at least once
//            (?=.*[a-z]) a lower case letter must occur at least once
//            (?=.*[A-Z]) an upper case letter must occur at least once
//            (?=.*[@#$%^&+=]) a special character must occur at least once
//            (?=\\S+$) no whitespace allowed in the entire string
//            .{8,} at least 8 characters

        return s.matches(regexPattern);
    }

    private boolean isBankCardPassword(String s){
        String regexPattern ;

        //regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        regexPattern = ".{4,}";

//        Explanations:
//            (?=.*[0-9]) a digit must occur at least once
//            (?=.*[a-z]) a lower case letter must occur at least once
//            (?=.*[A-Z]) an upper case letter must occur at least once
//            (?=.*[@#$%^&+=]) a special character must occur at least once
//            (?=\\S+$) no whitespace allowed in the entire string
//            .{8,} at least 8 characters

        return s.matches(regexPattern);
    }

    private boolean isVerifyCode(String s){
        String regexPattern ;

        //regexPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        regexPattern = ".{4,}";

//        Explanations:
//            (?=.*[0-9]) a digit must occur at least once
//            (?=.*[a-z]) a lower case letter must occur at least once
//            (?=.*[A-Z]) an upper case letter must occur at least once
//            (?=.*[@#$%^&+=]) a special character must occur at least once
//            (?=\\S+$) no whitespace allowed in the entire string
//            .{8,} at least 8 characters

        return s.matches(regexPattern);
    }

    public String clearPhone(String phone) {
        String tmp = phone
                .replace(" ", "")
                .replace("+98" , "0")
                .replace("0098","0");
        return tmp;
    }

    public String yearAndMonthFieldCheck(Context context,EditText editTextYear, EditText editTextMonth) {
        String message = null;
        if (editTextYear.getText().toString().length() == 2 && editTextMonth.getText().length() == 2) {


        }else {
//            message += context.getString(R.string.entered_year_month_is_not_correct) + "\n";
        }
        return message;
    }



//    public String yearAndMonthFailedRason(Context context, EditText editTextYear, EditText editTextMonth) {
//        message = "";
//        if (!this.yearAndMonthFieldCheck(editTextYear,editTextMonth))
//            message += context.getString(R.string.entered_year_month_is_not_correct) + "\n";
//
//        return message;
//    }
}
