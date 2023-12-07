package ir.sajjadyosefi.android.xTubeless.activity.common.blog;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.widget.imageView.NewZoomableImageView;

public class zoomImageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_image);

        NewZoomableImageView touch = (NewZoomableImageView)findViewById(R.id.zoom_image);
        touch.setImageResource(R.drawable.png_contact_us);

        Picasso.get()
                .load(getIntent().getStringExtra("image"))
                .placeholder(R.drawable.bg_search)
                //.centerInside()
                //.transform(transformation)
                .into(touch, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                        Picasso.get()
                                .load(R.drawable.png_image)
                                //.transform(transformation)
                                .into(touch);
                    }
                } );

        ((TextView) findViewById(R.id.textViewText)).setText(getIntent().getStringExtra("text"));
    }
}
