package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogImageActivity;
import ir.sajjadyosefi.android.xTubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.xTubeless.classes.model.BlogItem;

 
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.BlogItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TextPictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;

import static ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity.fillTitleForShare;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TimelineItem extends ParentItem {


    private String title;
    private String date;
    private String location;
    private String picture;
    private String text;
    private String registerDate;
    private int viewCount;
    private int shareCount;
    private String userName;
    private String userImage;
    private int categoryID;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public String getPictureTumble() {
        return picture + "2";
    }


    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserNameMasked() {
        return getUserNameMasked(userName);
    }

    public static String getUserNameMasked(String userName) {
        if (userName.equals("ثبت نام نکرده")){
            return userName;
        }else {
            if (userName.length() > 10) {
                try {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(userName.substring(0, 4));
                    stringBuilder.append("***");
                    stringBuilder.append(userName.substring(8, userName.length()));
                    return stringBuilder.toString();
                }catch (Exception ex){

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(userName.substring(0, 4));
                    stringBuilder.append("***");
                    return stringBuilder.toString();
                }
            }else {
                return userName;
            }
        }
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
//        final TimelineItem timelineItem = (TimelineItem)item;
//
//        final boolean[] loadedImage = {false};
//        DateConverterSjd dateUtiliti = new DateConverterSjd();
//
//        StringBuilder date = new StringBuilder();
//
//        if (timelineItem.getDate() != null) {
////            date.append(timelineItem.getDate());
//            date.append(dateUtiliti.getCurrentShamsidate(timelineItem.getDate()));
//        }
//
//        date.append(" ( ");
//        date.append("تاریخ ثبت : ");
//        date.append(timelineItem.getRegisterDate());
//        date.append(" ) ");
//        holder.textViewDate.setText(date.toString());
//
////        fillTitle(mContext,timelineItem.getTitle(),timelineItem.getCategoryID(),holder.textViewTitle);
//
//        holder.textViewLocation.setText(timelineItem.getLocation());
////        holder.textViewUserName.setText(timelineItem.getUserNameMasked());
////        holder.textViewCount.setText(timelineItem.getViewCount() + "");
//
//
//        holder.imageviewPicture.setVisibility(View.GONE);
//
//
//
//        onclicks(mContext,listType , holder, timelineItem);
////        LoadImages.loadAvatarimage(timelineItem.getUserImage(),holder.imageViewUserAvatar);
//        LoadImages.loadProfileimage(timelineItem.getPicture(),holder.imageviewPicture);
//    }

    private void onclicks(Context mContext , int listType, TextPictureItemViewHolder holder, TimelineItem timelineItem) {


        View.OnClickListener onStarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,yafteItem.getID(),Global.user.getUserID(),yafteItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();
            }
        };
//        holder.imageViewFavourite.setOnClickListener(onStarClickListener);
//        holder.textViewFavourite.setOnClickListener(onStarClickListener);
//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);


        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof TimelineItem) {
//                    if (listType == )
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();


                    Intent intent ;
//                    if ()
                        intent = new Intent(mContext, ReadBlogActivity.class);
//                    else
//                        intent = new Intent(mContext, ReadBlogImageActivity.class);

                    Gson gson = new Gson();
                    String json = gson.toJson(timelineItem);

                    // Old Transfer
//                    intent.putExtra("Object", json);

                    //New Transfer
                    Bundle bundle = new Bundle();
                    bundle.putString("Object", json);
                    bundle.putString("Type", "TimelineItem");
                    intent.putExtras(bundle);

                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                }
            }
        };


        holder.imageviewPicture.setOnClickListener(onContentClick);
        holder.textViewDate.setOnClickListener(onContentClick);
        holder.textViewLocation.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.linearLayoutCenter.setOnClickListener(onContentClick);
//        holder.textViewUserName.setOnClickListener(onclick2);

    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem0) {
//                EndlessList_Adapter.prepareToShare(mContext,blogItem.getTitlePicture(), blogItem.getStatement(), loadedImage[0]);

        TimelineItem timelineItem = (TimelineItem) timelineItem0;
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
        stringBuilder0.append("\n");
        stringBuilder0.append("\n");


        stringBuilder0.append(timelineItem.getTitle());
        stringBuilder0.append("-");
        stringBuilder0.append(timelineItem.getPicture());

        stringBuilder0.append(" در ");
        stringBuilder0.append(((TimelineItem) timelineItem0).getLocation());

        stringBuilder0.append("\n");
        stringBuilder0.append("\n");
        stringBuilder0.append(" ثبت شده در اپلیکیشن در تاریخ ");
        stringBuilder0.append(timelineItem.getRegisterDate());

        stringBuilder0.append("\n");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
        intent.setType("text/plain");
        ((Activity)mContext).startActivityForResult(intent , 60);
    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }


    public void prepareBlogItem(final Context mContext, final DilatingDotsProgressBar mProgressBar,
                                final BlogItemViewHolder holder, final List<Object> mTimelineItemList, final int position) {

        final boolean[] loadedImage = {false};
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        String json0000 = gson.toJson((BlogItem)(mTimelineItemList.get(position)));
        final BlogItem blogItem = gson.fromJson(json0000,BlogItem.class);


        DateConverterSjd dateUtiliti = new DateConverterSjd();
        holder.textViewTitle.setText(blogItem.getTitle());
//        holder.textViewTitle2.setText(blogItem.getTitle());
        holder.textViewStatment.setText(blogItem.getStatement());
        holder.textViewStatment2.setVisibility(View.GONE);

        if (blogItem.getUser() != null)
            holder.textViewUserName.setText(blogItem.getUser().getUserName());

        holder.textViewCount.setText(blogItem.getViewCount() + "");


        holder.linearLayoutFaveorative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.user2 == null){
                    DialogUtil.ShowMessageDialog(mContext,"",mContext.getString(R.string.NotLoggedIn3));
                }else {
//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,blogItem.getID(),Global.user.getUserId(),blogItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();
                }

            }
        });
        if(blogItem.isInMyFavList())
            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
        else
            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);

//        if (blogItem.getUser() != null)
//            Picasso.with(mContext)
//                    .load(blogItem.getUser().getUserImage())
//                    .placeholder(R.drawable.progress_animation)
//                    //.centerInside()
//                    .transform(transformation)
//                    .into(holder.imageViewUserAvatar, new Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError() {
////                            Picasso.with(mContext)
////                                    .load(R.drawable.sajjad)
////                                    .transform(transformation)
////                                    .into(holder.imageViewUserAvatar);
//                        }
//                    });

        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ReadBlogActivity.class);
//                Gson gson = new Gson();
//                String json = gson.toJson(timelineItem);
//                intent.putExtra("Object", json);
//                mContext.startActivity(intent);
//                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        };
        holder.textViewTitle.setOnClickListener(onclick);
        holder.textViewStatment.setOnClickListener(onclick);
        holder.textViewStatment2.setOnClickListener(onclick);
    }

    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}
