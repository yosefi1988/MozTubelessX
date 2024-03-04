package ir.sajjadyosefi.android.xTubeless.classes.model.post;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogImageActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TextPictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;

import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.LOGIN_REQUEST;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.isFreeStore;


/**
 * Created by sajjad on 7/30/2017.
 */

public class TextItem extends MainItem {
    List<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts> Amounts = new ArrayList<Amounts>();
    List<String> Images = new ArrayList<String>();

    private int CreatorID;
    private int ApplicationID;
    private int StateCode;
    private int CityCode;
    private int ViewCount;
    private String Text;
    private String StateName;
    private String CityName;
    private String PublishDate;
    private String DateTimeExpire;
    private String AcceptDate;
    private String ModifiedDate;
    private boolean IsActive;
    private boolean IsDeleted;
    private boolean ReciveMessage;

    private int Amount;
    private double Zarib;
    private String CreatorFullName;
    private String RefrenceNo;
    private String image;
    private String icon;
    private String title;
    private String TTN;
    private String DateTime;
    private boolean isFav;
    private boolean isSeen;
    private String TitlePicture;
    private String TextPicture;

    public TextItem(int i, String the_flight, String scott_masterson, String s) {
        super();
    }

    public String getCreatorFullName() {
        return CreatorFullName;
    }

    public void setCreatorFullName(String creatorFullName) {
        CreatorFullName = creatorFullName;
    }

    public String getTTN() {
        return TTN;
    }

    public void setTTN(String TTN) {
        this.TTN = TTN;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRefrenceNo() {
        return RefrenceNo;
    }

    public void setRefrenceNo(String refrenceNo) {
        RefrenceNo = refrenceNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public double getZarib() {
        return Zarib;
    }

    public void setZarib(double zarib) {
        Zarib = zarib;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
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

    public int getCreatorID() {
        return CreatorID;
    }

    public void setCreatorID(int creatorID) {
        CreatorID = creatorID;
    }

    public int getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(int applicationID) {
        ApplicationID = applicationID;
    }

    public int getStateCode() {
        return StateCode;
    }

    public void setStateCode(int stateCode) {
        StateCode = stateCode;
    }

    public int getCityCode() {
        return CityCode;
    }

    public void setCityCode(int cityCode) {
        CityCode = cityCode;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getAcceptDate() {
        return AcceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        AcceptDate = acceptDate;
    }

    public String getTextPicture() {
        return TextPicture;
    }

    public void setTextPicture(String textPicture) {
        TextPicture = textPicture;
    }

    public String getTitlePicture() {
        return TitlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        TitlePicture = titlePicture;
    }

    public String getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
    //
    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public boolean isReciveMessage() {
        return ReciveMessage;
    }

    public void setReciveMessage(boolean reciveMessage) {
        ReciveMessage = reciveMessage;
    }

    public String getDateTimeExpire() {
        return DateTimeExpire;
    }

    public void setDateTimeExpire(String dateTimeExpire) {
        DateTimeExpire = dateTimeExpire;
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



    private void onclicks(Context mContext , int listType, TextPictureItemViewHolder holder, MainItem timelineItem) {
        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof MainItem) {
//                    if (listType == )
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();

                    if (Global.user2 == null) {
                        Toast.makeText(mContext,mContext.getResources().getString(R.string.NotLoggedIn3),Toast.LENGTH_LONG).show();



                        Bundle bundle = new Bundle();
                        bundle.putInt("type" , 1);
                        Intent intent = SignInActivity.getIntent(mContext,bundle);
                        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
                        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                        ((Activity)mContext).startActivityForResult(intent, LOGIN_REQUEST);
                    } else {
                        if (isFreeStore(mContext, StaticValue.configuration)) {
                            //free
                            Intent intent ;
                            if (timelineItem.getTTC() == 5047)
                                intent = new Intent(mContext, ReadBlogImageActivity.class);
                            else
                                intent = new Intent(mContext, ReadBlogActivity.class);


                            Gson gson = new Gson();
                            String json = gson.toJson(timelineItem);

                            // Old Transfer
                            //intent.putExtra("Object", json);

                            //New Transfer
                            Bundle bundle = new Bundle();
                            bundle.putString("Object", json);
                            bundle.putString("Type", "NewTimelineItem");
                            intent.putExtras(bundle);

                            mContext.startActivity(intent);
                            ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                        }else {
//                            if ((timelineItem.getAmount() >= Global.user2.getWallet().getAmount()) && !isSeen()) {
//
//                                Toast.makeText(mContext,"موجودی کیف پول شما کافی نمی باشد",Toast.LENGTH_SHORT).show();
//                            } else {
                            Intent intent = new Intent(mContext, ReadBlogActivity.class);
                            Gson gson = new Gson();
                            String json = gson.toJson(timelineItem);

                            // Old Transfer
                            //intent.putExtra("Object", json);

                            //New Transfer
                            Bundle bundle = new Bundle();
                            bundle.putString("Object", json);
                            bundle.putString("Type", "NewTimelineItem");
                            intent.putExtras(bundle);

                            mContext.startActivity(intent);
                            ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
//                            }
                        }
                    }
                }
            }
        };
        holder.textViewDate.setOnClickListener(onContentClick);
        holder.textViewLocation.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.linearLayoutCenter.setOnClickListener(onContentClick);
    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {
        //super.fill(mContext, xAdapter, listType,holder0,item, position);

        TextPictureItemViewHolder holder = (TextPictureItemViewHolder) holder0;
        final TextItem timelineItem = (TextItem) item;


        if (timelineItem.getTTC() == 5048) {
            holder.textViewDate.setText("تاریخ گم شدن " + timelineItem.getDateTimeExpire() + " (تاریخ ثبت : " + timelineItem.getDateTime() + ")");
        } else if (timelineItem.getTTC() == 5050) {
            holder.textViewDate.setText("تاریخ سرقت " + timelineItem.getDateTimeExpire() + " (تاریخ ثبت : " + timelineItem.getDateTime() + ")");
        } else if (timelineItem.getTTC() == 5051) {
            holder.textViewDate.setText("تاریخ پیدا شدن " + timelineItem.getDateTimeExpire() + " (تاریخ ثبت : " + timelineItem.getDateTime() + ")");
        } else if (timelineItem.getTTC() == 8067 || timelineItem.getTTC() == 8068 || timelineItem.getTTC() == 8069) {
            holder.textViewDate.setText("تاریخ ثبت : " + timelineItem.getDateTime() + "");
        } else {
            if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
                holder.textViewDate.setText("تاریخ ثبت : " + timelineItem.getDateTime());
            }else {
                holder.textViewDate.setText("فرصت تا " + timelineItem.getDateTimeExpire() + " (تاریخ ثبت : " + timelineItem.getDateTime() + ")");
            }
        }

        if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
            holder.textViewLocation.setText("قرعه کشی در " + timelineItem.getTTN());
        }else {
            holder.textViewLocation.setText(timelineItem.getTTN() + (timelineItem.getStateName() == null ? "" : " استان " + timelineItem.getStateName()));
        }
//        holder.textViewLocation.setText(timelineItem.getTTN() + " استان " + timelineItem.getStateName() + "(" + timelineItem.getCityName() + ")");
        holder.textViewTitle.setText(timelineItem.getTitle());

        if (timelineItem.getTitlePicture() == null) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);

            //1
            final int radius = 10;
            final int margin = 3;
            final Transformation transformation = new RoundedCornersTransformation(radius, margin);
            //2
            //.transform(new CircleTransform())

            if (timelineItem.getTitlePicture() != null)
                Picasso.get()
    //                    .load(timelineItem.getTitlePicture().replace("\\Blog\\","\\Blog\\Thumbnail\\"))
                        .load(timelineItem.getTitlePicture())
                        .transform(transformation)
                        //.noPlaceholder()
                        .placeholder(R.drawable.png_image)
                        .error(R.drawable.png_image)
                        .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
                        .centerCrop()
                        .into(holder.imageView);
        }
//
        if (timelineItem.isSeen())
            holder.textViewpay.setVisibility(View.VISIBLE);
        else
            holder.textViewpay.setVisibility(View.GONE);

        if (timelineItem.isFav())
            holder.textViewfav.setVisibility(View.VISIBLE);
        else
            holder.textViewfav.setVisibility(View.GONE);


        if (timelineItem.isReciveMessage()) {
            holder.imageviewMessage.setVisibility(View.VISIBLE);
        } else {
            holder.imageviewMessage.setVisibility(View.GONE);
        }

        onclicks(mContext, listType, holder, timelineItem);
        loadImage(holder, timelineItem);


    }
}


