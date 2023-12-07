package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class PictureItemViewHolder extends TubelessMainViewHolder {

    public ImageView imageView;
    public ImageView imageViewMenu;

    public PictureItemViewHolder(View itemView) {
        super(itemView);
        imageView                  = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewMenu              = (ImageView) itemView.findViewById(R.id.imageViewMenu);
    }
}
