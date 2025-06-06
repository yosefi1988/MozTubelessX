package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

public abstract class ParentItem extends Object implements IItems{
    private int ID;
    View.OnClickListener onDeleteClickListener = null;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


//    public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position) {
//
//        PostViewHolder holder = (PostViewHolder) holder0;
//        final ParentItem timelineItem = (ParentItem) item;
//
////        if (Global.user != null) {
////            if (Global.user.getMobileNumber().contains("09123678522")) {
////
////
////            }else {
////                Toast.makeText(mContext,mContext.getResources().getString(R.string.not_access),Toast.LENGTH_SHORT).show();
////            }
////        }else {
////            Toast.makeText(mContext,mContext.getResources().getString(R.string.not_login),Toast.LENGTH_SHORT).show();
////        }
//
//
//        if (Global.userFixxxxxxxxx == null) {
////            if (holder.linearLayoutAdmin != null) {
////                holder.linearLayoutAdmin.setVisibility(View.GONE);
////                Toast.makeText(mContext,"is not Admin 1 ",Toast.LENGTH_SHORT).show();
////            }/
//        } else {
//            if (Global.user2.isUserAdmin()) {
////                holder.linearLayoutAdmin.setVisibility(View.VISIBLE);
////                Toast.makeText(mContext,"is Admin",Toast.LENGTH_SHORT).show();
//            } else {
////                holder.linearLayoutAdmin.setVisibility(View.GONE);
////                Toast.makeText(mContext,"is not Admin 2",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//        onclicks(mContext,xAdapter, listType, holder, timelineItem,position);
//    }





    public void onclicks(Context mContext, MainAdapter xAdapter, int listType, TubelessMainViewHolder holder, ParentItem timelineItem, int position) {

//        holder.imageViewMenu.setClickable(true);
//        holder.imageViewMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                modal(mContext,view,listType,timelineItem);
//            }
//        });



        View.OnClickListener onShareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(mContext ,listType, timelineItem);
            }
        };

        onDeleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle(mContext.getString(R.string.Delete))
                        .setMessage(mContext.getString(R.string.DeletePostMessage))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(mContext.getString(R.string.yes), new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String userId = "";

                                if(Global.userFixxxxxxxxx != null) {
                                    if (Global.userFixxxxxxxxx.getUserId() == 0) {
                                        userId = Global.userFixxxxxxxxx.getEmail();
                                    } else {
                                        userId = Global.userFixxxxxxxxx.getUserId() + "";
                                    }
                                    delete(mContext, xAdapter, userId, position, listType, timelineItem);
                                }else {
                                    Toast.makeText(mContext,mContext.getString(R.string.userIsNull), Toast.LENGTH_LONG).show();
                                }

                            }})
                        .setNegativeButton(mContext.getString(R.string.cancel), null).show();
            }
        };


        View.OnClickListener onInvisibleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Set Invisible")
                        .setMessage("Do you really want to set invisible?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String userId = "";

                                if (Global.userFixxxxxxxxx.getUserId() == 0){
                                    userId = Global.userFixxxxxxxxx.getEmail();
                                }else {
                                    userId = Global.userFixxxxxxxxx.getUserId() + "";
                                }
                                invisible(mContext, xAdapter,userId , position,listType, timelineItem);

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };



//        holder.textViewDelete.setOnClickListener(onDeleteClickListener);
//        holder.imageViewDelete.setOnClickListener(onDeleteClickListener);
//
//        holder.textViewInvisible.setOnClickListener(onInvisibleClickListener);
//        holder.imageViewInvisible.setOnClickListener(onInvisibleClickListener);
//
//        if (holder.imageViewShare != null) {
//            holder.imageViewShare.setOnClickListener(onShareClickListener);
//            holder.textViewShare.setOnClickListener(onShareClickListener);
//        }
    }


    public void modal(Context mContext, View view, int listType, ParentItem timelineItem){
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        if (Global.user2 != null && Global.user2.isUserAdmin())
            inflater.inflate(R.menu.actions_for_admin, popup.getMenu());
//        else if (Global.user != null && timelineItem.getUserID() == Global.user.getUserId())
//            inflater.inflate(R.menu.actions_for_owner, popup.getMenu());
        else if (Global.userFixxxxxxxxx != null)
            inflater.inflate(R.menu.actions_for_users, popup.getMenu());
        else
            inflater.inflate(R.menu.actions_for_everyone, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.pmnuDelete:
//
//                        onDeleteClickListener.onClick(view);
//
//                        break;
//                    case R.id.pmnuReport:
//
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(ContactUsActivity.Type , ContactUsActivity.CONTACT_US);
////                        bundle.putString(ContactUsActivity.Title , "محتوی نامناسب , کد : " + timelineItem.getBlogID());
//                        bundle.putString(ContactUsActivity.Text , "توضیح : ");
//                        ((Activity)mContext).startActivity(ContactUsActivity.getIntent(mContext,bundle));
//                        break;
//                    case R.id.pmnuShare:
//                        share(mContext ,listType, timelineItem);
//                        break;
                }
                return false;
            }
        });
        popup.show();
    }


    protected abstract void share(Context mContext, int listType, ParentItem timelineItem);
    protected abstract void delete(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem);
    protected abstract void invisible(Context mContext, MainAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem);

}
