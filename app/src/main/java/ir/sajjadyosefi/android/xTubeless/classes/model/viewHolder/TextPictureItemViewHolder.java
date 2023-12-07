package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class TextPictureItemViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutCenter;
    public ImageView imageviewPicture;
    public ImageView imageviewMessage;
    public ImageView imageView;
    public ImageView imageViewMenu;

    public ImageView textViewfav;
    public ImageView textViewpay;
    public TextView textViewTitle;
    public TextView textViewLocation;
    public TextView textViewDate;

    public TextPictureItemViewHolder(View itemView) {
        super(itemView);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);

        imageviewMessage           = (ImageView) itemView.findViewById(R.id.imageviewMessage);
        imageviewPicture           = (ImageView) itemView.findViewById(R.id.imageviewPicture);
        imageView                  = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewMenu              = (ImageView) itemView.findViewById(R.id.imageViewMenu);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewLocation              = (TextView) itemView.findViewById(R.id.textViewLocation);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);

        textViewfav                  = (ImageView) itemView.findViewById(R.id.imageviewfav);
        textViewpay                  = (ImageView) itemView.findViewById(R.id.imageViewpay);

    }
}
