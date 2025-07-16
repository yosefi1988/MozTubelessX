package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.Objects;

import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.SpinnerAdapterA;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;

import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.AppUtility;
import retrofit2.Call;

import static ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.SelectedCategory;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_FILTER_RESULT;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.AMLAK;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.BOURSE;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.BUSINESSES;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.ESTEKHDAM;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.MOZ;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.WINNER;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.YADAK;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity.YAFTE;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity.REQUEST_CATEGORY_LIST;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.CATEGORY_ID;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FilterFragment extends Fragment {
    public static Context mContext;
    private Button button,buttonBack,buttonReg;
    private CheckBox checkbox;

    private int PAGE_TYPE = 0;

    View ostanLinearLayout ;
    RadioButton radioButton0,radioButton1,radioButton2,radioButton3;
    Button buttonShareApp,buttonSelectCategory;
    TextView login_title;
    RadioGroup rgRadios;
    Spinner spinner;
    List<CategoryItem> stateItems = null;
    private TimelineRequest timelineSearchRequest;

    //ad
//    private FrameLayout bannerParentLayout;
//    private IronSourceBannerLayout ironSourceBannerLayout;
    //private Placement rewardedVideoPlacementInfo;

    EditText editTextTitle;
    private static int selectedState;

    public FilterFragment(TimelineRequest searchRequest) {
        timelineSearchRequest = searchRequest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_filter, container, false);
        super.onCreate(savedInstanceState);

        mContext = getContext();
        ostanLinearLayout = view.findViewById(R.id.ostanLinearLayout);
        checkbox = view.findViewById(R.id.checkbox);
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);
        radioButton0 = view.findViewById(R.id.radioButton0);
        rgRadios = view.findViewById(R.id.rgRadios);
        login_title = view.findViewById(R.id.login_title);
        button = (Button) view.findViewById(R.id.buttonReg);
        buttonBack = (Button) view.findViewById(R.id.buttonBack);
        buttonReg = (Button) view.findViewById(R.id.buttonNewReg);
        buttonShareApp = (Button) view.findViewById(R.id.buttonShareApp);
        buttonSelectCategory = (Button) view.findViewById(R.id.buttonSelectCategory);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        //ad
        //bannerParentLayout = view.findViewById(R.id.banner_frame_layout);

        editTextTitle = (EditText) view.findViewById(R.id.editTextTitle);

        if (BuildConfig.FLAVOR.equals("yafte")||BuildConfig.FLAVOR.equals("moz")){
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
                bundle.putInt("selectType" , LIST_CATEGORY_ONE_SELECT);
                bundle.putInt("CAT_COUNT", 11);
                bundle.putString("item1","value1");
                bundle.putLong("category",CATEGORY_ID);
                bundle.putLong("parentId",0);
                Intent intent = new Intent(getContext(),ContainerActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(),bundle),REQUEST_CATEGORY_LIST);



            }
        });
        if (BuildConfig.FLAVOR.equals("estekhdam")) {
            PAGE_TYPE = ESTEKHDAM;
            rgRadios.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("moz")) {
            PAGE_TYPE = MOZ;
        }
        if (BuildConfig.FLAVOR.equals("yadak")) {
            PAGE_TYPE = YADAK;
            rgRadios.setVisibility(View.GONE);
        }

        if (BuildConfig.FLAVOR.equals("yafte")) {
            PAGE_TYPE = YAFTE;
            radioButton3.setVisibility(View.VISIBLE);
            buttonBack.setVisibility(View.GONE);
            buttonReg.setVisibility(View.VISIBLE);
        }
        if (BuildConfig.FLAVOR.equals("amlak")) {
            PAGE_TYPE = AMLAK;
            radioButton3.setVisibility(View.VISIBLE);
            rgRadios.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("amlak")) {
            PAGE_TYPE = AMLAK;
            radioButton3.setVisibility(View.VISIBLE);
            rgRadios.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("winner")) {
            PAGE_TYPE = WINNER;
            radioButton3.setVisibility(View.VISIBLE);
            rgRadios.setVisibility(View.GONE);
            checkbox.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("businesses")) {
            PAGE_TYPE = BUSINESSES;
            rgRadios.setVisibility(View.GONE);
            buttonSelectCategory.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("bourse")) {
            PAGE_TYPE = BOURSE;
            rgRadios.setVisibility(View.GONE);
            ostanLinearLayout.setVisibility(View.GONE);
        }


        if (timelineSearchRequest == null){
            buttonBack.setVisibility(View.GONE);
            buttonReg.setVisibility(View.VISIBLE);
            login_title.setVisibility(View.GONE);
            checkbox.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                if (validData()) {
                    int state;
                    if (PAGE_TYPE == WINNER || PAGE_TYPE ==  BOURSE)
                        state = 8133;
                    else
                        state = stateItems.get(selectedState - 1).getID();


                    timelineSearchRequest = new TimelineRequest(editTextTitle.getText().toString(), state);
                    if (PAGE_TYPE == MOZ) {
                        if (radioButton1.isChecked()) {
                            timelineSearchRequest.setTtc("7");
                        }
                        if (radioButton2.isChecked()) {
                            timelineSearchRequest.setTtc("8");
                        }
                        if (radioButton0.isChecked()) {
                            timelineSearchRequest.setTtc(null);
                        }
                    }else if (PAGE_TYPE == YAFTE) {
                        if (radioButton1.isChecked()) {
                            timelineSearchRequest.setTtc("5048");
                        }
                        if (radioButton2.isChecked()) {
                            timelineSearchRequest.setTtc("5050");
                        }
                        if (radioButton3.isChecked()) {
                            timelineSearchRequest.setTtc("5051");
                        }
                    }else if (PAGE_TYPE == WINNER) {


                    } else if ((PAGE_TYPE == AMLAK) || (PAGE_TYPE == WINNER) || (PAGE_TYPE == ESTEKHDAM) || (PAGE_TYPE == YADAK) || (PAGE_TYPE == BOURSE)) {
                        if (SelectedCategory == 0) {
                            timelineSearchRequest.setTtc(null);
                        } else {
                            timelineSearchRequest.setTtc(String.valueOf(SelectedCategory));
                        }
                    } else {
                        if (radioButton1.isChecked()) {
                            timelineSearchRequest.setTtc("3045");
                        }
                        if (radioButton2.isChecked()) {
                            timelineSearchRequest.setTtc("4043");
                        }
                        if (radioButton0.isChecked()) {
                            timelineSearchRequest.setTtc(null);
                        }
                    }
                    timelineSearchRequest.setPageSize("10");
                    timelineSearchRequest.setPageIndex("0");
                    timelineSearchRequest.setActive(true);

                    if(Global.user2 != null){
                        timelineSearchRequest.setUserCode(Global.user2.getUserCodeAsString());
                    }else {
                        timelineSearchRequest.setUserCode(null);
                    }


                    if (!checkbox.isChecked()) {

                    }else {

                    }

                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , FRAGMENT_FILTER_RESULT);
                    bundle.putInt("CAT_COUNT", 10);
                    bundle.putSerializable("SearchRequest",timelineSearchRequest);
                    getContext().startActivity(ContainerActivity.getIntent(getContext(),bundle));
                }else {
                    Toast.makeText(mContext,"اطلاعات را به درستی وارد کنید",Toast.LENGTH_LONG).show();
                    Objects.requireNonNull(((TubelessActivity)mContext).progressDialog).hide();
                    button.setEnabled(true);
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                buttonShareApp.performClick();
                return true;
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.user2 == null) {
                    Toast.makeText(mContext, mContext.getString(R.string.must_login), Toast.LENGTH_LONG).show();
                    Bundle bundle = null;
                    if (bundle == null){
                        bundle = new Bundle();
                    }
                    Intent intent = SignInActivity.getIntent(mContext,bundle);
                    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                    ((Activity)mContext).startActivityForResult(intent, GO_TO_LOGIN);
                }else {
                    //if (Global.user2.isUserAdmin() || Global.user2.isUserCreator()) {
                        getContext().startActivity(new Intent(getContext(), RegNewPostActivity.class));
                    //} else {
                    //   showUserRegPostDialog(getContext(), getActivity().findViewById(android.R.id.content));
                    //}
                }




//                //ad
//                // call IronSource.destroyBanner() before loading a new banner
//                if (bannerParentLayout != null) {
//                    destroyBanner();
//                }
//
//                // choose banner size
//                // you can pick any banner size: ISBannerSize.BANNER, ISBannerSize.LARGE, ISBannerSize.RECTANGLE, ISBannerSize.SMART or even define a custom banner size by providing width and height
//                // initialize IronSourceBanner object, using the IronSource.createBanner API
//                ironSourceBannerLayout = IronSource.createBanner(getActivity(), ISBannerSize.BANNER);
//
//                if (ironSourceBannerLayout != null) {
//                    // add IronSourceBanner to your container
//                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
//                    bannerParentLayout.addView(ironSourceBannerLayout, 0, layoutParams);
//
//                    // set the banner listener
//                    ironSourceBannerLayout.setLevelPlayBannerListener(new LevelPlayBannerListener() {
//                        @Override
//                        public void onAdLoaded(AdInfo adInfo) {
//                            int a = 5;
//                        }
//
//                        @Override
//                        public void onAdLoadFailed(IronSourceError ironSourceError) {
//                            int a = 5;
//
//                        }
//
//                        @Override
//                        public void onAdClicked(AdInfo adInfo) {
//                            int a = 5;
//
//                        }
//
//                        @Override
//                        public void onAdLeftApplication(AdInfo adInfo) {
//                            int a = 5;
//
//                        }
//
//                        @Override
//                        public void onAdScreenPresented(AdInfo adInfo) {
//                            int a = 5;
//
//                        }
//
//                        @Override
//                        public void onAdScreenDismissed(AdInfo adInfo) {
//                            int a = 5;
//
//                        }
//                    });
//
//                    // load ad into the created banner
//                    //log("loadBanner");
//                    IronSource.loadBanner(ironSourceBannerLayout);
//                } else {
//                    //log("IronSource.createBanner returned null");
//                }

            }
        });

        //if (!BuildConfig.FLAVOR_market.equals("winner")) {
            buttonShareApp.setVisibility(View.GONE);
        //} else {
        //    buttonShareApp.setOnClickListener(new View.OnClickListener() {
        //        @Override
        //        public void onClick(View view) {
        //            AppUtility appUtility = new AppUtility();
        //            appUtility.shareApp(getContext());
        //        }
        //    });
        //}
        if(PAGE_TYPE != BOURSE)
            getCategories();

        if(Global.user2 != null){
            if(Global.user2.isUserAdmin()){
                checkbox.setVisibility(View.VISIBLE);
            }else {
                checkbox.setVisibility(View.GONE);
            }
        }else {
            checkbox.setVisibility(View.GONE);
        }

        if(PAGE_TYPE == WINNER)
            checkbox.setVisibility(View.GONE);

        return view;
    }

    //ad
//    private void destroyBanner() {
//        if (bannerParentLayout != null) {
//            //log("destroyBanner");
//            IronSource.destroyBanner(ironSourceBannerLayout);
//            bannerParentLayout.removeView(ironSourceBannerLayout);
//        }
//
//    }

    @Override
    public void onStart() {
        super.onStart();
        timelineSearchRequest = null;
    }

    private void getCategories() {

        CategoriesLookUpRequest request = new CategoriesLookUpRequest();
        request.setCategoryCode(5);
        request.setParentId(7061);

        Global.apiManagerTubeless.getCategoryLookUp(request, new TubelessRetrofitCallbackss(getContext(), CategoryListResponse.class) {
            @Override
            public void t_beforeSendRequest() {
                Objects.requireNonNull(((TubelessActivity)mContext).progressDialog).show();
            }

            @Override
            public void t_afterGetResponse() {
                Objects.requireNonNull(((TubelessActivity)mContext).progressDialog).hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                new TubelessException().handleServerMessage(getContext(),new TubelessException(ERR_CODE_TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(getContext(),new TubelessException(TUBELESS_TRY_AGAIN));
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


    private boolean validData() {
        boolean isValid = true;
        if (PAGE_TYPE != WINNER)
            if(spinner.getSelectedItemPosition() == 0){
                isValid = false;
            }

        return isValid;
    }
    public static boolean cancelByBackbuttonPressed = true;

}