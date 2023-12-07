package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder extends TubelessMainViewHolder {

    public ImageView imageViewUserAvatar;
    public TextView textViewUserName;


    public TextView textViewDate;
    public TextView textViewTitle;
    public TextView textViewText;

    public mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder(View itemView) {
        super(itemView);

        imageViewUserAvatar                 = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        textViewUserName                 = (TextView) itemView.findViewById(R.id.textViewUserName);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewText                    = (TextView) itemView.findViewById(R.id.textViewText);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
    }
}
