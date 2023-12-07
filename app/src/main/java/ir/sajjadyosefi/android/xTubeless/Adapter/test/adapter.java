package ir.sajjadyosefi.android.xTubeless.Adapter.test;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;

public abstract class adapter extends RecyclerView.Adapter<TwoLinesViewHolder> {

    xxxxxxxx xxxx ;

    @NonNull
    @Override
    public TwoLinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        xxxx.loadData();
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TwoLinesViewHolder holder, int position) {

        xxxx.scrollUp();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
