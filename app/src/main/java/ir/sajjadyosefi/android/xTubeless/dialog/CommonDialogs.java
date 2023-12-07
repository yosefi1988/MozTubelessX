package ir.sajjadyosefi.android.xTubeless.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import ir.sajjadyosefi.accountauthenticator.activity.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ATransactionApp;
import ir.sajjadyosefi.android.xTubeless.utility.AppUtility;

public class CommonDialogs {

    public static void modal(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);
        dialog.setContentView(view);

        final Button buttonAccept = view.findViewById(R.id.accept);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                AppUtility.restartApplication(mContext);
            }
        });
        dialog.show();
    }


    public static void modalAmountConfirm(Context mContext, int amount,View.OnClickListener onclick){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);

        final Button buttonAccept = view.findViewById(R.id.accept);
        final TextView textView = view.findViewById(R.id.textView_main);
        textView.setText(String.format(mContext.getString(R.string.AmountModalMainText),String.valueOf(amount)));
        buttonAccept.setOnClickListener(onclick);
        dialog.show();
    }



    public static void modal2(Context mContext,View.OnClickListener onclick){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_logout, null);
        dialog.setContentView(view);

        final Button buttonAccept = view.findViewById(R.id.accept);
        final Button buttonCancel = view.findViewById(R.id.cancel);
        buttonAccept.setOnClickListener(onclick);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
