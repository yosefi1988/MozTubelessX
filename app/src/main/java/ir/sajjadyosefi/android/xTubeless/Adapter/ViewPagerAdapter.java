package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.utility.file.FileUtils;

public class ViewPagerAdapter  extends PagerAdapter {
    // Declare Variables
    Context context;
    ArrayList<String> flag;
    LayoutInflater inflater;
    public ViewPagerAdapter(Context context, ArrayList<String> flag) {
        this.context = context;
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return flag.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtrank;
        ImageView imgflag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.image_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtrank = (TextView) itemView.findViewById(R.id.tvTitle);

        // Capture position and set to the TextViews
        txtrank.setText(flag.get(position));

        // Locate the ImageView in viewpager_item.xml
        imgflag = (ImageView) itemView.findViewById(R.id.iv_image);
        imgflag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileUtils.openFile((Activity)(context),flag.get(position).replace("\\","/"),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Capture position and set to the ImageView
        Picasso.get()
            .load(flag.get(position))
            .placeholder(R.drawable.png_image)
            .into(imgflag);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}