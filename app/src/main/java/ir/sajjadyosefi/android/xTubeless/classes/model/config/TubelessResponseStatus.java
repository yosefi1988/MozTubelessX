package ir.sajjadyosefi.android.xTubeless.classes.model.config;


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


public class TubelessResponseStatus extends Exception{
    private String Message;
    private int Code;
    private String date;


    @Override
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }






}