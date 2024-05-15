package ir.sajjadyosefi.android.xTubeless.classes.model.exception;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;


public class TubelessException extends Exception{

    public static final int ERR_CODE_TUBELESS_OPERATION_COMPLETE = 0;
    public static final int ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE = 10;
    public static final int TUBELESS_TRY_AGAIN = 11;
    public static final int ERR_CODE_TUBELESS_CHECK_INPUT_VALUES = 12;
    public static final int ERR_CODE_TUBELESS_CONTENT_IS_COPIED = 13;
    public static final int ERR_CODE_TUBELESS_PASSWORD_NOT_CORRECT = 14;
    public static final int ERR_CODE_TUBELESS_PASSWORD_IS_EMPTY = 15;
    public static final int TUBELESS_USERNAME_IS_EMPTY = 16;



    public static final int ERR_CODE_TUBELESS_RESPONSE_BODY_IS_NULL = 2001;
    public static final int ERR_CODE_TUBELESS_DATABASE_ERROR = 2002;
    public static final int ERR_CODE_DEVICE_NOT_REGISTER = 2004;
    public static final int ERR_CODE_UNKNOWN = 2005;


    public static final int ERR_CODE_NATIONAL_CODE_NOT_TRUE = 1001;
    public static final int NAME_NOT_TRUE = 1002;
    public static final int FAMILY_NOT_TRUE = 1003;
    public static final int FATHER_NOT_TRUE = 1004;


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private String message;
    private int code;



    public TubelessException() {

    }

    @Override
    public void printStackTrace() {
//        super.printStackTrace();
//        System.out.println(getMessage());
//        Log.w("myApp", getMessage());
        Log.e("tYafte", getMessage());
    }



    public TubelessException(int errorCode) {
        new TubelessException(errorCode ,null);
    }
    public TubelessException(int errorCode,String _message) {
        switch (errorCode){
            case ERR_CODE_NATIONAL_CODE_NOT_TRUE:{
                message = "sajjad Error : National Code Not true.";
                break;
            }

            case ERR_CODE_TUBELESS_DATABASE_ERROR:{
                message = "saajjad Error : CRUD database Error";
                break;
            }
            case ERR_CODE_TUBELESS_RESPONSE_BODY_IS_NULL:{
                message = "sajjad Error : we get null in body of response";
                break;
            }
            case ERR_CODE_TUBELESS_OPERATION_COMPLETE:{
                message = "با موفقیت انجام شد.";
                break;
            }
            case ERR_CODE_TUBELESS_CHECK_INPUT_VALUES:{
                message = "مقادیر وارد شده صحیح نیست.";
                break;
            }
            case ERR_CODE_TUBELESS_CONTENT_IS_COPIED:{
                message = "قبلا این پیام را ارسال کرده اید.";
                break;
            }
            case ERR_CODE_TUBELESS_PASSWORD_NOT_CORRECT:{
                message = "رمز عبور صحیح نیست";
                break;
            }
            case ERR_CODE_TUBELESS_PASSWORD_IS_EMPTY:{
                message = "رمز عبور کوتاه است.";
                break;
            }
            case ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE:{
                message = "انجام نشد.";
                break;
            }

            case ERR_CODE_DEVICE_NOT_REGISTER:{
                message = "دیتایی دریافت نشد.";
                break;
            }

            case ERR_CODE_UNKNOWN:{
                message = _message;
                break;
            }

            default:{
                message = "اینترنت شما متصل نیست";
            }
        }
        code = errorCode;
    }

    public void handleServerMessage(Context mContext , ServerResponseBase responseX) {
        try {
//            assert rootView == null;
//            assert mContext == null;

            int resID = mContext.getResources().getIdentifier("error_message_" + (-1 * responseX.getTubelessException().getCode()), "string", mContext.getPackageName());
            if (resID == 0) {
                //Snackbar.make(rootView, mContext.getString(R.string.error) + responseX.getTubelessException().getCode(), Snackbar.LENGTH_SHORT).show();

                View.OnClickListener snackOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,"ssss",Toast.LENGTH_SHORT).show();
                    }
                };
                Toast.makeText(mContext,"یک خطای پیش بینی نشده",Toast.LENGTH_SHORT).show();
//                Snackbar
//                        .make(rootView, "یک خطای پیش بینی نشده", 8000)
//                        //.setAction("Yes", snackOnClickListener)
//                        .setActionTextColor(Color.MAGENTA)
//                        .show();
            }else {
//                Snackbar.make(rootView, resID, Snackbar.LENGTH_SHORT).show();
                Toast.makeText(mContext,resID,Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        if (responseX != null) {
            handleServerMessage(mContext,responseX.getTubelessException().getCode());
        }else {
//            Toast.makeText(context, context.getResources().getString(R.string.server_not_response), Toast.LENGTH_SHORT).show();

            Toast.makeText(mContext, mContext.getResources().getString(R.string.server_not_response),Toast.LENGTH_SHORT).show();

//            if (rootView != null) {
//                Snackbar.make(rootView, mContext.getResources().getString(R.string.server_not_response), Snackbar.LENGTH_SHORT).show();
//            }
        }
    }

    public static void handleServerMessage(Context mContext, int code) {
        try {
            int resID = mContext.getResources().getIdentifier("error_message_" + code, "string", mContext.getPackageName());
//            Snackbar.make(view, resID, Snackbar.LENGTH_SHORT).show();
            Toast.makeText(mContext, mContext.getResources().getString(resID),Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void handleClientMessage(Context mContext, int code) {
//        if (mContext != null) {
//            SamanToast samanToast = null;
//            samanToast = new SamanToast(mContext,mContext.getString(R.string.error_text_entered_valuse_not_valid), SamanToast.ERROR);
//            samanToast.show();
//        }

        try {
            int resID = mContext.getResources().getIdentifier("error_message_" + code, "string", mContext.getPackageName());
//            Snackbar.make(view, resID, Snackbar.LENGTH_SHORT).show();
            Toast.makeText(mContext, mContext.getResources().getString(resID),Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    public void handleMessage(Context mContext, ServerResponse response) {
//        try {
//            int resID = mContext.getResources().getIdentifier("error_message_" + response.getException().getCode() , "string", mContext.getPackageName());
//            Toast.makeText(mContext, resID, Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void ShowSheetDialog(Context context, BottomSheetDialog dialog, View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

            int aaaaa = 5;
            aaaaa++;
        }
    }
    public static void ShowSheetFullScreenDialog(Context context, BottomSheetDialog dialog, View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.full, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){
            int aaaaa = 5;
            aaaaa++;
        }
    }

    public static void ShowSheetDialogMessage(Context context, BottomSheetDialog dialog, String message , View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            TextView buttonMessage = view.findViewById(R.id.textView);
            buttonMessage.setText(message);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

        }
    }

    public static void ShowSheetDialogMessage(Context context, BottomSheetDialog dialog, String message , String ok, View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            TextView buttonMessage = view.findViewById(R.id.textView);
            buttonMessage.setText(message);
            buttonTryAgain.setText(ok);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

        }

    }


    public void handleServerMessage(Context context, Throwable t) {
        final BottomSheetDialog progressDialog = new BottomSheetDialog(context);
        TubelessException.ShowSheetDialogMessage(context, progressDialog, t.getMessage() , "ok" , new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.dismiss();
                if (((TubelessException)t).code == ERR_CODE_TUBELESS_OPERATION_COMPLETE)
                    ((Activity)context).finish();
            }
        });
    }
}