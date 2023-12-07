package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.MainAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TransactionItem extends ParentItem {

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

    public String getRefrenceNo() {
        return RefrenceNo;
    }

    public void setRefrenceNo(String refrenceNo) {
        RefrenceNo = refrenceNo;
    }

    public String getTransactionTypeCode() {
        return TransactionTypeCode;
    }

    public void setTransactionTypeCode(String transactionTypeCode) {
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

    private String Amount;
    private String PersianCreatedOn;
    private String RefrenceNo;
    private String TransactionTypeCode;
    private String TransactionTypeName;
    private String PostTitle;
    private String PostId;


    public TransactionItem(TimelineItem item) {

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
//        TransactionViewHolder holder = (TransactionViewHolder) holder0;
//        final TransactionItem transactionItem = (TransactionItem)item;
//
//
////        holder.textViewAmount.setText(" مبلغ تراکنش: " + SamanString.addSpratorX(Integer.parseInt(transactionItem.getAmount())) + " ریال");
//        holder.textViewAmount.setText(" مبلغ تراکنش: " + SamanString.addSpratorX(Float.parseFloat(transactionItem.getAmount()))    + " ریال");
//        holder.textViewDate.setText("تاریخ: " + transactionItem.getPersianCreatedOn());
//        holder.textViewRef.setText("کدرهگیری: " + transactionItem.getRefrenceNo());
//        holder.textViewTitle.setText(transactionItem.getTransactionTypeName());
//
//        holder.textViewRef.setVisibility(View.GONE);
//
//        if (transactionItem.getPostTitle() != null) {
//            holder.textViewpay.setVisibility(View.VISIBLE);
//            holder.textViewpay.setText("جهت مشاهده: " +transactionItem.getPostTitle());
//        }else
//            holder.textViewpay.setVisibility(View.GONE);
//
//
//        if (Float.parseFloat(transactionItem.getAmount()) > 0) {
//            holder.imageviewup.setVisibility(View.VISIBLE);
//            holder.imageviewdown.setVisibility(View.GONE);
//        } else {
//            holder.imageviewdown.setVisibility(View.VISIBLE);
//            holder.imageviewup.setVisibility(View.GONE);
//        }
//    }

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

    }
}
