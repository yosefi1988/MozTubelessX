package ir.sajjadyosefi.android.xTubeless.activity.register;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.model.AWallet;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessListAmountsAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Adapter.SpinnerAdapterA;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;

import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;
 
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewPostResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineItemResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.dialog.SubStractDialogClass;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.service.FileUploadService;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.file.UriUtil;
import ir.sajjadyosefi.android.xTubeless.widget.samanPersianDatePicker.PersianDatePicker;
import ir.sajjadyosefi.android.xTubeless.widget.samanPersianDatePicker.util.PersianCalendar2;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessListAmountsAdapter.SUBSCRIPTIONS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition2;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_MULTY_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.isFreeStore;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity.CALL_AGAIN;
import static ir.sajjadyosefi.android.xTubeless.activity.register.PrePaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity.REQUEST_CATEGORY_LIST;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.CATEGORY_ID;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;
import static ir.sajjadyosefi.android.xTubeless.dialog.SubStractDialogClass.subscribeItem;
import static ir.sajjadyosefi.android.xTubeless.utility.DateTime.SamanDateTime.getGeorgianCalendar;


public class RegNewPostActivity extends TubelessTransparentStatusBarActivity {


    public Button buttonReg,  buttonBack ,buttonAddFiles ,buttonSelectCategory;
    EditText editTextText, editTextTitle, editTextAmount;
    TextView editTextDate,login_title,titleTextView,editTextTitleExample;
    LinearLayout linearLayoutAmount;
    RadioButton radioButton1, radioButton2 ,radioButton3;
    RadioGroup rgRadios;
    CheckBox checkbox;
    LinearLayout linearLayoutMoz;
    ScrollView sv;
    NewBlogRequest aaaa = new NewBlogRequest();
    static List<File> filesList;
    private int REQUEST_FILE_LIST = 525;

    public static int amountForRegNewPost_Toman = 1000;
    public static String amountForSeenNewPost = "5000";

    //state
    Spinner spinner;
    private static int selectedState;

    private int PAGE_TYPE = 0;
    public static final int MOZ = 1;
    public static final int ESTEKHDAM = 2;
    public static final int YADAK = 3;
    public static final int YAFTE = 4;
    public static final int AMLAK = 5;
    public static final int TUBELESS = 6;

    
    //default
    private final boolean defaultType = true;
    private final boolean defaultState = true;
    private final boolean defaultPrice = true;
    private final boolean defaultDate = true;
    private final boolean defaultText = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        filesList = new ArrayList<File>();
        File headerInList = new File();
        headerInList.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_HEADER);
        filesList.add(headerInList);

        File Emptylist = new File();
        Emptylist.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_EMPTY_LIST);
        filesList.add(Emptylist);
        setContentView(R.layout.activity_new_post);
        setRootActivity();
        buttonReg = findViewById(R.id.buttonReg);
        buttonBack = findViewById(R.id.buttonBack);
        linearLayoutAmount = findViewById(R.id.linearLayoutAmount);
        editTextText = findViewById(R.id.editTextText);
        editTextDate = findViewById(R.id.editTextDate);
        login_title = findViewById(R.id.login_title);
        titleTextView = findViewById(R.id.titleTextView);
        editTextTitleExample = findViewById(R.id.editTextTitleExample);
        buttonAddFiles = findViewById(R.id.buttonAddFiles);
        buttonSelectCategory = findViewById(R.id.buttonSelectCategory);


        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAmount = findViewById(R.id.editTextAmount);
        linearLayoutMoz = findViewById(R.id.linearLayout);
        rgRadios = findViewById(R.id.rgRadios);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        checkbox = findViewById(R.id.checkbox);
        spinner = findViewById(R.id.spinner);
        sv = (ScrollView) findViewById(R.id.scroll);


        if (BuildConfig.FLAVOR_version_name.equals("estekhdam")) {
            PAGE_TYPE = ESTEKHDAM;
            rgRadios.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR_version_name.equals("moz")) {
            PAGE_TYPE = MOZ;
        }
        if (BuildConfig.FLAVOR_version_name.equals("tubeless")) {
            PAGE_TYPE = TUBELESS;
            rgRadios.setVisibility(View.GONE);
            linearLayoutMoz.setVisibility(View.GONE);
            //((View)findViewById(R.id.linearLayoutImage)).setVisibility(View.GONE);
            ((View)findViewById(R.id.relativeLayoutState)).setVisibility(View.GONE);
            ((View)findViewById(R.id.editTextDate)).setVisibility(View.GONE);
            ((View)findViewById(R.id.textViewStateTitle)).setVisibility(View.GONE);
            ((View)findViewById(R.id.textViewDateTitle)).setVisibility(View.GONE);
            ((View)findViewById(R.id.textViewTitle2)).setVisibility(View.GONE);
            //((View)findViewById(R.id.editTextTitle)).setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
            PAGE_TYPE = YADAK;
            linearLayoutMoz.setVisibility(View.GONE);
            rgRadios.setVisibility(View.GONE);

        }
        if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
            PAGE_TYPE = YAFTE;
            radioButton3.setVisibility(View.VISIBLE);
            linearLayoutMoz.setVisibility(View.GONE);
            checkbox.setVisibility(View.GONE);

            editTextText.setHint( getContext().getString(R.string.yafte_hint));
        }

        if (BuildConfig.FLAVOR_version_name.equals("amlak")) {
            PAGE_TYPE = AMLAK;
            radioButton3.setVisibility(View.VISIBLE);
            linearLayoutMoz.setVisibility(View.GONE);
            editTextText.setHint( getContext().getString(R.string.yafte_hint));

            if (BuildConfig.FLAVOR_market.equals("bazzar") ) {
                if (!Global.user2.isUserAdmin()) {
                    linearLayoutAmount.setVisibility(View.INVISIBLE);
                }
            }
            rgRadios.setVisibility(View.GONE);
        }


        if(defaultType)
            radioButton1.setChecked(true);

        if(defaultPrice)
            editTextAmount.setText(amountForSeenNewPost);

        if (defaultText)
            if (Global.user2.isUserAdmin())
                editTextTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editTextText.setText(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }

                });



//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();


//        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);

// Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
//        });


//
//        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
//        intent.putExtra(ADD_ACCOUNT, true);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//                bundle.putParcelable(AccountManager.KEY_INTENT, intent);


        if (PAGE_TYPE == ESTEKHDAM){
//            ((RelativeLayout)findViewById(R.id.radioLayout)).setVisibility(View.GONE);
            login_title.setText(R.string.regNewEstekhdamTitle);
            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
            linearLayoutMoz.setVisibility(View.GONE);
        }
        if (PAGE_TYPE == YAFTE) {
            login_title.setText(R.string.regNewEstekhdamTitle);
        }
        if (PAGE_TYPE == YADAK){
            //5043
            login_title.setText(R.string.regNewEstekhdamTitle);
            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }
        if (PAGE_TYPE == MOZ){
            login_title.setText(R.string.regNewEstekhdamTitle);
            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }
        if (PAGE_TYPE == TUBELESS){
            login_title.setText(R.string.regNewEstekhdamTitle);
//            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }
        if (PAGE_TYPE == AMLAK){
            login_title.setText(R.string.regNewEstekhdamTitle);
//            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }

        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PAGE_TYPE == MOZ) {
                    if (b) {
                        linearLayoutMoz.setVisibility(View.VISIBLE);
                        buttonReg.setEnabled(true);
                    } else
                        linearLayoutMoz.setVisibility(View.GONE);
                }
            }
        });

        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buttonReg.setEnabled(true);
                if (PAGE_TYPE == MOZ) {
                    if (!b) {
                        linearLayoutMoz.setVisibility(View.VISIBLE);
                    } else
                        linearLayoutMoz.setVisibility(View.GONE);
                }
            }
        });

        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buttonReg.setEnabled(true);
                if (PAGE_TYPE == MOZ) {
                    if (!b) {
                        linearLayoutMoz.setVisibility(View.VISIBLE);
                    } else
                        linearLayoutMoz.setVisibility(View.GONE);
                }
            }
        });

        if (Global.user2.isUserAdmin()) {
            if (BuildConfig.FLAVOR_market.equals("bazzar")) {
                linearLayoutAmount.setVisibility(View.GONE);
            }else {
                linearLayoutAmount.setVisibility(View.VISIBLE);
            }
        } else {
            linearLayoutAmount.setVisibility(View.GONE);
        }
//        if (BuildConfig.FLAVOR_market.equals("bazzar")) {
//            ((View)findViewById(R.id.linearLayoutImage)).setVisibility(View.GONE);
//        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if (Global.user2.isUserAdmin()) {
            buttonAddFiles.setEnabled(true);
        }else {
            if (Global.user2.isUserCreator() && PAGE_TYPE == AMLAK) {
                buttonAddFiles.setEnabled(true);
            }else {
                buttonAddFiles.setEnabled(false);
            }
        }

        if (BuildConfig.FLAVOR_version_name.equals("yafte")){
            buttonSelectCategory.setVisibility(View.GONE);
        }
        buttonSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Activity CATEGORY
                Bundle bundle = new Bundle();
                if (Global.user2 != null && Global.user2.isUserAdmin())
                    bundle.putBoolean("MustRefresh", true);

                //bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                bundle.putInt("type" , FRAGMENT_CATEGORY);
                //bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT);
//                bundle.putInt("selectType" , LIST_CATEGORY_MULTY_SELECT);
                bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS);
                bundle.putInt("CAT_COUNT", 11);
                bundle.putString("item1","value1");
                bundle.putLong("category",CATEGORY_ID);
                bundle.putLong("parentId",0);
                Intent intent = new Intent(getContext(),ContainerActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(),bundle),REQUEST_CATEGORY_LIST);

            }
        });
        buttonAddFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (BuildConfig.FLAVOR_market.equals("bazzar")) {
//                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("FILE_COUNT", 4);
//                        bundle.putSerializable("LIST", (Serializable) filesList);
//                        getActivity().startActivityForResult(FileListActivity.getIntent(getContext(), bundle),REQUEST_FILE_LIST);
//                    }else {
////                    Toast.makeText(getContext(), "غیرفعال", Toast.LENGTH_LONG).show();
//                        Toast.makeText(getContext(), "برای استفاده از این ویژگی از فروشگاهی غیر از کافه بازار دانلود کنید", Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                if (Global.userFixxxxxxxxx != null && Global.userFixxxxxxxxx.isUserAdmin()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("FILE_COUNT", 4);
                    bundle.putSerializable("LIST", (Serializable) filesList);
                    getActivity().startActivityForResult(FileListActivity.getIntent(getContext(), bundle),REQUEST_FILE_LIST);
//                }else {
//                    Toast.makeText(getContext(),getContext().getString(R.string.notEnugh),Toast.LENGTH_LONG).show();
//                }

            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showModalBrithDate(getRootActivity());
            }
        });
        if (defaultDate){

        }

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if (
                        selectedState == 0 ||
                                editTextDate.getText().toString().length() < 5 ||
                                editTextText.getText().toString().length() < 5 ||
                                editTextTitle.getText().toString().length() < 5) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                } else if (Global.user2.isUserAdmin() ){
                    if (editTextAmount.getText().toString().length() < 2) {
                        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }else {
                        prepareRequest();
                    }
                } else {
                    //go to reg

//                if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                    if (PAGE_TYPE == YAFTE) {
                        if (radioButton3.isChecked()) {
                            modalPayType(getContext());
                        }else {
                            if (isFreeStore(getContext(), StaticValue.configuration)) {
                                prepareRequest();
                            }else {
                                modalPayType(getContext());
                            }
                        }
                    }else if (PAGE_TYPE == AMLAK) {
                        if (BuildConfig.FLAVOR_market.equals("bazzar") ) {
                            if (Global.user2.isUserAdmin()) {
                                modalPayType(getContext());
                            } else {
                                prepareRequest();
                            }
                        }else {
                            prepareRequest();
                        }
                    }else {
                        prepareRequest();
                    }
//                }

                }
            }
        });

        if (Global.user2 == null) {
//        if (Global.user2 == null || Global.user.getUserId() == NOT_LOGN_USER ){
            Bundle bundle = new Bundle();
            Intent intent = SignInActivity.getIntent(getContext(), bundle);
            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
        }

//        SendFileRequest ss = new SendFileRequest();
////            ss.setFileType("1");
//        ss.setFileType(((ir.sajjadyosefi.evaluation.model.business.File)files).getFileType() + "");
//        ss.setSenderType("1");
////            ss.setSerialRequestCode("9801000144");
//        ss.setSerialRequestCode(Global.CurrentTask.getSerialRequestCode());
//
//        ss.setUserName(systemUserName);
//        ss.setPassword(systemPassword);
//        ss.setAndroidId(androidId);
//        sendAvatar(ss,((File) files),(((File) files).getUri()));

        if (PAGE_TYPE != TUBELESS)
            getCategories();
        prepareList(getRootActivity());

        date();
        setRootActivity();

//todo uncomment
////        ckeckButtons
////                if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
////                    if (isFreeStore(getContext(), StaticValue.configuration)) {
////                    }else {
//                        if ((amountForRegNewPost >= Global.user2.getWallet().getAmount())) {
//                            buttonReg.setVisibility(View.GONE);
//                            buttonReg.setEnabled(false);
//                        }
////                    }
////                }
    }

    private void prepareRequest() {
        aaaa.setIDApplication(AccountGeneral.getIDApplication() + "");

        if (Global.user2 != null) {
            aaaa.setUserCode(Global.user2.getUserCodeAsString());
        }

        aaaa.setTitle(SelectedCategoryTitle + editTextTitle.getText().toString());
        aaaa.setText(editTextText.getText().toString());

        extracted();
        if (PAGE_TYPE == TUBELESS) {
            aaaa.setStateCode("8133");
        }else {
            aaaa.setStateCode(stateItems.get(selectedState - 1).getID() + "");
        }
        aaaa.setCityCode("3044");

        if (PAGE_TYPE == YAFTE) {
            aaaa.setAmount("0");
        }else if (PAGE_TYPE == AMLAK) {
            aaaa.setAmount("0");
        }else {
            if (Global.user2.isUserAdmin()) {
                aaaa.setAmount(editTextAmount.getText().toString());
            } else {
                aaaa.setAmount("0");
            }
        }

        aaaa.setReciveMessage((checkbox.isChecked() ? "true" : "false"));


        Date date = new Date();
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String output = outputFormat.format(date.getTime());
        aaaa.setPublishDate(output);
//                    aaaa.setExpireDate(editTextDate.getText().toString());
//                    aaaa.setExpireDate("2022-04-29 13:53:04.344");
        aaaa.setIP(AccountGeneral.getIP());
        List<Amounts> items = new ArrayList<>();
        for (Amounts amountsItem : amountsList) {
            items.add(amountsItem);
        }
        aaaa.setItems(items);
        newYafte(aaaa);
    }

    private void extracted() {
        if (PAGE_TYPE == MOZ) {
            if (radioButton1.isChecked()) {
                aaaa.setTtc("7");
            }
            if (radioButton2.isChecked()) {
                aaaa.setTtc("8");
            }
        }else if (PAGE_TYPE == YADAK) {
            if (radioButton1.isChecked()) {
                aaaa.setTtc("5043");
            }
            if (radioButton2.isChecked()) {
                aaaa.setTtc("5045");
            }
        }else if (PAGE_TYPE == ESTEKHDAM) {
            if (radioButton1.isChecked()) {
                aaaa.setTtc("3045");
            }
            if (radioButton2.isChecked()) {
                aaaa.setTtc("4043");
            }
        }else if (PAGE_TYPE == YAFTE) {
            if (radioButton1.isChecked()) {
                aaaa.setTtc("5048");
            }
            if (radioButton2.isChecked()) {
                aaaa.setTtc("5050");
            }
            if (radioButton3.isChecked()) {
                aaaa.setTtc("5051");
            }
        }else if (PAGE_TYPE == AMLAK) {
//            if (radioButton1.isChecked()) {
//                aaaa.setTtc("8067");
//            }
//            if (radioButton2.isChecked()) {
//                aaaa.setTtc("8068");
//            }
//            if (radioButton3.isChecked()) {
//                aaaa.setTtc("8069");
//            }
        }else {

        }
    }

    private void date() {

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        String sss = dateUtiliti.getCurrentShamsidate();
//        dateUtiliti.getCurrentShamsidate(timelineItem.getDate());

    }

    String dateTmpDisplay = "";
    String dateTmpServer = "";

    public void showModalBrithDate(View viewx) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        View view = (getActivity()).getLayoutInflater().inflate(R.layout.a_b_bottom_sheet_dialog_select_brith_date, null);
        dialog.setContentView(view);
        final PersianDatePicker persianDatePicker = (PersianDatePicker) view.findViewById(R.id.persianDatePicker);

        Calendar calendare = getGeorgianCalendar();
        PersianCalendar2 persianCalendar2 = new PersianCalendar2();
        if (calendare == null) {
            calendare = getGeorgianCalendar();
            persianCalendar2.set(calendare.get(Calendar.YEAR), calendare.get(Calendar.MONTH), calendare.get(Calendar.DAY_OF_MONTH));
        } else
            persianCalendar2.set(calendare.get(Calendar.YEAR), calendare.get(Calendar.MONTH), calendare.get(Calendar.DAY_OF_MONTH));

        persianDatePicker.setDisplayPersianDate(persianCalendar2);
        persianDatePicker.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(int newYear, int newMonth, int newDay) {
                //save DatePicker in customer
                Date selectedDate;
//                if(LocaleLanguageHelper.getSelectedLanguageCode(mContext,LocaleLanguageHelper.getPhoneLanguageCode(mContext)).equals(PERSIAN_LANGUAGE)) {
                selectedDate = persianDatePicker.getDisplayDate();
                try {
                    //String input = "Thu Jun 06 2015 00:00:00 GMT+0530 (India Standard Time)";
                    SimpleDateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
                    Date date = inputFormat.parse(checkZ(selectedDate.toString()));

                    String dateStr = inputFormat.format(date);



                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                    outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    String output = outputFormat.format(date);
                    System.out.println(output);
//                        customer.setBirthDay(output);

                } catch (Exception ex) {
                }
//                }else {
//                    PersianCalendar1 datez = persianDatePicker.getDisplayPersianDate();
//                    try {
//                        //String input = "Thu Jun 06 2015 00:00:00 GMT+0530 (India Standard Time)";
//
//
//                        Calendar cal = Calendar.getInstance();
//                        cal.set(Calendar.YEAR, datez.getPersianYear());
//                        cal.set(Calendar.MONTH, datez.getPersianMonth() - 1 );
//                        cal.set(Calendar.DAY_OF_MONTH, datez.getPersianDay());
//                        Date dateRepresentation = cal.getTime();
//
//
//                        System.out.print(dateRepresentation.getTime());
//
//                        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
//                        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//
//                        String output = outputFormat.format(dateRepresentation);
//                        System.out.println(output);
//                        customer.setBirthDay(output);
//
//                    } catch (Exception ex) {
//                    }
//                }
            }
        });


        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

                try {
                    //String input = "Thu Jun 06 2015 00:00:00 GMT+0530 (India Standard Time)";
                    DateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
                    Date date = inputFormat.parse(checkZ(persianDatePicker.getDisplayDate().toString()));

//                        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//                        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
                    outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    String output = outputFormat.format(date);
                    dateTmpServer = output;
                    editTextDate.setText(output);

                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianYear());
                    stringBuilder.append("-");
                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianMonth());
                    stringBuilder.append("-");
                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianDay());

                    editTextDate.setText(stringBuilder.toString());

                    System.out.println(output);
                    //"2022-04-29 13:53:04.344"

                    aaaa.setExpireDate(output);


                } catch (Exception ex) {

                }

                Calendar calendare = getGeorgianCalendar();
            }
        });
        dialog.show();
    }

    private String checkZ(String dateString) {
        String regex = "^([A-Z]{3})\\s+([A-Za-z]{3})\\s+(\\d{1,2})\\s+(\\d{1,2}):(\\d{1,2}):(\\d{1,2})\\s+'GMT'(\\d{1,4})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateString);

        if (matcher.matches()) {
            // تاریخ معتبر است
        } else {
            // تاریخ نامعتبر است

            String regex2 = "GMT";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(dateString);

            String dateString2 = matcher2.replaceAll("GMT+00:00");
            dateString = dateString2;
        }

        return dateString;
    }


    List<CategoryItem> stateItems = null;

    private void getCategories() {

        final boolean[] isFirst = {true};
        CategoriesLookUpRequest request = new CategoriesLookUpRequest();
        request.setCategoryCode(5);
        request.setParentId(7061);

        Global.apiManagerTubeless.getCategoryLookUp(request, new TubelessRetrofitCallbackss(getContext(), CategoryListResponse.class) {
            @Override
            public void t_beforeSendRequest() {
                ((TubelessActivity) getContext()).progressDialog.show();
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
                new TubelessException().handleServerMessage(getContext(), new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(getContext(), new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                //new TubelessException().handleServerMessage(getContext(),new TubelessException(TUBELESS_OPERATION_COMPLETE));
                stateItems = ((CategoryListResponse) response).getCatlist();

                String[] stateNames = new String[stateItems.size() + 1];
                stateNames[0] = ("استان");

                int x = 0;
                for (CategoryItem item : stateItems) {
                    stateNames[x + 1] = (item.getTitle());
                    x++;
                }
                SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(getContext(), stateNames);
                spinner.setAdapter(spinnerAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(defaultState && isFirst[0]) {
                            isFirst[0] = false;
                            spinner.setSelection(8);
                            selectedState = 8;
                        }else
                            selectedState = i;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        selectedState = 0;
                    }
                });
            }
        });
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

//    private void accounts() {
//        SAccounts sAccounts = new SAccounts(getContext());
////        sAccounts.getAccountManager().addAccount()
//
//        final Account account = new Account("accountName", "ir.sajjadyosefi.tubeless") ;
//        sAccounts.getAccountManager().addAccountExplicitly(account, "accountPassword",null);
//
//        AccountAuthenticator accountAuthenticator = new AccountAuthenticator(getContext());
////        accountAuthenticator.addAccount();
////        accountAuthenticator.
//    }

    String SelectedCategoryTitle = new String();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_FILE_LIST) {
                filesList = (List<File>) data.getSerializableExtra("LIST1");
            } else {

            }
        } else {
        }

        if (requestCode == GO_TO_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {

            } else {
                finish();
            }
        }
        Intent x;

        if (requestCode == 1000 || requestCode == CALL_AGAIN) {
            if (PaymentActivity.isPaymentSuccess()) {
                x = PaymentActivity.getPaymentIntent();
//                Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();

                x.getIntExtra("amount", 10);
                x.getStringExtra("ReturnData");
                x.getStringExtra("metaData");
                x.getStringExtra("item1");
                x.getIntExtra("type", 10);

//                Gson gson = new Gson();
//                PrePaymentActivity.ReturnData returnData = new PrePaymentActivity.ReturnData();
//                returnData = gson.fromJson(x.getStringExtra("ReturnData").toString(), PrePaymentActivity.ReturnData.class);
//                Global.user2.getWallet().setAmount(returnData.wallet.getAmount());
//                Wallet.savedToDataBase(Global.user2);


                //todo update database
                //user amount

//                send to server
                prepareRequest();
            } else {
                Toast.makeText(getContext(), getContext().getString(R.string.pay_not_success), Toast.LENGTH_LONG).show();
            }
            PaymentActivity.PaymentDone();
        }

        if (requestCode == REQUEST_CATEGORY_LIST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle dd = data.getExtras();

                List<ParentItem> list = (List<ParentItem>) dd.getSerializable("SelectedLIST");
                long SelectedCategory = dd.getLong("SelectedCategory");
                SelectedCategoryTitle = dd.getString("SelectedCategoryTitle") + "\n";
                selectedState = 8133;
                editTextDate.setText("2022-04-29 13:53:04.344");
                //editTextTitle.setText(SelectedCategoryTitle);
                aaaa.setExpireDate("2022-04-29 13:53:04.345");
                aaaa.setTtc(String.valueOf(SelectedCategory));
            }else {
                aaaa.setTtc(null);
                selectedState = 0;
                editTextDate.setText("");
            }
        }
    }

    private void newYafte(NewBlogRequest blogItem) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewPostResponse.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                NewPostResponse responseX = (NewPostResponse) response;

                if (responseX.getTubelessException().getCode() > 0) {
                    updateWallet(responseX);
                    boolean havePic = false;
                    if (filesList.size()>=3){
                        havePic = true;
                    }

                    if (havePic && (responseX.getTransactionList().size() == 1 || responseX.getTubelessException().getCode() == 200)) {
                        //ok
                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
                        mIntent.putExtra("BlogId", responseX.getTransactionList().get(0).getRefrenceNo());

                        if (lastCheckedPosition2 != -1) {
                            mIntent.putExtra("TitlePicture", UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
                        }

                        if (lastCheckedPosition != -1) {
                            mIntent.putExtra("TextPicture", UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
                        }

                        int index = 0;
                        for (File file : filesList) {
                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic()) {
                                index++;
                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
                            }
                        }
                        mIntent.putExtra("filesCount", index);
                        FileUploadService.enqueueWork(getContext(), mIntent);


                        Toast.makeText(getContext(), "درحال ارسال فایل ها", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                    }

//                    Global.user2.getWallet().setAmount(returnData.wallet.getAmount());
//                    Wallet.updateWalletAmount();
                } else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.registerNewBlog(blogItem, ssssssss);
    }

    private void updateWallet(NewPostResponse response) {
        //todo save new balanse

        if (Global.user2.getWallet() != null)
            Global.user2.getWallet().setAmount(response.getWallet().getAmount());

        Wallet wallet = new Wallet();
        AWallet aWallet = wallet.loadWalletData();
        wallet.updateWalletAmount(response.getWallet().getAmount());


        aWallet = wallet.loadWalletData();
    }


//    private void sendAvatar(SendFileRequest request, File files, String fileUri){
//        java.io.File file =  new java.io.File(FileUtils.getPath(getContext(), Uri.parse(fileUri)));
//        RequestBody requestFile = RequestBody.create(MediaType.parse("text/plain, application/json"), file);
////        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        MultipartBody.Part body =MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//
//        RequestBody userName = RequestBody.create(okhttp3.MultipartBody.FORM, "ApiService");
//        RequestBody password = RequestBody.create(okhttp3.MultipartBody.FORM,"BandarAndroid");
//        RequestBody androidId = RequestBody.create(okhttp3.MultipartBody.FORM, RetrofitHelperService.androidId);
//        RequestBody serialRequestCode = RequestBody.create(okhttp3.MultipartBody.FORM,Global.CurrentTask.getSerialRequestCode());
//        RequestBody fileType = RequestBody.create(okhttp3.MultipartBody.FORM,files.getFileType() + "");
//        RequestBody senderType = RequestBody.create(okhttp3.MultipartBody.FORM,"1");
//
//
//        Global.apiManagerTubeless.requestUpload2(body ,userName,password,androidId ,serialRequestCode,fileType,senderType, new TubelessRetrofitCallback<Object>(getContext(), getRootActivity(), true, null, new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//
//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                files.setSended(true);
//
//                boolean allSent = true;
//                for (File files : Global.CurrentTask.sendToServerfileList) {
//                    if (!((ir.sajjadyosefi.evaluation.model.business.File)files).isSended()){
//                        allSent = false;
//                    }
//                }
//                if (allSent){
//
//                    int a = 5;
//                    a++;
//                    //new Delete().from(Task.class).where("taskID = ?", Global.CurrentTask.getSerialRequestCode()).execute();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//
//                files.setSended(false);
//
//                int a = 5 ;
//                a++;
//            }
//        }));
//
//    }

    RecyclerView mRecyclerViewTimeline;
    EndlessListAmountsAdapter amountsAdapter;
    LinearLayoutManager mLayoutManager;
    List<Amounts> amountsList = new ArrayList<Amounts>();

    private void prepareList(View rootview) {
        mRecyclerViewTimeline = (RecyclerView) findViewById(R.id.recyclerView);
        ((Button) findViewById(R.id.buttonAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubStractDialogClass cdd = new SubStractDialogClass((Activity) getContext());
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show();
                cdd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (subscribeItem != null) {
                            amountsList.add(subscribeItem);
                            amountsAdapter.notifyDataSetChanged();
                        }
                        sv.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
        mRecyclerViewTimeline.setHasFixedSize(true);
        mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(getContext());
        amountsAdapter = new EndlessListAmountsAdapter(
                getContext(),
                mLayoutManager,
                rootview,
                true,
                amountsList);
        amountsAdapter.listType = SUBSCRIPTIONS;
        mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
        mRecyclerViewTimeline.setAdapter(amountsAdapter);

    }


    public void modalPayType(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_pay_type, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);



        final Button button_pay_by_wallet= view.findViewById(R.id.pay_by_wallet);
        final TextView textView = view.findViewById(R.id.textView);
        final Button button_pay_direct = view.findViewById(R.id.pay_direct);
        textView.setText(String.format(mContext.getString(R.string.text_for_pay_type),String.valueOf(amountForRegNewPost_Toman)));
        button_pay_by_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((amountForRegNewPost_Toman >= Global.user2.getWallet().getAmount())) {
                    Toast.makeText(mContext,"موجودی کیف پول شما کافی نمی باشد",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    //1
//                    Intent intent = new Intent(getContext(), PrePaymentActivity.class);
//                    intent.putExtra("defaultValue",amountForRegNewPost_Toman);
//                    ((Activity)getContext()).startActivityForResult(intent,CALL_AGAIN);

                    //2
//                    PrePaymentActivity.phone = Global.sAccountHelper.getUserAccountName();
//                    //amount = amountForRegNewPost;
//                    PrePaymentActivity.discription = Global.sAccountHelper.getUserAccountName() + " pay:" + amountForRegNewPost_Toman + " for reg post";
//                    paymentSharj(amountForRegNewPost_Toman,PrePaymentActivity.discription,PrePaymentActivity.phone);

                    //3
                    Intent intent = new Intent(getContext(), PrePaymentActivity.class);
                    getContext().startActivity(intent);

                }else {
                    //modal
                    //amountForRegNewPost
//                    Toast.makeText(mContext,"کیف پول شما غیرفعال است",Toast.LENGTH_SHORT).show();

                    prepareRequest();

//                    CommonDialogs.modalAmountConfirm(mContext, amountForRegNewPost_Toman, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // pay by wallet
//                            dialog.dismiss();
//
//
//                        }
//                    });

                }
            }
        });
        button_pay_direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to payment
                dialog.dismiss();
                PrePaymentActivity.phone = Global.sAccountHelper.getUserAccountName();
                //amount = amountForRegNewPost;
                PrePaymentActivity.discription = Global.sAccountHelper.getUserAccountName() + " pay:" + amountForRegNewPost_Toman + " for reg post";
                payment(amountForRegNewPost_Toman,PrePaymentActivity.discription,PrePaymentActivity.phone);
            }
        });
        dialog.show();
    }

    public void modalWalletShrje(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_pay_type, null);
        dialog.setContentView(view);
        dialog.setCancelable(true);

        final Button button_pay_by_wallet= view.findViewById(R.id.pay_by_wallet);
        final Button button_pay_direct = view.findViewById(R.id.pay_direct);
        button_pay_by_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.show();
    }


    private void payment(long amount,String discription,String mobileNumber) {
        //payment = charge by method
        //done
        Bundle bundle = new Bundle();
        bundle.putInt("type", 2);
        bundle.putInt("amount", (int) amount);

        Gson gson = new Gson();
        HashMap<String,String> metaData = new HashMap<String,String>();
        metaData.put("amount", amount + "0");
        metaData.put("discription",discription);
        metaData.put("mobile",mobileNumber);

        bundle.putString("metaData", gson.toJson(metaData));
        Intent intent = PaymentActivity.getIntent(this, bundle);
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        startActivityForResult(intent, 1000);

        //        Bundle bundle = new Bundle();
//        bundle.putInt("type" , 1);
//        Intent intent = SignInActivity.getIntent(context,bundle);
////        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
////                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
//        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//        //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//        startActivityForResult(intent, 1000);
    }

    private void paymentSharj(long amount,String discription,String mobileNumber) {
        //payment = charge by method
        //done
        Bundle bundle = new Bundle();
        bundle.putInt("type", 2);
        bundle.putInt("amount", (int) amount);

        Gson gson = new Gson();
        HashMap<String,String> metaData = new HashMap<String,String>();
        metaData.put("amount", amount + "0");
        metaData.put("discription",discription);
        metaData.put("mobile",mobileNumber);

        bundle.putString("metaData", gson.toJson(metaData));
        Intent intent = PaymentActivity.getIntent(this, bundle);
        intent.putExtra("defaultValue",amountForRegNewPost_Toman);
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        startActivityForResult(intent, CALL_AGAIN );
    }

}
