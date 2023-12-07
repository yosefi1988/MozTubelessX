package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class TubelessMainViewHolder extends RecyclerView.ViewHolder  {

    public TubelessMainViewHolder(View itemView) {
        super(itemView);
    }

    public void clearAnimation() {
        itemView.clearAnimation();
    }

}
