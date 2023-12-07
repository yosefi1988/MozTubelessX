package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;


public class TransactionItemViewHolder extends TubelessMainViewHolder {

    public LinearLayout linearLayoutCenter;
    public ImageView imageviewup , imageviewdown;

    public TextView textViewAmount;
    public TextView textViewpay;
    public TextView textViewTitle;
    public TextView textViewRef;
    public TextView textViewDate;

    public TransactionItemViewHolder(View itemView) {
        super(itemView);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        imageviewup                     = (ImageView) itemView.findViewById(R.id.imageviewup);
        imageviewdown                     = (ImageView) itemView.findViewById(R.id.imageviewdown);

        textViewAmount                 = (TextView) itemView.findViewById(R.id.textViewAmount);
        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
        textViewRef                   = (TextView) itemView.findViewById(R.id.textViewRef);
        textViewpay                  = (TextView) itemView.findViewById(R.id.textViewpay);

    }
}
