package ir.sajjadyosefi.android.xTubeless.classes.model.mainList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity;

 
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.Statement;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.TextContent;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CategoryViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;

/**
 * Created by sajjad on 1/20/2018.
 */

public class MainListItem extends ParentItem {

    private String title;
    private String titlePicture;
    private String statement;
    private String textPicture;
    private String text;
    private String registerDate;
    private int viewCount;
    private int shareCount;
    private int categoryID;
    private String userName;
    private String userImage;

    public MainListItem(TimelineItem item) {
        this.title = item.getTitle();

        String statement = "{\"title\":\"" + item.getTitle() + "\"," +
                "\"model\":\"" + "\","+
                "\"mobile\":\"00\"," +
                "\"text\":\""+ item.getText() +"\"," +
                "\"description\":\"" + "\"}";

        this.statement = statement;

        this.userImage = item.getUserImage();
        this.titlePicture = item.getPicture();
        this.userName = item.getUserName();
        this.textPicture = item.getPicture();
        this.categoryID = item.getCategoryID();
        this.registerDate = item.getRegisterDate();
        this.viewCount = item.getViewCount();
        this.shareCount = item.getShareCount();
        this.text = statement;
    }


    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public String getStatement() {
        return statement;
    }

    public String getTitletFromJson() {
        try {
            Gson gson = new Gson();

            Statement statementObj = gson.fromJson(statement, Statement.class);
            StringBuilder stringBuilder = new StringBuilder();

            if (statementObj.getTitle() != null) {
                stringBuilder.append(statementObj.getTitle());
            }
            stringBuilder.append(" ");

            if (BuildConfig.FLAVOR_version_name.equals("bourse")){
                //stringBuilder.append(" : ");
            }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
                //stringBuilder.append(" : ");
            }else if (BuildConfig.FLAVOR_version_name.equals("moz")){
                //stringBuilder.append(" : ");
             }else if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
                //stringBuilder.append(" : ");
             }else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")){
                //stringBuilder.append(" : ");
            }else{
                stringBuilder.append("-");
                stringBuilder.append(" مدل: ");
            }
            stringBuilder.append(statementObj.getModel());

            return stringBuilder.toString();
        }catch (Exception ex){
            return statement;
        }
    }

    public String getStatementFromJson() {
        try {
            Gson gson = new Gson();

            Statement statementObj = gson.fromJson(statement, Statement.class);
            StringBuilder stringBuilder = new StringBuilder();

//            if (statementObj.getTitle() != null) {
//                stringBuilder.append(statementObj.getTitle());
//                stringBuilder.append("-");
//            }
//
//            if (BuildConfig.FLAVOR_version_name.equals("bourse")){
//                //stringBuilder.append(" : ");
//            }else {
//                stringBuilder.append(" مدل: ");
//            }

//            stringBuilder.append(statementObj.getModel());
//            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getDescription());

            return stringBuilder.toString();
        }catch (Exception ex){
            return statement;
        }
    }

    public String getDateFromStatement() {
        try {
            Gson gson = new Gson();

            Statement statementObj = gson.fromJson(statement, Statement.class);
            StringBuilder stringBuilder = new StringBuilder();

            if (statementObj.getTitle() != null) {
                stringBuilder.append(statementObj.getTitle());
                stringBuilder.append("-");
            }

            stringBuilder.append(" مدل: ");
            stringBuilder.append(statementObj.getModel());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getDescription());

            return stringBuilder.toString();
        }catch (Exception ex){
            return statement;
        }
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTextPicture() {
        return textPicture;
    }

    public void setTextPicture(String textPicture) {
        this.textPicture = textPicture;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }


    public String getTextFromJson() {
        try {
            Gson gson = new Gson();

            TextContent statementObj = gson.fromJson(text, TextContent.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(statementObj.getTitle());

            if (statementObj.getModel() != null && statementObj.getModel().length() > 0) {
                stringBuilder.append("-");
                stringBuilder.append(" مدل: ");
                stringBuilder.append(statementObj.getModel());
            }

            //todo check in Yafte-Yadak-Tubeless
            //stringBuilder.append("\n");
            //stringBuilder.append(statementObj.getDescription());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getText());

            if (statementObj.getMobile().length() > 5) {
                stringBuilder.append("\n");
                stringBuilder.append(" موبایل: ");
                stringBuilder.append(statementObj.getMobile());
            }

            return stringBuilder.toString();
        }catch (Exception ex){
            return text;
        }
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
        }else if (userName.equals("اپراتور سیستم")){
            return userName;
        }else if (userName.equals("کاربر ناشناس")){
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
//        CategoryViewHolder holder = (CategoryViewHolder) holder0;
//        final MainListItem listItem = (MainListItem)item;
//
//
//        final boolean[] loadedImage = {false};
//        DateConverterSjd dateUtiliti = new DateConverterSjd();
//
//        StringBuilder date = new StringBuilder();
////        date.append("00/00/0000");
//        date.append(" ( ");
//        date.append("تاریخ ثبت : ");
//        date.append(listItem.getRegisterDate());
//        date.append(" ) ");
//        holder.textViewDate.setText(date.toString());
//
//
//        //title
//        //fillTitle(mContext, timelineItem.getTitle(), timelineItem.getCategoryID(), holder.textViewTitle);
//        holder.textViewTitle.setText(listItem.getTitletFromJson());
////        holder.textViewLocation.setText(timelineItem.getStatementFromJson());
////        holder.textViewUserName.setText(timelineItem.getUserNameMasked());
//        holder.textViewCount.setText(listItem.getViewCount() + "");
//
//        holder.imageViewPicture.setVisibility(View.GONE);
//
//        onclicks(mContext,listType , holder, listItem);
//        loadImage(holder, listItem);
//    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    private void onclicks(Context mContext , int listType, CategoryViewHolder holder, MainListItem timelineItem) {


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

        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timelineItem instanceof MainListItem) {

                }

            }
        };

        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof MainListItem) {
//                    if (listType == )
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(mContext, ReadBlogActivity.class);
                    Gson gson = new Gson();
                    String json = gson.toJson(timelineItem);

                    // Old Transfer
//                    intent.putExtra("Object", json);

                    //New Transfer
                    Bundle bundle = new Bundle();
                    bundle.putString("Object", json);
                    bundle.putString("Type", "NewTimelineItem");
                    intent.putExtras(bundle);

                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                }
            }
        };


        holder.imageViewPicture.setOnClickListener(onContentClick);
        holder.textViewDate.setOnClickListener(onContentClick);
//        holder.textViewLocation.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.linearLayoutCenter.setOnClickListener(onContentClick);
//        holder.textViewUserName.setOnClickListener(onclick2);
//        holder.textViewUserName.setOnClickListener(onContentClick);

    }

    private void loadImage(CategoryViewHolder holder, MainListItem timelineItem) {
        //LoadImages.loadAvatarimage(timelineItem.getUserImage(),holder.imageViewUserAvatar);
        LoadImages.loadProfileimage(timelineItem.getTitlePicture(),holder.imageViewPicture);
    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}
