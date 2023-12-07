package ir.sajjadyosefi.android.xTubeless.activity.register;

import android.accounts.AccountManager;
import android.app.Activity;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import ir.sajjadyosefi.accountauthenticator.activity.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessListAmountsAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Adapter.SpinnerAdapterA;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;
 
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewPostResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
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
import static ir.sajjadyosefi.android.xTubeless.activity.register.PrePaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.amountForSeenNewPost;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;
import static ir.sajjadyosefi.android.xTubeless.dialog.SubStractDialogClass.subscribeItem;
import static ir.sajjadyosefi.android.xTubeless.utility.DateTime.SamanDateTime.getGeorgianCalendar;


public class RegNewImageActivity extends TubelessTransparentStatusBarActivity {

    public Button buttonReg, buttonBack ,buttonAddFiles;
    EditText editTextText, editTextTitle, editTextAmount;
    TextView editTextDate,login_title,titleTextView,editTextTitleExample , textViewTitle2;
    LinearLayout linearLayoutAmount;
    RadioButton radioButton1, radioButton2;
    CheckBox checkbox;
    LinearLayout linearLayout;
    ScrollView sv;
    NewBlogRequest aaaa = new NewBlogRequest();
    static List<File> filesList;
    private int REQUEST_FILE_LIST = 525;

    //state
//    Spinner spinner;
    private static int selectedState;

    private int PAGE_TYPE = 0;
    public static final int MOZ = 1;
    public static final int ESTEKHDAM = 2;
    public static final int YADAK = 3;

    
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


        if (BuildConfig.FLAVOR_version_name.equals("estekhdam")) {
            PAGE_TYPE = ESTEKHDAM;
        }
        if (BuildConfig.FLAVOR_version_name.equals("moz")) {
            PAGE_TYPE = MOZ;
        }
        if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
            PAGE_TYPE = YADAK;
        }


        setContentView(R.layout.activity_new_image);
        setRootActivity();
        buttonReg = findViewById(R.id.buttonReg);
        buttonBack = findViewById(R.id.buttonBack);
        linearLayoutAmount = findViewById(R.id.linearLayoutAmount);
        editTextText = findViewById(R.id.editTextText);
//        editTextDate = findViewById(R.id.editTextDate);
        login_title = findViewById(R.id.login_title);
        titleTextView = findViewById(R.id.titleTextView);
        textViewTitle2 = findViewById(R.id.textViewTitle2);
        editTextTitleExample = findViewById(R.id.editTextTitleExample);
        buttonAddFiles = findViewById(R.id.buttonAddFiles);


        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAmount = findViewById(R.id.editTextAmount);
        linearLayout = findViewById(R.id.linearLayout);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        checkbox = findViewById(R.id.checkbox);
//        spinner = findViewById(R.id.spinner);
        sv = (ScrollView) findViewById(R.id.scroll);

        if(defaultType)
            radioButton1.setChecked(true);

        if(defaultPrice)
            editTextAmount.setText(amountForSeenNewPost);

        if (defaultText)
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
            linearLayout.setVisibility(View.GONE);
        }
        if (PAGE_TYPE == YADAK){
            //5043
            login_title.setText(R.string.regNewEstekhdamTitle);
            titleTextView.setHint(R.string.regNewEstekhdamTitleText2);
            editTextText.setHint(R.string.regNewEstekhdamTitleHint);
            textViewTitle2.setText(R.string.regNewEstekhdamTitleText2);

            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }
        if (PAGE_TYPE == MOZ){
            login_title.setText(R.string.regNewEstekhdamTitle);
            titleTextView.setText(R.string.regNewEstekhdamTitleText);
            editTextTitleExample.setText(R.string.regNewEstekhdamTitleTextExample);
        }

        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    linearLayout.setVisibility(View.VISIBLE);
                    buttonReg.setEnabled(true);
                } else
                    linearLayout.setVisibility(View.GONE);
            }
        });

        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                buttonReg.setEnabled(true);

                if (!b) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else
                    linearLayout.setVisibility(View.GONE);
            }
        });

        if (Global.user2.isUserAdmin()) {
            linearLayoutAmount.setVisibility(View.VISIBLE);
        } else {
            linearLayoutAmount.setVisibility(View.GONE);
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        if (Global.user2.isUserAdmin()) {
            buttonAddFiles.setEnabled(true);
//        }else {
//            buttonAddFiles.setEnabled(false);
//        }

        buttonAddFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

//        editTextDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showModalBrithDate(getRootActivity());
//            }
//        });
//        if (defaultDate){
//
//        }

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if (
//                                selectedState == 0 ||
//                                editTextDate.getText().toString().length() < 5 ||
                                editTextText.getText().toString().length() < 5 ||
                                editTextTitle.getText().toString().length() < 5) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                } else if (Global.user2.isUserAdmin() && editTextAmount.getText().toString().length() < 2) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    aaaa.setIDApplication(AccountGeneral.getIDApplication() + "");

                    if (Global.user2 != null) {
                        aaaa.setUserCode(Global.user2.getUserCodeAsString());
                    }

                    aaaa.setTitle(editTextTitle.getText().toString());
                    aaaa.setText(editTextText.getText().toString());
                    aaaa.setTtc("5047");
                    //aaaa.setStateCode(stateItems.get(selectedState - 1).getID() + "");
                    aaaa.setStateCode("2016");
                    aaaa.setCityCode("3044");
                    if (Global.user2.isUserAdmin()) {
                        aaaa.setAmount(editTextAmount.getText().toString());
                    } else {
                        aaaa.setAmount("100");
                    }
                    aaaa.setReciveMessage((checkbox.isChecked() ? "true" : "false"));


                    Date date = new Date();
                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
                    outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String output = outputFormat.format(date.getTime());
                    aaaa.setPublishDate(output);


//                    aaaa.setExpireDate(editTextDate.getText().toString());
//                    aaaa.setExpireDate("2022-04-29 13:53:04.344");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.YEAR, 15);
                    Date newDate = c.getTime();
                    aaaa.setExpireDate(outputFormat.format(newDate.getTime()));

                    aaaa.setIP(AccountGeneral.getIP());
                    List<Amounts> items = new ArrayList<>();
                    for (Amounts amountsItem : amountsList) {
                        items.add(amountsItem);
                    }
                    aaaa.setItems(items);
                    newYafte(aaaa);
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
        getCategories();
        prepareList(getRootActivity());

        date();
        setRootActivity();
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
                    DateFormat inputFormat = new SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'z yyyy", Locale.ENGLISH);
                    Date date = inputFormat.parse(selectedDate.toString());

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
                    Date date = inputFormat.parse(persianDatePicker.getDisplayDate().toString());

//                        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//                        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
                    outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    String output = outputFormat.format(date);
                    dateTmpServer = output;
//                    editTextDate.setText(output);

                    StringBuilder stringBuilder = new StringBuilder();

                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianYear());
                    stringBuilder.append("-");
                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianMonth());
                    stringBuilder.append("-");
                    stringBuilder.append(persianDatePicker.getDisplayPersianDate().getPersianDay());

//                    editTextDate.setText(stringBuilder.toString());

                    System.out.println(output);
                    //"2022-04-29 13:53:04.344"
                    //aaaa.setExpireDate(output);


                } catch (Exception ex) {

                }

                Calendar calendare = getGeorgianCalendar();
            }
        });
        dialog.show();
    }


//    List<CategoryItem> stateItems = null;

    private void getCategories() {

        CategoriesLookUpRequest request = new CategoriesLookUpRequest();
        request.setCategoryCode(5);
        request.setParentId(0);

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
//                new TubelessException().handleServerMessage(getContext(),new TubelessException(TUBELESS_OPERATION_COMPLETE));
//                stateItems = ((CategoryListResponse) response).getCatlist();
//
//                String[] stateNames = new String[stateItems.size() + 1];
//                stateNames[0] = ("استان");
//
//                int x = 0;
//                for (CategoryItem item : stateItems) {
//                    stateNames[x + 1] = (item.getTitle());
//                    x++;
//                }
//                SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(getContext(), stateNames);
//                spinner.setAdapter(spinnerAdapter);
//                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        if(defaultState) {
//                            spinner.setSelection(8);
//                            selectedState = 8;
//                        }else
//                            selectedState = i;
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//                        selectedState = 0;
//                    }
//                });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_FILE_LIST){
                filesList = (List<File>) data.getSerializableExtra("LIST1");
            }else {

            }
        }else {

        }

        if (requestCode == GO_TO_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {

            } else {
                finish();
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
}
