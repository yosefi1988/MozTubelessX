package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TestPosts;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.PostsViewHolder>{

    private Context context;
    private List<TestPosts> posts;

    public TestAdapter(Context context, List<TestPosts> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_view__test_item, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        TestPosts post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.intro.setText(post.getIntro());
        holder.date.setText(post.getDate());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView intro;
        private TextView date;

        public PostsViewHolder(View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            intro = (TextView) itemView.findViewById(R.id.intro);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
