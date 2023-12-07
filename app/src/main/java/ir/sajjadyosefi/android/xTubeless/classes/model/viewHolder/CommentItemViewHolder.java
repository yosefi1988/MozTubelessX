package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class CommentItemViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutTop;
    public LinearLayout linearLayoutCenter;
    public LinearLayout linearLayoutBottom;




    public ImageView imageViewUserAvatar;
    public ImageView imageViewReport;
    public TextView textViewUserName;
    public TextView textViewReport;

    public TextView textViewTitle;
    public TextView textViewText;
    public TextView textViewDate;
    public TextView textViewCount;

    public CommentItemViewHolder(View itemView) {
        super(itemView);

        linearLayoutTop               = (LinearLayout) itemView.findViewById(R.id.linearLayoutTop);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        linearLayoutBottom            = (LinearLayout) itemView.findViewById(R.id.linearLayoutBottom);

        imageViewUserAvatar           = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        imageViewReport                 = (ImageView) itemView.findViewById(R.id.imageViewReport);
        textViewUserName              = (TextView) itemView.findViewById(R.id.textViewUserName);
        textViewReport              = (TextView) itemView.findViewById(R.id.textViewReport);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewText                  = (TextView) itemView.findViewById(R.id.textViewText);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
        textViewCount                 = (TextView) itemView.findViewById(R.id.textViewCount);
    }
}
