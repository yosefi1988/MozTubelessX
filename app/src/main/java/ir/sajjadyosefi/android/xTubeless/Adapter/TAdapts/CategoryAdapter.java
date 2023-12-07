package ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.TimelineRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.EmptyTextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.CategoriesLookUpRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CategoryViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.EmptyTextViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_MULTY_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.TransactionsAdapter.VIEW_TYPE_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.old.XAdapterold.VIEW_EMPTY_TEXT;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_FILTER_RESULT;


public class CategoryAdapter extends ITubelessAdapter {
    private CategoryItem categoryItem;
    private View rootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private Fragment fragment;
    CategoryAdapter adapter;
    int listType;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private Context context;
    private TextView emptyView2;
    public LinearLayoutManager mLayoutManager = null ;
    EndlessRecyclerOnScrollListener onScrollListener;
    public static List<CategoryItem> categoryItemStack = new ArrayList<>();

    public TimelineRequest timelineSearchRequest = null;

    Long category;
    Long parentId;
    int selectType;
    public CategoryAdapter(Long _category,Long _parentId,int _selectType) {
        category = _category;
        parentId = _parentId;
        selectType = _selectType;
    }

    @Override
    public void init(int listType, CategoryItem categoryItem, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle, TextView emptyView2 , TimelineRequest searchRequest) {

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
                //loadTimeline(context, current_page, false);
            }
        };
        recyclerView.addOnScrollListener(onScrollListener);
        firstLoadAndRefresh(context);
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

    private void loadTimeline(Context context,int _current_page, boolean isRefresh) {
        //if (listType == LIST_CATEGORY_ONE_SELECT) {
            ((TubelessActivity) context).progressDialog.show();
            //CategoriesLookUpRequest request = new CategoriesLookUpRequest(_current_page - 1);
            CategoriesLookUpRequest request = new CategoriesLookUpRequest();

            //request.setCategoryCode(CATEGORY_ID);//root
            request.setCategoryCode(category);//from adapter
            request.setParentId(parentId);//from adapter
            //selectType
            Global.apiManagerTubeless.getCategoryLookUp(request, new TubelessRetrofitCallbackss(context, CategoryListResponse.class) {
                @Override
                public void t_beforeSendRequest() {
                }

                @Override
                public void t_afterGetResponse() {
                }

                @Override
                public void t_complite() {
                }

                @Override
                public void t_responseNull() {
                }

                @Override
                public void t_retry(Call<Object> call) {
                }

                @Override
                public void t_onSuccess(Object response) {
                    //selectType
                    for (CategoryItem item:((CategoryListResponse) response).getCatlist()) {
                        ((ListFragment) fragment).dataList.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(((ListFragment)fragment).dataList.size());
                        }
                    }
                    if (selectType == LIST_CATEGORY_ONE_SELECT || selectType == LIST_CATEGORY_MULTY_SELECT ) {
                        CategoryItem categoryItemAll = new CategoryItem();
                        categoryItemAll.setSelectable(true);
                        categoryItemAll.setSelectableS("true");
                        categoryItemAll.setTitle(context.getString(R.string.all));
                        categoryItemAll.setID(0);
                        categoryItemAll.setHID(Math.toIntExact(category));
                        categoryItemAll.setCID(((CategoryItem)((ListFragment) fragment).dataList.get(0)).getCID());
                        ((ListFragment) fragment).dataList.add(categoryItemAll);
                    }
                    ((TubelessActivity)context).progressDialog.hide();

                    loadSelectedItems(context);
                }
            });
        //}
    }

    private void loadSelectedItems(Context context) {
        boolean refresh = false;
        if (context instanceof ContainerActivity) {
            for (CategoryItem selectedItem : ((ContainerActivity) context).listSelectedCategoryItems) {
                for (ParentItem item : ((ListFragment) fragment).dataList) {
                    if (((CategoryItem) item).getTitle().equals(selectedItem.getTitle()) && selectedItem.getID() == item.getID()) {
                        ((CategoryItem) item).setSelected(true);
                        refresh = true;
                    }
                }
            }
        }
        if (refresh)
            adapter.notifyDataSetChanged();
    }

    public static int VIEW_TYPE_UserItem = 2;
    @Override
    public TubelessMainViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        //BuildConfig.FLAVOR_version_name.equals("amlak")
        TubelessMainViewHolder holder = null;
        View view;

        if (viewType == VIEW_EMPTY_TEXT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._xrow_of_empty, parent, false);
            holder = new EmptyTextViewHolder(view);
        }else if (viewType == VIEW_TYPE_CATEGORY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_category_item, parent, false);
            holder = new CategoryViewHolder(view);
        }else {

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final TubelessMainViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
         if (((ListFragment)fragment).dataList.get(position) instanceof CategoryItem) {
             CategoryViewHolder holder = (CategoryViewHolder) viewHolder;

             final CategoryItem item = (CategoryItem) ((ListFragment)fragment).dataList.get(position);
            item.fill(context,this , listType, viewHolder, item, position,((ListFragment)fragment));


             //int count = bundle.getInt("CAT_COUNT");
             if (selectType == LIST_CATEGORY_ONE_SELECT || selectType == LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS) {
                 holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if (!item.getSelectable()) {
                             CategoryItem currentNode = new CategoryItem();
                             currentNode.setID(parentId.intValue());
                             currentNode.setCID(category);
                             currentNode.setTitle(item.getTitle());
                             categoryItemStack.add(categoryItemStack.size(), currentNode);

                             String path = new String();
                             for (CategoryItem item1: categoryItemStack) {
                                 path = path + item1.getTitle() + "/";
                             }

                             if(context.getClass().getName().contains("ContainerActivity")) {
                                 ((ContainerActivity) context).moveTo(category, item.getID(), path,selectType);
                             }else {
                                 ((ListFragment) fragment).CalendarPageFragmentListener.onSwitchToNextFragment(category, item.getID(),path,selectType);
                             }
                         } else {
                             //read blog
                             //Search by filter
                             if (BuildConfig.FLAVOR_version_name.equals("tubeless")) {
                                 if ( selectType == LIST_CATEGORY_ONE_SELECT_FOR_NEW_BLOGS){
                                     String path = new String();
                                     for (CategoryItem item1: categoryItemStack) {
                                         path = path + item1.getTitle() + "/";
                                     }
                                     path = path + item.getTitle();
                                     //return Cat ID
                                     Intent returnIntent = new Intent();
                                     Bundle bundle = new Bundle();
                                     bundle.putLong("SelectedCategory", item.getID());
                                     bundle.putString("SelectedCategoryTitle",path);
                                     returnIntent.putExtras(bundle);
                                     ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                                     ((Activity) context).finish();
                                 }else {
                                     TimelineRequest timelineSearchRequest = new TimelineRequest(new String(),8133 );
                                     if (item.getID() == 0) {
                                         timelineSearchRequest.setTtc(null);
                                     } else {
                                         timelineSearchRequest.setTtc(String.valueOf(item.getID()));
                                     }
                                     timelineSearchRequest.setPageSize("10");
                                     timelineSearchRequest.setPageIndex("0");
                                     timelineSearchRequest.setActive(true);

                                     Bundle bundle = new Bundle();
                                     bundle.putInt("type" , FRAGMENT_FILTER_RESULT);
                                     bundle.putInt("CAT_COUNT", 10);
                                     bundle.putSerializable("SearchRequest",timelineSearchRequest);
                                     ((Activity) context).startActivity(ContainerActivity.getIntent(context,bundle));
                                 }
                             }else {
                                 //return Cat ID
                                 Intent returnIntent = new Intent();
                                 Bundle bundle = new Bundle();
//                       bundle.putSerializable("SelectedLIST", (Serializable) list);
                                 bundle.putLong("SelectedCategory", item.getID());
                                 returnIntent.putExtras(bundle);
                                 ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                                 ((Activity) context).finish();
                             }



                         }
                     }
                 });
             }
             if (selectType == LIST_CATEGORY_MULTY_SELECT) {
                 holder.imageView.setVisibility(View.GONE);
                 ((Activity)context).runOnUiThread(new Runnable(){
                     public void run() {
                         // UI code goes here
                         holder.checkBox.setChecked(((CategoryItem) ((ListFragment) fragment).dataList.get(position)).getSelected());
                     }
                 });
                 holder.checkBox.setVisibility(View.VISIBLE);
                 holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                     @Override
                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                         if (b){
                             ((CategoryItem) ((ListFragment)fragment).dataList.get(position)).setSelected(true);
                         }else {
                             ((CategoryItem) ((ListFragment)fragment).dataList.get(position)).setSelected(false);
                         }
                         //adapter.notifyDataSetChanged();
//                         recyclerView.post(new Runnable()
//                         {
//                             @Override
//                             public void run() {
//                                 adapter.notifyDataSetChanged();
//                             }
//                         });
//                         adapter.notifyDataSetChanged();

                     }
                 });
                 holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if (!item.getSelectable()) {

                             for (ParentItem item:((ListFragment) fragment).dataList) {
                                 if (((CategoryItem)item).getSelected())
                                     ((ContainerActivity)context).listSelectedCategoryItems.add((CategoryItem)item);
                             }

                             CategoryItem currentNode = new CategoryItem();
                             currentNode.setID(parentId.intValue());
                             currentNode.setCID(category);
                             currentNode.setTitle(item.getTitle());
                             categoryItemStack.add(categoryItemStack.size(), currentNode);

                             String path = new String();
                             for (CategoryItem item1: categoryItemStack) {
                                 path = path + item1.getTitle() + "/";
                             }

                             if(context.getClass().getName().contains("ContainerActivity")) {
                                 ((ContainerActivity) context).moveTo(category, item.getID(), path,selectType);
                             }else {
                                 ((ListFragment) fragment).CalendarPageFragmentListener.onSwitchToNextFragment(category, item.getID(),path,selectType);
                             }
                         }
//                         else {
//                             //return Cat ID
//                             Intent returnIntent = new Intent();
//                             Bundle bundle = new Bundle();
//    //                       bundle.putSerializable("SelectedLIST", (Serializable) list);
//                             bundle.putLong("SelectedCategory",item.getID());
//                             returnIntent.putExtras(bundle);
//                             ((Activity)context).setResult(Activity.RESULT_OK, returnIntent);
//                             ((Activity) context).finish();
//                         }
                     }
                 });
             }

             if (categoryItemStack.size() == 0) {
                 ((ListFragment) fragment).buttonBack.setEnabled(false);
             }else {
                 ((ListFragment) fragment).buttonBack.setEnabled(true);
             }

             ((ListFragment) fragment).buttonBack.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (categoryItemStack.size() > 0) {
                         checkAndAddToReturnList();

                         CategoryItem currentNode = categoryItemStack.get(categoryItemStack.size() - 1);
                         categoryItemStack.remove(categoryItemStack.size() - 1);


                         String path = new String();
                         for (CategoryItem item1: categoryItemStack) {
                             path = path + item1.getTitle() + "/";
                         }

                         if(context.getClass().getName().contains("ContainerActivity")) {
                             ((ContainerActivity) context).moveTo(currentNode.getCID(), currentNode.getID(), path,selectType);
                         }else {
                             ((ListFragment) fragment).CalendarPageFragmentListener.onSwitchToNextFragment(currentNode.getCID(), currentNode.getID(), path,selectType);
                         }
                     }
                 }
             });
             ((ListFragment) fragment).buttonHome.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (categoryItemStack.size() > 0) {
                         checkAndAddToReturnList();

                         CategoryItem currentNode = categoryItemStack.get(categoryItemStack.size() - 1);
                         categoryItemStack.clear();

                         if(context.getClass().getName().contains("ContainerActivity")) {
                             ((ContainerActivity) context).moveTo(currentNode.getCID(), 0,context.getString(R.string.title_home),selectType);
                         }else {
                             ((ListFragment) fragment).CalendarPageFragmentListener.onSwitchToNextFragment(currentNode.getCID(), 0,context.getString(R.string.title_home), selectType);
                         }
                     }
                 }
             });
             ((ListFragment) fragment).buttonDone.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //multi SELECT

                     checkAndAddToReturnList();
                     Intent returnIntent = new Intent();
                     Bundle bundle = new Bundle();
                     bundle.putSerializable("SelectedLIST", (Serializable) ((ContainerActivity)context).listSelectedCategoryItems);
                     //bundle.putLong("SelectedCategory",item.getID());
                     returnIntent.putExtras(bundle);
                     ((Activity)context).setResult(Activity.RESULT_OK, returnIntent);
                     ((Activity) context).finish();
                 }
             });

         }

    }

    private void checkAndAddToReturnList() {
        for (ParentItem item:((ListFragment) fragment).dataList) {
            if (((CategoryItem)item).getSelected())
                ((ContainerActivity)context).listSelectedCategoryItems.add((CategoryItem)item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (((ListFragment)fragment).dataList.get(position) instanceof CategoryItem){
            return VIEW_TYPE_CATEGORY;
        }else if (((ListFragment)fragment).dataList.get(position) instanceof EmptyTextItem){
            return VIEW_EMPTY_TEXT;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if (((ListFragment)fragment).dataList == null)
            return 0;
        else
            return ((ListFragment)fragment).dataList.size();
    }
}

