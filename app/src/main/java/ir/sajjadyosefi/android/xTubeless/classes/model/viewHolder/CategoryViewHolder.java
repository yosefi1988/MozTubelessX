package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class CategoryViewHolder extends TubelessMainViewHolder {

    public TextView textViewTitle;
    public TextView textViewDescription;
    public ImageView buttonDelete;
    public ImageButton buttonShow;

    public RelativeLayout linearLayoutMain;
    public LinearLayout linearLayoutTop;
    public LinearLayout linearLayoutCenter;
    public LinearLayout linearLayoutBottom;



    public ImageView imageView;
    public ImageView imageViewPicture;
    public ImageView readmore;
    public CheckBox checkBox;
    public TextView textViewUserName;
    public TextView textViewPicture;


    public TextView textViewLocation;
    public TextView textViewDate;
    public TextView textViewCount;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewDescription           = (TextView) itemView.findViewById(R.id.textViewDescription);
        buttonDelete                = (ImageView) itemView.findViewById(R.id.buttonDelete);
        buttonShow                  = (ImageButton) itemView.findViewById(R.id.buttonShow);



        linearLayoutTop               = (LinearLayout) itemView.findViewById(R.id.linearLayoutTop);
        linearLayoutMain               = (RelativeLayout) itemView.findViewById(R.id.linearLayoutMain);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        linearLayoutBottom            = (LinearLayout) itemView.findViewById(R.id.linearLayoutBottom);


        imageView                       = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewPicture              = (ImageView) itemView.findViewById(R.id.imageViewPicture);
        checkBox                      = (CheckBox) itemView.findViewById(R.id.checkBox);
        readmore                        = (ImageView) itemView.findViewById(R.id.readmore);
        textViewUserName              = (TextView) itemView.findViewById(R.id.textViewUserName);
        textViewPicture               = (TextView) itemView.findViewById(R.id.textViewPicture);
        textViewLocation              = (TextView) itemView.findViewById(R.id.textViewLocation);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
        textViewCount                 = (TextView) itemView.findViewById(R.id.textViewCount);

    }
}
