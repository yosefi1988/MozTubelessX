package ir.sajjadyosefi.android.xTubeless.classes.model.post;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PictureItemViewHolder;


/**
 * Created by sajjad on 7/30/2017.
 */

public class EmptyTextItem extends MainItem {
    List<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts> Amounts = new ArrayList<Amounts>();
    List<String> Images = new ArrayList<String>();

    private String Text;
    private String image;
    private String title;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmptyTextItem() {
        super();
    }
    public List<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts> getAmounts() {
        return Amounts;
    }

    public void setAmounts(List<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts> amounts) {
        Amounts = amounts;
    }

    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> images) {
        Images = images;
    }

//    public void fill(Context mContext,
//                     MainAdapter xAdapter,
//                     int listType,
//                     PostViewHolder holder0,
//                     IItems item,
//                     int position) {
//
//        super.fill(mContext, xAdapter, listType,holder0,item, position);
//
//        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
//        final EmptyTextItem timelineItem = (EmptyTextItem)item;
//
//        holder.imageviewPicture.setVisibility(View.GONE);
//        holder.textViewTitle.setText(timelineItem.getTitle());
//
////        if (timelineItem.getTitlePicture() == null) {
////            holder.imageView.setVisibility(View.GONE);
////        }else {
////            holder.imageView.setVisibility(View.VISIBLE);
////            Picasso.get()
//////                    .load(timelineItem.getTitlePicture().replace("\\Blog\\","\\Blog\\Thumbnail\\"))
////                    .load(timelineItem.getTitlePicture())
////                    //.noPlaceholder()
////                    .placeholder(R.drawable.png_image)
////                    .error(R.drawable.png_image)
////                    .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
////                    .centerCrop()
////                    .into(holder.imageView);
////        }
//        onclicks(mContext,listType , holder, timelineItem);
//        loadImage(holder, timelineItem);
//    }

    private void onclicks(Context mContext , int listType, PictureItemViewHolder holder, MainItem timelineItem) {

    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}


