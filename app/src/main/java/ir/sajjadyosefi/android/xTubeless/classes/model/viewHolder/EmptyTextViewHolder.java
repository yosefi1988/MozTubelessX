package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class EmptyTextViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutCenter;
    public ImageView imageviewPicture;
    public TextView textViewTitle;
    public TextView textViewText;

    public EmptyTextViewHolder(View itemView) {
        super(itemView);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);

        imageviewPicture           = (ImageView) itemView.findViewById(R.id.imageviewPicture);
        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewText                 = (TextView) itemView.findViewById(R.id.textViewText);

    }
}
