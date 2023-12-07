package ir.sajjadyosefi.android.xTubeless.classes.model;

import android.content.Context;
import android.view.View;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessListAmountsAdapter;

/**
 * Created by sajjad on 10/31/2016.
 */
public class Amounts extends TubelessObject  {

    private String Text;
    private String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        this.Value = value;
    }

    public String getText() {
        return Text;
    }
    public void setText(String text) {
        this.Text = text;
    }


    public void prepareYafteItem(Context mContext, EndlessListAmountsAdapter.SubscribeViewHolder holder, List<Amounts> mTimelineItemList, int position, EndlessListAmountsAdapter adapter) {
        Amounts subscribe = (Amounts) mTimelineItemList.get(position);

        StringBuilder text = new StringBuilder();

        holder.textViewTitle.setText(subscribe.getText() + "");
        holder.textViewValue.setText(subscribe.getValue() + "");

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimelineItemList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
