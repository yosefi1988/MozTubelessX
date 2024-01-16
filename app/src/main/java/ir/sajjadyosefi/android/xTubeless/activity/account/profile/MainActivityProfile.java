package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.ChangePasswordActivity;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.accountauthenticator.model.request.ATransactionListRequest;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.PrePaymentActivity;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.Validator;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.FileUploaderModel;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.dialog.CommonDialogs;
import ir.sajjadyosefi.android.xTubeless.service.ServiceGenerator;
import ir.sajjadyosefi.android.xTubeless.utility.AppUtility;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import ir.sajjadyosefi.android.xTubeless.utility.SamanString;
import ir.sajjadyosefi.android.xTubeless.utility.file.FileCompressor;
import ir.sajjadyosefi.android.xTubeless.utility.file.UriUtil;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;
import retrofit2.Response;

import static com.yalantis.ucrop.UCrop.REQUEST_CROP;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.CHANGE_PASSWORD_REQUEST_CODE;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CREATORS_POST;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYPURCHESE;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MYTRANSACTIONS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmm2;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmmm;

import static ir.sajjadyosefi.android.xTubeless.Global.sAccountHelper;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.isFreeStore;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_RESPONSE_BODY_IS_NULL;
import static ir.sajjadyosefi.android.xTubeless.classes.model.user.User2.deleteAllUsersData;
import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.SelectSource;

public class MainActivityProfile extends TubelessTransparentStatusBarActivity implements IProfileView, IFileUploadView {

    public static final int REQUEST_TAKE_PHOTO = 1001;
    public static final int PERMISSION_REQUEST_TAKE_PHOTO = 10010;
    public static final int REQUEST_GALLERY_PHOTO = 1002;
    public static final int PERMISSION_REQUEST_GALLERY_PHOTO = 10020;

    private static final int AVATAR_SELECTED = 1;
    private static final int PROFILE_SELECTED = 2;
    private static final int NOT_SELECTED = 0;
    private static int SELECTED_IMAGE = NOT_SELECTED;

    Context context;
    Activity activity;

    @BindView(R.id.ueditTextNameUserId)
    TextView ueditTextNameUserId;

    @BindView(R.id.linearLayoutWallet)
    LinearLayout linearLayoutWallet;

//    @BindView(R.id.editTextPhone)
//    TextView editTextPhone;

//    @BindView(R.id.editTextEmail)
//    EditText editTextEmail;

    @BindView(R.id.header_cover_image)
    ImageView headerProfileImage;
    @BindView(R.id.user_profile_photo)
    ImageButton userAvatarPhoto;

    @BindView(R.id.user_profile_name)
    TextView userProfileName;

    @BindView(R.id.user_wallet)
    TextView user_wallet;

//    @BindView(R.id.user_profile_short_bio)
    TextView userProfileShortBio;
//    @BindView(R.id.profile_layout)
//    RelativeLayout profileLayout;
    @BindView(R.id.textViewProgress)
    TextView txtProgress;
    @BindView(R.id.upload_file_progress)
    Button uploadFileProgress;
    @BindView(R.id.charge)
    Button charge;

    @BindView(R.id.buttonFav)
    Button buttonFav;
    @BindView(R.id.buttonMyPost)
    Button buttonMyPost;
    @BindView(R.id.buttonCreatorsPost)
    Button buttonCreatorsPost;
    @BindView(R.id.buttonMessages)
    Button buttonMessages;
    @BindView(R.id.buttonCreatorMessages)
    Button buttonCreatorMessages;
    @BindView(R.id.buttonLastSeen)
    Button buttonLastSeen;

    @BindView(R.id.buttonReciveReport)
    Button buttonReciveReport;

    @BindView(R.id.buttonChangePassword)
    Button buttonChangePassword;


    @BindView(R.id.btn_upload_file_without_progress)
    Button btnUploadFileWithoutProgress;

    private ImagePresenter mImagePresenter;
    private FileUploaderPresenter mUploaderPresenter;

    File mPhotoFile;
    private FileCompressor mCompressor;


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        bundle.putString("item2","value1");
        Intent intent = new Intent(context, MainActivityProfile.class);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        ButterKnife.bind(this);



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




        context = this;
        activity = this;
        mImagePresenter = new ImagePresenter(this);
        mCompressor = new FileCompressor(this);
        mUploaderPresenter = new FileUploaderPresenter(this, new FileUploaderModel(ServiceGenerator.createService()));

        if (Global.user2 == null){
            finish();
        }else {
            ueditTextNameUserId.setText(Global.user2.getUserCodeAsString());
//            userProfileName.setText(" پروفایل " + (Global.user2.getName() == null ? "" : Global.user2.getName()) + " " + (Global.user2.getFamily() == null ? "" : Global.user2.getFamily()));

            if (Global.user2.getWallet() != null) {
                user_wallet.setText(" موجودی شما : " + SamanString.addSpratorX(Global.user2.getWallet().getAmount()) + " " + "ریال");
            }else{
                user_wallet.setText(" موجودی شما : " + "0.0" + " " + "ریال");
            }

//            editTextEmail.setText(Global.user2.getEmail());
//            editTextPhone.setText(Global.user2.getLoginPhone());
        }

        getImageFromSeviceStream();

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PrePaymentActivity.class);
                getContext().startActivity(intent);
            }
        });
        buttonReciveReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                        //تراکنش های من

                ATransactionListRequest xxxxxxxxxxx = new ATransactionListRequest("110012","10","0");
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type" , FRAGMENT_MYTRANSACTIONS);
                getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle2));
            }
        });

        if ((new Validator()).isIranianMobileNumber(sAccountHelper.getAccountX().name))
            buttonChangePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Global.user2.getMobileNumberConfirmed().equals("true")) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 1);
                        Intent intent = ChangePasswordActivity.getIntent(getContext(), bundle);
                        //intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE); todo not need
                        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_ADMIN_USER);
                        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
                        intent.putExtra(AuthenticatorActivity.PARAM_USER_CODE, String.valueOf(Global.user2.getUserCode()));
                        //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
                        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                        getActivity().startActivityForResult(intent, CHANGE_PASSWORD_REQUEST_CODE);
                    }else {
                        Toast.makeText(context,"دوباره تلاش کنید",Toast.LENGTH_LONG).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                AppUtility.restartApplication(context);
                            }
                        },100);
                    }
                }
            });
        else
            buttonChangePassword.setEnabled(false);


        buttonLastSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        //خرید های من
//                timelineSearchRequest = new TimelineRequest();
//                timelineSearchRequest.setPageSize("10");
//                timelineSearchRequest.setPageIndex("1");

                Bundle bundle2 = new Bundle();
                bundle2.putInt("type" , FRAGMENT_MYPURCHESE);
                bundle2.putInt("CAT_COUNT", 10);
                getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle2));
            }
        });
        buttonMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmmm);
                getContext().startActivity(ContainerActivity.getIntent(getContext(), bundle2));
            }
        });

        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                timelineSearchRequest = new TimelineRequest();
//                timelineSearchRequest.setStateCode("0");
//                timelineSearchRequest.setTypeCode("0");
//                timelineSearchRequest.setCityCode("0");
//                timelineSearchRequest.setPageSize("10");
//                timelineSearchRequest.setPageIndex("1");
//
//                timelineSearchRequest.setUi(Global.user2.getUserName());
//                timelineSearchRequest.setTypeCode("0");
//                timelineSearchRequest.setIp(DeviceUtil.GetIP(getContext()));
                Bundle bundle = new Bundle();
                bundle.putInt("type" , FRAGMENT_MYFAVS);
                bundle.putInt("CAT_COUNT", 10);
                getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));

            }
        });
        buttonMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("type" , FRAGMENT_MYPOSTS);
                bundle.putInt("CAT_COUNT", 10);
                getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
            }
        });

        if (Global.user2.getUserTypeCode() == 3){
            buttonCreatorMessages.setVisibility(View.VISIBLE);
            buttonCreatorMessages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"درحال آماده سازی",Toast.LENGTH_SHORT).show();
//                    Bundle bundle2 = new Bundle();
//                    bundle2.putInt("type", FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmm2);
//                    getContext().startActivity(ContainerActivity.getIntent(getContext(), bundle2));
                }
            });

            buttonCreatorsPost.setVisibility(View.VISIBLE);
            buttonCreatorsPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , FRAGMENT_CREATORS_POST);
                    bundle.putInt("CAT_COUNT", 10);
                    getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
                }
            });
        }else {
            buttonCreatorsPost.setVisibility(View.GONE);
        }


        if(Global.user2 != null)
            getImageFromSevice();

        if (isFreeStore(context, StaticValue.configuration)) {
            linearLayoutWallet.setVisibility(View.GONE);
            buttonReciveReport.setVisibility(View.GONE);
        }

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
                try {
                    //sajjad
                    Uri mPhotoURI0 = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", mPhotoFile);
                    Uri newCropedFile = mImagePresenter.cropImage(mPhotoURI0);

//                    //cafebazarr
//                    Uri imageUri = data.getData();
//                    if(imageUri != null) {
//                        // do something with image
//                        mImagePresenter.cropImage(imageUri);
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
                try {
                    selectedImage = mImagePresenter.cropImage(selectedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (requestCode == REQUEST_CROP) {
                Uri resultUri = UCrop.getOutput(data);
                try {
                    File resultedFile = mCompressor.compressToFile(new File(Objects.requireNonNull(getRealPathFromUri(resultUri))));
                    mImagePresenter.saveImage(resultedFile.getPath());
                    mImagePresenter.showPreview(resultedFile);
                }
                catch (Exception ex){

                    int aaaa = 5;
                    aaaa++;
                }
            }else if (requestCode == CHANGE_PASSWORD_REQUEST_CODE) {
                Toast.makeText(getApplicationContext(), "رمز شما تغییر کرد.", Toast.LENGTH_SHORT).show();
            }
        }
        uCrop = null;
    }


    private UCrop uCrop;
    private String folder = null;
    private boolean withTimeStamp = true;
    private int cropActivityColor = ir.sajjadyosefi.android.superpickerlibrary.R.color.colorPrimary;
    private String imageName;

    @Override
    @SuppressLint("ResourceAsColor")
    public Uri startCropActivity(Uri source){
        imageName = getString(R.string.app_name_file);
        Uri newCropedFile = null;

        if(uCrop == null){
            newCropedFile = getImageFile(this);
            if (SELECTED_IMAGE == AVATAR_SELECTED){
                uCrop = UCrop.of(source, newCropedFile).withMaxResultSize(200, 200);
            }else if (SELECTED_IMAGE == PROFILE_SELECTED){
                uCrop = UCrop.of(source, newCropedFile).withMaxResultSize(600, 200);
            }
            uCrop = uCrop.useSourceImageAspectRatio();
            UCrop.Options options = new UCrop.Options();
            options.setFreeStyleCropEnabled(true);

            options.setToolbarColor(cropActivityColor);
            options.setStatusBarColor(cropActivityColor);
//            options.setActiveWidgetColor(cropActivityColor);
            uCrop = uCrop.withOptions(options);
        }
        uCrop.start(this);
        return newCropedFile;
    }

    protected Uri getImageFile(Activity activity){
        String imagePathStr = Environment.getExternalStorageDirectory() + "/" +
                (folder == null ?
                        Environment.DIRECTORY_DCIM + "/" + activity.getString(R.string.app_name_file) :
                        folder);

        File path = new File(imagePathStr);
        if (!path.exists()) {
            path.mkdir();
        }

        String finalPhotoName = imageName +
                (withTimeStamp ? "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(System.currentTimeMillis())) :  "")
                + ".jpg";

        long currentTimeMillis = System.currentTimeMillis();
        String photoName = imageName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date(currentTimeMillis)) + ".jpg";
        File photo = new File(path, finalPhotoName);

        return Uri.fromFile(photo);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            switch (requestCode) {
                case PERMISSION_REQUEST_GALLERY_PHOTO:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //GRANTED
                        mImagePresenter.chooseGalleryClick();
                    } else {
                        //DENIED
                        showSettingsDialog();
                    }
                    break;
                case PERMISSION_REQUEST_TAKE_PHOTO:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //GRANTED
                        mImagePresenter.cameraClick();
                    } else {
                        //DENIED
                        showSettingsDialog();
                    }
                    break;
                default:
                    break;
            }
        } else {


        }
    }

    @OnClick({R.id.user_profile_photo,R.id.buttonSignOut,R.id.buttonBack,R.id.header_cover_image, R.id.upload_file_progress, R.id.btn_upload_file_without_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                finish();
                break;
            case R.id.buttonSignOut:
                CommonDialogs.modal2(context,new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Account user = sAccountHelper.getUserAccount();
                        if (sAccountHelper.removeAccount(user)){
                            //db
                            deleteAllUsersData();
                            Global.ClearLogedInUser(getContext());
                            Global.user2 = null;
                        }

                        Toast.makeText(getApplicationContext(), "از حساب کاربری خارج شدید.", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setResult(Activity.RESULT_OK, getIntent());
                                finish();
                            }
                        }, 100);

                        finish();
                    }
                });

                break;
            case R.id.user_profile_photo:
//                selectImage(this);
                SELECTED_IMAGE = AVATAR_SELECTED;
                break;
            case R.id.header_cover_image:
//                selectImage(this);
                SELECTED_IMAGE = PROFILE_SELECTED;
                break;
            case R.id.upload_file_progress:
                mUploaderPresenter.onFileSelected(mImagePresenter.getImage(), Global.user2.getUserCode() + "", "avatar");
                break;
            case R.id.btn_upload_file_without_progress:
                DialogUtil.showLoadingDialog(this);
                mUploaderPresenter.onFileSelectedWithoutShowProgress(mImagePresenter.getImage(), "androidwave", "info@androidwave");
                break;
        }
    }


    private void selectImage(Context context) {
        txtProgress.setText("");
//        final CharSequence[] items = {getString(R.string.camera), getString(R.string.gallery),
//                getString(R.string.cancel)};
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityProfile.this);
//        builder.setItems(items, (dialog, item) -> {
//            if (items[item].equals("Capture Photo")) {
//                mImagePresenter.cameraClick();
//            } else if (items[item].equals("Choose from Library")) {
//                mImagePresenter.chooseGalleryClick();
//            } else if (items[item].equals("Cancel")) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
        SelectSource(context,findViewById(android.R.id.content) , mImagePresenter);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getApplicationContext(), "دوباره تلاش کنید.", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadCompleted() {
        DialogUtil.hideLoading();
        Toast.makeText(getApplicationContext(), "بروز شد", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUploadProgress(int progress) {
        Toast.makeText(getApplicationContext(), "در حال ارسال...." + progress, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean checkPermission(String permisson) {
        return AndroidOs.checkPermission(context , permisson);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showPermissionDialog(boolean isGallery) {
//        //not Work
//        ActivityCompat.requestPermissions(
//                ((Activity)context),
//                permissions,
//                PERMISSION_REQUEST_GALLERY_PHOTO);
//
//        //Work OK
//        ActivityCompat.requestPermissions(this, new String[]
//                {
//                        CAMERA,
//                        RECORD_AUDIO,
//                        SEND_SMS,
//                        GET_ACCOUNTS
//                }, PERMISSION_REQUEST_GALLERY_PHOTO);
//
//    <uses-permission android:name="android.permission.READ_CONTACTS" />
//    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
//    <uses-permission android:name="android.permission.CAMERA"/>
//    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
//    <uses-permission android:name="android.permission.SEND_SMS"/>
        DialogUtil.ShowMessageDialog(
            context,
            context.getString(R.string.permission_title),
            (isGallery) ? context.getString(R.string.permission_gallery_text):context.getString(R.string.permission_camera_text)
            ,isGallery);
    }

    @Override
    public File getFilePath() {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    @Override
    public void openSettings() {

    }


    @Override
    public void startCamera(File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            if (file != null) {
                Uri mPhotoURI0 = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI0);
                mPhotoFile = file;
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


//    @Override
//    public void startCamera(File file) {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            if (file != null) {
//
////                ContentValues values = new ContentValues(1);
////                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
////                Uri mPhotoURI = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
////                takePictureIntent.setClipData( ClipData.newRawUri( "", mPhotoURI ) );
////                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
//
//
//                //old
//                Uri mPhotoURI0 = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI0);
//                mPhotoFile = file;
//
//
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//
//
////        new PickerManagerBuilder(this,true, PickerManagerBuilder.SELECT_FROM_CAMERA)
////                .setOnImageReceivedListener(new PickerManagerBuilder.onImageReceivedListener() {
////                    @Override
////                    public void onImageReceived(Uri imageUri) {
//////                        Toast.makeText(activity,"Got image - " + imageUri, Toast.LENGTH_LONG).show();
////
////                    }
////                })
////                .setImageName("avatar")
////                .start();
//    }

    @Override
    public void chooseGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);

//        new PickerManagerBuilder(this,true, PickerManagerBuilder.SELECT_FROM_GALLERY)
//                .setOnImageReceivedListener(new PickerManagerBuilder.onImageReceivedListener() {
//                    @Override
//                    public void onImageReceived(Uri imageUri) {
////                        Toast.makeText(activity,"Got image - " + imageUri, Toast.LENGTH_LONG).show();
////                        imageView.setImageURI(imageUri);
////
////                        ir.sajjadyosefi.android.xTubeless.classes.model.File map1 = new ir.sajjadyosefi.android.xTubeless.classes.model.File();
////                        map1.setTitle(imageUri.toString().substring(imageUri.toString().lastIndexOf("/")+1));
////                        map1.setRequestContentId(1);
////                        map1.setFrame(1);
////                        map1.setFileType(MAP_1);
////                        map1.setUri(imageUri.toString());
////                        map1.setType(FILES);
////                        fileList.add(map1);
////                        adapter_Posts.notifyDataSetChanged();
////
////                        if (fileCount == fileList.size()){
////                            ((Button)(findViewById(R.id.buttonGallery))).setEnabled(false);
////                            ((Button)(findViewById(R.id.buttonCamera))).setEnabled(false);
////                        }
//                    }
//                })
//                .setImageName("avatar")
//                .start();

    }

    @Override
    public File newFile() {
        Calendar cal = Calendar.getInstance();
        long timeInMillis = cal.getTimeInMillis();
        String mFileName = String.valueOf(timeInMillis) + ".jpeg";
        File mFilePath = getFilePath();
        try {
            File newFile = new File(mFilePath.getAbsolutePath(), mFileName);
            newFile.createNewFile();
            return newFile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void showErrorDialog() {
        Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayImagePreview(File mFile) {

        if (SELECTED_IMAGE == AVATAR_SELECTED) {
            LoadImages.loadAvatarimage(mFile, userAvatarPhoto);
            mUploaderPresenter.onFileSelected(mImagePresenter.getImage(), Global.user2.getUserCode() + "", "avatar");

            //service
//            if (mImagePresenter.getImage().isEmpty()) {
//                Toast.makeText(this, "Select file first", Toast.LENGTH_LONG).show();
//                return;
//            }
//            Intent mIntent = new Intent(this, FileUploadService.class);
//            mIntent.putExtra("mFilePath", mImagePresenter.getImage());
//            FileUploadService.enqueueWork(this, mIntent);

        }else if (SELECTED_IMAGE == PROFILE_SELECTED) {
            LoadImages.loadProfileimage(mFile, headerProfileImage);

            mUploaderPresenter.onFileSelected(mImagePresenter.getImage(), Global.user2.getUserCode() + "", "profile");
        }

    }




    @Override
    protected void onStart() {
        super.onStart();
//        AdView adView = findViewById(R.id.adView);

        if (Global.user2.isUserAdmin()) {
//        if (true) {
//            adView.setVisibility(View.VISIBLE);
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {

//            }
//        });


//            //google ads
//            AdRequest adRequest = new AdRequest.Builder().build();
//
////        adView.setAdSize(AdSize.BANNER);
////        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");         //test google
//            adView.loadAd(adRequest);
//
//            adView.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    // Code to be executed when an ad finishes loading.
//                    Toast.makeText(getContext(), "onAdLoaded", Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onAdFailedToLoad(int errorCode) {
//                    // Code to be executed when an ad request fails.
//                    Toast.makeText(getContext(), errorCode + "", Toast.LENGTH_LONG).show();
//
//                }
//
//                @Override
//                public void onAdOpened() {
//                    // Code to be executed when an ad opens an overlay that
//                    // covers the screen.
//                    Toast.makeText(getContext(), "onAdOpened", Toast.LENGTH_LONG).show();
//
//                }
//
//                @Override
//                public void onAdClicked() {
//                    // Code to be executed when the user clicks on an ad.
//                    Toast.makeText(getContext(), "onAdClicked", Toast.LENGTH_LONG).show();
//
//                }
//
//                @Override
//                public void onAdLeftApplication() {
//                    // Code to be executed when the user has left the app.
//                    Toast.makeText(getContext(), "onAdLeftApplication", Toast.LENGTH_LONG).show();
//
//                }
//
//                @Override
//                public void onAdClosed() {
//                    // Code to be executed when the user is about to return
//                    // to the app after tapping on an ad.
//                    Toast.makeText(getContext(), "onAdClosed", Toast.LENGTH_LONG).show();
//
//                }
//            });
        }else {
//            adView.setVisibility(View.GONE);
        }
    }

    private void getImageFromSevice() {
        //اینجا میشه هم درخواست جدید فرستاد هم از شی کاربر تصاویر را نشان داد

        ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest request =
                new LoginRequest(context, Global.user2.getUserCode() + "", "", DeviceUtil.GetAndroidId(context));
        retrofit2.Callback callback = new retrofit2.Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                ServerResponseBase responseX = null;

                try {
                    if (response.body() == null){
                        new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL);
                    }

                    responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
                    if (response.body() != null ) {
                        if (responseX.getTubelessException().getCode() != 0) {
                            if (responseX.getTubelessException().getCode() > 0) {
                                if (call != null && response != null) {
                                    Object object = gson.fromJson(jsonElement.getAsString(), (Type) User.class);
                                    User tmpUser = Primitives.wrap(User.class).cast(object);

//                                    Global.user2.setProfileImage(tmpUser.getProfileImage());
//                                    Global.user2.setUserImage(tmpUser.getUserImage());
//
//                                    if (Global.user2.update(Global.user2.getUserId()) > 0){
//
//                                        String profileImage = Global.user2.getProfileImage();
//                                        String avatarImage = Global.user2.getUserImage();
//
//                                        LoadImages.loadProfileimage(profileImage, headerProfileImage);
//                                        LoadImages.loadAvatarimage(avatarImage, userAvatarPhoto);
//
//                                    }else {
//                                        String profileImage = Global.user2.getProfileImage();
//                                        String avatarImage = Global.user2.getUserImage();
//
//                                        LoadImages.loadProfileimage(profileImage, headerProfileImage);
//                                        LoadImages.loadAvatarimage(avatarImage, userAvatarPhoto);
//                                    }
                                }
                            } else {
                                new TubelessException(responseX.getTubelessException().getCode());
                            }
                        }else {
                            new TubelessException(responseX.getTubelessException().getCode());
                        }
                    }else {
                        new TubelessException(TUBELESS_RESPONSE_BODY_IS_NULL);
                    }
                }catch (Exception sException) {
                    int a = 5 ;
                    a++;
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                LoadImages.loadAvatarimage(new String(), userAvatarPhoto);
                LoadImages.loadProfileimage(new String(), headerProfileImage);
            }

            private void retry(Call call) {
                call.clone().enqueue(this);
            }
        };
        Global.apiManagerTubeless.getProfileImages(request, callback);
    }

    private void getImageFromSeviceStream() {
//        ir.sajjadyosefi.android.xTubeless.classes.model.File map1 = new ir.sajjadyosefi.android.xTubeless.classes.model.File();
//        map1.setTitle("imageUri.toString().substring(imageUri.toString().lastIndexOf");
//        map1.setRequestContentId(1);
//        map1.setFrame(1);
//        map1.setFileType(MAP_1);
//        map1.setUri("http://sajjadyosefi.ir/img/profile.jpg");
//        map1.setType(FILES);
//
//        final RetrofitImageLoader imageLoader = new RetrofitImageLoader(userProfilePhoto);
//            ImageRequest.DEFAULT_BODY.setContentId("1");
//            ImageRequest.DEFAULT_BODY.setFrame("x");
//            Global.apiManagerTubeless.getImageFromRemoteServer(context, ImageRequest.DEFAULT_BODY,new retrofit2.Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                ResponseBody body = response.body();
//                if (response.isSuccessful() && body != null) {
//                    try {
//                        imageLoader.execute(map1 , body.byteStream());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Log.d(TAG, "Retrofit onResponse(): CODE = [" + response.code() + "], MESSAGE = [" + response.message() + "]");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(TAG, "Retrofit onFailure(): t = [" + t + "]");
//            }
//        });
    }

    @Override
    public String getRealPathFromUri(Uri contentUri) {
//        String realpath0 = UriUtil.getRealPathFromUri(this,contentUri); ///storage/5FFB-19EA/DCIM/100ANDRO/DSC_0185.JPG
        String realpath =  UriUtil.getPath(this,contentUri);            ///storage/5FFB-19EA/DCIM/100ANDRO/DSC_0185.JPG

        return realpath;
    }

    public void showSettingsDialog() {
        DialogUtil.ShowMessageDialog(context,context.getString(R.string.permission_title),context.getString(R.string.WeNeedYourDeviceInfo));


//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.message_need_permission));
//        builder.setMessage(getString(R.string.message_grant_permission));
//        builder.setPositiveButton(getString(R.string.label_setting), (dialog, which) -> {
//            dialog.cancel();
//            openSettings();
//        });
//        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.cancel());
//        builder.show();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
//            user_wallet.setText(" موجودی حساب شما : " + Global.user2.getBalanse());
            if (Global.user2.getWallet() != null) {
                user_wallet.setText(" موجودی شما : " + SamanString.addSpratorX((Global.user2.getWallet().getAmount())) + " " + "ریال");
            }
        }catch (Exception ex){
        }
    }
}
