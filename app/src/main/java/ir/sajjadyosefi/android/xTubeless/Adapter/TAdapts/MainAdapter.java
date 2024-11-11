package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;

//ITEM_TYPE_MYFAVS
//LIST_TYPE_AMLAK_TIMELINE
//ITEM_TYPE_MYPURCHESE
//LIST_TYPE_MYPOSTS
//LIST_TYPE_AMLAK_FILTER
//FRAGMENTLIST_TYPE_MOZ_CREATORS_POST
//FRAGMENTLIST_TYPE_MOZ_TIMELINE

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.EmptyTextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewTimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.EmptyTextViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TextPictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;

import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.FRAGMENTLIST_TYPE_MOZ_CREATORS_POST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYFAVS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.ITEM_TYPE_MYPURCHESE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_AMLAK_FILTER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_TYPE_MYPOSTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.TransactionsAdapter.VIEW_EMPTY_TEXT;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.getAppDownloadedStore;


public class MainAdapter extends ITubelessAdapter {
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private Fragment fragment;
    MainAdapter adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    EndlessRecyclerOnScrollListener onScrollListener;


    public TimelineRequest timelineSearchRequest = null;

    public MainAdapter() {

    }

//    public MainAdapter(int listType, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {
//         new MainAdapter(listType, null, context, rootView, recyclerView, linearLayoutManager, mSwipeRefreshLayout, fragment, bundle,null,null) ;
//    }

    @Override
    public void init(int listType, CategoryItem categoryItem, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2 , TimelineRequest _searchRequest) {

        this.listType = listType;
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
        this.timelineSearchRequest = _searchRequest;

        if (((ListFragment)fragment).dataList == null)
            ((ListFragment)fragment).dataList = new ArrayList<>();

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
        firstLoadAndRefresh(context);
    }

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
        ((TubelessActivity) context).progressDialog.show();
        TimelineRequest timelineRequest = null;

        if (listType == LIST_TYPE_AMLAK_FILTER) {
            timelineRequest = timelineSearchRequest;
            timelineRequest.setPageIndex(_current_page - 1 + "");
        }else {
            timelineRequest = new TimelineRequest(_current_page - 1);
            if (timelineSearchRequest != null)
                timelineRequest.setTtc(timelineSearchRequest.getTtc());
        }

        //Active
        if (listType == FRAGMENTLIST_TYPE_MOZ_CREATORS_POST) {
            if (Global.user2 != null && Global.user2.isUserAdmin()) {
                timelineRequest.setActive(false);
            } else {
                timelineRequest.setActive(true);
            }
        }else {
            timelineRequest.setActive(true);
        }

        //Fav
        if (listType == ITEM_TYPE_MYFAVS) {
            timelineRequest.setFaved(true);
        }else {
            timelineRequest.setFaved(false);
        }

        //visited
        if (listType == ITEM_TYPE_MYPURCHESE) {
            timelineRequest.setVisited(true);
        }else {
            timelineRequest.setVisited(false);
        }

        //Store
        timelineRequest.setStore(getAppDownloadedStore());

        //User Code
        if (listType != FRAGMENTLIST_TYPE_MOZ_CREATORS_POST) {
            if (Global.user2 == null) {
                timelineRequest.setUserCode(null);
            } else {
                timelineRequest.setUserCode(Global.user2.getUserCodeAsString());
            }
            if(BuildConfig.FLAVOR.equals("amlak") && ((Activity) context).getIntent().hasExtra("AMLAK_USERCODE")) {
                timelineRequest.setUserCode(((Activity) context).getIntent().getStringExtra("AMLAK_USERCODE"));
            }
        }

        TubelessRetrofitCallbackss retrofitCallbackssx = new TubelessRetrofitCallbackss(context, NewTimelineListResponse.class) {
            @Override
            public void t_beforeSendRequest() {
            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity) context).progressDialog.hide();
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

                if (_current_page == 1 && ((ListFragment) fragment).dataList.size() == 0) {
                    //empty row 3
                    EmptyTextItem empyItem = new EmptyTextItem();
                    ((ListFragment) fragment).dataList.add(empyItem);
                    adapter.notifyDataSetChanged();
                }

//                    //empty view 1
//                    if (BuildConfig.FLAVOR.equals("yafte") || BuildConfig.FLAVOR.equals("yadak")) {
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
        }else {
            Global.apiManagerTubeless.getTimeline(timelineRequest, retrofitCallbackssx);
        }
    }
    public static int VIEW_TYPE_TEXT_POST = 1;
    public static int VIEW_TYPE_TEXT_IMAGE_POST = 2;
    public static int VIEW_TYPE_TEXT_IMAGE_AD_POST = 3;
    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        //BuildConfig.FLAVOR.equals("amlak")
        TubelessMainViewHolder holder = null;
        View view;

        if (viewType == VIEW_EMPTY_TEXT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_empty, parent, false);
            holder = new EmptyTextViewHolder(view);
            return holder;
        }

        if (viewType == VIEW_TYPE_TEXT_POST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_text_item, parent, false);
        } else if (viewType == VIEW_TYPE_TEXT_IMAGE_AD_POST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_textpicture_aditem, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_textpicture_item, parent, false);
        }
        holder = new TextPictureItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder holder, final int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof TextItem) {
            final MainItem item = (MainItem) ((ListFragment) fragment).dataList.get(position);
            item.fill(context, this, listType, holder, item, position,null);
        }
    }

    public void removeItem (int listType , int removeIndex ){
        ((ListFragment)fragment).dataList.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }

    @Override
    public int getItemViewType(int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof EmptyTextItem){
            return VIEW_EMPTY_TEXT;
        }else if (((ListFragment)fragment).dataList.get(position) instanceof MainItem){
            if ((((TextItem) ((ListFragment) fragment).dataList.get(position)).getTTC()) == 9138) {
                return VIEW_TYPE_TEXT_IMAGE_AD_POST;
            } else {
                if ((((TextItem) ((ListFragment) fragment).dataList.get(position)).getTitlePicture()) == null) {
                    return VIEW_TYPE_TEXT_POST;
                } else {
                    return VIEW_TYPE_TEXT_IMAGE_POST;
                }
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (((ListFragment)fragment).dataList == null)
            return 0;
        else
            return ((ListFragment)fragment).dataList.size();
    }
}

