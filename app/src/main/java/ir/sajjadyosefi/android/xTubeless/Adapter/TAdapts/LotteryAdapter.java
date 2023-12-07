package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;

// FRAGMENT_POST_SEARCH_RESULT   = LotteryItem

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.lottery.LotterySearchResponseItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.LotteryItemViewHolder;

public class LotteryAdapter extends ITubelessAdapter {
    //private CategoryItem categoryItem;
    public static View rootView;
    private Bundle bundle;
    private Fragment fragment;
    LotteryAdapter adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    public TimelineRequest timelineSearchRequest = null;

    @Override
    public void firstLoadAndRefresh(Context context) { }

    @Override
    public void init(
            int listType,
            CategoryItem categoryItem,
            final Context context,
            View rootView,
            RecyclerView recyclerView,
            LinearLayoutManager linearLayoutManager,
            SwipeRefreshLayout mSwipeRefreshLayout,
            Fragment fragment,
            Bundle bundle,
            TextView emptyView2 ,
            TimelineRequest searchRequest) {
        this.listType = listType;
        this.fragment = fragment;
        this.picasso = Picasso.get();
        this.context = context;
        this.emptyView2 = emptyView2;
        this.rootView = rootView;
        this.mLayoutManager = linearLayoutManager ;
        this.recyclerView = recyclerView;
        this.adapter = this;
        this.bundle = bundle;
        this.timelineSearchRequest = searchRequest;

        if (((ListFragment)fragment).dataList == null)
            ((ListFragment)fragment).dataList = new ArrayList<>();

//        ((ListFragment) fragment).list.add(gson.fromJson(jsonObject, LotteryItem.class));
//        adapter.notifyDataSetChanged();
    }



    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_lottery_item, parent, false);
        TubelessMainViewHolder holder = new LotteryItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder viewHolder, final int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof LotterySearchResponseItem) {
            final LotterySearchResponseItem lotteryItem = (LotterySearchResponseItem) ((ListFragment) fragment).dataList.get(position);
            lotteryItem.fill(context, this, listType, viewHolder, lotteryItem, position,null);
        }
    }

    @Override
    public int getItemCount() {
        return ((ListFragment)fragment).dataList.size();
    }
}

