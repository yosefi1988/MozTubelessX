package ir.sajjadyosefi.android.xTubeless.classes.model.category;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CategoryViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_CATEGORY;

public class CategoryItem extends ParentItem {
    private int HID;
    private Long CID;
    private String Title;
    private String Statment;
    private String Value;
    private String SelectableS;
    private Boolean Selectable;
    private Boolean Selected;
    private String Image;
    private String Icon;

    private String pathTitle;

    public String getPathTitle() {
        return pathTitle;
    }
    public void setPathTitle(String pathTitle) {
        this.pathTitle = pathTitle;
    }

    public int getHID() {
        return HID;
    }

    public void setHID(int HID) {
        this.HID = HID;
    }

    public Long getCID() {
        return CID;
    }

    public void setCID(Long CID) {
        this.CID = CID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStatment() {
        return Statment;
    }

    public void setStatment(String statment) {
        Statment = statment;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getSelectableS() {
        return SelectableS;
    }

    public void setSelectableS(String selectableS) {
        SelectableS = selectableS;
    }

    public Boolean getSelectable() {
        return Selectable;
    }

    public void setSelectable(Boolean selectable) {
        Selectable = selectable;
    }


    public Boolean getSelected() {
        if (Selected == null)
            return false;
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment xfragment) {
        // public void fill(Context mContext, MainAdapter xAdapter, int listType, PostViewHolder holder0 , ParentItem item, int position, ListFragment fragment) {

        CategoryViewHolder holder = (CategoryViewHolder) holder0;
        final CategoryItem categoryItem = (CategoryItem)item;

        holder.textViewTitle.setText(" " + categoryItem.getTitle());
        holder.textViewDescription.setVisibility(View.GONE);
        holder.buttonDelete.setVisibility(View.GONE);
        holder.buttonShow.setVisibility(View.GONE);
        //fragment = xfragment;
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

//    public void bind(Context mContext,
//                     EndlessList_AdapterCategory.CategoryViewHolder holder,
//                     List<Category> categoryList,
//                     int position,
//                     EndlessList_AdapterCategory adapter,
//                     boolean deletable) {
//
//        Category category = (Category) categoryList.get(position);
//
//        StringBuilder text = new StringBuilder();
//        text.append(category.getName());
//
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        holder.textViewTitle.setText(text.toString());
//        holder.textViewDescription.setText(category.getStatement());
//
//        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        holder.textViewDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//        if (deletable){
//            holder.buttonDelete.setEnabled(deletable);
//            holder.buttonDelete.setVisibility(View.VISIBLE);
//        }else {
//            holder.buttonDelete.setEnabled(deletable);
//            holder.buttonDelete.setVisibility(View.GONE);
//        }
//
//        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                categoryList.remove(position);
//
//
//                ((CategoryListActivity)mContext).deleteDone();
//                ((CategoryListActivity)mContext).refreshButtons();
//            }
//        });
//
//
//        Picasso.get()
//                .load(category.getImage())
//                //.transform(transformation)
//                .into(holder.imageView);
//    }
}
