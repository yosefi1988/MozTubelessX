package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.lottery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.LotteryAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.readProfile.ReadProfileActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.LotteryItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LotterySearchResponseItem extends ParentItem
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("showing_description")
    @Expose
    private String showingDescription;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("is_popular")
    @Expose
    private Integer isPopular;
    @SerializedName("cooperation_type")
    @Expose
    private Integer cooperationType;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
//    @SerializedName("meta")
//    @Expose
//    private Meta meta;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("subcategory_id")
    @Expose
    private Integer subcategoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowingDescription() {
        return showingDescription;
    }

    public void setShowingDescription(String showingDescription) {
        this.showingDescription = showingDescription;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(Integer isPopular) {
        this.isPopular = isPopular;
    }

    public Integer getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Integer cooperationType) {
        this.cooperationType = cooperationType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

//    public Meta getMeta() {
//        return meta;
//    }
//
//    public void setMeta(Meta meta) {
//        this.meta = meta;
//    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder viewHolder, ParentItem parentItem, int position, ListFragment fragment) {
        //super.fill(mContext, xAdapter, listType, viewHolder,item, position);

        LotteryItemViewHolder holder = (LotteryItemViewHolder) viewHolder;
        final LotterySearchResponseItem lotteryItem = (LotterySearchResponseItem)parentItem;

        LoadImages.loadAvatarimage(lotteryItem.getLogo() ,holder.imageViewLogo);
        holder.textViewName.setText(lotteryItem.getName());
        holder.textViewHref.setText(lotteryItem.getHref());
        holder.textViewWebsite.setText(lotteryItem.getWebsite());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lotteryItem.getDescription().substring(0, Math.min(lotteryItem.getDescription().length(), 150)));
        stringBuilder.append("\n");
        stringBuilder.append(" مشاهده متن...");
        holder.textViewDescription.setText(stringBuilder.toString());
        onclicks(mContext,listType , holder, lotteryItem,null);
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

    private void onclicks(Context mContext, int listType, LotteryItemViewHolder electedUserItemViewHolder, LotterySearchResponseItem item, View rootView) {
        View.OnClickListener onShareClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1
//                Bundle bundle2 = new Bundle();
//                bundle2.putInt("type", FRAGMENT_Message);
//                bundle2.putInt("CAT_COUNT", 10);
//                bundle2.putInt("blogId", blogId);
//                bundle2.putBoolean("isOwner", isOwner);
//                bundle2.putString("userId", userItem.getUi());
//                mContext.startActivity(ContainerActivity.getIntent(mContext, bundle2));

                //2 elected User Item
//                Intent intent = new Intent(mContext, ReadProfileActivity.class);
//                Gson gson = new Gson();
//                String json = gson.toJson(item);
//                Bundle bundle = new Bundle();
//                bundle.putString("Object", json);
//                bundle.putString("Type", "electedUserItem");//"TimelineItem");
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
//                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                //3
                StringBuilder stringBuilder0 = new StringBuilder();

                stringBuilder0.append("قرعه کشی در");
                stringBuilder0.append(" ");
                stringBuilder0.append(item.getName());
                stringBuilder0.append("\n");

                stringBuilder0.append("توضیحات:");

                stringBuilder0.append("\n");
                stringBuilder0.append("منتشر شده از اپلیکیشن");
                stringBuilder0.append(BuildConfig.FLAVOR_version_name);
                stringBuilder0.append("\n");
                if (BuildConfig.FLAVOR_market.equals("bazzar")){
//                    stringBuilder0.append("https://cafebazaar.ir/app/ir.sajjadyosefi.android.xYafte");
                    stringBuilder0.append("آدرس دانلود فایل نصبی");

                }else if (BuildConfig.FLAVOR_market.equals("myket")){
//                    stringBuilder0.append("https://myket.ir/app/ir.sajjadyosefi.android.xYafte");
                    stringBuilder0.append("آدرس دانلود فایل نصبی");
                }else {
                    stringBuilder0.append("آدرس دانلود فایل نصبی");
                }
                stringBuilder0.append("\n");

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
                intent.setType("text/plain");
                ((Activity)mContext).startActivityForResult(intent , 60);
            }
        };
        View.OnClickListener onViewDetailsClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //before inflating the custom alert dialog layout, we will get the current activity viewgroup
                ViewGroup viewGroup = LotteryAdapter.rootView.findViewById(android.R.id.content);

                //then we will inflate the custom alert dialog xml that we created
                final View dialogView = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, viewGroup, false);
                TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
                Button buttonOk = dialogView.findViewById(R.id.buttonOk);
                Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
                textViewStatment.setText(item.getDescription());

                //Now we need an AlertDialog.Builder object
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                //setting the view of the builder to our custom view that we already inflated
                builder.setView(dialogView);

                //finally creating the alert dialog and displaying it
                final AlertDialog alertDialog = builder.create();
                buttonCancel.setText(mContext.getString(R.string.ok));
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                buttonOk.setVisibility(View.GONE);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        };
        electedUserItemViewHolder.layoutShare.setOnClickListener(onShareClick);
        electedUserItemViewHolder.textViewDescription.setOnClickListener(onViewDetailsClick);
        electedUserItemViewHolder.textViewName.setOnClickListener(onViewDetailsClick);
        electedUserItemViewHolder.textViewHref.setOnClickListener(onViewDetailsClick);
        electedUserItemViewHolder.imageViewLogo.setOnClickListener(onViewDetailsClick);
        electedUserItemViewHolder.textViewWebsite.setOnClickListener(onViewDetailsClick);

    }
}
