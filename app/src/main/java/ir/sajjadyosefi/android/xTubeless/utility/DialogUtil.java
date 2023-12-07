package ir.sajjadyosefi.android.xTubeless.utility;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.profile.ImagePresenter;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.ServerResponse;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.activity.account.profile.MainActivityProfile.PERMISSION_REQUEST_GALLERY_PHOTO;
import static ir.sajjadyosefi.android.xTubeless.activity.account.profile.MainActivityProfile.PERMISSION_REQUEST_TAKE_PHOTO;

public class DialogUtil {

    public static void showConnectionLostFullScreenDialog(Context context, final ProgressBar progressBar, final Runnable runnable) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        TubelessException.ShowSheetFullScreenDialog(context,dialog ,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(runnable,5);
                dialog.dismiss();
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
//    public static void showConnectionLostFullScreenDialog(Context context, final ProgressBar progressBar, final Runnable runnable) {
//        final BottomSheetDialog dialog = new BottomSheetDialog(context);
//        TubelessException.ShowSheetFullScreenDialog(context,dialog ,new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Handler().postDelayed(runnable,5);
//                dialog.dismiss();
//                if (progressBar != null)
//                    progressBar.setVisibility(View.VISIBLE);
//            }
//        });
//    }

    public static void showConnectionLostDialog(Context context, final ProgressBar progressBar, final Runnable runnable) {
        if (context != null) {
            final BottomSheetDialog dialog = new BottomSheetDialog(context);
            TubelessException.ShowSheetDialog(context, dialog, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Handler().postDelayed(runnable, 5);
                    dialog.dismiss();
                    if (progressBar != null)
                        progressBar.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void modal2(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.a_b_bottom_sheet_dialog_delete2, null);
        dialog.setContentView(view);



        final TextView buttonDelete = view.findViewById(R.id.textViewDelete);
//        final TextView textViewChangeToDiscountCard = view.findViewById(R.id.textViewChangeToDiscountCard);
        final View textViewChangeToDiscountCardHr = view.findViewById(R.id.textViewChangeToDiscountCardHr);
//        final TextView textViewDiscountCenters = view.findViewById(R.id.textViewDiscountCenters);

//        textViewDiscountCenters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.sep.ir/searchStore"));
////                            intent.setPackage("com.cloudacl");  // package of SafeBrowser App
//                ((Activity)mContext).startActivity(intent);
//
//            }
//        });

//        if (bankCard.getDiscountRequestStatusID() == 1 || bankCard.getDiscountRequestStatusID() == 0) {
//            textViewChangeToDiscountCard.setVisibility(View.GONE);
//            textViewChangeToDiscountCardHr.setVisibility(View.GONE);
//        }else {
//            textViewChangeToDiscountCard.setVisibility(View.VISIBLE);
//            textViewChangeToDiscountCardHr.setVisibility(View.VISIBLE);
//
//            textViewChangeToDiscountCard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, DiscountCardRequestActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("CardNo", bankCard.getCardNo());
//                    intent.putExtras(bundle);
//                    dialog.dismiss();
//                    mContext.startActivity(intent);
//                }
//            });
//        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void modal1(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.a_b_bottom_sheet_dialog_delete2, null);
        dialog.setContentView(view);



        final TextView buttonDelete = view.findViewById(R.id.textViewDelete);
//        final TextView textViewChangeToDiscountCard = view.findViewById(R.id.textViewChangeToDiscountCard);
        final View textViewChangeToDiscountCardHr = view.findViewById(R.id.textViewChangeToDiscountCardHr);
//        final TextView textViewDiscountCenters = view.findViewById(R.id.textViewDiscountCenters);

//        textViewDiscountCenters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.sep.ir/searchStore"));
////                            intent.setPackage("com.cloudacl");  // package of SafeBrowser App
//                ((Activity)mContext).startActivity(intent);
//
//            }
//        });

//        if (bankCard.getDiscountRequestStatusID() == 1 || bankCard.getDiscountRequestStatusID() == 0) {
//            textViewChangeToDiscountCard.setVisibility(View.GONE);
//            textViewChangeToDiscountCardHr.setVisibility(View.GONE);
//        }else {
//            textViewChangeToDiscountCard.setVisibility(View.VISIBLE);
//            textViewChangeToDiscountCardHr.setVisibility(View.VISIBLE);
//
//            textViewChangeToDiscountCard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, DiscountCardRequestActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("CardNo", bankCard.getCardNo());
//                    intent.putExtras(bundle);
//                    dialog.dismiss();
//                    mContext.startActivity(intent);
//                }
//            });
//        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void ShowMessageDialog(final Context context, String title, String text ){
        ShowMessageDialog(context, title, text , null);
    }
    public static void ShowMessageDialog(final Context context, String title, String text ,boolean isGallery){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


        final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button okBtn = (Button) dialoglayout.findViewById(R.id.buttonOk);
        okBtn.setVisibility(View.GONE);

        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.title);
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textViewStatment);
        textViewText.setText(text);
        textViewTitle.setText(title);
        cancelBtn.setText(context.getString(R.string.ok));


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGallery) {
                    ActivityCompat.requestPermissions(
                            ((Activity)context),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_GALLERY_PHOTO);
                }else {
                    ActivityCompat.requestPermissions(
                            ((Activity)context),
                            new String[]{
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_TAKE_PHOTO);
                }
                alertDialog1.dismiss();
            }
        });


        try {
            alertDialog1.show();
        }catch (Exception ex){

        }

    }

    public static void ShowMessageDialog(final Context context, String title, String text, View.OnClickListener clickListner){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


        final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button okBtn = (Button) dialoglayout.findViewById(R.id.buttonOk);
        okBtn.setVisibility(View.GONE);

        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.title);
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textViewStatment);
        textViewText.setText(text);
        textViewTitle.setText(title);
        cancelBtn.setText(context.getString(R.string.ok));

        if(clickListner != null){
//            cancelBtn.setVisibility(View.INVISIBLE);
//            alertDialog1.setCancelable(false);
            cancelBtn.setOnClickListener(clickListner);
        } else {
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog1.cancel();
                }
            });
        }


        try {
            alertDialog1.show();
        }catch (Exception ex){

        }
    }

    public static void ShowSelectSturceDialog(final Context context,String title,String text,String btn1Text,View.OnClickListener onClickListener1,String btn2Text,View.OnClickListener onClickListener2){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(false);

        final Button btn1 = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button btn2 = (Button) dialoglayout.findViewById(R.id.buttonOk);
        btn1.setText(btn1Text);
        btn2.setText(btn2Text);
        btn1.setOnClickListener(onClickListener1);
        btn2.setOnClickListener(onClickListener2);

        //titile
        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.title);
        textViewTitle.setText(title);


        //text
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textViewStatment);
        textViewText.setText(text);


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public static void showNotAnyResultDialog(Context context, View view, final ServerResponse responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup;
        viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        textViewStatment.setText(responseX.getMessage());

        //Now we need an AlertDialog.Builder object
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final android.app.AlertDialog alertDialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                goToResult(context,responseX);
            }
        });
        alertDialog.show();
    }

    public static void showManyResultDialog(Context context, View view, final ServerResponse responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        textViewStatment.setText(responseX.getMessage());



        //Now we need an AlertDialog.Builder object
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        android.app.AlertDialog alertDialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                goToResult(context,responseX);
            }
        });
        alertDialog.show();
    }

    public static void goToResult(Context context, ServerResponse responseX) {
        Bundle bundle = new Bundle();
        bundle.putInt("type" , FRAGMENT_POST_SEARCH_RESULT);
        bundle.putSerializable("LIST", (Serializable) responseX.getData());
        ((Activity)context).startActivity(ContainerActivity.getIntent(context,bundle));
    }


    public static void SelectSource(Context context, ViewGroup viewGroup, ImagePresenter mImagePresenter) {

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        TextView title = dialogView.findViewById(R.id.title);

        title.setText(context.getString(R.string.avatar_picture));
        textViewStatment.setText(context.getString(R.string.select_image_source));
        buttonOk.setText(context.getString(R.string.camera));
        buttonCancel.setText(context.getString(R.string.gallery));

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final android.app.AlertDialog alertDialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                mImagePresenter.chooseGalleryClick();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                mImagePresenter.cameraClick();
            }
        });
        alertDialog.show();
    }

    public static ProgressDialog mProgressDialog;
    public static void showLoadingDialog(Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.show();
        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }
    public static void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
