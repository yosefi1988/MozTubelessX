package ir.sajjadyosefi.android.xTubeless.classes.model.ddl;

import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;

/**
 * Created by sajjad on 10/31/2016.
 */
public class DdlSelectsObject extends TubelessObject {

    private int keyValue;
    private String textValue;

    public DdlSelectsObject(String text, int key) {
        keyValue = key;
        textValue = text;
    }

    public int getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(int keyValue) {
        this.keyValue = keyValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

//    public void prepareYafteItem(Context mContext, boolean enable, EndlessList_Adapter.ToDotViewHolder holder, List<TubelessObject> mTimelineItemList, int position, EndlessList_Adapter adapter) {
//
//        AbfaxSelectsObjectSelectable request = (AbfaxSelectsObjectSelectable) mTimelineItemList.get(position);
//        holder.textView.setText(request.getTextValue() + "");
//        //holder.checkBox.setText(request.getKeyValue());
//
//        if (enable){
//            holder.checkBox.setEnabled(enable);
//            if (request.isSelected()){
//                holder.checkBox.setSelected(request.isSelected());
//            }
//            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    request.setSelected(b);
//                }
//            });
//        }else {
//            holder.checkBox.setEnabled(enable);
//        }
//
//    }
}
