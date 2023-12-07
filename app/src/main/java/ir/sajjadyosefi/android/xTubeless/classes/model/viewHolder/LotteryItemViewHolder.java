package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;

public class LotteryItemViewHolder extends TubelessMainViewHolder {

    public ConstraintLayout rootView;
    public ImageView imageViewLogo;

    public TextView textViewName;
    public TextView textViewHref;
    public TextView textViewWebsite;
    public TextView textViewDescription;
    public ConstraintLayout layoutShare;

    public LotteryItemViewHolder(View _row_user_item) {
        super(_row_user_item);
        rootView                    = (ConstraintLayout) _row_user_item.findViewById(R.id.rootView);
        imageViewLogo               = (ImageView) _row_user_item.findViewById(R.id.imageViewLogo);

        textViewName                = (TextView) _row_user_item.findViewById(R.id.textViewName);
        textViewHref                = (TextView) _row_user_item.findViewById(R.id.textViewHref);
        textViewWebsite             = (TextView) _row_user_item.findViewById(R.id.textViewWebsite);
        textViewDescription         = (TextView) _row_user_item.findViewById(R.id.textViewDescription);
        layoutShare                 = (ConstraintLayout) _row_user_item.findViewById(R.id.layoutShare);
    }
}
