package ir.sajjadyosefi.android.xTubeless.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.Toolbar;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//import com.orm.SugarContext;
import com.readystatesoftware.systembartint.SystemBarTintManager;
//import com.vansuita.gaussianblur.GaussianBlur;


import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.Validation;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_TUBELESS_CHECK_INPUT_VALUES;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_TUBELESS_CONTENT_IS_COPIED;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_TUBELESS_OPERATION_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;


public class ContactUsActivity extends TubelessTransparentStatusBarActivity {

    //Bundle String list
    public static final String Type = "TYPE";
    public static final String Title = "Title";
    public static final String Text = "Text";
    public static final String Phone = "Phone";
    public static final String ID = "ID";

    //Create New Activity List                    // code    id
    public static final int SUGGESTION = 48;   //51
    public static final int ORDER_APP = 49;   //52
    public static final int CONTACT_US = 50;   //53
    public static final int CONTENT_REPORT = 51;   //54
    public static final int CREATOR_REQUEST = 52;
    public static int messageType = 0;


    Context context;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;

    EditText editTextTitle, editTextText, editTextPhone;
    Button buttonReg, buttonBack;
    ContactUsRequest message = new ContactUsRequest();


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context, new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1", "value1");
        Intent intent = new Intent(context, ContactUsActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
//        SugarContext.init(this);



        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        boolean hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME);

        if (hasBackKey && hasHomeKey) {
            // no navigation bar, unless it is enabled in the settings
        } else {
            // 99% sure there's a navigation bar
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                Window w = getWindow();
//                w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            }
        }

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout._activity_contact_us);


        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextText = (EditText) findViewById(R.id.editTextText);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        buttonReg = (Button) findViewById(R.id.buttonReg);
        buttonBack = (Button) findViewById(R.id.buttonBack);


        if (Global.user2 != null) {
            editTextPhone.setVisibility(View.GONE);
        } else {
            editTextPhone.setVisibility(View.VISIBLE);
        }

        if (BuildConfig.FLAVOR.equals("yafte") || BuildConfig.FLAVOR.equals("yadak")) {
            radioButton5.setVisibility(View.GONE);
        }

        //getType
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (intent.hasExtra((Type))) {
            switch (bundle.getInt(Type)) {
                case CONTACT_US: {
                    messageType = CONTACT_US;
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    break;
                }
                case ORDER_APP: {
                    messageType = ORDER_APP;
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(true);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    break;
                }
                case SUGGESTION: {
                    messageType = SUGGESTION;
                    radioButton1.setChecked(true);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    break;
                }

                case CREATOR_REQUEST: {
                    messageType = CREATOR_REQUEST;
                    radioButton1.setChecked(false);
                    radioButton1.setEnabled(false);
                    radioButton2.setChecked(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setChecked(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setChecked(false);
                    radioButton4.setEnabled(false);
                    radioButton5.setChecked(true);
                    radioButton5.setEnabled(true);
                    editTextTitle.setText(bundle.getString(Title));
                    editTextTitle.setEnabled(false);
                    editTextPhone.setText(bundle.getString(Phone));
                    //message.setDetailId(bundle.getString(ID));
                    editTextPhone.setVisibility(View.GONE);
                    editTextText.setText(bundle.getString(Text));
                    break;
                }

                case CONTENT_REPORT: {
                    messageType = CONTENT_REPORT;
                    radioButton1.setChecked(false);
                    radioButton1.setEnabled(false);
                    radioButton2.setChecked(false);
                    radioButton2.setEnabled(false);
                    radioButton3.setChecked(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setChecked(true);
                    radioButton5.setChecked(false);
                    radioButton5.setEnabled(false);
                    editTextTitle.setText(bundle.getString(Title));
                    editTextTitle.setEnabled(false);
                    editTextPhone.setText(bundle.getString(Phone));
                    //message.setDetailId(bundle.getString(ID));
                    editTextPhone.setVisibility(View.GONE);
                    editTextText.setText(bundle.getString(Text));
                    break;
                }
            }
        }

        //get Checked Radio
        ((RadioGroup) findViewById(R.id.rgRadios)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
//                    case R.id.radioButton1:
//                        radioButton1.setChecked(true);
//                        radioButton2.setChecked(false);
//                        radioButton3.setChecked(false);
//                        radioButton4.setChecked(false);
//                        radioButton5.setChecked(false);
//                        messageType = SUGGESTION;
//                        break;
//                    case R.id.radioButton2:
//                        radioButton1.setChecked(false);
//                        radioButton2.setChecked(true);
//                        radioButton3.setChecked(false);
//                        radioButton4.setChecked(false);
//                        radioButton5.setChecked(false);
//                        messageType = ORDER_APP;
//                        break;
//                    case R.id.radioButton3:
//                        radioButton1.setChecked(false);
//                        radioButton2.setChecked(false);
//                        radioButton3.setChecked(true);
//                        radioButton4.setChecked(false);
//                        radioButton5.setChecked(false);
//                        messageType = CONTACT_US;
//                        break;
//
//                    case R.id.radioButton4:
//                        radioButton1.setChecked(false);
//                        radioButton2.setChecked(false);
//                        radioButton3.setChecked(false);
//                        radioButton4.setChecked(true);
//                        radioButton5.setChecked(false);
//                        messageType = CONTENT_REPORT;
//                        break;
//                    case R.id.radioButton5:
//                        radioButton1.setChecked(false);
//                        radioButton2.setChecked(false);
//                        radioButton3.setChecked(false);
//                        radioButton4.setChecked(false);
//                        radioButton5.setChecked(true);
//                        messageType = CREATOR_REQUEST;
//                        break;


                }
            }
        });


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final StringBuilder[] stringBuilderMeta = {new StringBuilder()};
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isValid = true;
                stringBuilderMeta[0] = new StringBuilder();
                stringBuilderMeta[0].append("MDV:1");
            stringBuilderMeta[0].append("|");

                if (Global.user2 == null) {
                    if (messageType != CONTENT_REPORT) {
                        if (Validation.validatePhoneNumber(editTextPhone.getText().toString())) {
                            message.setPhoneNumber(editTextPhone.getText().toString());
                        } else {
                            isValid = false;
                        }
                    } else if (editTextPhone.getVisibility() == View.VISIBLE) {
                        if (Validation.validatePhoneNumber(editTextPhone.getText().toString())) {
                            message.setPhoneNumber(editTextPhone.getText().toString());
                        } else {
                            isValid = false;
                        }
                    }
                } else {
                    stringBuilderMeta[0].append("user logged in by :");
                    stringBuilderMeta[0].append(Global.user2.getUserCodeAsString());
                    stringBuilderMeta[0].append("|");
                    message.setSenderUserID(Global.user2.getUserCodeAsString());
                }

                message.setPhoneNumber(editTextPhone.getText().toString());
                stringBuilderMeta[0].append("user mobile number :");
                stringBuilderMeta[0].append(editTextPhone.getText().toString());
                stringBuilderMeta[0].append("|");


                if (intent.hasExtra((Title))) {
                    if (bundle.getString(Title).contains("محتوی نامناسب")) {


                    } else {
                        if (editTextTitle.getText().toString().length() < 5) {
                            isValid = false;
                        }
                    }
                } else {
                    if (editTextTitle.getText().toString().length() < 5) {
                        isValid = false;

                    }
                }

                if (editTextText.getText().toString().length() < 15) {
                    isValid = false;

                }


                if (isValid) {
                    switch (messageType) {
                        case CONTACT_US: {
                            message.setTitle(radioButton3.getText().toString());
                            break;
                        }
                        case ORDER_APP: {
                            message.setTitle(radioButton2.getText().toString());
                            break;
                        }
                        case SUGGESTION: {
                            message.setTitle(radioButton1.getText().toString());
                            break;
                        }
                        case CONTENT_REPORT: {
                            message.setTitle(radioButton4.getText().toString());
                            break;
                        }
                        case CREATOR_REQUEST: {
                            message.setTitle(radioButton5.getText().toString());
                        }
                    }
                    stringBuilderMeta[0].append("message type :" + messageType);
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append("Devcie id :" + DeviceUtil.GetAndroidId(context));
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append("message title:\n" + message.getTitle());
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append("edit text Title : \n");
                    stringBuilderMeta[0].append(editTextTitle.getText().toString());
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append("User Ip : ");
                    stringBuilderMeta[0].append(DeviceUtil.GetIP(context));
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append("Message :");
                    stringBuilderMeta[0].append("|");
                    stringBuilderMeta[0].append(editTextText.getText().toString() );


                    message.setTitle(message.getTitle() + "|" + editTextTitle.getText().toString());
                    message.setTypeCode(messageType + "");
                    message.setMetaData(stringBuilderMeta[0].toString());

                    if (message.getText() != null && message.getText().toString().contains(editTextText.getText().toString())) {
                        new TubelessException().handleServerMessage(context, new TubelessException(ERR_CODE_TUBELESS_CONTENT_IS_COPIED));
                    } else {
                        message.setText(editTextText.getText().toString() + "\n\n\n" + message.getPhoneNumber());
                        sendMessage(message);
                    }
                } else {
                    new TubelessException().handleServerMessage(context, new TubelessException(ERR_CODE_TUBELESS_CHECK_INPUT_VALUES));
                }
            }
        });


        if (intent.hasExtra((Title))) {
            if (bundle.getString(Title).contains("محتوی نامناسب") || bundle.getString(Title).contains("درخواست ثبت مزایده") ) {
                editTextTitle.setText(bundle.getString(Title));
                editTextTitle.setEnabled(false);
                radioButton1.setEnabled(false);
                radioButton2.setEnabled(false);
            } else {
                editTextTitle.setEnabled(true);
            }
        }
    }

    private void sendMessage(ContactUsRequest request) {
        ((TubelessActivity) getContext()).progressDialog.show();
        Global.apiManagerTubeless.newContactUs(request, new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity) getContext()).progressDialog.hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                request.setText("");
                new TubelessException().handleServerMessage(getContext(), new TubelessException(ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                request.setText("");
                new TubelessException().handleServerMessage(getContext(), new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                editTextText.setText("");
                new TubelessException().handleServerMessage(getContext(), new TubelessException(ERR_CODE_TUBELESS_OPERATION_COMPLETE));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                svScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 200);
    }

    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

}
