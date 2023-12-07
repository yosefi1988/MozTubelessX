package ir.sajjadyosefi.android.xTubeless.classes.model.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.readProfile.ReadProfileActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.user.ElectedUserItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;

public class ElectedUserItem extends UserItem{

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCountPosts() {
        return CountPosts;
    }

    public void setCountPosts(String countPosts) {
        CountPosts = countPosts;
    }

    public String getIDApplication() {
        return IDApplication;
    }

    public void setIDApplication(String IDApplication) {
        this.IDApplication = IDApplication;
    }

    public String getUserCreatedOn() {
        return UserCreatedOn;
    }

    public void setUserCreatedOn(String userCreatedOn) {
        UserCreatedOn = userCreatedOn;
    }

    public boolean isElected() {
        return IsElected;
    }

    public void setElected(boolean elected) {
        IsElected = elected;
    }

    public String getElectedDate() {
        return ElectedDate;
    }

    public void setElectedDate(String electedDate) {
        ElectedDate = electedDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int star) {
        Star = star;
    }

    public boolean isElectedUserIsActive() {
        return ElectedUserIsActive;
    }

    public void setElectedUserIsActive(boolean electedUserIsActive) {
        ElectedUserIsActive = electedUserIsActive;
    }

    public boolean isElectedUserIsDeleted() {
        return ElectedUserIsDeleted;
    }

    public void setElectedUserIsDeleted(boolean electedUserIsDeleted) {
        ElectedUserIsDeleted = electedUserIsDeleted;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }



    private String UserId;
    private String CountPosts;
    private String IDApplication;
    private String UserCreatedOn;
    private boolean IsElected;
    private String ElectedDate;
    private String Title;
    private String Comment;
    private int Star;
    private boolean ElectedUserIsActive;
    private boolean ElectedUserIsDeleted;
    private String StateName;
    private String Location;
    private String Tel;
    private String Address;


    private void onclicks(Context mContext , int listType, ElectedUserItemViewHolder electedUserItemViewHolder, ElectedUserItem electedUserItem) {
        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle2 = new Bundle();
//                bundle2.putInt("type", FRAGMENT_Message);
//                bundle2.putInt("CAT_COUNT", 10);
//                bundle2.putInt("blogId", blogId);
//                bundle2.putBoolean("isOwner", isOwner);
//                bundle2.putString("userId", userItem.getUi());
//                mContext.startActivity(ContainerActivity.getIntent(mContext, bundle2));
                Intent intent = new Intent(mContext, ReadProfileActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(electedUserItem);
                Bundle bundle = new Bundle();
                bundle.putString("Object", json);
                bundle.putString("Type", "electedUserItem");//"TimelineItem");
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        };
        electedUserItemViewHolder.textViewStateName.setOnClickListener(onclick);
        electedUserItemViewHolder.textViewTitle.setOnClickListener(onclick);
        electedUserItemViewHolder.ratingBarStar.setOnClickListener(onclick);
        electedUserItemViewHolder.textViewFamily.setOnClickListener(onclick);
        electedUserItemViewHolder.textViewName.setOnClickListener(onclick);
        electedUserItemViewHolder.rootView.setOnClickListener(onclick);
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

    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder viewHolder, ParentItem parentItem, int position, ListFragment fragment) {
        //super.fill(mContext, xAdapter, listType, viewHolder,item, position);

        ElectedUserItemViewHolder userItemViewHolder = (ElectedUserItemViewHolder) viewHolder;
        final ElectedUserItem electedUserItem = (ElectedUserItem)parentItem;

        LoadImages.loadAvatarimage(electedUserItem.getAvatar() ,userItemViewHolder.imageViewUserAvatar);

        userItemViewHolder.textViewStateName.setText(electedUserItem.getStateName());
        userItemViewHolder.textViewTitle.setText(electedUserItem.getTitle());

        userItemViewHolder.ratingBarStar.setRating((float) electedUserItem.getStar());
        userItemViewHolder.ratingBarStar.setProgress(electedUserItem.getStar());

        userItemViewHolder.textViewFamily.setText(electedUserItem.getFamily());
        userItemViewHolder.textViewName.setText(electedUserItem.getName());

        onclicks(mContext,listType , userItemViewHolder, electedUserItem);
    }

}
