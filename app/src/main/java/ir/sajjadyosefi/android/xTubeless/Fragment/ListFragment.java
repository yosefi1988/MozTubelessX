package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.readystatesoftware.systembartint.SystemBarTintManager.SystemBarConfig;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.CategoryAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.InstagramAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.LotteryAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.TransactionsAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.UsersAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView.XAdapterNerkhRoz;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewCommentActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewImageActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;


import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.Display;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.RecyclerViewEmptySupport;


import static ir.sajjadyosefi.accountauthenticator.activity.payments.PaymentActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_WINNER_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_AMLAK_LIST_1;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_MULTY_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_CREATORS_POST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_YADAK_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_Old;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_IMAGE_IN_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_MESSAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_MESSAGE2;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_Message_FROM_USERS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_TRANS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm2;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORIES_DATA;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_LOTTERY_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYPURCHESE;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.isFreeStore;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewCommentActivity.LOGIN_REQUEST_NEW_COMMENT;



public class ListFragment extends Fragment  {

    private CategoryItem categoryItem;
    //private
    private RecyclerViewEmptySupport mRecyclerView;
    private TextView emptyView2;

    private CoordinatorLayout mCoordinatorLayout;
    private View fragmentRootView;
    private Activity activity;

    private int listType;
    private Intent intent;

    private Bundle bundle;
    public List<ParentItem> dataList;
    public ArrayList<ParentItem> listForReturn;

    private int scrolledPos = 0;
    private FloatingActionButton fab1;

    private LinearLayoutManager mLayoutManager;
    private TextView mTextViewNoting;
    public TextView categoryTitle;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout bourseExpire , ElectedAmlakLayout,BrowseCategoryLayout;
    private Button countinueButton;
    public Button buttonBack;
    public Button buttonHome;
    public Button buttonDone;

    private DilatingDotsProgressBar mProgressBar;
    private TimelineRequest searchRequest;
    public static FirstFragmentsAdapter.CalendarPageListener CalendarPageFragmentListener ;

    public ListFragment(Context context,FirstFragmentsAdapter.CalendarPageListener listener, int _listType, Intent intent) {
        CalendarPageFragmentListener = listener;
        this.context = context;
        this.listType = _listType;
        this.intent = intent;
    }
//    public static ListFragment newInstance(Context context, int page, int list, int headerId) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
//        ListFragment fragment = new ListFragment(context , 0);
//        fragment.setArguments(args);
//        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
//        return fragment;
//    }

    //public
    public int getListType() {
        return listType;
    }
    private SystemBarConfig config;
    private ViewGroup mRoot;

    //static
    public static final String      ARG_PAGE = "ARG_PAGE";
    public static final String      ARG_LIST = "ARG_LIST";
    public static final String      ARG_HEADER = "ARG_HEADER";
    public static Context           context;

    public ListFragment(Context context, int _listType, TimelineRequest _searchRequest) {
        this.context = context;
        this.listType = _listType;
        this.searchRequest = _searchRequest;
        constractorInit();
    }
    public ListFragment(Context context, int _listType) {
        this.context = context;
        this.listType = _listType;
        constractorInit();
    }
    public ListFragment(Context context, Intent intent) {
        this.context = context;
        this.listType = intent.getIntExtra("selectType",0);
        this.intent = intent;
    }
    public ListFragment(Context context, List<ParentItem> list, int listType, CategoryItem head) {
        this.context = context;
        this.listType = listType;
        this.categoryItem = head;
        this.dataList = list;
    }
    public ListFragment() {
    }
    public ListFragment(Context context,List<ParentItem> list, int _listType) {
        this.context = context;
        this.listType = _listType;
        this.dataList = list;
    }
    public ListFragment(Context context,List<ParentItem> list, int _listType , Bundle bundle) {
        this.context = context;
        this.listType = _listType;
        this.dataList = list;
        this.bundle = bundle;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString(TITLE, "Gladiator");
//        savedInstanceState.putDouble(RATING, 8.5);
//        savedInstanceState.putInt(YEAR, 2000);

//        mRecyclerView.computeHorizontalScrollOffset();
//        mRecyclerView.computeVerticalScrollOffset();
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        title = savedInstanceState.getString(TITLE);
//        rating = savedInstanceState.getDouble(RATING);
//        year = savedInstanceState.getInt(YEAR);
    }
    private void constractorInit() {
    }

    //0
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tabindex = getArguments().getInt(ARG_PAGE);
//        listType = getArguments().getInt(ARG_LIST);
//        idHeader = getArguments().getInt(ARG_HEADER);
//
//        id = getArguments().getInt("id");
//        term = getArguments().getString("term");
        listForReturn = new ArrayList<>();
    }

    //1
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (fragmentRootView == null)
            fragmentRootView = inflater.inflate(R.layout.x_fragment_timeline, container, false);
        fragment = this;
        return fragmentRootView;
    }

    //2
    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerViewEmptySupport) view.findViewById(R.id.recyclerView);
            mTextViewNoting = (TextView) view.findViewById(R.id.nothing_text);
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
            fab1 = (FloatingActionButton) view.findViewById(R.id.fabInFtagment);

            if (getActivity().getClass().getSimpleName().contains("Main")){
                CoordinatorLayout.LayoutParams params1 = new CoordinatorLayout.LayoutParams(
                        CoordinatorLayout.LayoutParams.WRAP_CONTENT,
                        CoordinatorLayout.LayoutParams.WRAP_CONTENT
                );
                params1.setMargins(Display.dp2px(getContext(), 30), DeviceUtil.getDisplayHeightAsPixel(getContext()), Display.dp2px(getContext(), 30), 0);
                params1.anchorGravity = Gravity.START;
                fab1.setLayoutParams(params1);
            }


            if(listType == LIST_CATEGORY_ONE_SELECT || listType == LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS || listType == LIST_CATEGORY_MULTY_SELECT) {
                BrowseCategoryLayout = (LinearLayout) view.findViewById(R.id.BrowseCategoryLayout);
                BrowseCategoryLayout.setVisibility(View.VISIBLE);
                categoryTitle = (TextView) view.findViewById(R.id.CategoryTitle);
                buttonBack = (Button) view.findViewById(R.id.buttonBack);
                buttonHome = (Button) view.findViewById(R.id.buttonHome);
                buttonDone = (Button) view.findViewById(R.id.buttonDone);

                if (listType == LIST_CATEGORY_MULTY_SELECT){
                    buttonDone.setVisibility(View.VISIBLE);
                }else {
                    buttonDone.setVisibility(View.GONE);
                }
            }
            if (listType == ITEM_TYPE_AMLAK_LIST_1) {
                ElectedAmlakLayout = (LinearLayout) view.findViewById(R.id.ElectedAmlakLayout);
                ElectedAmlakLayout.setVisibility(View.VISIBLE);
                Button buttonElectedAmlak = (Button) view.findViewById(R.id.buttonElectedAmlak);
                buttonElectedAmlak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CREATOR_REQUEST);
                        bundle.putString(ContactUsActivity.ID, 10 + "");
                        bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                                context.getString(R.string.requestHamkari),
                                ""));
                        bundle.putString(ContactUsActivity.Phone,  String.format("phone=%s-mail=%s-SID=%s-UN=%s",
                                ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getMobileNumber())),
                                ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getEmail())),
                                ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getSimCardId())),
                                ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getUsername()))));
                        context.startActivity(ContactUsActivity.getIntent(context, bundle));
                    }
                });
            }

            if (listType == TYPE_BOURSE_ANALIZE_All || listType == TYPE_BOURSE_ANALIZE_Old) {
                bourseExpire = (LinearLayout) view.findViewById(R.id.bourseExpire);
                countinueButton = (Button) view.findViewById(R.id.countinueButton);

                if (!isFreeStore(context, StaticValue.configuration)) {

                    //سرور دستور داده که رایگا ن باشه
                    bourseExpire.setVisibility(View.GONE);
                } else {

                    if (StaticValue.bourseState.totalPayedValue > 0) {
//                        if (BourseState.CheckDateIsValid(StaticValue.bourseState.endDate, StaticValue.configuration.getResponseStatus().getDate())) {
//                            //پرداخت کرده و منقضی هم نشده
//                            bourseExpire.setVisibility(View.GONE);
//                        } else {
//                            //پرداخت کرده و منقضی شده
//                            bourseExpire.setVisibility(View.VISIBLE);
//
//                            countinueButton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
////                                    Bundle bundle = new Bundle();
////                                    bundle.putInt("type", TYPE_BOURSE_PLANE);
////                                    getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(), bundle), READ_RULE_AND_PAY);
//                                }
//                            });
//                        }
                    } else {
                        //هیچ پرداختی قبلا انجام نداده است
                    }
                }
            }else if (listType == FRAGMENT_CATEGORY || listType == TYPE_LIST_CATEGORIES_DATA) {
                ((TubelessActivity)context).progressDialog.show();
            }
        }

        //fab1 new
        if(listType == LIST_CATEGORY_ONE_SELECT || listType == LIST_CATEGORY_MULTY_SELECT){
            fab1.setVisibility(View.GONE);
        }

        //fab1
        if (
                listType == LIST_TYPE_AMLAK_FILTER ||
                listType == LIST_TYPE_MYPOSTS ||
                listType == ITEM_TYPE_MYPURCHESE ||
                listType == ITEM_TYPE_AMLAK_LIST_1 ||

                listType == TYPE_ITEM_Message_FROM_USERS ||
                listType == TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm2 ||
                listType == ITEM_TYPE_FILTER ||
                listType == FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE ||
                listType == ITEM_TYPE_MYFAVS ||
                listType == ITEM_TYPE_TRANS ||
                listType == TYPE_ITEM_mmmmmmmmmmmmmmmmmmmmmmmmmmm
        ){
        }
        if (listType == ITEM_TYPE_TRANS){
            fab1.setVisibility(View.GONE);
        }
        if (listType == FRAGMENT_LOTTERY_SEARCH_RESULT){
            fab1.setVisibility(View.GONE);
        }
        if (listType == ITEM_TYPE_AMLAK_LIST_1) {
            fab1.setVisibility(View.GONE);
        }
    }
    public static ListFragment fragment;

    public void seenMethod(int id) {

        try {
            int pos = 0;
            for (ParentItem item : dataList) {
                if (item instanceof MainItem){
                    if (((MainItem)item).getID() == id){
                        if (!((TextItem) item).isSeen()) {
                            ((TextItem)item).setSeen(false);
                            mainAdapter.notifyItemChanged(pos + 1);
                            break;
                        }
                    }
                }
                pos++;
            }
        }catch (Exception ex){

        }
    }
    public void favMethod(int id,boolean bool) {

        try {
            int pos = 0;
            for (ParentItem item : dataList) {
                if (item instanceof MainItem){
                    if (((MainItem)item).getID() == id){
                        ((TextItem)item).setFav(bool);
                        mainAdapter.notifyItemChanged(pos + 1);
                        break;
                    }
                }
                pos++;
            }
        }catch (Exception ex){

        }
    }

    //3
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        activity            = (TubelessActivity) getActivity();
        if (activity == null)
            activity            = (Activity) getActivity();

//        if (activity.getSystemBarTint() != null)
//            config              = activity.getSystemBarTint().getConfig();

        if (mRoot == null) {
            mRoot = (ViewGroup) activity.findViewById(R.id.LinearLayout01);
            if (mRoot instanceof CoordinatorLayout) {
                mCoordinatorLayout = (CoordinatorLayout) mRoot;
            }
        }

        if (mLayoutManager == null)
            createAdater();

        mRecyclerView.getAdapter();
        prepareFabButton(fragmentRootView,listType);

        //font 4
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//        fontChanger.replaceFonts((ViewGroup) this.getView());

    }

//    TransactionsAdapter mainAdapter;
//    UsersAdapter mainAdapter;
//    MainAdapter mainAdapter;

    ITubelessAdapter mainAdapter;
    XAdapterNerkhRoz xAdapterNerkhRoz;

    private void createAdater( ) {
        mRecyclerView.setHasFixedSize(true);


        if(BuildConfig.FLAVOR_version_name.equals("yadak") && (listType == LIST_TYPE_IMAGE_IN_YADAK)) {
            mLayoutManager = new GridLayoutManager(context, 3);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }else {
            mLayoutManager = new LinearLayoutManager(context);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        mRecyclerView.setEmptyView(mRoot.findViewById(R.id.list_empty));
        emptyView2 = (TextView) mRoot.findViewById(R.id.list_empty);

        if(listType == LIST_TYPE_AMLAK_TIMELINE ||
                listType == FRAGMENTLIST_TYPE_WINNER_TIMELINE ||
                listType == ITEM_TYPE_MYFAVS ||
                listType == FRAGMENTLIST_YADAK_TIMELINE ||
                listType == ITEM_TYPE_FILTER ||
                listType == FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE ||
                listType == FRAGMENTLIST_TYPE_MOZ_TIMELINE ||
                listType == FRAGMENTLIST_TYPE_BOURSE_TRAIN ||
                listType == TYPE_BOURSE_ANALIZE_All ||
                listType == TYPE_BOURSE_NEWS ||
                listType == LIST_TYPE_MYPOSTS ||
                listType == LIST_TYPE_AMLAK_FILTER ||
                listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST ||
                listType == ITEM_TYPE_MYPURCHESE){
            mainAdapter = new MainAdapter();
        }else if(listType == LIST_CATEGORY_ONE_SELECT || listType == LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS || listType == LIST_CATEGORY_MULTY_SELECT){
            Long category = intent.getLongExtra("category",0);
            Long parentId = intent.getLongExtra("parentId",0);
            String title = intent.getStringExtra("title");
            int selectType = intent.getIntExtra("selectType",0);
            categoryTitle.setText(title);
            mainAdapter = new CategoryAdapter(category,parentId,selectType);
        }else if(listType == LIST_TYPE_IMAGE_IN_YADAK){
            mainAdapter = new InstagramAdapter();
        }else if(listType == FRAGMENT_POST_SEARCH_RESULT){
            mainAdapter = new LotteryAdapter();
        }else if(listType == FRAGMENT_LOTTERY_SEARCH_RESULT){
            mainAdapter = new LotteryAdapter();
        }else if(listType == ITEM_TYPE_TRANS){
            mainAdapter = new TransactionsAdapter();
        }else if(listType == ITEM_TYPE_AMLAK_LIST_1){
            mainAdapter = new UsersAdapter();
        }

        if(listType < 1200) {
            mainAdapter.init(
                    listType,
                    categoryItem,
                    getContext(),
                    mRoot,
                    mRecyclerView,
                    mLayoutManager,
                    //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
                    //height,
                    //hasAppBarLayout,
                    mSwipeRefreshLayout,
                    this,
                    bundle,
                    emptyView2,
                    searchRequest);
//            if (listType == ITEM_TYPE_FILTER) {
//                xAdapter.timelineSearchRequest = searchRequest ;
//            }
            mRecyclerView.setAdapter(mainAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }else {
//            xAdapterNerkhRoz = new XAdapterNerkhRoz(
//                    listType,
//                    catid,
//                    getContext(),
//                    mRoot,
//                    mRecyclerView,
//                    mLayoutManager,
//                    //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
//                    //height,
//                    //hasAppBarLayout,
//                    mSwipeRefreshLayout,
//                    this,
//                    bundle);
            mRecyclerView.setAdapter(xAdapterNerkhRoz);
        }
    }


    public void refreshForAdmin(){
//        if (xAdapter != null)
//            xAdapter.notifyDataSetChanged();

        dataList.clear();
//        scrollToTop();
//        xAdapter.loadTimeline(context,1,true);


        mainAdapter.notifyDataSetChanged();
        mainAdapter.firstLoadAndRefresh(context);

//        if (xAdapterNerkhRoz != null)
//            xAdapterNerkhRoz.notifyDataSetChanged();
    }

    public static List<ParentItem> createData() {
        List<ParentItem> list = new ArrayList<>();

//        PictureItem item1 = new PictureItem();
//        item1.setCarID("1002");
//        item1.setCarName("فورد");
//        List<Tag> ssssss = new ArrayList<>();
//        Tag t1 = new Tag();
//        t1.setID(1);
//        t1.setTitle("car");
//        ssssss.add(t1);
//        item1.setListTag(ssssss);
//        item1.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2278.jpg");
//        item1.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art570.jpg");
//        list.add(item1);
//
//        PictureItem item2 = new PictureItem();
//        item2.setCarID("1003");
//        item2.setCarName("مزدا");
//        ssssss.add(t1);
//        t1.setID(2);
//        t1.setTitle("mazda");
//        ssssss.add(t1);
//        item2.setListTag(ssssss);
//        item2.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2282.jpg");
//        item2.setThumbnailFilePath("https://cdn.bama.ir/uploads//BamaImages/News/41bb71bf-3bb8-48b4-90e1-017830545958/article_637132150003497227_thumb_960_542.jpg");
//        list.add(item2);
//
//        PictureItem item3 = new PictureItem();
//        item3.setCarID("1004");
//        item3.setCarName("بنز");
//        item3.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2293.jpg");
//        item3.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art579.jpg");
//        list.add(item3);
//
//        PictureItem item4 = new PictureItem();
//        item4.setCarID("1007");
//        item4.setCarName("BMW");
//        item4.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2293.jpg");
//        item4.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art581.jpg");
//        list.add(item4);

        TextItem item5 = new TextItem(1, "The Flight", "Scott Masterson", "https://i.imgur.com/dyyP2iO.jpg");
        list.add(item5);
        return list;
    }

//    private PictureItem[] createData0() {
//        return new PictureItem[]{
//                new PictureItem(1,"The Flight", "Scott Masterson", "https://i.imgur.com/dyyP2iO.jpg"),
//                new PictureItem(2,"Room of Plates", "Ali Conners", "https://i.imgur.com/da6QIlR.jpg"),
//                new PictureItem(3,"The Sleek Boot", "Sandra Adams", "https://i.imgur.com/YHoOJh4.jpg"),
//                new PictureItem(4,"Night Hunting", "Janet Perkins", "https://i.imgur.com/3jxqrKP.jpg"),
//                new PictureItem(5,"Rain and Coffee", "Peter Carlsson", "https://i.imgur.com/AZRynvM.jpg"),
//                new PictureItem(6,"Ocean View", "Trevor Hansen", "https://i.imgur.com/IvhOJcw.jpg"),
//                new PictureItem(7,"Lovers Of The Roof", "Britta Holt", "https://i.imgur.com/pxgI1b4.png"),
//                new PictureItem(8,"Lessons from Delhi", "Mary Johnson", "https://i.imgur.com/oT1WYX9.jpg"),
//                new PictureItem(9,"Mountaineers", "Abbey Christensen", "https://i.imgur.com/CLLDz.jpg"),
//                new PictureItem(10,"Plains In The Night", "David Park", "https://i.imgur.com/7MrSvXE.jpg?1"),
//                new PictureItem(11,"Dear Olivia", "Sylvia Sorensen", "https://i.imgur.com/3mkUuux.jpg"),
//                new PictureItem(12,"Driving Lessons", "Halime Carver", "https://i.imgur.com/LzYAfFL.jpg"),
//        };
//    }

    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    private void prepareFabButton(View view, final int listType) {

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mLayoutManager = new LinearLayoutManager(context);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        if (fab1 != null) {
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TIMELINE Yafte
                    if (listType == FRAGMENTLIST_TYPE_MOZ_TIMELINE || listType == ITEM_TYPE_FILTER ) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
//                            if (Global.user2 != null
//                                    &&
//                                    //(Global.user2.isUserAdmin() || Global.user2.isUserCreator()) ||
//                                    BuildConfig.FLAVOR_version_name.equals("yafte")) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
//                            } else {
//                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
//                            }
                        }
                    }

                    if (listType == FRAGMENTLIST_TYPE_WINNER_TIMELINE) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            if (Global.user2.isUserAdmin()) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
                            } else {
                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                            }
                        }
                    }

                    if (listType == FRAGMENTLIST_YADAK_TIMELINE ) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            if (Global.user2 != null && (Global.user2.isUserAdmin() || BuildConfig.FLAVOR_version_name.equals("yadak"))) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
                            } else {
                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                            }
                        }
                    }

                    if (listType == LIST_TYPE_AMLAK_TIMELINE || listType ==  LIST_TYPE_AMLAK_FILTER) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
//                            if (Global.user2 != null && (Global.user2.isUserAdmin()|| Global.user2.isUserCreator())) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
//                            } else {
//                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
//                            }
                        }
                    }


                    if (listType == LIST_TYPE_IMAGE_IN_YADAK) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
//                            if (Global.user2 != null && (Global.user2.isUserAdmin() || Global.user2.isUserCreator()) || BuildConfig.FLAVOR_version_name.equals("yafte")) {
                            context.startActivity(new Intent(context, RegNewImageActivity.class));
//                            } else {
//                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
//                            }
                        }
                    } else if (listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST) {
                        if (Global.user2 != null) {
                            context.startActivity(new Intent(context, RegNewPostActivity.class));
                        } else {
                            showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                        }
                    } else if (listType == FRAGMENTLIST_TYPE_BUSINESSES_TIMELINE ) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            context.startActivity(new Intent(context, RegNewPostActivity.class));
                        }
                    }
                    else if (listType == ITEM_TYPE_MYPURCHESE) {
                        if (Global.user2 != null) {
                            context.startActivity(new Intent(context, RegNewPostActivity.class));
                        } else {
                            showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                        }
                    } else if (listType == ITEM_TYPE_AMLAK_LIST_1) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            //   برو به تماس با ما
                            Bundle bundle = new Bundle();
                            bundle.putInt(ContactUsActivity.Type , ContactUsActivity.CONTACT_US);
                            getActivity().startActivity(ContactUsActivity.getIntent(getContext(),bundle));
                        }
                    } else if (listType == TYPE_ITEM_MESSAGE || listType == TYPE_ITEM_MESSAGE2) {   //ok
                        //listType == TYPE_ITEM_MESSAGE is true  پیام 2 و صفحه اصلی
                        //صفFحه اصلی = isOwner=true

                        // listType == TYPE_ITEM_MESSAGE2 پیام
                        getActivity().startActivityForResult(RegNewCommentActivity.getIntent(getContext(), bundle), LOGIN_REQUEST_NEW_COMMENT);

                    } else if (listType == LIST_TYPE_MYPOSTS) {
                        context.startActivity(new Intent(context, RegNewPostActivity.class));
                    } else if (listType == ITEM_TYPE_MYFAVS) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();

                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            if (Global.user2 != null && (Global.user2.isUserAdmin() || Global.user2.isUserCreator()) || BuildConfig.FLAVOR_version_name.equals("yafte")) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
                            } else {
                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                            }
                        }
//                        if (Global.userFixxxxxxxxx.getUserTypeCode() != 1) {
//                            context.startActivity(new Intent(context, RegNewPostActivity.class));
//                        } else {
//                            showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
//                        }
                    } else if (listType == LIST_TYPE_IMAGE_IN_YADAK) {

                    } else if (listType == FRAGMENT_POST_SEARCH_RESULT) {
                        if (Global.user2 == null) {
                            Toast.makeText(context,  getContext().getString(R.string.must_login), Toast.LENGTH_LONG).show();
                            if (bundle == null) {
                                bundle = new Bundle();
                            }
                            Intent intent = SignInActivity.getIntent(getContext(), bundle);
                            bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                            getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                        } else {
                            if (Global.user2 != null &&
                                    (Global.user2.isUserAdmin() || Global.user2.isUserCreator()) ||
                                    BuildConfig.FLAVOR_version_name.equals("yafte") ||
                                    BuildConfig.FLAVOR_version_name.equals("estekhdam") ||
                                    BuildConfig.FLAVOR_version_name.equals("moz")) {
                                context.startActivity(new Intent(context, RegNewPostActivity.class));
                            } else {
                                showUserRegPostDialog(context, getActivity().findViewById(android.R.id.content));
                            }
                        }
                    } else if (listType == FRAGMENTLIST_TYPE_BOURSE_TRAIN) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_BOURSE_ANALIZE_All || listType == TYPE_BOURSE_ANALIZE_Old) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_BOURSE_NEWS) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == FRAGMENT_COMMENTS) {
//                        int blogId = bundle.getInt("blogId");
//xxxxxxxxxxxxxxxx
//                        Bundle bundle = new Bundle();
//                        bundle.putString("X" , "X");
                        getActivity().startActivityForResult(RegNewCommentActivity.getIntent(getContext(), bundle), LOGIN_REQUEST_NEW_COMMENT);
                    } else if (listType == FRAGMENT_CATEGORY || listType == LIST_CATEGORY_ONE_SELECT || listType == LIST_CATEGORY_MULTY_SELECT){
//                        Toast.makeText(context, " cat id is : " + categoryNode.getCatId(), Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(context, "not set", Toast.LENGTH_LONG).show();
                    }
                }
            });


//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (dy > 0) {
//                    // Scroll Down
//                    if (floatingActionButton.isShown()) {
//                        floatingActionButton.hide();
//                    }
//                }
//                else if (dy < 0) {
//                    // Scroll Up
//                    if (!floatingActionButton.isShown()) {
//                        floatingActionButton.show();
//                    }
//                }
//            }
//        });

        }
    }



    public static void showUserRegPostDialog(Context context , ViewGroup viewGroup) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup


        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        TextView title = dialogView.findViewById(R.id.title);
        TextView textViewRole = dialogView.findViewById(R.id.textViewRole);
        textViewRole.setVisibility(View.VISIBLE);
        textViewRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("WebType" , "rule");
                context.startActivity(WebViewActivity.getIntent(context,bundle));
            }
        });
        buttonOk.setText("پذیرش قوانین و ثبت درخواست");
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        textViewStatment.setText(context.getString(R.string.normalUserMessage));
        title.setText("اطلاع رسانی");



        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

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
                Bundle bundle = new Bundle();
                bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CREATOR_REQUEST);
                bundle.putString(ContactUsActivity.ID, 10 + "");
                bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                        context.getString(R.string.createorContent),
                        ""));
                bundle.putString(ContactUsActivity.Phone,  String.format("phone=%s-mail=%s-SID=%s-UN=%s",
                        ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getMobileNumber())),
                        ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getEmail())),
                        ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getSimCardId())),
                        ((Global.userFixxxxxxxxx == null ? "X":Global.userFixxxxxxxxx.getUsername()))));
                context.startActivity(ContactUsActivity.getIntent(context, bundle));
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void newRetVal(ParentItem items) {
        listForReturn.add(items);

        if (intent.hasExtra("CAT_COUNT")){
            int count = intent.getIntExtra("CAT_COUNT",0);
            if (listForReturn.size() == count){
                Intent returnIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("SelectedLIST", (Serializable) listForReturn);
                returnIntent.putExtras(bundle);
                ((Activity)context).setResult(Activity.RESULT_OK, returnIntent);
                ((Activity)context).finish();
            }
        }
    }
}
