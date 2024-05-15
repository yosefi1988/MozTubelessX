package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;

//ITEM_TYPE_TRANS

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.classes.ITransactionsListRequest;
import ir.sajjadyosefi.accountauthenticator.model.ATransaction;
import ir.sajjadyosefi.accountauthenticator.model.request.ATransactionListRequest;
import ir.sajjadyosefi.accountauthenticator.model.response.ATransactionListResponse;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.EmptyTextItem;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.TransactionItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.EmptyTextViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TransactionItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;

import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.KEY_ERROR_MESSAGE;
import static ir.sajjadyosefi.accountauthenticator.activity.accounts.AuthenticatorActivity.PARAM_TRANSACTION_LIST;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.ERR_CODE_UNKNOWN;


public class TransactionsAdapter extends ITubelessAdapter {//extends RecyclerView.Adapter<TubelessMainViewHolder> {

    private CategoryItem categoryItem;
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private Fragment fragment;
    TransactionsAdapter adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    EndlessRecyclerOnScrollListener onScrollListener;

    public TimelineRequest timelineSearchRequest = null;

    private final static int FADE_DURATION = 500; //FADE_DURATION in milliseconds

    public TransactionsAdapter() {

    }

    public void firstLoadAndRefresh(Context context) {
        loadTimeline(context,1,false);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onScrollListener != null)
                    onScrollListener.reset();
                ((ListFragment)fragment).dataList.clear();
                loadTimeline(context,1,true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void init(int listType, CategoryItem categoryItem, Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2, TimelineRequest searchRequest) {

        this.listType = listType;
        this.categoryItem = categoryItem;
        this.fragment = fragment;
        this.picasso = Picasso.get();
        this.context = context;
        this.emptyView2 = emptyView2;
        this.rootView = rootView;
        this.mLayoutManager = linearLayoutManager ;
        this.recyclerView = recyclerView;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout ;
        this.adapter = this;
        this.bundle = bundle;
        this.timelineSearchRequest = searchRequest;

        if (((ListFragment)fragment).dataList == null)
            ((ListFragment)fragment).dataList = new ArrayList<>();

        onScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            protected void onScrollUp() {

            }

            @Override
            protected void onScrollDown() {

            }

            @Override
            public void onLoadMore(int current_page) {
                loadTimeline(context, current_page, false);
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
        firstLoadAndRefresh(context);
    }

    private void loadTimeline(Context context,int _current_page, boolean isRefresh) {
        ((TubelessActivity) context).progressDialog.show();
        Gson gson = new Gson();
        SignInActivity signInActivity = new SignInActivity();
        ATransactionListRequest xxxxxxxxxxx = new ATransactionListRequest(Global.user2.getUserCodeAsString(), "10", (_current_page - 1) + "");
        signInActivity.getTransactionsList(xxxxxxxxxxx, new ITransactionsListRequest<Boolean, Intent>() {
            @Override
            public void onResponse(Boolean isSuccess, Intent intent) {
                ((TubelessActivity) context).progressDialog.hide();

                Bundle bundle = intent.getExtras();
                if (intent.hasExtra(PARAM_TRANSACTION_LIST)) {
                    String config = bundle.getString(PARAM_TRANSACTION_LIST);
                    ATransactionListResponse aTransactionListResponse = new Gson().fromJson(config, ATransactionListResponse.class);
                    for (ATransaction aTransactionItem : aTransactionListResponse.getTransactionList()) {
                        TransactionItem transactionItem = new TransactionItem();

                        transactionItem.setAmount(String.valueOf(aTransactionItem.getAmount()));
                        transactionItem.setID(Integer.parseInt(aTransactionItem.getID()));
                        transactionItem.setPersianCreatedOn(aTransactionItem.getDateTime());    //todo convert date
                        transactionItem.setPostId(aTransactionItem.getRefrenceNo());
                        transactionItem.setPostTitle(aTransactionItem.getTitle());
                        transactionItem.setRefrenceNo(aTransactionItem.getRefrenceNo());
                        transactionItem.setTransactionTypeCode(aTransactionItem.getTTC());
                        transactionItem.setTransactionTypeName(aTransactionItem.getTTN());
                        transactionItem.setZarib(aTransactionItem.getZarib());
                        transactionItem.setImage(aTransactionItem.getImage());
                        transactionItem.setIcon(aTransactionItem.getIcon());
                        transactionItem.setCreatorName(aTransactionItem.getCreatorFullName());
                        ((ListFragment) fragment).dataList.add(transactionItem);

                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyItemInserted(((ListFragment) fragment).dataList.size());
                        }
                    }
                } else if (intent.hasExtra(KEY_ERROR_MESSAGE)) {
                    String error = bundle.getString(KEY_ERROR_MESSAGE);
                    TubelessException sException = new TubelessException();
                    sException.handleServerMessage(context,new TubelessException(ERR_CODE_UNKNOWN,error));

                }
            }
        });
    }
    public static int VIEW_TYPE_TEXT_POST = 1;
    public static int VIEW_TYPE_TEXT_IMAGE_POST = 2;
    public static int VIEW_TYPE_IMAGE_POST = 5047;
    public static int VIEW_TYPE_TRANSACTION = 3;
    public static int VIEW_TYPE_CATEGORY = 4;
    public static int VIEW_EMPTY_TEXT = 100;

    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        TubelessMainViewHolder holder = null;
        if (viewType == VIEW_EMPTY_TEXT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_empty, parent, false);
            holder = new EmptyTextViewHolder(view);

            //listType == TYPE_LIST_CATEGORY
        } else if (viewType == VIEW_TYPE_TRANSACTION) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_transaction_item, parent, false);
            holder = new TransactionItemViewHolder(view);
//        }else if (listType == ITEM_TYPE_TRANS) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_moz_transaction_item, parent, false);
//            holder = new TransactionViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder holder, final int position) {
        //first Item
        if (position == 0 && ((Activity)context) instanceof ContainerActivity) {                 //  <= فاصله اولین آیتم از بالای لیست
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 60;
        }
        //last Item
        if (position == getItemCount() - 1) {
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;
        } else {
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
        }
        if (((ListFragment)fragment).dataList.get(position) instanceof TransactionItem) {
            final TransactionItem item = (TransactionItem) ((ListFragment)fragment).dataList.get(position);
            item.fill(context,this , listType, holder, item, position,((ListFragment)fragment));
//        }else if (((ListFragment)fragment).list.get(position) instanceof ATransactionApp) {
//            final ATransactionApp item = (ATransactionApp) ((ListFragment)fragment).list.get(position);
//            item.fill(context,this , listType, holder, item, position);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof EmptyTextItem){
            return VIEW_EMPTY_TEXT;
        }else if (((ListFragment)fragment).dataList.get(position) instanceof TransactionItem){
            return VIEW_TYPE_TRANSACTION;
        }else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        if (((ListFragment)fragment).dataList == null)
            return 0;
        else
            return ((ListFragment)fragment).dataList.size();
    }
}

