package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class TwoLinesViewHolder extends TubelessMainViewHolder {

    public final TextView title;
    public final TextView textViewCarName;
    public final ImageView imageViewCarLogo;
    public final ImageView imageViewMenu;
    public final ImageView imageView;

    public final TextView textViewCount;
    public final View layoutCount ;
    public final View layoutShare ;
    public final View layoutDownload ;
    public final View layoutDelete ;
    public final View viewInvisible ;


    public TwoLinesViewHolder(final View itemView) {
        super(itemView);
        layoutCount = (View) itemView.findViewById(R.id.layoutCount);
        layoutShare = (View) itemView.findViewById(R.id.layoutShare);
        layoutDownload = (View) itemView.findViewById(R.id.layoutDownload);
        layoutDelete = (View) itemView.findViewById(R.id.layoutDelete);
        viewInvisible = (View) itemView.findViewById(R.id.viewInvisible);
        textViewCount = (TextView) itemView.findViewById(R.id.textViewCount);


        title = (TextView) itemView.findViewById(R.id.title);
        textViewCarName = (TextView) itemView.findViewById(R.id.textViewCarName);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewCarLogo = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        imageViewMenu = (ImageView) itemView.findViewById(R.id.imageViewMenu);
    }
}
