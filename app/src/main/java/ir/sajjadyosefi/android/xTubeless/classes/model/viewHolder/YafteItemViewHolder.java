package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class YafteItemViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutTop;
    public LinearLayout linearLayoutCenter;
    public LinearLayout linearLayoutBottom;

    public ImageView imageViewShare;
    public TextView textViewShare;
    public ImageView imageViewDelete;
    public TextView textViewDelete;

    public ImageView imageViewUserAvatar;
    public TextView textViewUserName;

    public TextView textViewTitle;
    public TextView textViewStatment;
    public TextView textViewStatment2;
    public TextView textViewCount;

    public YafteItemViewHolder(View itemView) {
        super(itemView);

        linearLayoutTop               = (LinearLayout) itemView.findViewById(R.id.linearLayoutTop);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        linearLayoutBottom            = (LinearLayout) itemView.findViewById(R.id.linearLayoutBottom);

        imageViewShare               = (ImageView) itemView.findViewById(R.id.imageViewShare);
        textViewShare                = (TextView) itemView.findViewById(R.id.textViewShare);
        imageViewDelete               = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        textViewDelete                = (TextView) itemView.findViewById(R.id.textViewDelete);


        imageViewUserAvatar           = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        textViewUserName              = (TextView) itemView.findViewById(R.id.textViewUserName);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewStatment              = (TextView) itemView.findViewById(R.id.textViewStatment);
        textViewStatment2             = (TextView) itemView.findViewById(R.id.textViewStatment2);
        textViewCount                 = (TextView) itemView.findViewById(R.id.textViewCount);
    }
}
