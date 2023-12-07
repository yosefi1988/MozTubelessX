package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

public abstract class ITubelessAdapter extends RecyclerView.Adapter<TubelessMainViewHolder> {
//    public void removeItem(int listType, int itemIndex);
    public abstract void firstLoadAndRefresh(Context context);
    public abstract void init(int listType, CategoryItem categoryItem, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2 , TimelineRequest searchRequest);

}
