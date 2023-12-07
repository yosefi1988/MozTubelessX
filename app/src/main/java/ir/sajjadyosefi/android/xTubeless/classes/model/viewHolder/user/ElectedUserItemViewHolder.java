package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

public class ElectedUserItemViewHolder extends TubelessMainViewHolder {

    public ConstraintLayout rootView;
    public ImageView imageViewUserAvatar;

    public TextView textViewStateName;
    public TextView textViewFamily;
    public TextView textViewName;
    public TextView textViewTitle;
    public RatingBar ratingBarStar;

    public ElectedUserItemViewHolder(View _row_user_item) {
        super(_row_user_item);

        rootView                    = (ConstraintLayout) _row_user_item.findViewById(R.id.rootView);

        imageViewUserAvatar           = (ImageView) _row_user_item.findViewById(R.id.imageViewUserAvatar);

        textViewStateName               = (TextView) _row_user_item.findViewById(R.id.textViewStateName);
        textViewFamily                  = (TextView) _row_user_item.findViewById(R.id.textViewFamily);
        textViewName                    = (TextView) _row_user_item.findViewById(R.id.textViewName);
        textViewTitle                    = (TextView) _row_user_item.findViewById(R.id.textViewTitle);
        ratingBarStar                    = (RatingBar) _row_user_item.findViewById(R.id.ratingBarStar);
    }
}
