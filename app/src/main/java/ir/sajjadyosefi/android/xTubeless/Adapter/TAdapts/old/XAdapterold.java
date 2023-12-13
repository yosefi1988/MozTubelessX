package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.old;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.classes.ITransactionsListRequest;
import ir.sajjadyosefi.accountauthenticator.model.request.ATransactionListRequest;
import ir.sajjadyosefi.accountauthenticator.model.response.ATransactionListResponse;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;

import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.mainList.MainListItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.EmptyTextItem;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TransactionItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.UserMessageItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.MainListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewTimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CategoryViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.EmptyTextViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TransactionItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.PARAM_TRANSACTION_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_CREATORS_POST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_YADAK_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_AMLAK_LIST_1;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYPURCHESE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_TRANS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_TIMELINE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_IMAGE_IN_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_MOZ;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_POST_SEARCH_RESULT;


public class XAdapterold extends RecyclerView.Adapter<TubelessMainViewHolder> {

    private CategoryItem categoryItem;
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private Fragment fragment;
    XAdapterold adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    EndlessRecyclerOnScrollListener onScrollListener;

    public TimelineRequest timelineSearchRequest = null;

    public XAdapterold() {

    }

    public XAdapterold(int listType, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {
         new XAdapterold(listType, null, context, rootView, recyclerView, linearLayoutManager, mSwipeRefreshLayout, fragment, bundle,null,null) ;
    }

    public XAdapterold(int listType, CategoryItem categoryItem, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2 , TimelineRequest searchRequest) {

        this.listType = listType;
        this.categoryItem = categoryItem;
        this.fragment = fragment;
        this.picasso = Picasso.get();
        this.context = context;
        this.emptyView2 = emptyView2;
        this.rootView = rootView;
        this.mLayoutManager = linearLayoutManager ;
        this.recyclerView = recyclerView;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout ;
        this.adapter = this;
        this.bundle = bundle;
        this.timelineSearchRequest = searchRequest;

        if (((ListFragment)fragment).dataList == null)
            ((ListFragment)fragment).dataList = new ArrayList<>();

        if(listType != LIST_CATEGORY_ONE_SELECT) {
            onScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
                @Override
                protected void onScrollUp() {

                }

                @Override
                protected void onScrollDown() {

                }

                @Override
                public void onLoadMore(int current_page) {
                    loadTimeline(context, current_page, false);
                }
            };
            recyclerView.addOnScrollListener(onScrollListener);
        }
        firstLoadAndRefresh(context);
    }

    private final static int FADE_DURATION = 500; //FADE_DURATION in milliseconds


    public void firstLoadAndRefresh(Context context) {
        loadTimeline(context,1,false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onScrollListener != null)
                    onScrollListener.reset();
                ((ListFragment)fragment).dataList.clear();
                loadTimeline(context,1,true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadTimeline(Context context,int _current_page, boolean isRefresh) {

        if (listType == FRAGMENT_MOZ) {
            MainListResponse responseX = new MainListResponse();
            List<MainListItem> dddddd = new ArrayList<>();
            TimelineItem dddddddddd = new TimelineItem();
            dddddddddd.setTitle("Group One");
            dddddddddd.setText("group One Text");
            Date date = new Date();
            dddddddddd.setDate(date.getTime() + "");
            dddddddddd.setRegisterDate(date.getTime() + "");
            dddddddddd.setPicture("https://flutter-learn.ir/wp-content/uploads/2019/09/maxresdefault-100x75.png");

            MainListItem sssssssssssss = new MainListItem(dddddddddd);
            dddddd.add(sssssssssssss);
            responseX.setMainListItems(dddddd);
            for (MainListItem item : responseX.getMainListItems()){
                ((ListFragment)fragment).dataList.add(item);
                if (isRefresh) {
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.notifyItemInserted(((ListFragment)fragment).dataList.size());
                }
            }
        }else if (listType == ITEM_TYPE_TRANS) {
            ((TubelessActivity) context).progressDialog.show();
            SignInActivity signInActivity = new SignInActivity();
            ATransactionListRequest xxxxxxxxxxx = new ATransactionListRequest(Global.user2.getUserCodeAsString(), "10", (_current_page - 1) + "");
            signInActivity.getTransactionsList(xxxxxxxxxxx, new ITransactionsListRequest<Boolean, Intent>() {
                @Override
                public void onResponse(Boolean isSuccess, Intent intent) {
                    ((TubelessActivity) context).progressDialog.hide();

                    Bundle bundle = intent.getExtras();
                    String config = bundle.getString(PARAM_TRANSACTION_LIST);
                    ATransactionListResponse responseX2 = new Gson().fromJson(config, ATransactionListResponse.class);
                    //String error = bundle.getString(KEY_ERROR_MESSAGE);

//                    for (ATransaction item : responseX2.getTransactionList()) {
//                        ATransactionApp aTransactionApp = new ATransactionApp() {
//                            @Override
//                            public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//
//                            }
//
//                            @Override
//                            public void modal(Context mContext, View view, int listType, ATransactionApp timelineItem) {
//                                super.modal(mContext, view, listType, timelineItem);
//                            }
//                        };
//                        aTransactionApp.setAmount(item.getAmount());
//                        aTransactionApp.setTTC(item.getTTC());
//                        aTransactionApp.setTTN(item.getTTN());
//                        aTransactionApp.setCreatorFullName(item.getCreatorFullName());
//                        aTransactionApp.setDateTime(item.getDateTime());
//                        aTransactionApp.setIcon(item.getIcon());
//                        aTransactionApp.setID(item.getID());
//                        aTransactionApp.setImage(item.getImage());
//                        aTransactionApp.setZarib(item.getZarib());
//                        aTransactionApp.setRefrenceNo(item.getRefrenceNo());
//                        aTransactionApp.setTitle(item.getTitle());
//
////                        item.setType(Tubeless_ITEM_TYPE);
//                        ((ListFragment) fragment).list.add(aTransactionApp);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        } else {
//                            adapter.notifyItemInserted(((ListFragment) fragment).list.size());
//                        }
//                    }
                }
            });
        }else if (listType == LIST_CATEGORY_ONE_SELECT) {
            CategoriesLookUpRequest request = new CategoriesLookUpRequest();
            request.setCategoryCode(5);
            request.setParentId(0);
            Global.apiManagerTubeless.getCategoryLookUp(request, new TubelessRetrofitCallbackss(context, CategoryListResponse.class) {
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

                @Override
                public void t_onSuccess(Object response) {
//                    for (CategoryItem item:((CategoryListResponse) response).getCatlist()) {
//                        ((ListFragment) fragment).list.add(item);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
//                        }
//                    }
                    ((TubelessActivity)context).progressDialog.hide();
                }
            });
        }else if (
                listType == LIST_TYPE_AMLAK_TIMELINE ||             //ok
                listType == LIST_TYPE_AMLAK_FILTER ||               //ok
                listType == LIST_TYPE_MYPOSTS ||                    //ok
                listType == ITEM_TYPE_MYPURCHESE ||                 //ok
                listType == ITEM_TYPE_AMLAK_LIST_1 ||                 //ok
                listType == ITEM_TYPE_MYFAVS ||                     //ok

                listType == FRAGMENTLIST_TYPE_MOZ_TIMELINE ||       //ok
                listType == FRAGMENTLIST_YADAK_TIMELINE ||          //ok
                listType == FRAGMENTLIST_TYPE_BOURSE_TRAIN ||       //ok
                listType == LIST_TYPE_IMAGE_IN_YADAK ||                      //ok
                listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST ||  //ok
                listType == ITEM_TYPE_FILTER) {                     //ok
            TimelineRequest timelineRequest = null;
            if (listType == ITEM_TYPE_FILTER) {
                timelineRequest = timelineSearchRequest;
                timelineRequest.setPageIndex(_current_page - 1 + "");
            }else if (listType == LIST_TYPE_AMLAK_FILTER) {
                timelineRequest = timelineSearchRequest;
                timelineRequest.setPageIndex(_current_page - 1 + "");
            }else if (listType == LIST_TYPE_IMAGE_IN_YADAK){
                timelineRequest = new TimelineRequest(_current_page - 1);
                timelineRequest.setActive(true);
                if (Global.user2 == null) {
                    timelineRequest.setUserCode(null);
                } else {
                    timelineRequest.setUserCode(Global.user2.getUserCodeAsString());
                }
                timelineRequest.setTtc("5047");

            }else {
                timelineRequest = new TimelineRequest(_current_page - 1);
                if (listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST) {
                    if (Global.user2 != null && Global.user2.isUserAdmin()) {
                        timelineRequest.setActive(false);
                    } else {
                        timelineRequest.setActive(true);
                    }
                } else {
                    timelineRequest.setActive(true);
                    if (Global.user2 == null) {
                        timelineRequest.setUserCode(null);
                    } else {
                        timelineRequest.setUserCode(Global.user2.getUserCodeAsString());
                    }
                    if (listType == ITEM_TYPE_MYPURCHESE) {
                        timelineRequest.setVisited(true);
                    }
                    if (listType == ITEM_TYPE_MYFAVS) {
                        timelineRequest.setFaved(true);
                    }
                }
            }

            TubelessRetrofitCallbackss retrofitCallbackssx = new TubelessRetrofitCallbackss(context, NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {
                    try {
                        ((TubelessActivity) context).progressDialog.show();
                    }catch (Exception ex){

                    }
                }

                @Override
                public void t_afterGetResponse() {
                    ((TubelessActivity)context).progressDialog.hide();
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

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;
//                    data.add(new NotiesItem());
                    Gson gson = new Gson();


                    for (Object item : responseX.getPostList()) {
//                        item.setType(Tubeless_ITEM_TYPE);
                        JsonObject jsonObject = gson.toJsonTree(item).getAsJsonObject();
                        //MainItem mainItem = gson.fromJson(jsonObject, MainItem.class);

//                        if (mainItem.getTTC() == 5047) {
//                            PictureItem post = gson.fromJson(jsonObject, PictureItem.class);
//                            if (post.getTitlePicture() != null && post.getTitlePicture().length() > 5) {
//                                ((ListFragment) fragment).list.add(post);
//                            }
//                        } else {
                            ((ListFragment) fragment).dataList.add(gson.fromJson(jsonObject, TextItem.class));
//                        }

                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyItemInserted(((ListFragment) fragment).dataList.size());
                        }
                    }

                    if (_current_page == 1 && ((ListFragment)fragment).dataList.size() == 0){
                        //empty row 3
                        EmptyTextItem empyItem = new EmptyTextItem();
                        ((ListFragment) fragment).dataList.add(empyItem);
                        adapter.notifyDataSetChanged();
                    }

//                    //empty view 1
//                    if (BuildConfig.FLAVOR_version_name.equals("yafte") || BuildConfig.FLAVOR_version_name.equals("yadak")) {
//
//                    }else {
//                        if (_current_page == 1 && ((ListFragment)fragment).list.size() == 0){
//                            rootView.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
//                        }
//                    }

//                    //empty view 2
//                    if (((ListFragment)fragment).list.isEmpty()) {
//                        recyclerView.setVisibility(View.GONE);
//                        emptyView2.setVisibility(View.VISIBLE);
//                    }
//                    else {
//                        recyclerView.setVisibility(View.VISIBLE);
//                        emptyView2.setVisibility(View.GONE);
//                    }


                }
            };
            if (listType == LIST_TYPE_MYPOSTS) {
                Global.apiManagerTubeless.getMyPOST(timelineRequest, retrofitCallbackssx);
            }else if(listType == LIST_TYPE_IMAGE_IN_YADAK){
                Global.apiManagerTubeless.getAmlakList1(timelineRequest, retrofitCallbackssx);
            }else {
                Global.apiManagerTubeless.getTimeline(timelineRequest, retrofitCallbackssx);
            }
        } else if (listType == FRAGMENT_POST_SEARCH_RESULT) {
            adapter.notifyDataSetChanged();
        }
    }

    public static int VIEW_TYPE_TEXT_POST = 1;
    public static int VIEW_TYPE_TEXT_IMAGE_POST = 2;
    public static int VIEW_TYPE_IMAGE_POST = 5047;
    public static int VIEW_TYPE_TRANSACTION = 3;
    public static int VIEW_TYPE_CATEGORY = 4;
    public static int VIEW_EMPTY_TEXT = 100;

    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        TubelessMainViewHolder holder = null;
        if(BuildConfig.FLAVOR_version_name.equals("amlak") && (
                listType == LIST_TYPE_AMLAK_TIMELINE ||
                listType == LIST_TYPE_AMLAK_FILTER ||
                listType == LIST_TYPE_MYPOSTS ||
                listType == ITEM_TYPE_MYFAVS ||
                listType == ITEM_TYPE_AMLAK_LIST_1 ||
                listType == ITEM_TYPE_MYPURCHESE
                )) {
            View view;
            if (viewType == VIEW_TYPE_TEXT_POST) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_text_item, parent, false);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_textpicture_item, parent, false);
            }
            holder = new PictureItemViewHolder(view);
        } else {
            if (viewType == VIEW_TYPE_TEXT_POST) {
                View view;
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_moz_text_post_item, parent, false);
                holder = new PictureItemViewHolder(view);
            } else if (viewType == VIEW_TYPE_IMAGE_POST) {
                View view;
                if (BuildConfig.FLAVOR_version_name.equals("yadak") && (listType == LIST_TYPE_IMAGE_IN_YADAK))
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_picture_item, parent, false);
                else
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_picture_item, parent, false);

                holder = new PictureItemViewHolder(view);


            } else if (viewType == VIEW_EMPTY_TEXT) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_empty, parent, false);
                holder = new EmptyTextViewHolder(view);

                //listType == TYPE_LIST_CATEGORY
            } else if (viewType == VIEW_TYPE_CATEGORY) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_category_item, parent, false);
                holder = new CategoryViewHolder(view);

            } else if (viewType == VIEW_TYPE_TRANSACTION) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_transaction_item, parent, false);
                holder = new TransactionItemViewHolder(view);
//        }else if (listType == ITEM_TYPE_TRANS) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_moz_transaction_item, parent, false);
//            holder = new TransactionViewHolder(view);


            } else if (listType == FRAGMENT_POST_SEARCH_RESULT) {
                final View view = LayoutInflater.from(context).inflate(R.layout._row_of_post_item, parent, false);
                holder = new TubelessMainItemViewHolder(view);
            } else if (listType == FRAGMENT_MOZ) {
                final View view = LayoutInflater.from(context).inflate(R.layout._row_of_bourse_nerkhroz_header, parent, false);
                holder = new CategoryViewHolder(view);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder holder, final int position) {
        //first Item
        if (position == 0 && ((Activity)context) instanceof ContainerActivity) {                 //  <= فاصله اولین آیتم از بالای لیست
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 60;
        }
        //last Item
        if (position == getItemCount() - 1) {
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;
        } else {
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
        }

        if (((ListFragment)fragment).dataList.get(position) instanceof PictureItem) {
            final PictureItem item = (PictureItem) ((ListFragment)fragment).dataList.get(position);
            //todo uncomment item.fill(context , this, listType, holder, item, position);
        }else if (((ListFragment)fragment).dataList.get(position) instanceof TextItem) {
            final MainItem item = (MainItem) ((ListFragment)fragment).dataList.get(position);
            //todo uncomment item.fill(context,this , listType, holder, item, position);

//        }else if (((ListFragment)fragment).list.get(position) instanceof CategoryItem) {
//            final CategoryItem item = (CategoryItem) ((ListFragment)fragment).list.get(position);
//            //todo uncomment item.fill(context,this , listType, holder, item, position,((ListFragment)fragment));
////            item.setFragment(((ListFragment)fragment));
        }else if (((ListFragment)fragment).dataList.get(position) instanceof UserMessageItem) {
            final UserMessageItem item = (UserMessageItem) ((ListFragment)fragment).dataList.get(position);
//            item.fill(context,this , listType, holder, item, position);
        }else if (((ListFragment)fragment).dataList.get(position) instanceof TransactionItem) {
            final TransactionItem item = (TransactionItem) ((ListFragment)fragment).dataList.get(position);
//            item.fill(context,this , listType, holder, item, position);
//        }else if (((ListFragment)fragment).list.get(position) instanceof ATransactionApp) {
//            final ATransactionApp item = (ATransactionApp) ((ListFragment)fragment).list.get(position);
//            //todo uncomment item.fill(context,this , listType, holder, item, position);
//        }else if (((ListFragment)fragment).list.get(position) instanceof PostSearchResponseItem) {
//            PostSearchResponseItem item = (PostSearchResponseItem) ((ListFragment)fragment).list.get(position);
//            //todo uncomment item.fill(context , this, listType, holder, item,position);
        }
    }

    public void removeItem (int listType , int removeIndex ){
        ((ListFragment)fragment).dataList.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }

    @Override
    public int getItemViewType(int position) {
//        if (
//                listType == FRAGMENTLIST_TYPE_MOZ_TIMELINE ||
//                        listType == TYPE_ITEM_MYPURCHESE ||
//                        listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST ||
//                        listType == TYPE_ITEM_MYPOSTS ||
//                        listType == TYPE_ITEM_FILTER ||
//                        listType == TYPE_ITEM_MYFAVS) {
//            return VIEW_TYPE_TEXT_POST;
//        }
//        if (listType == TYPE_IMAGE) {
//            return VIEW_TYPE_IMAGE_POST;
//        }

        if (listType == FRAGMENT_POST_SEARCH_RESULT){
            return 0;
        }
        if (((ListFragment)fragment).dataList.get(position) instanceof EmptyTextItem){
            return VIEW_EMPTY_TEXT;
//        }else if (((ListFragment)fragment).list.get(position) instanceof CategoryItem){
//            return VIEW_TYPE_CATEGORY;
        }else if (((ListFragment)fragment).dataList.get(position) instanceof MainItem){
            if (listType == LIST_TYPE_AMLAK_TIMELINE || listType == LIST_TYPE_AMLAK_FILTER || listType == LIST_TYPE_MYPOSTS || listType == ITEM_TYPE_MYFAVS || listType == ITEM_TYPE_AMLAK_LIST_1) {
                if ((((TextItem) ((ListFragment) fragment).dataList.get(position)).getTitlePicture()) == null) {
                    return VIEW_TYPE_TEXT_POST;
                } else {
                    return VIEW_TYPE_TEXT_IMAGE_POST;
                }
            }else if (((MainItem)((ListFragment)fragment).dataList.get(position)).getTTC() == VIEW_TYPE_IMAGE_POST) {
                return VIEW_TYPE_IMAGE_POST;
            }else {
                return VIEW_TYPE_TEXT_POST;
            }
//        }else if (((ListFragment)fragment).list.get(position) instanceof ATransactionApp){
//            return VIEW_TYPE_TRANSACTION;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        if (((ListFragment)fragment).dataList == null)
            return 0;
        else
            return ((ListFragment)fragment).dataList.size();
    }
}

