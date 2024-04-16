package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;
import android.view.View;

import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TransactionItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.SamanString;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TransactionItem extends ParentItem {
    private String Amount;
    private String PersianCreatedOn;
    private String RefrenceNo;
    private int TransactionTypeCode;
    private String TransactionTypeName;
    private float Zarib;
    private String PostTitle;
    private String Image;
    private String Icon;
    private String CreatorName;
    private String PostId;


    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPersianCreatedOn() {
        return PersianCreatedOn;
    }

    public void setPersianCreatedOn(String persianCreatedOn) {
        PersianCreatedOn = persianCreatedOn;
    }

    public float getZarib() {
        return Zarib;
    }

    public void setZarib(float zarib) {
        Zarib = zarib;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getCreatorName() {
        return CreatorName;
    }

    public void setCreatorName(String creatorName) {
        CreatorName = creatorName;
    }

    public String getRefrenceNo() {
        return RefrenceNo;
    }

    public void setRefrenceNo(String refrenceNo) {
        RefrenceNo = refrenceNo;
    }

    public int getTransactionTypeCode() {
        return TransactionTypeCode;
    }

    public void setTransactionTypeCode(int transactionTypeCode) {
        TransactionTypeCode = transactionTypeCode;
    }

    public String getTransactionTypeName() {
        return TransactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        TransactionTypeName = transactionTypeName;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public void setPostTitle(String postTitle) {
        PostTitle = postTitle;
    }

    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
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


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {
//        super.fill(mContext, xAdapter, listType,holder0,item, position);

        TransactionItemViewHolder holder = (TransactionItemViewHolder) holder0;
        final TransactionItem transactionItem = (TransactionItem)item;

        holder.textViewTitle.setText(transactionItem.getTransactionTypeName());//+" - " + transactionItem.getTransactionTypeCode());
        holder.textViewAmount.setText(" مبلغ تراکنش: " + SamanString.addSpratorX(Float.parseFloat(transactionItem.getAmount()))    + " ریال" );//+ "X" + transactionItem.getZarib());
        holder.textViewDate.setText("تاریخ: " + transactionItem.getPersianCreatedOn()+ " شناسه تراکنش: " + transactionItem.getID());
        holder.textViewRef.setText("کدرهگیری: " + transactionItem.getRefrenceNo());

        holder.textViewRef.setVisibility(View.GONE);

        if (transactionItem.getPostTitle() != null) {
            holder.textViewpay.setVisibility(View.VISIBLE);
            holder.textViewpay.setText("جهت پست: " + transactionItem.getRefrenceNo() + " - " +transactionItem.getPostTitle());
        }else
            holder.textViewpay.setVisibility(View.GONE);


        if (Float.parseFloat(transactionItem.getAmount()) > 0) {
            holder.imageviewup.setVisibility(View.VISIBLE);
            holder.imageviewdown.setVisibility(View.GONE);
        } else {
            holder.imageviewdown.setVisibility(View.VISIBLE);
            holder.imageviewup.setVisibility(View.GONE);
        }

    }

}
