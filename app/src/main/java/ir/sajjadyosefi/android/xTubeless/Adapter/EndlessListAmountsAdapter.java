package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;


public class EndlessListAmountsAdapter extends RecyclerView.Adapter<EndlessListAmountsAdapter.ParentViewHolder> {

    public static final int SUBSCRIPTIONS = 4;
    public int listType = 0;
    public boolean enable = true;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<Amounts> mTimelineItemList;

    EndlessListAmountsAdapter adapter;
    public LinearLayoutManager mLayoutManager = null ;


    //  Subscriptions
    public EndlessListAmountsAdapter(Context context, LinearLayoutManager mLayoutManager, View rootview, boolean enable, List<Amounts> wasterWaterList) {
        this.mContext = context ;
        this.mLayoutManager = mLayoutManager;
        this.mRecyclerView = rootview.findViewById(R.id.recyclerView);
        this.mTimelineItemList = wasterWaterList;
        this.adapter = this ;
        this.enable = enable ;
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        public ParentViewHolder(View itemView) {
            super(itemView);
        }
        public void clearAnimation() {
            itemView.clearAnimation();
        }
    }

    public class SubscribeViewHolder extends ParentViewHolder {
        public TextView textViewTitle;
        public TextView textViewValue;
        public Button buttonDelete;

        @SuppressLint("WrongViewCast")
        public SubscribeViewHolder(View itemView) {
            super(itemView);
            textViewTitle                = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewValue                = (TextView) itemView.findViewById(R.id.textViewValue);
            buttonDelete            = (Button) itemView.findViewById(R.id.buttonDelete);
        }
    }


    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == SUBSCRIPTIONS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_subscribe, parent, false);
            SubscribeViewHolder viewHolder = new SubscribeViewHolder(view);
            return viewHolder;
//        }
//        if (viewType == 0) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_progress, parent, false);
//            ProgressViewHolder holder = new ProgressViewHolder(view);
//            return holder;
//        }
//        return null;
    }

    @Override
    public void onBindViewHolder(final ParentViewHolder holder, final int position) {
        ((Amounts)mTimelineItemList.get(position)).prepareYafteItem(mContext, (SubscribeViewHolder) holder, mTimelineItemList, position,adapter);
    }

    @Override
    public int getItemCount() {
        return mTimelineItemList.size() ;
    }

    @Override
    public void onViewDetachedFromWindow(final ParentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
        ((ParentViewHolder)holder).clearAnimation();
    }


}