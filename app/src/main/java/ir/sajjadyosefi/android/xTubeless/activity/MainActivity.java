package ir.sajjadyosefi.android.xTubeless.activity;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.andremion.counterfab.CounterFab;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.ResetPasswordActivity;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;
import ir.sajjadyosefi.android.xTubeless.activity.account.ResetPasswordHelpActivity;
import ir.sajjadyosefi.android.xTubeless.activity.account.profile.MainActivityProfile;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.lottery.activity.SearchLotteryActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User2;
import ir.sajjadyosefi.android.xTubeless.classes.model.wallet.Wallet;
import ir.sajjadyosefi.android.xTubeless.dialog.CommonDialogs;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import it.sephiroth.android.library.bottomnavigation.BadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.FloatingActionButtonBehavior;
import it.sephiroth.android.library.bottomnavigation.MiscUtils;
import retrofit2.Call;
import static android.util.Log.VERBOSE;
//import static ir.sajjadyosefi.accountauthenticator.activity.AuthenticatorActivity.PARAM_USER;
import static ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_AMLAK_LIST_1;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_MULTY_SELECT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.fragmentx2;

import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity.REQUEST_CATEGORY_LIST;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.CATEGORY_CAR_BRANDS_ID;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.Instagram;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.Telegram;

@TargetApi (Build.VERSION_CODES.KITKAT_WATCH)
public class MainActivity extends TubelessActivity implements BottomNavigation.OnMenuItemSelectionListener  {

    //val
    public static int LOGIN_REQUEST = 101 ;
    private static int OPEN_PROFILE_REQUEST_CODE = 102 ;
    private static int OPEN_CATEGORY_REQUEST_CODE = 103;
    public static int CHANGE_PASSWORD_REQUEST_CODE = 104;
    public static int RESET_PASSWORD_REQUEST_CODE = 105;


    public static int payType;


    private BottomNavigation mBottomNavigation;
    private DrawerLayout drawer_layout;
    public ViewPager viewPager;
    public static FirstFragmentsAdapter firstFragmentsAdapter;
    ViewGroup root;
    public ViewGroup rootView ;


    //Top of page
    private ImageView headerImageView;
    private CollapsingToolbarLayout toolbar_layout;
    private TextView headerText;
    private Toolbar toolbar;
    private AppBarLayout AppBarLayout01;
    private CounterFab counterFab;
    private int lastSelectedPosition = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_REQUEST || requestCode == GO_TO_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
//                data.putString("USER_CODE", accountUserCode);
//                data.putString("USER_TYPE", userTypeCode);
//                data.putString("USER_NAME", accountUserName);
//                data.putString("USER_PASS", accountUserPass);
//                bundle.putString("USER_OBJECT", gson.toJson(signInUser));
                if (data.hasExtra("USER_CODE")){
                    Toast.makeText(getContext(),getContext().getString(R.string.welcome) ,Toast.LENGTH_LONG).show();
                    Gson gson = new Gson();
                    Global.user2 = gson.fromJson(data.getExtras().getString("USER_OBJECT"), User2.class);
                    AccountGeneral.setUserCode(Global.user2.getUserCode() + "");

                    if(User2.savedToDataBase(Global.user2)){
                        Wallet.savedToDataBase(Global.user2);
//                        if (Global.user != null && Global.user.isUserAdmin()) {
//                            firstFragmentsAdapter.notifyList();
//                            updatedrawableMenuItems();
//                        }
                    }
                }

//                if ((Global.user2 != null && Global.user.isUserAdmin()) || (data.hasExtra("MustRefresh") && (data.getExtras().getBoolean("MustRefresh")) ))
//                    firstFragmentsAdapter.notifyList();
                updatedrawableMenuItems();
            }else {
                updatedrawableMenuItems();
            }
        }
        if (requestCode == LOGIN_REQUEST) {

        }
        if (requestCode == RESET_PASSWORD_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                CommonDialogs.modal(getContext());
            }
        }
        if (requestCode == REQUEST_CATEGORY_LIST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle dd = data.getExtras();

                List<CategoryItem> list = (List<CategoryItem>) dd.getSerializable("SelectedLIST");
                //Distinct
//                if (id <> 0 and hid == 0)
//                not selectable item  (id)
//
//                if (id <> 0 and hid <> 0)
//                selectable item  (id)
//
//                if (id = 0 and hid = 0 and CID <> 0)
//                all root (CID)
//
//                if (id == 0 and hid <> 0)
//                all not root (Hid)

                SelectedCategory = dd.getLong("SelectedCategory");
//                if (list.size() == 1) {
//                    TimelineRequest searchRequest = new TimelineRequest("", ((CategoryItem) list.get(0)).getID());
//                    searchRequest.setTtc(null);
//                    searchRequest.setPageSize("10");
//                    searchRequest.setPageIndex("0");
//                    searchRequest.setActive(true);
//
//                    if (Global.user2 != null) {
//                        searchRequest.setUserCode(Global.user2.getUserCodeAsString());
//                    } else {
//                        searchRequest.setUserCode(null);
//                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type", FRAGMENT_FILTER_RESULT);
//                    bundle.putSerializable("SearchRequest",searchRequest);
//                    getContext().startActivity(ContainerActivity.getIntent(getContext(), bundle));
//                }
            }else {
                SelectedCategory = 0;
            }
        }
    }
    public static long SelectedCategory = 0;
    boolean backButtonPressedInPayment = false;

    public static String getAppDownloadedStore()  {
//        BuildConfig.FLAVOR
//        BuildConfig.APPLICATION_ID
//        BuildConfig.BASE_URLX
//        BuildConfig.BUILD_TYPE
//        BuildConfig.DEBUG
//        BuildConfig.FLAVOR_market
//        BuildConfig.FLAVOR_version_name
//        BuildConfig.HOST
//        BuildConfig.URL_PREFIX
//        BuildConfig.VERSION_CODE
//        BuildConfig.VERSION_NAME

        if (BuildConfig.FLAVOR_market.equals("bazzar")){
            return "bazar";

        }else if (BuildConfig.FLAVOR_market.equals("jhobin")){
            return "jhobin";

        }else if (BuildConfig.FLAVOR_market.equals("iranApps")){
            return "iranApps";

        }else if (BuildConfig.FLAVOR_market.equals("socialNetwork")){
            return "socialNetwork";

//        }else if (BuildConfig.FLAVOR_version_name.equals("myket")){
        }else if (BuildConfig.FLAVOR_market.equals("myket")){
            return "myket";
        }else {
            return "myket";
        }
    }

    public static boolean isFreeStore(Context mContext , Configuration configuration)  {

        if (Global.aConfig.getStoreList().size() == 1){
            return Global.aConfig.getStoreList().get(0).isFree();
        }else
            return false;



//        Boolean aBoolean = false;
//
//        try {
//            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
//            String versionName = pInfo.versionName;
//            if (versionName.contains("a")) {
//                aBoolean = configuration.getData().getConfiguration().Play;
//
//            } else if (versionName.contains("b")) {
//                aBoolean = configuration.getData().getConfiguration().Bazar;
//
//            } else if (versionName.contains("m")) {
//                aBoolean = configuration.getData().getConfiguration().Myket;
//
//            } else if (versionName.contains("i")) {
//                aBoolean = configuration.getData().getConfiguration().Iranapps;
//
//            } else if (versionName.contains("j")) {
//                aBoolean = configuration.getData().getConfiguration().Jhobin;
//
//            } else if (versionName.contains("s")) {
//                aBoolean = configuration.getData().getConfiguration().Social;
//
//            } else if (versionName.contains("k")) {
//                aBoolean = configuration.getData().getConfiguration().Kandoo;
//            } else {
//                aBoolean = configuration.getData().getConfiguration().Play;
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            aBoolean = false;
//        }
//        return aBoolean;
    }
    public static String checkStore(Context mContext , Configuration configuration)  {
        String storeName = "";

        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String versionName = pInfo.versionName;
//            if (versionName.contains("a")) {
//                storeName = configuration.getData().getConfiguration().PlayStr;
//
//            } else if (versionName.contains("b")) {
//                storeName = configuration.getData().getConfiguration().BazarStr;
//
//            } else if (versionName.contains("m")) {
//                storeName = configuration.getData().getConfiguration().MyketStr;
//
//            } else if (versionName.contains("i")) {
//                storeName = configuration.getData().getConfiguration().IranappsStr;
//
//            } else if (versionName.contains("j")) {
//                storeName = configuration.getData().getConfiguration().JhobinStr;
//
//            } else if (versionName.contains("s")) {
//                storeName = configuration.getData().getConfiguration().SocialStr;
//
//            } else if (versionName.contains("k")) {
//                storeName = configuration.getData().getConfiguration().KandooStr;
//
//            } else {
//                storeName = configuration.getData().getConfiguration().PlayStr;
//            }

        }catch (Exception e){
            e.printStackTrace();
            storeName = "";
        }
        return storeName;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        BottomNavigation.Companion.setDEBUG(BuildConfig.DEBUG);
        setContentView(R.layout.activity_main);
        rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);


        root = findViewById(R.id.CoordinatorLayout01);
        counterFab = (CounterFab) findViewById(R.id.fabx);
        headerImageView = (ImageView) findViewById(R.id.header);
        toolbar_layout =  findViewById(R.id.toolbar_layout);
        headerText = (TextView) findViewById(R.id.headerText);
        toolbar = findViewById(R.id.toolbar);
        AppBarLayout01 = findViewById(R.id.AppBarLayout01);
        drawer_layout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.ViewPager01);




//        loadTubelessAccountData(getContext());


        setSupportActionBar(toolbar);
        drawableMenu(toolbar);


        final int statusbarHeight = getStatusBarHeight();
//        final boolean translucentStatus = false;
        final boolean translucentStatus = hasTranslucentStatusBar();
        final boolean translucentNavigation = hasTranslucentNavigation();

        MiscUtils.INSTANCE.log(VERBOSE, "translucentStatus: %b", translucentStatus);

        if (translucentStatus) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
            params.topMargin = -statusbarHeight;

            params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
//            params.topMargin = statusbarHeight;

            int a = 5;
            a++;
        }

        if (translucentNavigation) {
            final DrawerLayout drawerLayout = getDrawerLayout();
            if (null != drawerLayout) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) drawerLayout.getLayoutParams();
                params.bottomMargin = 0;

//                params.bottomMargin = 400;
//                params.bottomMargin = getNavigationBarHeight();


//                params = (ViewGroup.MarginLayoutParams)(findViewById(R.id.ccdsd4)).getLayoutParams();
//                params.bottomMargin = getNavigationBarHeight();

            }
        }

        initializeBottomNavigation(savedInstanceState);
        initializeUI(savedInstanceState);



        //loadNews();

////        toolbar
////          mBottomNavigation

//        Thread nonstandard =new Thread(new Runnable() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//
//            @Override
//            public void run() {
//                System.out.println("i'm zero ");
//            }
//        });
//        nonstandard.start();




//        Uri data2 = getIntent().getData();
//        ZarinPal.getPurchase(getContext()).verificationPayment(data2, new OnCallbackVerificationPaymentListener() {
//            @Override
//            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
//                if(isPaymentSuccess){
////                    //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();
//                    DialogUtil.showLoadingDialog(getContext());
//
////                    if(payType == 1){
////                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip1Month() + StaticValue.bourseState.totalPayedValue;
////                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip1Month();
////                    }
////                    if(payType == 2){
////                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip2Month() + StaticValue.bourseState.totalPayedValue;
////                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip2Month();
////                    }
////                    if(payType == 3){
////                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip3Month() + StaticValue.bourseState.totalPayedValue;
////                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip3Month();
////                    }
////                    if(payType == 6){
////                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip6Month() + StaticValue.bourseState.totalPayedValue;
////                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip6Month();
////                    }
//                    if(payType == 100){
//                        //کارت سوخت
//                        firstFragmentsAdapter.kartesokhtPayComplete(rootView);
//                        cancelByBackbuttonPressed = false;
//                        int a = 5 ;
//                        a++;
//                    }
//                    if(payType == 0) {
//                        StaticValue.bourseState.totalPayedValue = 0 + StaticValue.bourseState.totalPayedValue;
//                        StaticValue.bourseState.lastPayedValue = 0;
//                    }
//
//
//                    if(payType != 100) {
//
//                        StaticValue.bourseState.lastPayedType = payType;
////                        StaticValue.bourseState.updateAfterPay(30, StaticValue.configuration.getResponseStatus().getDate());
//
//                        //refresh tab 3
//                        firstFragmentsAdapter.notifyDataSetChanged();
//
//                        //load tsb 3
//                        viewPager.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                getViewPager().setCurrentItem(3,true);
//                            }
//                        }, 200);
//                    }
//
//                }else {
//                    //not ok
////                    show message refID
////                    Toast.makeText(getContext(),"not ok " ,Toast.LENGTH_LONG).show();
//
//                    cancelByBackbuttonPressed = true;
//                }
//
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        DialogUtil.hideLoading();
//                    }
//                },2000);
//
//            }
//        });

        if (
                BuildConfig.FLAVOR_version_name.equals("yafte") ||
                BuildConfig.FLAVOR_version_name.equals("estekhdam") ||
                        BuildConfig.FLAVOR_version_name.equals("moz") ||
                        BuildConfig.FLAVOR_version_name.equals("amlak") ||
                        BuildConfig.FLAVOR_version_name.equals("winner") ||
                        BuildConfig.FLAVOR_version_name.equals("yadak") ||
                        BuildConfig.FLAVOR_version_name.equals("bourse")) {
            getBottomNavigation().setVisibility(View.VISIBLE);
        }else {
            getBottomNavigation().setVisibility(View.GONE);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

//        View noticeView = (View) findViewById(R.id.noticeView);
//        AdadBannerAd bannerAd = (AdadBannerAd) findViewById(R.id.banner_1);
//
//        //تعریف کردن یک listener برای آگاهی از وضعیت تبلیغ بنری
//        bannerAd.setAdListener(new AdadAdListener() {
//            @Override
//            public void onLoaded() {
//                Toast.makeText(getContext(),"با کلیک روی تبلیغ آن را حذف خواهید کرد" ,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onShowed() {
//
//            }
//
//            @Override
//            public void onActionOccurred(int i) {
//                System.out.println();
//                noticeView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onError(int i, String s) {
//
//            }
//
//            @Override
//            public void onClosed() {
//
//            }
//        });
        if (checkIsFirstRun()){
            drawer_layout.openDrawer(Gravity.LEFT);
            setFirstRunIsDone();
        }

        if(backButtonPressedInPayment){
            DialogUtil.hideLoading();
        }

        //startActivity(new Intent(this, filterDetailsActivity.class));




//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (firstFragmentsAdapter != null) {
//                    firstFragmentsAdapter.kartesokhtPayComplete(rootView);
//                }
//
//            }
//        },10000);
    }

    private void loadNews() {

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), view.toString(), Toast.LENGTH_SHORT).show();
//                counterFab.setCount(10); // Set the count value to show on badge
//                counterFab.increase(); // Increase the current count value by 1

//                Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
//                getContext().startActivity(autoActivityIntent);

                //getContext().startActivity(new Intent(getContext(), ContainerActivity.class));
            }
        };

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), TimelineListResponse.class) {
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

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void t_onSuccess(Object response) {
                TimelineListResponse responseX = (TimelineListResponse) response;
                counterFab.setCount(responseX.getTimelineList().size());


                if(responseX.getTimelineList().size() > 0){
                    counterFab.setVisibility(View.VISIBLE);
                }

                for (TimelineItem item : responseX.getTimelineList()){

                    ////////////////////////////////// on click ///////////////////////////////////
                    View.OnClickListener goToReadNews = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            counterFab.setCount(10); // Set the count value to show on badge
//                            counterFab.increase(); // Increase the current count value by 1



                            Intent intent = new Intent(getContext(), ReadBlogActivity.class);
                            Gson gson = new Gson();
                            String json = gson.toJson(item);

                            // Old Transfer
//                    intent.putExtra("Object", json);

                            //New Transfer
                            Bundle bundle = new Bundle();
                            bundle.putString("Object", json);
                            bundle.putString("Type", "MainPageNews");
                            intent.putExtras(bundle);

                            getContext().startActivity(intent);
                            ((Activity) getContext()).overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                        }
                    };

                    counterFab.setOnClickListener(goToReadNews);
                    AppBarLayout01.setOnClickListener(goToReadNews);
                    toolbar.setOnClickListener(goToReadNews);
                    headerText.setOnClickListener(goToReadNews);
                    headerImageView.setOnClickListener(goToReadNews);


                    Picasso.get()
                            .load("item.getPicture()")
                            .placeholder(R.drawable.jpg_paeez)
                            //.centerInside()
                            //.transform(transformation)
                            .into(headerImageView, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError(Exception e) {

                                    Picasso.get()
                                            .load(R.drawable.jpg_paeez)
                                            //.transform(transformation)
                                            .into(headerImageView);
                                }
                            } );

                    toolbar.setTitle(item.getTitle());
                    toolbar_layout.setTitle(item.getTitle());
//                    headerText.setText(item.getText() + "\n" + item.getRegisterDate());
                    headerText.setText("\n" + item.getRegisterDate());
                    ////////////////////////////////// end on click ///////////////////////////////////
                }
            }
        };
        Global.apiManagerTubeless.getTubelessNews(ssssssss);
    }

    private void drawableMenu(Toolbar toolbar) {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.about_us, R.string.cancel);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        //add humberger
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.save, R.string.cancel);
        drawer_layout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

//        //humburger icon
//        toolbar.setNavigationIcon(R.drawable.png_upload);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.inputError),Toast.LENGTH_SHORT).show();
//            }
//        });
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)toolbar.getLayoutParams();
        //params.bottomMargin = 100;






        updatedrawableMenuItems();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_profile) {
                    Bundle bundle = new Bundle();
                    if (Global.user2 != null && Global.user2.isUserAdmin())
                        bundle.putBoolean("MustRefresh" , true);

//                    getActivity().startActivityForResult(ProfileActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);
                    getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(),bundle), LOGIN_REQUEST);

                }else if (id == R.id.nav_role) {

                    Bundle bundle = new Bundle();
                    bundle.putString("WebType" , "rule");
                    getActivity().startActivityForResult(WebViewActivity.getIntent(getContext(),bundle), LOGIN_REQUEST);

                    //todo uncomment for bourse bazaar and etc...
//                }else if (id == R.id.nav_resource) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("WebType" , "source");
//                    getActivity().startActivity(WebViewActivity.getIntent(getContext(),bundle));

                }else if (id == R.id.nav_about_us) {
                    Bundle bundle = new Bundle();
                    bundle.putString("WebType" , "about");
                    getActivity().startActivity(WebViewActivity.getIntent(getContext(),bundle));
                }else if (id == R.id.nav_electedAmlak) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , ITEM_TYPE_AMLAK_LIST_1);
                    bundle.putInt("CAT_COUNT", 10);
                    getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
                }else  if (id == R.id.nav_forgetpassword) {
                    Bundle bundle = new Bundle();
                    if (Global.user2 == null) {
                        bundle.putInt("type", 1);
                        Intent intent = ResetPasswordActivity.getIntent(getContext(), bundle);

                        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_ADMIN_USER);
                        intent.putExtra(AuthenticatorActivity.PARAM_MOBILE, "");

                        if (Global.user2 == null) {
                            intent.putExtra(AuthenticatorActivity.PARAM_USER_CODE, "");
                        } else {
                            intent.putExtra(AuthenticatorActivity.PARAM_USER_CODE, String.valueOf(Global.user2.getUserCode()));
                        }

                        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                        getActivity().startActivityForResult(intent, RESET_PASSWORD_REQUEST_CODE);
                    }else {
                        startActivity(new Intent(getContext(),ResetPasswordHelpActivity.class));
                    }

                }else  if (id == R.id.nav_login) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , 1);
                    Intent intent = SignInActivity.getIntent(getContext(),bundle);
//                    intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
//                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS); //todo check and fix
                    intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
                    //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
                    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                    getActivity().startActivityForResult(intent, LOGIN_REQUEST);


//                }else  if (id == R.id.nav_account) {
//                    // Handle the camera action
//                    if (Global.user != null) {
//                        Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
//                        getActivity().startActivity(autoActivityIntent);
//                    }else {
//                        Intent autoActivityIntent = new Intent(getContext(), LoginActivity.class);
//                        getActivity().startActivity(autoActivityIntent);
//                    }
//
////                } else if (id == R.id.nav_gallery) {
////                    Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
////                    getActivity().startActivity(autoActivityIntent);
//                } else if (id == R.id.nav_add_new_yafte_item) {
////                    Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
////                    getContext().startActivity(autoActivityIntent);
//
//                    getContext().startActivity(new Intent(getContext(), RegNewYafteActivity.class));

//                } else if (id == R.id.nav_add_new_yadak_item) {
////                    if (getContext().getPackageName().contains("moz")){
////                        final BottomSheetDialog progressDialog = new BottomSheetDialog(getContext());
////                        TubelessException.ShowSheetDialogMessage(getContext(), progressDialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
////                            @Override
////                            public void onClick(View view) {
////                                progressDialog.dismiss();
////                            }
////                        });
////                    }else {
////                        getActivity().startActivity(new Intent(getContext(), SearchByNationalCodeActivity.class));
////                        getActivity().finish();
////                    }
//                    getContext().startActivity(new Intent(getContext(), RegNewYadakActivity.class));


                } else if (id == R.id.nav_filter) {
                    try {
                        //1
//                        PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
//                        String versionName = pInfo.versionName;
//                        //todo 1 change version if not complete
//                        if (versionName.contains("4.3.0") || versionName.contains("1.3.0")|| versionName.contains("1.1.0")) {
//                            Toast.makeText(getContext(),"در حال آماده سازی هستیم." , Toast.LENGTH_LONG).show();
//                        } else {
//                            getContext().startActivity(new Intent(getContext(), KarteSokhtActivity.class));
//                        }

                        //2  ok = filter
                        //صفحه فیلتر ها
                        //پست های من
                        Bundle bundle = new Bundle();
                        bundle.putInt("type" , FRAGMENT_FILTER);
//                        bundle.putSerializable("LIST", (Serializable) responseX.getData());
                        getActivity().startActivity(ContainerActivity.getIntent(getContext(),bundle));

                    }catch (Exception ex){

                    }

//                } else if (id == R.id.nav_search_by_name) {
//                    if (getContext().getPackageName().contains("moz")){
//                        final BottomSheetDialog progressDialog = new BottomSheetDialog(getContext());
//                        TubelessException.ShowSheetDialogMessage(getContext(), progressDialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                progressDialog.dismiss();
//                            }
//                        });
//                    }else {
////                        getActivity().startActivity(new Intent(getContext(), SearchByNameActivity.class));
//
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("type" , TYPE_SEARCH_POST_BY_NAME);
//                        getActivity().startActivity(ContainerActivity.getIntent(getContext(),bundle));
//                    }

                } else if (id == R.id.nav_contact_us) {
//                    ContainerActivity.type = 2 ;
//                    getContext().startActivity(new Intent(getContext(), ContainerActivity.class));

                    Bundle bundle = new Bundle();
                    bundle.putInt(ContactUsActivity.Type , ContactUsActivity.CONTACT_US);
                    getActivity().startActivity(ContactUsActivity.getIntent(getContext(),bundle));

//                } else if (id == R.id.nav_test) {
                    //share on telegram

//                    final String appName = "org.telegram.messenger";
//                    final boolean isAppInstalled = isAppAvailable(getApplicationContext(), appName);
//                    if (isAppInstalled)
//                    {
//                        Intent myIntent = new Intent(Intent.ACTION_SEND);
//                        myIntent.setType("text/plain");
//                        myIntent.setPackage(appName);
//                        myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
//                        startActivity(Intent.createChooser(myIntent, "Share with"));
//                    }
//                    else
//                    {
//                        Toast.makeText(getContext(), "Telegram not Installed", Toast.LENGTH_SHORT).show();
//                    }


                } else if (id == R.id.nav_telegram) {
                    //channel
                    Uri uri = Uri.parse(Telegram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("org.telegram.messenger");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(Telegram)));
                    }
                } else if (id == R.id.nav_instagram) {
                    Uri uri = Uri.parse(Instagram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.instagram.android");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(Instagram)));
                    }
                }

                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void updatedrawableMenuItems() {

        // get menux from navigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menux = navigationView.getMenu();

        MenuItem login = menux.findItem(R.id.nav_login);
        MenuItem profile = menux.findItem(R.id.nav_profile);

        if (Global.user2 == null) {
            login.setVisible(true);
            profile.setVisible(false);

        }else {
            login.setVisible(false);
            profile.setVisible(true);
        }

//        {
//            // find MenuItem you want to change
//            MenuItem nav_camara = menux.findItem(R.id.nav_account);
//
//            // set new text to the MenuItem
//            nav_camara.setTitle(getContext().getString(R.string.profile));
//            nav_camara.setIcon(R.drawable.ic_menu_send);
//            nav_camara.setVisible(false);

//        // do the same for other MenuItems
//        MenuItem nav_gallery = menux.findItem(R.id.nav_gallery);
//        nav_gallery.setTitle("NewTitleForGallery");
//        menux.add(R.string.code_melli);
//        }


//        if (BuildConfig.FLAVOR_version_name.equals("kartesokht")){
//            MenuItem kartesokht = menux.findItem(R.id.nav_karte_sokht);
//            kartesokht.setVisible(false);
//        }
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {

        if (null == savedInstanceState) {
            BottomNavigation x = getBottomNavigation();
            if(x != null) {
                x.setDefaultSelectedIndex(0);
                final BadgeProvider provider = getBottomNavigation().getBadgeProvider();
                provider.show(R.id.bbn_item3);
                provider.show(R.id.bbn_item2);
            }
        }
        if (null != getBottomNavigation()) {
//            Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
            mBottomNavigation.setMenuItemSelectionListener(this);
//            mBottomNavigation.setDefaultTypeface(typeface);
        }
    }

    protected void initializeUI(final Bundle savedInstanceState) {
        final FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        if (null != floatingActionButton) {
            floatingActionButton.setOnClickListener(view -> {
                Snackbar snackbar = Snackbar.make(root, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction(
                                "Action",
                                null
                        );
                snackbar.show();
            });

            if (hasTranslucentNavigation()) {
                final ViewGroup.LayoutParams params = floatingActionButton.getLayoutParams();
                if (CoordinatorLayout.LayoutParams.class.isInstance(params)) {
                    CoordinatorLayout.LayoutParams params1 = (CoordinatorLayout.LayoutParams) params;
                    if (FloatingActionButtonBehavior.class.isInstance(params1.getBehavior())) {
                        ((FloatingActionButtonBehavior) params1.getBehavior()).setNavigationBarHeight(getNavigationBarHeight());
                    }
                }
            }
        }

//        final ViewPager viewPager = getViewPager();
        if (null != viewPager && getBottomNavigation() != null) {
            getBottomNavigation().setMenuChangedListener(parent -> {
                firstFragmentsAdapter = new FirstFragmentsAdapter(getContext(), getActivity(), parent.getMenuItemCount());

                viewPager.setAdapter(firstFragmentsAdapter);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(
                            final int position, final float positionOffset, final int positionOffsetPixels) { }

                    @Override
                    public void onPageSelected(final int position) {
                        if (getBottomNavigation() != null) {
                            if (getBottomNavigation().getSelectedIndex() != position) {
                                getBottomNavigation().setSelectedIndex(position, false);
                            }
                        }
                    }
                    @Override
                    public void onPageScrollStateChanged(final int state) { }
                });
            });

            if (firstFragmentsAdapter != null && firstFragmentsAdapter.getItem(0) instanceof KartesekhtFragment) {

                int a = 5 ;
                a++;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if( keyCode == KeyEvent.KEYCODE_BACK ) {

                int index = viewPager.getCurrentItem();
                Fragment fragment = firstFragmentsAdapter.getItem(index);

                if (viewPager.getCurrentItem() == 1){

                    int ssss = ((ListFragment)fragment).getListType();
//                    if (ssss == LIST_CATEGORY_ONE_SELECT) {
//                        if (categoryLinkedList.getCurrent() != null && categoryLinkedList.getCurrent().getPreNode() != null) {
//                            changeSecoundFragment(getContext(), categoryLinkedList.getCurrent().getPreNode());
//                            categoryLinkedList.getCurrent().getPreNode().setNextNode(null);
//                            categoryLinkedList.setCurrent(categoryLinkedList.getCurrent().getPreNode());
//                            categoryLinkedList.getLast();
//                        } else {
//                            return super.onKeyDown(keyCode, event);
//                        }
//                    }else{
//                        categoryFiltersNodeList = new CategoryFiltersNodeList();
//                        List<IItems> iItems = new ArrayList<>();
//                        categoryFiltersNodeList.AddLast(createRootNode());
//                        ListFragment fragmentx2 = new ListFragment(getContext(), iItems, TYPE_LIST_CATEGORY, categoryFiltersNodeList.getHead());
//                        resetSecoundFragment(getContext(), fragmentx2);
//                    }
                }
                return super.onKeyDown(keyCode, event);
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public static boolean resetSecoundFragment(Context context , ListFragment listFragment){
        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().remove(fragmentx2).commit();
        fragmentx2 = listFragment;
        firstFragmentsAdapter.notifyDataSetChanged();
        return true;
    }

//    public static boolean changeSecoundFragment(Context context , CategoryItem categoryItem){
//        try {
//            try {
//                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().remove(fragmentx2).commit();
//            }catch (Exception e){
//
//            }
//
//            List<ParentItem> idList = new ArrayList<>();
////            StringBuilder stringBuilder = new StringBuilder();
////            stringBuilder.append(context.getResources().getInteger(R.integer.cat1Yadak));
////            stringBuilder.append("_");
////            stringBuilder.append(context.getResources().getInteger(R.integer.cat2Yadak));
////            stringBuilder.append("_");
////            stringBuilder.append(context.getResources().getInteger(R.integer.cat3Yadak));
//
//            Bundle bundle = new Bundle();
//            bundle.putString("ids", categoryItem.getID() + "");
//            fragmentx2 = new ListFragment(context, idList, TYPE_LIST_CATEGORIES_DATA, bundle);
//
//            firstFragmentsAdapter.notifyDataSetChanged();
//            return true;
//        }catch (Exception ex){
//            List<ParentItem> iItems = new ArrayList<>();
//            CategoryNode newNode = createRootNode();
//            categoryLinkedList.AddLast(newNode);
//            fragmentx2 = new ListFragment(context,iItems, LIST_CATEGORY_ONE_SELECT, categoryLinkedList.getHead());
//            return false;
//        }
//    }

//    public static boolean changeSecoundFragment(Context context , CategoryItem categoryItem){
////    public static boolean changeSecoundFragment(Context context, TimelineFilter timelineFilter ){
//        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().remove(fragmentx2).commit();
////        fragmentx2 = new BlankFragment(((AppCompatActivity)context).getSupportFragmentManager());
//        List<ParentItem> iItems = new ArrayList<>();
//        if (categoryItem == null) {
//            List<ParentItem> idList = new ArrayList<>();
////            CategoryItem cat = new CategoryItem(new TimelineItem());
////            cat.setId(10);
////            idList.add(cat);
//            fragmentx2 = new ListFragment(context,idList, TYPE_LIST_CATEGORIES_DATA);
//        } else {
//            fragmentx2 = new ListFragment(context, iItems, LIST_CATEGORY_ONE_SELECT, categoryItem);
//        }
//
//        firstFragmentsAdapter.notifyDataSetChanged();
//
//        return true;
//    }

    public int getStatusBarHeight() {
        return getSystemBarTint().getConfig().getStatusBarHeight();
    }

    @TargetApi (19)
    public boolean hasTranslucentStatusBar() {
        if (!mTranslucentStatusSet) {
            if (Build.VERSION.SDK_INT >= 19) {
                mTranslucentStatus = ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                mTranslucentStatus = false;
            }
            mTranslucentStatusSet = true;
        }
        return mTranslucentStatus;
    }

    @TargetApi (19)
    public boolean hasTranslucentNavigation() {
        if (!mTranslucentNavigationSet) {
            final SystemBarTintManager.SystemBarConfig config = getSystemBarTint().getConfig();
            if (Build.VERSION.SDK_INT >= 19) {
                boolean themeConfig =
                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                mTranslucentNavigation = themeConfig && config.hasNavigtionBar() && config.isNavigationAtBottom()
                        && config.getNavigationBarHeight() > 0;
            }
            mTranslucentNavigationSet = true;
        }
        return mTranslucentNavigation;
    }

    public int getNavigationBarHeight() {
        return getSystemBarTint().getConfig().getNavigationBarHeight();
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public boolean hasManagedToolbarScroll() {
        return hasAppBarLayout() && findViewById(R.id.CoordinatorLayout01) instanceof CoordinatorLayout;
    }

    public boolean hasAppBarLayout() {
        return getToolbar().getParent() instanceof AppBarLayout;
    }

    public BottomNavigation getBottomNavigation() {
        if (null == mBottomNavigation) {
            mBottomNavigation = findViewById(R.id.BottomNavigation);
        }
        return mBottomNavigation;
    }

    @Override
    public SystemBarTintManager getSystemBarTint() {
        if (null == mSystemBarTint) {
            mSystemBarTint = new SystemBarTintManager(this);
        }
        return mSystemBarTint;
    }

    public DrawerLayout getDrawerLayout() {
        return drawer_layout;
    }

    private ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {
        if (fromUser) {
            getBottomNavigation().getBadgeProvider().remove(itemId);


            if (BuildConfig.FLAVOR_version_name.equals("moz") ||
                    BuildConfig.FLAVOR_version_name.equals("amlak") ||
                    BuildConfig.FLAVOR_version_name.equals("estekhdam")) {
                // yadak
                if (null != getViewPager() && position != 2) {
                    getViewPager().setCurrentItem(position);
                    lastSelectedPosition = position;
                }
                if (position == 2) {
                    if (Global.user2 != null) {

                        Bundle bundle = new Bundle();
                        if (Global.user2 != null && Global.user2.isUserAdmin())
                            bundle.putBoolean("MustRefresh", true);

//                        getActivity().startActivityForResult(ProfileActivity.getIntent(getContext(),bundle),OPEN_PROFILE_REQUEST_CODE);
                        getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(), bundle), OPEN_PROFILE_REQUEST_CODE);


                    } else {
                        Toast.makeText(getContext(), getContext().getString(R.string.not_login), Toast.LENGTH_LONG).show();
                        getBottomNavigation().setSelectedIndex(lastSelectedPosition, false);
                    }
                }
            } else if (BuildConfig.FLAVOR_version_name.equals("winner")) {
                //tubeless
                if (null != getViewPager() && !(position == 3 || position == 2)) {
                    getViewPager().setCurrentItem(position);
                }
                if (position == 2) {
                    getActivity().startActivity(new Intent(getContext(), SearchLotteryActivity.class));
                }

                if (position == 3) {
                    if (Global.user2 != null) {
                        Bundle bundle = new Bundle();
                        if (Global.user2 != null && Global.user2.isUserAdmin())
                            bundle.putBoolean("MustRefresh", true);
                        getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(), bundle), OPEN_PROFILE_REQUEST_CODE);
                    } else {
                        Toast.makeText(getContext(), getContext().getString(R.string.not_login), Toast.LENGTH_LONG).show();
                    }
                }
            } else if (
                    BuildConfig.FLAVOR_version_name.equals("yafte") ||
                            BuildConfig.FLAVOR_version_name.equals("kartesokht") ||
                            BuildConfig.FLAVOR_version_name.equals("bourse")) {
                if (null != getViewPager()) {
                    getViewPager().setCurrentItem(position);
                }
            } else {
                //Default , tubeless
                if (null != getViewPager() && position != 2) {
                    getViewPager().setCurrentItem(position);
                }
                if (position == 2) {
                    if (Global.user2 != null) {

//Activity CATEGORY one select
                        Bundle bundle = new Bundle();
//                        if (Global.user2 != null && Global.user2.isUserAdmin())
//                            bundle.putBoolean("MustRefresh", true);
//
//                        //bundle.putInt("type" , TYPE_SELECT_CATEGORY);
//                        bundle.putInt("type" , FRAGMENT_CATEGORY);
//                        bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT);
//                        bundle.putInt("CAT_COUNT", 11);
//                        bundle.putString("item1","value1");
//                        bundle.putLong("category",CATEGORY_ID);
//                        bundle.putLong("parentId",0);
//                        Intent intent = new Intent(getContext(),ContainerActivity.class);
//                        intent.putExtras(bundle);
//                        getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(),bundle),REQUEST_CATEGORY_LIST);

////Activity CATEGORY multi select
                        //bundle.putInt("type" , TYPE_SELECT_CATEGORY);
                        bundle.putInt("type" , FRAGMENT_CATEGORY);
                        bundle.putInt("selectType" , LIST_CATEGORY_MULTY_SELECT);
                        bundle.putInt("CAT_COUNT", 11);
                        bundle.putString("item1","value1");
                        bundle.putLong("category",CATEGORY_CAR_BRANDS_ID);
                        bundle.putLong("parentId",0);
                        Intent intent = new Intent(getContext(),ContainerActivity.class);
                        intent.putExtras(bundle);
                        getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(),bundle),REQUEST_CATEGORY_LIST);


                    } else {
                        Toast.makeText(getContext(), getContext().getString(R.string.not_login), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {
        onMenuItemSelect(itemId,position,fromUser);

//        MiscUtils.INSTANCE.log(INFO, "onMenuItemReselect(" + itemId + ", " + position + ", " + fromUser + ")");

//        if (fromUser) {
//            final FragmentManager manager = getSupportFragmentManager();
//            ListFragment fragment = (ListFragment) manager.findFragmentById(R.id.fragment);
//            if (null != fragment) {
//                fragment.scrollToTop();
//            }
//        }
//        Toast.makeText(getContext(),position+"",Toast.LENGTH_LONG).show();
    }


    public boolean checkIsFirstRun() {
        SharedPreferences prefs = null;
        prefs =  this.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        return prefs.getBoolean("firstrun", true);
    }

    public boolean setFirstRunIsDone() {
        SharedPreferences prefs = null;
        prefs =  this.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);

        try {
            prefs.edit().putBoolean("firstrun", false).commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    public void BackButtonPressed() {


        if (doubleBackToExitPressedOnce) {
            finish();
        }
        this.doubleBackToExitPressedOnce = true;

//        Snackbar snack = Snackbar.make(rootView, getContext().getString(R.string.exit_double_tap_message), Snackbar.LENGTH_SHORT);
//        View view = snack.getView();
//        //view.setBackgroundColor(getContext().getResources().getColor(R.color.samanGreenText));
//        TextView tv = view.findViewById(R.id.snackbar_text);
//        tv.setTextColor(Color.WHITE);
//        //((SnackbarContentLayout) tv.getParent()).setBackgroundColor(getContext().getResources().getColor(R.color.samanGreenText));
//        snack.show();

        Toast.makeText(getContext(), getContext().getString(R.string.exit_double_tap_message),Toast.LENGTH_LONG).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        BackButtonPressed();
    }

    Calendar calendar = Calendar.getInstance();
    public double getFirstNumber() {
        return Double.parseDouble(calendar.get(Calendar.SECOND) + "");
    }

    public double getSecondNumber() {
        return Double.parseDouble(calendar.get(Calendar.SECOND) + "");
    }

    public void setAdditionResult(double result) {
        Log.d("test : ", Double.toString(result));
    }



}
