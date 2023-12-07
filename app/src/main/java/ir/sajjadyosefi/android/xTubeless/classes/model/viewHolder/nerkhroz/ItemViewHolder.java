package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.Main;

public class ItemViewHolder extends ChildViewHolder {

//    public RelativeLayout linearLayoutMain;
//    public TextView textViewTitle;
//    public TextView textViewDate;
//
//    public BourseDataViewHolder(View itemView) {
//        super(itemView);
//
//        linearLayoutMain               = (RelativeLayout) itemView.findViewById(R.id.linearLayoutMain);
//        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
//        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
//    }

    private ImageView imageViewDirection , imageViewPicture;
    private TextView textViewTitle, textViewPrice , textViewChange;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        textViewChange = (TextView) itemView.findViewById(R.id.textViewChange);
        imageViewPicture = (ImageView) itemView.findViewById(R.id.imageViewPicture);
        imageViewDirection = (ImageView) itemView.findViewById(R.id.imageViewDirection);
    }

    public void bind(Main movies) {
        textViewTitle.setText(movies.getName());
        textViewPrice.setText(movies.getFPrice());
        textViewChange.setText(movies.getDiff());
        Picasso.get()
                .load("http://media.sajjadyosefi.ir/image/bourse/static/stock-index.png")
                .into(imageViewPicture);

        if (movies.getType().equals("low")) {
            Picasso.get()
                    .load("http://media.sajjadyosefi.ir/image/bourse/static/down.png")
                    .into(imageViewDirection);
            textViewChange.setTextColor(Color.RED);
        }else {
            Picasso.get()
                    .load("http://media.sajjadyosefi.ir/image/bourse/static/up.png")
                    .into(imageViewDirection);
            textViewChange.setTextColor(Color.parseColor("#006400"));
        }
    }
}
