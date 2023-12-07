package ir.sajjadyosefi.android.xTubeless.classes.model.post.blog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
 
import ir.sajjadyosefi.android.xTubeless.classes.model.user.UserItem;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_Message;


/**
 * Created by sajjad on 7/30/2017.
 */

public class UserMessageItem extends UserItem {

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

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }


    public int getPostId() {
        return PostId;
    }

    public void setPostId(int postId) {
        PostId = postId;
    }

    private String Text;
    private int PostId;
    private String Title;
    private Boolean IsActive;
    private String ExpireDate;
    private String CreatedOn;

//    @Override
//    public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//
//        mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder holder = (mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder) holder0;
//        final UserMessageItem userItem = (UserMessageItem)item;
//
//        holder.textViewUserName.setText("کاربر" + userItem.getFullName() + "\n" + userItem.getUserName());
//        holder.textViewText.setText(userItem.getText());
//        holder.textViewTitle.setText(userItem.getTitle());
//        holder.textViewDate.setText(userItem.getExpireDate());
//
//        onclicks(mContext,listType , holder, userItem);
//        LoadImages.loadAvatarimage(((UserMessageItem) item).getAvatarUrl(),holder.imageViewUserAvatar);
////        LoadImages.loadProfileimage(((UserItem) item).getAvatarUrl(),holder.imageViewUserAvatar);
//    }

    private void onclicks(Context mContext , int listType, mmmmmmmmmmmmmmmmmmm2TimelineItemViewHolder holder, UserMessageItem userItem) {
        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userItem instanceof UserMessageItem) {

                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("type", FRAGMENT_Message);
                    bundle2.putInt("CAT_COUNT", 10);
                    bundle2.putInt("blogId", userItem.getPostId());
                    bundle2.putBoolean("isOwner", true);
                    bundle2.putBoolean("isCreator", true);
//                    bundle2.putString("userId", userItem.getUi());
                    mContext.startActivity(ContainerActivity.getIntent(mContext, bundle2));
                }

            }
        };
        holder.textViewUserName.setOnClickListener(onclick);
        holder.textViewDate.setOnClickListener(onclick);
        holder.textViewTitle.setOnClickListener(onclick);
        holder.textViewText.setOnClickListener(onclick);
        holder.imageViewUserAvatar.setOnClickListener(onclick);
    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

}


