package ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.Main;
import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.NerkhrozBourse;

import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.ParentListItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz.ItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView.expandable.ExpandableRecyclerAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz.HeaderViewHolder;


public class XAdapterNerkhRoz extends ExpandableRecyclerAdapter<HeaderViewHolder, ItemViewHolder> {

    private LayoutInflater mInflator;

    public XAdapterNerkhRoz(Context context, List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public HeaderViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View movieCategoryView = mInflator.inflate(R.layout._row_of_bourse_nerkhroz_header, parentViewGroup, false);
        return new HeaderViewHolder(movieCategoryView);
    }

    @Override
    public ItemViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View moviesView = mInflator.inflate(R.layout._row_of_bourse_nerkhroz_item, childViewGroup, false);
        return new ItemViewHolder(moviesView);
    }

    @Override
    public void onBindParentViewHolder(HeaderViewHolder headerViewHolder, int position, ParentListItem parentListItem) {
        NerkhrozBourse movieCategory = (NerkhrozBourse) parentListItem;
        headerViewHolder.bind(movieCategory);
    }

    @Override
    public void onBindChildViewHolder(ItemViewHolder moviesViewHolder, int position, Object childListItem) {
        Main item = (Main) childListItem;
        moviesViewHolder.bind(item);
    }
}

