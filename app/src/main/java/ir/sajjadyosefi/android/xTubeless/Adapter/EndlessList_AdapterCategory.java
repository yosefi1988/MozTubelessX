package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;


public class EndlessList_AdapterCategory extends RecyclerView.Adapter<EndlessList_AdapterCategory.ParentViewHolder> {

    public enum ListType {
        LIST_OF_PICTURES,
        FILES,
    };
    public enum ListItemType {
        TYPE_ITEM,
        TYPE_HEADER,
        TYPE_EMPTY_LIST,
        TYPE_LAST_ITEM,
        TYPE_PROGRESS
    };

//    //val
//    public static  final int TYPE_LAST_ITEM = 99;
//    public static  final int TYPE_HEADER = 98;
//    public static final int TYPE_ITEM = 3;

    private boolean deletable;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<CategoryItem> mTimelineItemList;

    EndlessList_AdapterCategory adapter;
    public LinearLayoutManager mLayoutManager = null ;

    private int lastPosition = -1;


    //FILES
    public EndlessList_AdapterCategory(Context context, LinearLayoutManager mLayoutManager, View rootview, List<CategoryItem> fileItemList, ListType type, boolean deletable) {
        if (type == ListType.LIST_OF_PICTURES) {
            this.mContext = context;
            this.mLayoutManager = mLayoutManager;
            this.mRecyclerView = rootview.findViewById(R.id.recyclerView);
            this.mTimelineItemList = fileItemList;
            this.adapter = this;
            this.deletable = deletable ;
        }
    }


    ///////////////////////  ViewHolder   /////////////////////////
    public class ParentViewHolder extends RecyclerView.ViewHolder {

        public ParentViewHolder(View itemView) {
            super(itemView);
        }

        public void clearAnimation() {
            itemView.clearAnimation();
        }
    }

    public class ProgressViewHolder extends ParentViewHolder {
        public ProgressViewHolder(View itemView) {
            super(itemView);
//            linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        }
    }

    public class EndOfListHolder extends ParentViewHolder {
        public TextView textView;
        public EndOfListHolder (View itemView) {
            super(itemView);
            textView                = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class EmptyListHolder extends ParentViewHolder {
        public TextView textView;
        public EmptyListHolder (View itemView) {
            super(itemView);
            textView                = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class HeaderOfListHolder extends ParentViewHolder {
//        public TextView textView;
        public TextView textViewDelete;
        public HeaderOfListHolder (View itemView) {
            super(itemView);
//            textView                = (TextView) itemView.findViewById(R.id.textView);
            textViewDelete                = (TextView) itemView.findViewById(R.id.textViewDelete);
        }
    }


    public class CategoryViewHolder extends ParentViewHolder {
        public View rootView;
        public TextView textViewTitle , textViewDescription;
        public ImageView buttonDelete;
        public ImageView imageView;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            rootView = (View) itemView.findViewById(R.id.rootView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            buttonDelete = (ImageView) itemView.findViewById(R.id.buttonDelete);

        }
    }

    public class AddViewHolder extends ParentViewHolder {
        public Button buttonSubmit;


        public AddViewHolder(View itemView) {
            super(itemView);
//            buttonSubmit            = (Button) itemView.findViewById(R.id.submit);

        }
    }
    //////////////////// End ViewHolder   /////////////////////////

    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        switch (viewType)   {
            case 1: //TYPE_ITEM
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_category_item, parent, false);
                CategoryViewHolder viewHolder = new CategoryViewHolder(view);
                return viewHolder;
            case 2: //TYPE_HEADER
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_header, parent, false);
                return new HeaderOfListHolder(view);
            case 3: //TYPE_PROGRESS
                break;
            case 4: //TYPE_LAST_ITEM
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_last_item_files, parent, false);
                return new EndOfListHolder(view);
            case 5: //TYPE_EMPTY_LIST
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_empty_item, parent, false);
                return new EmptyListHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_progress, parent, false);
                ProgressViewHolder holder = new ProgressViewHolder(view);
                return holder;
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(final ParentViewHolder holder, final int position) {
        if (holder instanceof CategoryViewHolder) {
            //String dataItem = getItem(position);
            //cast holder to VHItem and set data
//            ((CategoryNode) mTimelineItemList.get(position)).bind(mContext, (CategoryViewHolder) holder, mTimelineItemList, position, adapter, deletable);

        } else if (holder instanceof HeaderOfListHolder) {
            //Header
            //cast holder to VHHeader and set data for header.

            if (deletable){
                ((HeaderOfListHolder) holder).textViewDelete.setEnabled(deletable);
                ((HeaderOfListHolder) holder).textViewDelete.setVisibility(View.VISIBLE);
            }else {
                ((HeaderOfListHolder) holder).textViewDelete.setEnabled(deletable);
                ((HeaderOfListHolder) holder).textViewDelete.setVisibility(View.GONE);
            }

        } else if (holder instanceof EmptyListHolder) {
            //Header
            //cast holder to VHHeader and set data for header.
            ((EmptyListHolder) holder).textView.setText(R.string.not_any_category);

        }else if (holder instanceof EndOfListHolder) {
            ((EndOfListHolder) holder).textView.setText(R.string.end_of_list);
        }

        if (position == 0){

        }else {
            if (
                    mTimelineItemList.size() > 0 &&
                    mTimelineItemList.size() != position
                           // && mTimelineItemList.get(position).getType() == TYPE_ITEM
            ) {


            } else {
                if (mTimelineItemList.size() == 0) {
                    //LAST ITEM
                    ((EndOfListHolder) holder).textView.setText(R.string.not_any_file);
                } else {
                    //Header
//                    ((HeaderOfListHolder) holder).textView.setText(R.string.not_any_file);
                }
            }
        }
    }

    ///////////////////////  ok   /////////////////////////
    @Override
    public int getItemCount() {
//        if (mTimelineItemList == null){
//            return 0;
//        }else {
//            return mTimelineItemList.size() + 1;
//        }
        return mTimelineItemList.size();
    }

    @Override
    public void onViewDetachedFromWindow(final ParentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
        ((ParentViewHolder)holder).clearAnimation();
    }

    @Override
    public int getItemViewType(int position) {
//        switch (mTimelineItemList.get(position).getListItemType())   {
//            case TYPE_ITEM:
//                return 1;
//            case TYPE_HEADER:
//                return 2;
//            case TYPE_PROGRESS:
//                return 3;
//            case TYPE_LAST_ITEM:
//                return 4;
//            case TYPE_EMPTY_LIST:
//                return 5;
//            default:
//                return 0;
//        }
//        if (mTimelineItemList.size() == 0){
//            return TYPE_LAST_ITEM;
//        }else if (mTimelineItemList.size() >= 1){
//            if (position == 0){
//                return TYPE_HEADER;
//            }else {
//                if (position == mTimelineItemList.size())
//                    return TYPE_LAST_ITEM;
//                else
//                    return mTimelineItemList.get(position).getType();
//            }
//        }else {
            return 0;
//        }
    }

}