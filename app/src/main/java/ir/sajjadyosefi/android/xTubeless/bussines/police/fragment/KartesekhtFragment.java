package ir.sajjadyosefi.android.xTubeless.bussines.police.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

//import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
//import com.zarinpal.ewallets.purchase.PaymentRequest;
//import com.zarinpal.ewallets.purchase.ZarinPal;

import ir.sajjadyosefi.android.xTubeless.Adapter.SpinnerAdapterA;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class KartesekhtFragment extends Fragment {
    public static Context mContext;
    private static EditText editText1 ;
    private static String editText1Value = null;
    private static EditText editText2 ;
    private static String editText2Value = null;
    private static EditText editText3 ;
    private static String editText3Value = null;
    private static String editTextXValue ;
    private Button button ,buttonContactUS;
    Spinner listView;
    String[] countryNames = {"--", "الف", "ب", "ت", "ج", "د", "س", "ص", "ط", "ع", "ق", "ل", "م", "ن", "و", "ه", "ي", "ک", "ژ"};
    private static int selectedChar ;

    public KartesekhtFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_kart_sokht, container, false);
        super.onCreate(savedInstanceState);


        mContext = getContext();


        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        editText3 = (EditText) view.findViewById(R.id.editText3);
        button = (Button) view.findViewById(R.id.button);
        listView = (Spinner) view.findViewById(R.id.spinner);



        final SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(mContext,countryNames);
        listView.setAdapter(spinnerAdapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedChar = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedChar = 0;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                ((TubelessActivity)mContext).progressDialog.show();

                saveFields();
                if (validDataLevelOne()) {
                    CheckFilter(view);
                }else {
                    Toast.makeText(mContext,"اطلاعات را به درستی وارد کنید",Toast.LENGTH_LONG).show();
                    ((TubelessActivity)mContext).progressDialog.hide();
                    button.setEnabled(true);
                }
            }
        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                payment(1000);
//            }
//        });

// then just handle callbacks

        return view;
    }


    private boolean validDataLevelOne() {
        boolean isValid = true;

        if (selectedChar != 0){
            if (editText1.getText().toString().length() == 2){
                try {
                    new Integer(Integer.parseInt(editText1.getText().toString()));

                    if (editText2.getText().toString().length() == 3){
                        try {
                            new Integer(Integer.parseInt(editText2.getText().toString()));
                            if (editText3.getText().toString().length() == 2){
                                try {
                                    new Integer(Integer.parseInt(editText3.getText().toString()));
                                    isValid = true;

                                }catch (Exception e){
                                    isValid = false;
                                }
                            }

                        }catch (Exception e){
                            isValid = false;
                        }
                    }

                }catch (Exception e){
                    isValid = false;
                }
            }else {
                isValid = false;
            }
        }


        if(editText1Value.length() != 2){
            isValid = false;
        }else {
            try {
                Integer.parseInt(editText1Value);
            }catch (Exception e){
                isValid = false;
            }
        }

        if(editText2Value.length() != 3){
            isValid = false;
        }else {
            try {
                Integer.parseInt(editText2Value);
            }catch (Exception e){
                isValid = false;
            }
        }
        if(editText3Value.length() != 2){
            isValid = false;
        }else {
            try {
                Integer.parseInt(editText3Value);
            }catch (Exception e){
                isValid = false;
            }
        }

        if(listView.getSelectedItemPosition() == 0){
            isValid = false;
        }
        return isValid;
    }

    private void saveFields() {
        editText1Value = editText1.getText().toString();
        editText2Value = editText2.getText().toString();
        editText3Value = editText3.getText().toString();
        editTextXValue = listView.getSelectedItemPosition() + "";
    }
    private void loadFields() {
        editText1.setText(editText1Value);
        editText2.setText(editText2Value);
        editText3.setText(editText3Value);
        listView.setSelection(Integer.parseInt(editTextXValue));
    }



    public static boolean error = false;

    private void CheckFilter(View view) {

        Global.retrofitHelperEpolice.callPelackservice("platesearch","1", "1","66" +  "03" + "25"  + "566" , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body().contains("خارج از کشور")) {
                    Toast.makeText(mContext,"در صورتی که از فیلتر شکن استفاده می کنید آن را خاموش کنید.",Toast.LENGTH_SHORT).show();
                }else {
                    button.setEnabled(false);
//                    Global.apiManagerTubeless.config(null,new Callback<Configuration>() {
//                        @Override
//                        public void onResponse(Call<Configuration> call, Response<Configuration> response) {
//                            if (error == false) {
////                                if (checkResult(response)) {
////                                    ShowSelectSturceDialog(mContext,response.body().getConfiguration());
////                                } else {
////                                    callService(view);
////                                }
//                            } else {
////                                    callService();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Configuration> call, Throwable t) {
//                            ((TubelessActivity)mContext).progressDialog.hide();
//
//                            Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(mContext,"اینترنت خود را چک کنید",Toast.LENGTH_SHORT).show();
//                            error = true;
//                            if (button != null)
//                                button.setEnabled(true);
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ((TubelessActivity)mContext).progressDialog.hide();

                Toast.makeText(mContext,"فیلتر شکن تان را خاموش کنید",Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"دوباره تلاش کنید",Toast.LENGTH_SHORT).show();

                if (button != null)
                    button.setEnabled(true);
            }
        });
    }

    public static boolean cancelByBackbuttonPressed = true;

    @Override
    public void onResume() {
        super.onResume();
        if (editText1Value != null){
            loadFields();
        }

        if (cancelByBackbuttonPressed){
            ((TubelessActivity)getContext()).progressDialog.hide();

            if (button != null)
                button.setEnabled(true);
        }
    }

    private boolean checkResult(Response<Configuration> response)  {
        Boolean aBoolean = false;

        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String versionName = pInfo.versionName;
//            if (versionName.contains("a")) {
//                aBoolean = response.body().getConfiguration().play;
//
//            } else if (versionName.contains("s")) {
//                aBoolean = response.body().getConfiguration().social;
//            } else if (versionName.contains("b")) {
//                aBoolean = response.body().getConfiguration().bazaar;
//            } else if (versionName.contains("m")) {
//                aBoolean = response.body().getConfiguration().myket;
//
//            } else if (versionName.contains("i")) {
//                aBoolean = response.body().getConfiguration().iranapps;
//
//            } else if (versionName.contains("j")) {
//                aBoolean = response.body().getConfiguration().jhobin;
//
//            } else if (versionName.contains("k")) {
//                aBoolean = response.body().getConfiguration().kandoo;
//
//            } else {
//                aBoolean = response.body().getConfiguration().play;
//            }

        }catch (Exception e){
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }

    public void callService(View view) {
        ((TubelessActivity) mContext).progressDialog.show();

        Global.retrofitHelperEpolice.callPelackservice("platesearch", "1", "1", editText1Value + (Integer.parseInt(editTextXValue) <= 9 ? "0" + editTextXValue : editTextXValue) + editText3Value + editText2Value, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ((TubelessActivity) mContext).progressDialog.hide();
                error = false;

//                response.body()

                showManyResultDialog(view, response.body());


                editText1Value = null;
                editText2Value = null;
                editText3Value = null;
                editTextXValue = null;

                if (button != null)
                    button.setEnabled(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ((TubelessActivity) mContext).progressDialog.hide();

                Toast.makeText(mContext, "عملیات با خطا مواجه شد - درصورت روشن بودن فیلتر شکن آن را خاموش کنید", Toast.LENGTH_LONG).show();
                Toast.makeText(mContext, "دوباره تلاش کنید", Toast.LENGTH_SHORT).show();

                if (button != null)
                    button.setEnabled(true);
            }
        });
    }

    private void showManyResultDialog(View view, String responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = view.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        buttonCancel.setText("باشه");
        buttonOk.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewStatment.setText(Html.fromHtml(responseX, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textViewStatment.setText(Html.fromHtml(responseX));
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
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

            }
        });
        alertDialog.show();
    }



    public void ShowSelectSturceDialog(final Context context, ConfigurationObject configuration){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.pay_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
//        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(true);

        final Button btn1 = (Button) dialoglayout.findViewById(R.id.button1);
        final TextView textView = (TextView) dialoglayout.findViewById(R.id.textView1);
        final Button btn10 = (Button) dialoglayout.findViewById(R.id.button10);
        btn1.setText(String.format("پرداخت"));
//        textView.setText(String.format("هزینه هر بار استعلام %s تومان می باشد.\n\n%s" , configuration.oneTime, mContext.getString(R.string.paySecoundMessage)));
//        btn2.setText(btn2Text);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                payment(configuration.getOneTime());
                alertDialog1.dismiss();
                cancelByBackbuttonPressed = true;
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                payment(configuration.getOneTime());
                alertDialog1.dismiss();
                cancelByBackbuttonPressed = true;
            }
        });

        //titile
//        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.textView1);
//        textViewTitle.setText("پرداخت");

        //text
//        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
//        textViewText.setText(text);
        alertDialog1.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                ((TubelessActivity)mContext).progressDialog.hide();
            }
        });
        alertDialog1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((TubelessActivity)mContext).progressDialog.hide();
            }
        });


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void payment(long amount) {
//        ZarinPal purches = ZarinPal.getPurchase(mContext);
//        PaymentRequest payment = ZarinPal.getPaymentRequest();
//
//        payment.setMerchantID("e8a913e8-f089-11e6-8dec-005056a205be");
//        payment.setAmount(amount);
//        payment.setDescription(getString(R.string.paymenttitle));
//        payment.setCallbackURL(String.format("%s://%s",getString(R.string.schemezarinpalpayment),getString(R.string.zarinpalpayment)));
//
//        MainActivity.payType = 100 ;
//
//        purches.startPayment(payment, new OnCallbackRequestPaymentListener() {
//            @Override
//            public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {
//
//                if (status == 100){
//                    //ok
//                    startActivity(intent);
//                }else {
//                    //error in payment
//                    ((TubelessActivity)mContext).progressDialog.hide();
//                }
//
//            }
//        });

    }



}