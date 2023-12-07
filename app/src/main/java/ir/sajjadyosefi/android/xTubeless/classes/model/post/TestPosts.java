package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import ir.sajjadyosefi.android.xTubeless.Adapter.TAdapts.ITubelessAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TubelessMainViewHolder;

public class TestPosts extends MainItem {
    private int id;
    private String title;
    private String intro;
    private String fullPost;
    private String date;

    public TestPosts() {
        super();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFullPost() {
        return fullPost;
    }

    public void setFullPost(String fullPost) {
        this.fullPost = fullPost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public void fill(Context mContext, ITubelessAdapter xAdapter, int listType, TubelessMainViewHolder holder0, ParentItem item, int position, ListFragment fragment) {

    }
}
