package ir.sajjadyosefi.android.xTubeless.activity.filter;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.TestAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TestPosts;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class filterDetailsActivity extends TubelessTransparentStatusBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_details);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        TestAdapter postsAdapter= new TestAdapter(this, DataFakeGenerator.getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(postsAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeButtonEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        HitosViewPagerAdapter adapter = new HitosViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.setupWithViewPager(viewPager);



        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            float dX = 0.0f;
            float dY = 0.0f;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Title");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }

                //anim 1
//                linearLayout.setPadding( verticalOffset* -1 , verticalOffset *-10 , verticalOffset *-100 , verticalOffset*-10);

                //anim 2
                linearLayout.animate()
                        .y((verticalOffset * -2))
                        //.x((verticalOffset * -1) )
                        .setDuration(0)
                        .start();
            }
        });
    }

    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    private static class DataFakeGenerator {
        public static List<TestPosts> getData() {
            List<TestPosts> posts = new ArrayList<>();
            for (int i = 0; i < 10; i++){
                TestPosts post = new TestPosts();
                post.setTitle("Title num: "+ i );
                post.setIntro("Intro num: "+ i );
                post.setFullPost("Full Post num: "+ i );
                post.setDate("Date num: "+ i);

                posts.add(post);
            }
            return posts;
        }
    }
}
