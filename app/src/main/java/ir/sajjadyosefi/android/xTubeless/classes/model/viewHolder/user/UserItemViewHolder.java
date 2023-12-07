package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

public class UserItemViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutTop;
    public ImageView imageViewUserAvatar;
    public TextView textViewUserName;

    public UserItemViewHolder(View _row_user_item) {
        super(_row_user_item);

        linearLayoutTop               = (LinearLayout) _row_user_item.findViewById(R.id.linearLayoutTop);
        imageViewUserAvatar           = (ImageView) _row_user_item.findViewById(R.id.imageViewUserAvatar);
        textViewUserName              = (TextView) _row_user_item.findViewById(R.id.textViewUserName);
    }
}
