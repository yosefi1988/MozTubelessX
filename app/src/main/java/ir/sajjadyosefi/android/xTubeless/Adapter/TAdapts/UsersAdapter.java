package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;

// ITEM_TYPE_AMLAK_LIST_1   = ElectedUserItem

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.EmptyTextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.electedUser.ElectedUserResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.ElectedUserItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.EmptyTextViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.user.ElectedUserItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.TransactionsAdapter.VIEW_EMPTY_TEXT;


public class UsersAdapter extends ITubelessAdapter {

    private CategoryItem categoryItem;
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private Fragment fragment;
    UsersAdapter adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    EndlessRecyclerOnScrollListener onScrollListener;

    public TimelineRequest timelineSearchRequest = null;

    public UsersAdapter() {

    }

    @Override
    public void init(int listType, CategoryItem categoryItem, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2 , TimelineRequest searchRequest) {

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
        TimelineRequest timelineRequest = new TimelineRequest(_current_page - 1);

        //User Code
        if (Global.user2 == null) {
            timelineRequest.setUserCode(null);
        } else {
            timelineRequest.setUserCode(Global.user2.getUserCodeAsString());
        }

        TubelessRetrofitCallbackss retrofitCallbackssx = new TubelessRetrofitCallbackss(context, ElectedUserResponse.class) {
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
                ElectedUserResponse responseX = (ElectedUserResponse) response;
//                    data.add(new NotiesItem());
                Gson gson = new Gson();

                for (Object item : responseX.getElectedUserList()) {
                    JsonObject jsonObject = gson.toJsonTree(item).getAsJsonObject();
                    ((ListFragment) fragment).dataList.add(gson.fromJson(jsonObject, ElectedUserItem.class));
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
        Global.apiManagerTubeless.getElectedUsers(5,_current_page - 1 , timelineRequest, retrofitCallbackssx);
    }
    public static int VIEW_TYPE_UserItem = 2;
    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        //BuildConfig.FLAVOR_version_name.equals("amlak")
        TubelessMainViewHolder holder = null;
        View view;

        if (viewType == VIEW_EMPTY_TEXT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_empty, parent, false);
            holder = new EmptyTextViewHolder(view);
            return holder;
        }else//if (viewType == VIEW_TYPE_UserItem) {
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_elected_user_item, parent, false);
            holder = new ElectedUserItemViewHolder(view);
            return holder;
        }



    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder viewHolder, final int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof ElectedUserItem) {
            final ElectedUserItem electedUserItem = (ElectedUserItem) ((ListFragment) fragment).dataList.get(position);
            electedUserItem.fill(context, this, listType, viewHolder, electedUserItem, position,null);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof EmptyTextItem){
            return VIEW_EMPTY_TEXT;
        }else if (((ListFragment)fragment).dataList.get(position) instanceof ElectedUserItem){
            return VIEW_TYPE_UserItem;
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

