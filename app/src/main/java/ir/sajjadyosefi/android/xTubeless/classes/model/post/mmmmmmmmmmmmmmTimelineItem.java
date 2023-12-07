package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_Message2;

/**
 * Created by sajjad on 1/20/2018.
 */

public class mmmmmmmmmmmmmmTimelineItem extends ParentItem {

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPersianExpireDate() {
        return PersianExpireDate;
    }

    public void setPersianExpireDate(String persianExpireDate) {
        PersianExpireDate = persianExpireDate;
    }

    public boolean isFavorit() {
        return IsFavorit;
    }

    public void setFavorit(boolean favorit) {
        IsFavorit = favorit;
    }

    private String Text;
    private String Title;
    private String PersianExpireDate;
    private boolean IsFavorit;


    public mmmmmmmmmmmmmmTimelineItem(TimelineItem item) {

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

//    public void fill(Context mContext,
//                     MainAdapter xAdapter,
//                     int listType,
//                     PostViewHolder holder0,
//                     IItems item,
//                     int position) {
//
//        super.fill(mContext, xAdapter, listType,holder0,item, position);
//
//        mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder holder = (mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder) holder0;
//        final mmmmmmmmmmmmmmTimelineItem timelineItem = (mmmmmmmmmmmmmmTimelineItem)item;
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        stringBuilder.append("PersianExpireDate:");
//        stringBuilder.append(timelineItem.getPersianExpireDate());
//        stringBuilder.append("\n");
//
//
//        stringBuilder.append("Text:");
//        stringBuilder.append(timelineItem.getText());
//        stringBuilder.append("\n");
//
//        stringBuilder.append("Title:");
//        stringBuilder.append(timelineItem.getTitle());
//        stringBuilder.append("\n");
//
//        stringBuilder.append("ID:");
//        stringBuilder.append(timelineItem.getID());
//        stringBuilder.append("\n");
//
//        holder.textViewTitle.setText(timelineItem.getTitle());
//        holder.textViewText.setText(timelineItem.getText());
//        holder.textViewDate.setText("اعتبار آگهی تا : " + timelineItem.getPersianExpireDate());
//
//        if (timelineItem.isFavorit())
//            holder.imageViewfav.setVisibility(View.VISIBLE);
//        else
//            holder.imageViewfav.setVisibility(View.GONE);
//
//        holder.imageViewpay.setVisibility(View.GONE);
//        holder.imageviewMessage.setVisibility(View.GONE);
//
//        onclicks(mContext,listType , holder, timelineItem);
//        loadImage(holder, timelineItem);
//
//    }

    private void onclicks(Context mContext , int listType, mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder holder, mmmmmmmmmmmmmmTimelineItem timelineItem) {
        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("type", FRAGMENT_Message2);
                bundle2.putInt("CAT_COUNT", 10);
                bundle2.putInt("blogId", timelineItem.getID());
                mContext.startActivity(ContainerActivity.getIntent(mContext, bundle2));
            }
        };


        holder.textViewDate.setOnClickListener(onContentClick);
        holder.textViewText.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.imageViewfav.setOnClickListener(onContentClick);
    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem0) {
//        StringBuilder stringBuilder0 = new StringBuilder();
//        NewTimelineItem timelineItem = (NewTimelineItem) timelineItem0;
//
//        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//
//
//        stringBuilder0.append(timelineItem.getTitle());
//        stringBuilder0.append("-");
//        stringBuilder0.append("توضیحات:");
//        stringBuilder0.append(((NewTimelineItem) timelineItem0).getStatementFromJson());
//
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//        stringBuilder0.append(" ثبت شده در اپلیکیشن در تاریخ ");
//        stringBuilder0.append(timelineItem.getRegisterDate());
//
//        stringBuilder0.append("\n");
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
//        intent.setType("text/plain");
//        ((Activity)mContext).startActivityForResult(intent , 60);
    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {
//        Global.apiManagerTubeless.deleteTimelineItem(timelineItem.getBlogID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
//            @Override
//            public void t_beforeSendRequest() {
//                ((TubelessActivity)mContext).progressDialog.show();
//
//            }
//
//            @Override
//            public void t_afterGetResponse() {
//                ((TubelessActivity)mContext).progressDialog.hide();
//            }
//
//            @Override
//            public void t_complite() {
//            }
//
//            @Override
//            public void t_responseNull() {
//                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
//            }
//
//            @Override
//            public void t_retry(Call<Object> call) {
//                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
//            }
//
//            @Override
//            public void t_onSuccess(Object response) {
//                xAdapter.removeItem(listType,position);
//            }
//        });
    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {
//        Global.apiManagerTubeless.invisibleTimelineItem(timelineItem.getBlogID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
//            @Override
//            public void t_beforeSendRequest() {
//                ((TubelessActivity)mContext).progressDialog.show();
//
//            }
//
//            @Override
//            public void t_afterGetResponse() {
//                ((TubelessActivity)mContext).progressDialog.hide();
//            }
//
//            @Override
//            public void t_complite() {
//            }
//
//            @Override
//            public void t_responseNull() {
//                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
//            }
//
//            @Override
//            public void t_retry(Call<Object> call) {
//                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
//            }
//
//            @Override
//            public void t_onSuccess(Object response) {
//                xAdapter.removeItem(listType,position);
//            }
//        });
    }

    private void loadImage(mmmmmmmmmmmmmmmmmmmTimelineItemViewHolder holder, mmmmmmmmmmmmmmTimelineItem timelineItem) {
//        LoadImages.loadAvatarimage(timelineItem.getUserImage(),holder.imageViewUserAvatar);
//        LoadImages.loadProfileimage(timelineItem.getTitlePicture(),holder.imageviewPicture);
    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}
