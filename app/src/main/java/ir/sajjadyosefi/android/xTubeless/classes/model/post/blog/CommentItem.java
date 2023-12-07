package ir.sajjadyosefi.android.xTubeless.classes.model.post.blog;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;

 
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.CommentContent;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CommentItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;


/**
 * Created by sajjad on 7/30/2017.
 */

public class CommentItem extends ParentItem {


    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getConversationId() {
        return ConversationId;
    }

    public void setConversationId(int conversationId) {
        ConversationId = conversationId;
    }

    public boolean isSeen() {
        return IsSeen;
    }

    public void setSeen(boolean seen) {
        IsSeen = seen;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
    private String CreateDate;
    private int ConversationId;
    private boolean IsSeen;
    private String Message;


//    @Override
//    public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//        super.fill(mContext, xAdapter, listType, holder0,item, position);
//
//        CommentItemViewHolder holder = (CommentItemViewHolder) holder0;
//        final CommentItem CommentItem = (CommentItem)item;
//
//        final boolean[] loadedImage = {false};
//        DateConverterSjd dateUtiliti = new DateConverterSjd();
//
//        StringBuilder date = new StringBuilder();
//        date.append(CommentItem.getCreateDate());
//        date.append(" ( ");
////        date.append("تاریخ ثبت : ");
//        date.append(CommentItem.getCreateDate());
//        date.append(" ) ");
//        holder.textViewDate.setText(date.toString());
//
//
////        holder.textViewText.setText(CommentItem.getText());
//        holder.textViewText.setText(CommentItem.getStatementFromJson());
//        holder.textViewUserName.setText("کاربر" );
////        holder.textViewCount.setText(CommentItem.getViewCount() + "");
//
//
////        holder.imageviewPicture.setVisibility(View.GONE);
//
//
//
//        onclicks(mContext,listType , holder, CommentItem);
//        LoadImages.loadAvatarimage(null,holder.imageViewUserAvatar);
////        LoadImages.loadProfileimage(CommentItem.getPicture(),holder.imageviewPicture);
//
//    }


    public String getStatementFromJson() {
        try {
            Gson gson = new Gson();

            CommentContent statementObj = gson.fromJson(getMessage(), CommentContent.class);
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
            stringBuilder.append(statementObj.getText());

            return stringBuilder.toString();
        }catch (Exception ex){
            return Message;
        }
    }

    private void onclicks(Context mContext , int listType, CommentItemViewHolder holder, CommentItem timelineItem) {

        View.OnClickListener onReportClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CONTENT_REPORT);
                bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                        mContext.getString(R.string.reportContent),
                        ((CommentItem) timelineItem).getStatementFromJson()));
//                bundle.putString(ContactUsActivity.Phone, String.format("CID=%s-CUID=%s-UID=%s-UN=%s",
//                        ((CommentItem) timelineItem).getID(),
//                        ((CommentItem) timelineItem).getConversationId(),
//                        ((Global.user2 == null ? "X" : Global.userFixxxxxxxxx.getUserId())),
//                        (Global.user2 == null ? "X" :
//                                (Global.user2.getUserCode() == null ? "" : Global.userFixxxxxxxxx.getName()) +
//                                " " +
//                                (Global.userFixxxxxxxxx.getFamily() == null ? "" : Global.userFixxxxxxxxx.getFamily()))));
                ((Activity)mContext).startActivity(ContactUsActivity.getIntent(mContext, bundle));
            }
        };
        holder.imageViewReport.setOnClickListener(onReportClickListener);
        holder.textViewReport.setOnClickListener(onReportClickListener);

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


        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timelineItem instanceof CommentItem) {
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext,"," , Toast.LENGTH_SHORT).show();

//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type" , TYPE_COMMENTS);
//                    bundle.putInt("blogId" , timelineItem.getBlogID());
//                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
//                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
                }

            }
        };

        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof CommentItem) {
//

                }
            }
        };


//        holder.imageviewPicture.setOnClickListener(onclick);
        holder.textViewDate.setOnClickListener(onclick);
//        holder.textViewLocation.setOnClickListener(onclick);
        holder.textViewTitle.setOnClickListener(onclick);
        holder.linearLayoutCenter.setOnClickListener(onclick);
        holder.textViewUserName.setOnClickListener(onclick);

    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {
        Global.apiManagerTubeless.deleteBlogComment(((CommentItem)timelineItem).getID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                ((TubelessActivity)mContext).progressDialog.show();

            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity)mContext).progressDialog.hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                xAdapter.removeItem(listType,position);
            }
        });
    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {
        Global.apiManagerTubeless.invisibleBlogComment(((CommentItem)timelineItem).getID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                ((TubelessActivity)mContext).progressDialog.show();

            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity)mContext).progressDialog.hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                xAdapter.removeItem(listType,position);
            }
        });
    }


    private int blogId = 0;
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    private boolean isOwner = false;
    public void isOwner(boolean _isOwner) {
        isOwner = _isOwner;
    }

    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}


