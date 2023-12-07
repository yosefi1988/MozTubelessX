package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder extends TubelessMainViewHolder {

    public ImageView imageviewMessage;
    public ImageView imageViewfav;
    public ImageView imageViewpay;

    public TextView textViewDate;
    public TextView textViewTitle;
    public TextView textViewText;

    public mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder(View itemView) {
        super(itemView);

        imageviewMessage                 = (ImageView) itemView.findViewById(R.id.imageviewMessage);
        imageViewfav                     = (ImageView) itemView.findViewById(R.id.imageviewfav);
        imageViewpay                     = (ImageView) itemView.findViewById(R.id.imageViewpay);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewText                    = (TextView) itemView.findViewById(R.id.textViewText);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);


    }
}
