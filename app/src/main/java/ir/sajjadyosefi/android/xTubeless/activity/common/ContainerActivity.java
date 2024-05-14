package ir.sajjadyosefi.android.xTubeless.activity.common;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.readystatesoftware.systembartint.SystemBarTintManager;
//import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
//import com.zarinpal.ewallets.purchase.PaymentRequest;
//import com.zarinpal.ewallets.purchase.ZarinPal;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.FilterFragment;
import ir.sajjadyosefi.android.xTubeless.bussines.post.fragment.SearchByNameFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_AMLAK_LIST_1;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_CREATORS_POST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_MESSAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_MESSAGE2;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYPURCHESE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_Message_FROM_USERS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_TRANS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm2;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.CategoryAdapter.categoryItemStack;


public class ContainerActivity extends TubelessTransparentStatusBarActivity {

    ///////////// ContainerActivity //////////////////
    public static int FRAGMENT_CATEGORY                 = 101;

    public static int ContainerActivityType             = 0 ;
    public static int FRAGMENT_CREATORS_POST            = 923;
    public static int FRAGMENT_MYPURCHESE               = 10;
    public static int FRAGMENT_MYFAVS                   = 12;
    public static int FRAGMENT_MYPOSTS                  = 11;
    public static int FRAGMENT_MYTRANSACTIONS           = 13;
    public static int FRAGMENT_POST_SEARCH_RESULT       = 4;
    public static int FRAGMENT_LOTTERY_SEARCH_RESULT       = 42;
    public static int FRAGMENT_SEARCH_POST_BY_NAME      = 5;
    public static int FRAGMENT_COMMENTS                 = 6;
    public static int FRAGMENT_BOURSE_PLANE             = 7;
    public static int FRAGMENT_FILTER                   = 8;
    public static int FRAGMENT_FILTER_RESULT            = 71;
    ////// ContainerActivity => must check /////////
    public static int FRAGMENT_Message                  = 14;
    public static int FRAGMENT_Message2                 = 17;
    public static int FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmmm = 16;
    public static int FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmm2 = 18;
    public static int FRAGMENT_Message_FROM_USERS       = 15;
    public static int FRAGMENT_MOZ                      = 9;


    private Context mContext;
    private List<ParentItem> list;
    public List<CategoryItem> listSelectedCategoryItems;
    private Toolbar toolbar;


    //singletone instance
    private static ContainerActivity containerActivity;

    //singletone
    public synchronized static ContainerActivity getInstance(){
        if (containerActivity == null){
            containerActivity = new ContainerActivity();
        }
        return containerActivity;
    }

    //default constractor
    public ContainerActivity() { }


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context,ContainerActivity.class);
        intent.putExtras(bundle);
        return intent;
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static final int READ_BLOG_COMMENTS = 1042;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContainerActivityType == FRAGMENT_COMMENTS){
            if (resultCode == Activity.RESULT_OK) {

                ((TubelessActivity)mContext).progressDialog.show();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                list = new ArrayList<>();
                ft.replace(R.id.include, new ListFragment(this, list, ContainerActivityType, bundle));
                ft.addToBackStack(ft.getClass().getName());
                ft.commit();
            }
        }
//        if (requestCode == READ_RULE_AND_PAY) {
//            if (resultCode == Activity.RESULT_OK) {
////                int payType = data.getIntExtra("payType" , 0);
//                setResult(Activity.RESULT_OK, data);
//                finish();

//                Intent returnIntent = new Intent();
//                Bundle bundle = new Bundle();
//        //            bundle.putSerializable("LIST", (Serializable) returnedcategoryList);
//                returnIntent.putExtras(bundle);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();

//            }else {
//                setResult(Activity.RESULT_CANCELED);
//                finish();
//                Intent returnIntent = new Intent();
//                setResult(Activity.RESULT_CANCELED, returnIntent);
//                finish();
//            }
//        }

    }

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //categoryItemStack.clear();//main activity not work  home and etc...

        setContentView(R.layout.x_activity_container);
        mContext = this;
        if(ContainerActivityType == 0){
            ContainerActivityType = getIntent().getIntExtra("type", 0);
        }else if (getIntent() != null && getIntent().hasExtra("type") && ContainerActivityType != getIntent().getIntExtra("type", 0)){
            ContainerActivityType = getIntent().getIntExtra("type", 0);
        }

        boolean bbbbbb = getIntent().getBooleanExtra("isOwner", false);

        if (ContainerActivityType != 0) {
            list = (List<ParentItem>) getIntent().getSerializableExtra("LIST");
            if (ContainerActivityType == FRAGMENT_POST_SEARCH_RESULT) {
                //            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentAbouteUs fragmentDemo = FragmentAbouteUs.newInstance();
                //            ft.replace(R.id.output, fragmentDemo);
                //            ft.commit();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new ListFragment(this, list, FRAGMENT_POST_SEARCH_RESULT));
                //            ft.replace(R.id.include, new BlankFragment(getContext()));
                ft.commit();
                //            ListFragment2 newFragment = new ListFragment2(getContext(),2);
                //            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //            transaction.replace(R.id.include, newFragment);
                //            transaction.addToBackStack(null);
                //            transaction.commit();

            } else if (ContainerActivityType == FRAGMENT_LOTTERY_SEARCH_RESULT) {
                //            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentAbouteUs fragmentDemo = FragmentAbouteUs.newInstance();
                //            ft.replace(R.id.output, fragmentDemo);
                //            ft.commit();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new ListFragment(this, list, FRAGMENT_LOTTERY_SEARCH_RESULT));
                //            ft.replace(R.id.include, new BlankFragment(getContext()));
                ft.commit();
                //            ListFragment2 newFragment = new ListFragment2(getContext(),2);
                //            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //            transaction.replace(R.id.include, newFragment);
                //            transaction.addToBackStack(null);
                //            transaction.commit();

            } else if (ContainerActivityType == FRAGMENT_SEARCH_POST_BY_NAME) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new SearchByNameFragment());
                ft.commit();
            } else if (ContainerActivityType == FRAGMENT_MOZ) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, FRAGMENTLIST_TYPE_MOZ_TIMELINE));
                ft.commit();
            } else if (ContainerActivityType == FRAGMENT_CATEGORY) {
                listSelectedCategoryItems = new ArrayList<>();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, getIntent()));
                ft.commit();
            } else if (ContainerActivityType == FRAGMENT_CREATORS_POST) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, FRAGMENTLIST_TYPE_MOZ_CREATORS_POST));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_MYPURCHESE) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, ITEM_TYPE_MYPURCHESE));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_Message) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                bundle.putString("userId", getIntent().getStringExtra("userId"));
                bundle.putBoolean("isOwner", getIntent().getBooleanExtra("isOwner" , false));
                bundle.putBoolean("isCreator", getIntent().getBooleanExtra("isCreator" , false));
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_ITEM_MESSAGE, bundle));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_Message2) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                bundle.putString("userId", getIntent().getStringExtra("userId"));
                bundle.putBoolean("isOwner", getIntent().getBooleanExtra("isOwner" , false));
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_ITEM_MESSAGE2, bundle));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmmm) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
//                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
//                bundle.putString("userId", getIntent().getStringExtra("userId"));
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm, bundle));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_mmmmmmmmmmmmmmmmmmmmmmm2) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
//                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
//                bundle.putString("userId", getIntent().getStringExtra("userId"));
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm2, bundle));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_Message_FROM_USERS) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                bundle.putBoolean("isOwner", getIntent().getBooleanExtra("isOwner" , false));
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_ITEM_Message_FROM_USERS, bundle));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_MYTRANSACTIONS) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, ITEM_TYPE_TRANS));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_MYPOSTS) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, LIST_TYPE_MYPOSTS));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_FILTER_RESULT) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                TimelineRequest searchRequest = (TimelineRequest) getIntent().getSerializableExtra("SearchRequest");
                ft.replace(R.id.include,  new ListFragment(this, LIST_TYPE_AMLAK_FILTER,searchRequest));
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_FILTER) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                TimelineRequest searchRequest = (TimelineRequest) getIntent().getSerializableExtra("SearchRequest");
                ft.replace(R.id.include, new FilterFragment(searchRequest), "KartesekhtFragment");
                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_MYFAVS) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, ITEM_TYPE_MYFAVS));
                ft.commit();
            } else if (ContainerActivityType == ITEM_TYPE_AMLAK_LIST_1) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include,  new ListFragment(this, ITEM_TYPE_AMLAK_LIST_1));
                ft.commit();
            } else if (ContainerActivityType == FRAGMENT_BOURSE_PLANE) {
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.include, new FinancialAccountLimitFragment(this));
//                ft.commit();

            } else if (ContainerActivityType == FRAGMENT_COMMENTS) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                ft.replace(R.id.include, new ListFragment(this, list, ContainerActivityType, bundle));
                ft.commit();

            } else if (ContainerActivityType == 1) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                ft.commit();

            } else if (ContainerActivityType == 2) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentProfile fragmentDemo = FragmentProfile.newInstance(getContext() ,1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                ft.commit();
                //        }else {
                //            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                //            ft.commit();
            } else {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.include, prepareFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        }
//
//
//        Uri data2 = getIntent().getData();
//        ZarinPal.getPurchase(getContext()).verificationPayment(data2, new OnCallbackVerificationPaymentListener() {
//            @Override
//            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
//                if(isPaymentSuccess){
////                    //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();
//                    DialogUtil.showLoadingDialog(getContext());
//
//                    if(payType == 100){
//                        //کارت سوخت
//                        KartesekhtFragment myFragment = (KartesekhtFragment)getSupportFragmentManager().findFragmentByTag("KartesekhtFragment");
//                        if (myFragment != null && myFragment.isVisible()) {
//                            // add your code here
//                            if (myFragment instanceof KartesekhtFragment){
//                                ((KartesekhtFragment)myFragment).callService(getRootActivity());
//                            }
//                        }
//
//                        cancelByBackbuttonPressed = false;
//                        int a = 5 ;
//                        a++;
//                    }
//
//
//                    if(payType != 100) {
//
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

        int a = 5 ;
        a++;


        KartesekhtFragment myFragment = (KartesekhtFragment)getSupportFragmentManager().findFragmentByTag("KartesekhtFragment");
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
            if (myFragment instanceof KartesekhtFragment){
                ((KartesekhtFragment)myFragment).callService(getRootActivity());
            }
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

    Fragment prepareFragment() {

        int id = getIntent().getExtras().getInt("id");
        String term = getIntent().getExtras().getString("term");

        Bundle args = new Bundle();
        args.putString("term", term);
        args.putInt("id", id);
//        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
//        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
//        return fragment;
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ContainerActivityType = 0;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        timelineSearchRequest = timelineSearchRequest;
//        timelineSearchRequest = null;
    }

    public void moveTo(long category,long parentId,String title,int selectType){
        bundle = new Bundle();
        bundle.putInt("type" , FRAGMENT_CATEGORY);
        bundle.putInt("CAT_COUNT", 11);
        bundle.putString("item1","value1");
        bundle.putString("title",title);
        bundle.putLong("category",category);
        bundle.putLong("parentId",parentId);
        bundle.putInt("selectType",selectType);
        Intent intent = new Intent(getContext(),ContainerActivity.class);
        intent.putExtras(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.include,  new ListFragment(this, intent));
        ft.commit();
    }
}