package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.File;
import ir.sajjadyosefi.android.xTubeless.classes.model.file.FileListProperties;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.ListType.LIST_OF_PICTURES;

public class EndlessList_AdapterFile extends RecyclerView.Adapter<EndlessList_AdapterFile.ParentViewHolder> {

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

    private FileListProperties fileListProperties = null;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<File> mTimelineItemList;

    EndlessList_AdapterFile adapter;
    public LinearLayoutManager mLayoutManager = null ;

    private int lastPosition = -1;


    //FILES
    public EndlessList_AdapterFile(Context context, LinearLayoutManager mLayoutManager, View rootview, List<File> fileItemList, ListType type, boolean deletable) {
        if (type == LIST_OF_PICTURES) {
            this.mContext = context;
            this.mLayoutManager = mLayoutManager;
            this.mRecyclerView = rootview.findViewById(R.id.recyclerView);
            this.mTimelineItemList = fileItemList;
            this.adapter = this;
            fileListProperties = new FileListProperties(false);
            fileListProperties.setDeletable(deletable);
        }
    }

    //FILES
    public EndlessList_AdapterFile(Context context, LinearLayoutManager mLayoutManager, View rootview, List<File> fileItemList, ListType type, FileListProperties fileListProperties) {
        if (type == LIST_OF_PICTURES) {
            this.mContext = context;
            this.mLayoutManager = mLayoutManager;
            this.mRecyclerView = rootview.findViewById(R.id.recyclerView);
            this.mTimelineItemList = fileItemList;
            this.adapter = this;
            this.fileListProperties = fileListProperties;
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


    public class FileViewHolder extends ParentViewHolder {
        public View rootView;
        public TextView textView;
        public ImageButton buttonDelete , buttonShow;
        public RadioButton radioButton;
        public RadioButton radioButton2;
        public ImageView imageView;


        public FileViewHolder(View itemView) {
            super(itemView);
            rootView = (View) itemView.findViewById(R.id.rootView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            buttonDelete = (ImageButton) itemView.findViewById(R.id.buttonDelete);
            buttonShow = (ImageButton) itemView.findViewById(R.id.buttonShow);
            radioButton = (RadioButton) itemView.findViewById(R.id.radioButton);
            radioButton2 = (RadioButton) itemView.findViewById(R.id.radioButton2);

            if(fileListProperties.isOnlyShow()){
                radioButton.setVisibility(View.GONE);
                radioButton2.setVisibility(View.GONE);
            }else {
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int copyOfLastCheckedPosition = lastCheckedPosition;
                        lastCheckedPosition = getAdapterPosition();
                        notifyItemChanged(copyOfLastCheckedPosition);
                        notifyItemChanged(lastCheckedPosition);

                    }
                });
                radioButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int copyOfLastCheckedPosition = lastCheckedPosition2;
                        lastCheckedPosition2 = getAdapterPosition();
                        notifyItemChanged(copyOfLastCheckedPosition);
                        notifyItemChanged(lastCheckedPosition2);
                    }
                });
            }
        }
    }

    public class AddViewHolder extends ParentViewHolder {
        public Button buttonSubmit;


        public AddViewHolder(View itemView) {
            super(itemView);
            buttonSubmit            = (Button) itemView.findViewById(R.id.submit);

        }
    }
    //////////////////// End ViewHolder   /////////////////////////

    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        switch (viewType)   {
            case 1: //TYPE_ITEM
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file, parent, false);
                FileViewHolder viewHolder = new FileViewHolder(view);
                return viewHolder;
            case 2: //TYPE_HEADER
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file_header, parent, false);
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

    public static RadioGroup lastCheckedRadioGroup = null;
    public static  int lastCheckedPosition = -1;
    public static  int lastCheckedPosition2 = -1;


    @Override
    public void onBindViewHolder(final ParentViewHolder holder, final int position) {
        if (holder instanceof FileViewHolder) {
            //String dataItem = getItem(position);
            //cast holder to VHItem and set data
            ((File) mTimelineItemList.get(position)).prepareFileItem(mContext, (FileViewHolder) holder, mTimelineItemList, position, adapter, fileListProperties.isDeletable());

        } else if (holder instanceof HeaderOfListHolder) {
            //Header
            //cast holder to VHHeader and set data for header.

            if (fileListProperties.isDeletable()){
                ((HeaderOfListHolder) holder).textViewDelete.setEnabled(fileListProperties.isDeletable());
                ((HeaderOfListHolder) holder).textViewDelete.setVisibility(View.VISIBLE);
            }else {
                ((HeaderOfListHolder) holder).textViewDelete.setEnabled(fileListProperties.isDeletable());
                ((HeaderOfListHolder) holder).textViewDelete.setVisibility(View.GONE);
            }

        } else if (holder instanceof EmptyListHolder) {
            //Header
            //cast holder to VHHeader and set data for header.
            ((EmptyListHolder) holder).textView.setText(R.string.not_any_file);

        }else if (holder instanceof EndOfListHolder) {
            ((EndOfListHolder) holder).textView.setText(R.string.end_of_list);
        }

//        if (position == 0){
//
//        }else {
//            if (
//                    mTimelineItemList.size() > 0 &&
//                    mTimelineItemList.size() != position &&
//                    mTimelineItemList.get(position).getType() == TYPE_ITEM) {
//
//
//            } else {
//                if (mTimelineItemList.size() == 0) {
//                    //LAST ITEM
//                    ((EndOfListHolder) holder).textView.setText(R.string.not_any_file);
//                } else {
//                    //Header
////                    ((HeaderOfListHolder) holder).textView.setText(R.string.not_any_file);
//                }
//            }
//        }
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
        switch (mTimelineItemList.get(position).getListItemType())   {
            case TYPE_ITEM:
                return 1;
            case TYPE_HEADER:
                return 2;
            case TYPE_PROGRESS:
                return 3;
            case TYPE_LAST_ITEM:
                return 4;
            case TYPE_EMPTY_LIST:
                return 5;
            default:
                return 0;
        }
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
//            return 0;
//        }
    }

}