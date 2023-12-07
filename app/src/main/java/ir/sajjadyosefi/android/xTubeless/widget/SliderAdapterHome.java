package ir.sajjadyosefi.android.xTubeless.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.viewpager.widget.PagerAdapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import ir.sajjadyosefi.android.xTubeless.R;


public class SliderAdapterHome  extends PagerAdapter {

    private ArrayList<String> XMENArrayV2;
    private LayoutInflater inflater;
    private Context mContext;

    public SliderAdapterHome(Context context,ArrayList<String> XMENArrayV2) {
        this.mContext = context;
        this.XMENArrayV2 = XMENArrayV2;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return XMENArrayV2.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View myImageLayout = inflater.inflate(R.layout.slide, view, false);
        final ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);

        Picasso.get()
                .load(String.valueOf(XMENArrayV2.get(position)))
                .into(myImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(R.drawable.png_image)
                                .into(myImage);
                    }

                });

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
//        myImage.setImageResource(images.get(position));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
