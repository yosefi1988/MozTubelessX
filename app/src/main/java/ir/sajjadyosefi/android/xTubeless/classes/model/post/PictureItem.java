package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.activity.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewImageActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.FavRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PictureItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;
import ir.sajjadyosefi.android.xTubeless.utility.file.FileUtils;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment.context;
import static ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment.showUserRegPostDialog;
import static ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity.DO_ACCEPT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity.DO_INVISIBLE;
import static ir.sajjadyosefi.android.xTubeless.activity.register.PrePaymentActivity.GO_TO_LOGIN;

/**
 * Created by sajjad on 7/30/2017.
 */

public class PictureItem extends MainItem {

    List<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts> Amounts = new ArrayList<ir.sajjadyosefi.android.xTubeless.classes.model.Amounts>();
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

    private void onclicks(Context mContext , int listType, PictureItemViewHolder holder, PictureItem timelineItem) {
        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");
                wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //        hhhhhh.title.setText(wordtoSpan);
//                hhhhhh.title.setText(sssss.CarName + sssss.CarName);
//
//                hhhhhh.textViewCarName.setText(sssss.CarName);
//                hhhhhh.textViewCarName.setText(sssss.CarName);
//                holder.imageViewMenu.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, holder.imageViewMenu);

                if (Global.user2 != null && (Global.user2.isUserAdmin() || Global.user2.isUserCreator())) {
                    popup.getMenuInflater().inflate(R.menu.menupic_admin, popup.getMenu());
                }else {
                    popup.getMenuInflater().inflate(R.menu.menupic, popup.getMenu());
                }

                for (int i = 0; i < popup.getMenu().size(); i++) {
                    MenuItem item = popup.getMenu().getItem(i);
                    SpannableString spanString = new SpannableString(popup.getMenu().getItem(i).getTitle().toString());
                    spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0);
                    item.setTitle(spanString);
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.action_regnewPost){
                            if (Global.user2 == null){
                                Toast.makeText(mContext, "ابتدا وارد شوید", Toast.LENGTH_LONG).show();
                                Bundle bundle = null;
                                if (bundle == null){
                                    bundle = new Bundle();
                                }
                                Intent intent = SignInActivity.getIntent(mContext,bundle);
                                bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                                ((Activity)mContext).startActivityForResult(intent, GO_TO_LOGIN);
                            }else {
                                if (Global.user2 != null && (Global.user2.isUserAdmin() || Global.user2.isUserCreator()) || BuildConfig.FLAVOR_version_name.equals("yafte")|| BuildConfig.FLAVOR_version_name.equals("yadak")) {
                                    mContext.startActivity(new Intent(mContext, RegNewImageActivity.class));
                                } else {
                                    showUserRegPostDialog(mContext, ((Activity)mContext).findViewById(android.R.id.content));
                                }
                            }
                        }
                        if (item.getItemId() == R.id.action_accept){
                            FavRequest aaaa = new FavRequest();
                            aaaa.setUserCode(Global.user2.getUserCodeAsString());
                            aaaa.setIDPost(timelineItem.getID() + "");
                            actionOnPost(aaaa,DO_ACCEPT);
                        }
                        if (item.getItemId() == R.id.action_not_accept){
                            FavRequest aaaa = new FavRequest();
                            aaaa.setUserCode(Global.user2.getUserCodeAsString());
                            aaaa.setIDPost(timelineItem.getID() + "");
                            actionOnPost(aaaa,DO_INVISIBLE);
                        }

                        if (item.getItemId() == R.id.action_settings){
                            Bundle bundle = new Bundle();
                            bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CONTENT_REPORT);
                            bundle.putString(ContactUsActivity.ID, timelineItem.getID() + "");
                            bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                                    mContext.getString(R.string.reportContent),
                                    timelineItem.getTitle()));
                            bundle.putString(ContactUsActivity.Phone,  String.format("BID=%s-BUID=%s-UID=%s",
                                    timelineItem.getID(),
                                    timelineItem.getCreatorID(),
                                    ((Global.user2 == null ? "X":Global.user2.getUserCode()))));
                            ((Activity)mContext).startActivity(ContactUsActivity.getIntent(mContext, bundle));
                        }

//                                Toast.makeText(mContext, "You Selected : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
                //                popup.show();//showing popup menu
//                    }
//                });


//                if (timelineItem instanceof MainItem) {
//                    if (Global.user2 == null) {
//                        Toast.makeText(mContext,"ابتدا وارد حساب کاربری خود شوید.",Toast.LENGTH_SHORT).show();
//                    } else {
//                        if (isFreeStore(mContext, StaticValue.configuration)) {
//                            //free
//                            Intent intent = new Intent(mContext, ReadBlogActivity.class);
//                            Gson gson = new Gson();
//                            String json = gson.toJson(timelineItem);
//
//                            // Old Transfer
//                            //intent.putExtra("Object", json);
//
//                            //New Transfer
//                            Bundle bundle = new Bundle();
//                            bundle.putString("Object", json);
//                            bundle.putString("Type", "NewTimelineItem");
//                            intent.putExtras(bundle);
//
//                            mContext.startActivity(intent);
//                            ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
//                        }else {
////                            if ((timelineItem.getAmount() >= Global.user2.getWallet().getAmount()) && !isSeen()) {
////
////                                Toast.makeText(mContext,"موجودی کیف پول شما کافی نمی باشد",Toast.LENGTH_SHORT).show();
////                            } else {
//                            Intent intent = new Intent(mContext, ReadBlogActivity.class);
//                            Gson gson = new Gson();
//                            String json = gson.toJson(timelineItem);
//
//                            // Old Transfer
//                            //intent.putExtra("Object", json);
//
//                            //New Transfer
//                            Bundle bundle = new Bundle();
//                            bundle.putString("Object", json);
//                            bundle.putString("Type", "NewTimelineItem");
//                            intent.putExtras(bundle);
//
//                            mContext.startActivity(intent);
//                            ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
////                            }
//                        }
//                    }
//                }
            }
        };
        View.OnClickListener onImageClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileUtils.openFile((Activity)(context),timelineItem.getTitlePicture().replace("\\","/"),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        holder.imageViewMenu.setOnClickListener(onContentClick);
        holder.imageView.setOnClickListener(onImageClick);
    }

    private void actionOnPost(FavRequest favRequest , int type) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(context, ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
            }

            @Override
            public void t_afterGetResponse() {
            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {
                TubelessException.ShowSheetDialogMessage(context, dialog, context.getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() == 200) {
                    Toast.makeText(context,"انجام شد" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"به مشکل خوردیم" ,Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (type == DO_ACCEPT) {
            Global.apiManagerTubeless.acceptPost(favRequest, ssssssss);
        }
        if (type == DO_INVISIBLE) {
            Global.apiManagerTubeless.invisiblePost(favRequest, ssssssss);
        }
//        if (type == DO_FAV) {
//            Global.apiManagerTubeless.favoritPost(favRequest, ssssssss);
//        }
    }

    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {
        PictureItemViewHolder holder = (PictureItemViewHolder) holder0;
        final PictureItem timelineItem = (PictureItem) item;
        //1
        final int radius = 10;
        final int margin = 3;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        //2
        //.transform(new CircleTransform())

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

        onclicks(mContext, listType, holder, timelineItem);
        loadImage(holder, timelineItem);
    }
}


