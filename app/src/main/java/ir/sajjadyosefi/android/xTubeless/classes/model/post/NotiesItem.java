package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

/**
 * Created by sajjad on 7/30/2017.
 */

public class NotiesItem extends ParentItem {

    public NotiesItem() {

    }

//    @Override
//    public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//
//        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
//        final MainItem timelineItem = (MainItem)item;
//
//    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}


