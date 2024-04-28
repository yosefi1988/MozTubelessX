package ir.sajjadyosefi.android.xTubeless.activity.register;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.service.FileUploadService;
import ir.sajjadyosefi.android.xTubeless.utility.file.UriUtil;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition2;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.NOT_LOGN_USER;


public class RegNewYadakActivity extends TubelessTransparentStatusBarActivity {


    public Button buttonReg , buttonBack , buttonAddFiles,buttonAddCategory;
    EditText editTextText,editTextTextPicture,editTextTitleStatment,editTextTitlePicture,editTextTitle;
    RadioButton radioButton1,radioButton2,radioButton3;
    int REQUEST_FILE_LIST = 525;
    public static int REQUEST_CATEGORY_LIST = 526;

    static List<File> filesList;
    static List<CategoryItem> catList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        filesList = new ArrayList<File>();
        catList = new ArrayList<CategoryItem>();

        File headerInList = new File();
        headerInList.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_HEADER);
        filesList.add(headerInList);

        File Emptylist = new File();
        Emptylist.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_EMPTY_LIST);
        filesList.add(Emptylist);


        CategoryItem headerInCatList = new CategoryItem();
        //headerInCatList.setListItemType(EndlessList_AdapterCategory.ListItemType.TYPE_HEADER);
        catList.add(headerInCatList);

        CategoryItem EmptyCatlist = new CategoryItem();
        //EmptyCatlist.setListItemType(EndlessList_AdapterCategory.ListItemType.TYPE_EMPTY_LIST);
        catList.add(EmptyCatlist);

        setContentView(R.layout.activity_new_yadak);

        buttonReg = findViewById(R.id.buttonReg);
        buttonBack = findViewById(R.id.buttonBack);
        buttonAddFiles = findViewById(R.id.buttonAddFiles);
        buttonAddCategory = findViewById(R.id.buttonAddCategory);
        editTextText = findViewById(R.id.editTextText);
        editTextTextPicture = findViewById(R.id.editTextTextPicture);
        editTextTitleStatment = findViewById(R.id.editTextTitleStatment);
        editTextTitlePicture = findViewById(R.id.editTextTitlePicture);
        editTextTitle = findViewById(R.id.editTextTitle);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

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




        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonAddFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.user2.isUserAdmin()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("FILE_COUNT", 4);
                    bundle.putSerializable("LIST", (Serializable) filesList);
                    getActivity().startActivityForResult(FileListActivity.getIntent(getContext(), bundle),REQUEST_FILE_LIST);
                }else {
                    Toast.makeText(getContext(),getContext().getString(R.string.notEnugh),Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if (
                        editTextTitleStatment.getText().toString().length() < 5 ||
                        editTextText.getText().toString().length() < 5 ||
                        editTextTitle.getText().toString().length() < 5 ||
                        catList.size() == 0) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {
//                    NewBlogRequest aaaa = new NewBlogRequest();
//                    if (radioButton1.isChecked()) {
//                        aaaa.setCategoryID(getResources().getInteger(R.integer.cat1Yadak));
//                    }
//                    if (radioButton2.isChecked()) {
////                        aaaa.setCategoryID(R.integer.cat2Yadak);
//                        aaaa.setCategoryID(getResources().getInteger(R.integer.cat2Yadak));
//                    }
//                    if (radioButton3.isChecked()) {
////                        aaaa.setCategoryID(R.integer.cat3Yadak);
//                        aaaa.setCategoryID(getResources().getInteger(R.integer.cat3Yadak));
//                    }
//
//                    aaaa.setCategoryID(catList.get(0).getID());
//
//
//                    if(Global.user == null){
//                        aaaa.setUserID(140430);
//                    }else {
//                        aaaa.setUserID((int) Global.user.getUserId());
//                    }
//
//                    aaaa.setTitlePicture("");
//                    aaaa.setTextPicture("");
//
//                    JsonObject oooo = new JsonObject();
//                    oooo.addProperty("title",       editTextTitle.getText().toString());
//                    oooo.addProperty("model",       editTextTitleStatment.getText().toString());
//                    oooo.addProperty("description", editTextTitlePicture.getText().toString());
//
//                    aaaa.setTitle(editTextTitle.getText().toString());
//                    aaaa.setStatement(oooo.toString());
//
//                    oooo.addProperty("mobile","00");
//                    oooo.addProperty("text",        editTextText.getText().toString());
//                    aaaa.setText(oooo.toString());
//                    newYadak(aaaa);
                }
            }
        });


//        if (Global.IDUser == NOT_LOGN_USER ){




        if ((!BuildConfig.BUILD_TYPE.equals("debug")) && (Global.user2 == null || Global.userFixxxxxxxxx.getUserId() == NOT_LOGN_USER )){
//            Bundle bundle = new Bundle();
//            Intent intent = SignInActivity.getIntent(getContext(),bundle);
//            intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
//            intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
//            intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
//            //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
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


//        try {
//            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
//            String versionName = pInfo.versionName;
//            if (versionName.contains("4.5.0") || versionName.contains("1.5.0")|| versionName.contains("1.3.0")) {
//                buttonAddFiles.setEnabled(false);
//            } else {
//                buttonAddFiles.setEnabled(true);
//            }

        if (Global.userFixxxxxxxxx != null && Global.user2.isUserAdmin()) {
            buttonAddFiles.setVisibility(View.VISIBLE);
        }else {
            buttonAddFiles.setVisibility(View.GONE);
        }
//        }catch (Exception ex){
//
//        }
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


        if (requestCode == GO_TO_LOGIN){
            if (resultCode == Activity.RESULT_OK){

            }else {
                finish();
            }
        }


        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_FILE_LIST){
                filesList = (List<File>) data.getSerializableExtra("LIST1");
            }else if (requestCode == REQUEST_CATEGORY_LIST){
                catList = (List<CategoryItem>) data.getSerializableExtra("LIST");
            }else {

            }
        }else {

        }

    }

    private void newYadak(NewBlogRequest blogItem) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                try {
                    ((TubelessActivity)getContext()).progressDialog.show();
                }catch (Exception ex){

                }
            }

            @Override
            public void t_afterGetResponse() {
                try {
                    ((TubelessActivity)getContext()).progressDialog.hide();
                }catch (Exception ex){

                }
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
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    boolean havePic = false;
                    if (filesList.size()>=3){
                        havePic = true;
                    }

                    if(havePic){

                        //ok
                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
                        mIntent.putExtra("BlogId", responseX.getTubelessException().getMessage());

                        if (lastCheckedPosition2 != -1){
                            mIntent.putExtra("TitlePicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
                        }

                        if (lastCheckedPosition != -1){
                            mIntent.putExtra("TextPicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
                        }

                        int index = 0;
                        for (File file : filesList) {
                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic() ) {
                                index++;
                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
                            }
                        }
                        mIntent.putExtra("filesCount", index );
                        FileUploadService.enqueueWork(getContext(), mIntent);


                    }else {
                        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.registerNewBlog(blogItem ,ssssssss);


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
}
