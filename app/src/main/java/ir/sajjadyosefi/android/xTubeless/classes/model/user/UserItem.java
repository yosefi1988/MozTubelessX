package ir.sajjadyosefi.android.xTubeless.classes.model.user;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;


import ir.sajjadyosefi.android.xTubeless.classes.model.post.MainItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.user.ElectedUserItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_Message;


/**
 * Created by sajjad on 7/30/2017.
 */

public class UserItem extends MainItem {

    private String Name;
    private String Family;
    private String Avatar;
    private String ProfileImage;
    private String Mobile;
    private String Email;
    private boolean MobileNumberConfirmed;
    private String UserCode;
    private String CodeMelli;
    private String UserName;
    private String SimcardID;
    private int UserTypeCode;
    private boolean UserIsActive;
    private boolean UserIsDeleted;
    private String UserCreateDate;


    public String getName() {
        if (Name == null){
            return "";
        }else {
            return Name;
        }
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFamily() {
        if (Family == null){
            return "";
        }else {
            return Family;
        }
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        if (Email == null){
            return "نامشخص";
        }else {
            return Email;
        }
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isMobileNumberConfirmed() {
        return MobileNumberConfirmed;
    }

    public void setMobileNumberConfirmed(boolean mobileNumberConfirmed) {
        MobileNumberConfirmed = mobileNumberConfirmed;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getCodeMelli() {
        return CodeMelli;
    }

    public void setCodeMelli(String codeMelli) {
        CodeMelli = codeMelli;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSimcardID() {
        return SimcardID;
    }

    public void setSimcardID(String simcardID) {
        SimcardID = simcardID;
    }

    public int getUserTypeCode() {
        return UserTypeCode;
    }

    public void setUserTypeCode(int userTypeCode) {
        UserTypeCode = userTypeCode;
    }

    public boolean isUserIsActive() {
        return UserIsActive;
    }

    public void setUserIsActive(boolean userIsActive) {
        UserIsActive = userIsActive;
    }

    public boolean isUserIsDeleted() {
        return UserIsDeleted;
    }

    public void setUserIsDeleted(boolean userIsDeleted) {
        UserIsDeleted = userIsDeleted;
    }

    public String getUserCreateDate() {
        return UserCreateDate;
    }

    public void setUserCreateDate(String userCreateDate) {
        UserCreateDate = userCreateDate;
    }

    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {
        //super.fill(mContext, xAdapter, listType, viewHolder,item, position);

        ElectedUserItemViewHolder userItemViewHolder = (ElectedUserItemViewHolder) holder0;
        final ElectedUserItem electedUserItem = (ElectedUserItem)item;

        LoadImages.loadAvatarimage(electedUserItem.getAvatar() ,userItemViewHolder.imageViewUserAvatar);

        userItemViewHolder.textViewStateName.setText(electedUserItem.getStateName());
        userItemViewHolder.textViewTitle.setText(electedUserItem.getTitle());

        userItemViewHolder.ratingBarStar.setRating((float) electedUserItem.getStar());
        userItemViewHolder.ratingBarStar.setProgress(electedUserItem.getStar());

        userItemViewHolder.textViewFamily.setText(electedUserItem.getFamily());
        userItemViewHolder.textViewName.setText(electedUserItem.getName());
    }

//    public void fill(Context mContext,
//                              RecyclerView.Adapter<RecyclerView.ViewHolder> xAdapter,
//                              int listType, TubelessMainViewHolder viewHolder, ParentItem parentItem, int position, ListFragment fragment);
}


