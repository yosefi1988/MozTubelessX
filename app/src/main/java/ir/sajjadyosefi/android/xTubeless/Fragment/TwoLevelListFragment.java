package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView.XAdapterNerkhRoz;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
 
import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.Main;
import ir.sajjadyosefi.android.xTubeless.classes.model.nerkhroz.bourse.NerkhrozBourse;
import ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView.expandable.ExpandableRecyclerAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TwoLevelListFragment extends Fragment  {
    private XAdapterNerkhRoz mAdapter;
    private RecyclerView recyclerView;
    private TextView textView;

    View fragmentRootView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Callback callback;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (mAdapter != null)
            mAdapter.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mAdapter != null)
            mAdapter.onRestoreInstanceState(savedInstanceState);
    }



    //1
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (fragmentRootView == null) {
            fragmentRootView = inflater.inflate(R.layout.x_fragment_timeline, container, false);
            textView = fragmentRootView.findViewById(R.id.recyclerViewText);
            mSwipeRefreshLayout = (SwipeRefreshLayout) fragmentRootView.findViewById(R.id.swipeRefreshLayout);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Global.retrofitHelperNerkhRoz.getBourseDetailsService(callback);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }

        ((TubelessActivity)getContext()).progressDialog.show();


        callback = new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.body() == null){
                    Toast.makeText(getContext(),"no have data",Toast.LENGTH_SHORT).show();
                    return;
                }

                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                NerkhrozBourse[] founderArray = gson.fromJson(jsonElement.getAsString(), NerkhrozBourse[].class);

//                Main movie_one = new Main("The Shawshank Redemption");
//                Main  movie_two  = new Main("The Godfather");
//                Main  movie_three = new Main("The Dark Knight");
//                Main movie_four  = new Main("Schindler's List ");
//                Main movie_five = new Main("12 Angry Men ");
//                Main movie_six = new Main("Pulp Fiction");
//                Main movie_seven = new Main("The Lord of the Rings: The Return of the King");
//                Main movie_eight = new Main("The Good, the Bad and the Ugly");
//                Main movie_nine = new Main("Fight Club");
//                Main movie_ten = new Main("Star Wars: Episode V - The Empire Strikes");
//                Main movie_eleven = new Main("Forrest Gump");
//                Main movie_tweleve = new Main("Inception");
//
//                NerkhrozBourse molvie_category_one = new NerkhrozBourse("Drama", Arrays.asList(movie_one, movie_two, movie_three, movie_four));
//                NerkhrozBourse molvie_category_two = new NerkhrozBourse("Action", Arrays.asList(movie_five, movie_six, movie_seven,movie_eight));
//                NerkhrozBourse molvie_category_three = new NerkhrozBourse("History", Arrays.asList(movie_nine, movie_ten, movie_eleven,movie_tweleve));
//                NerkhrozBourse molvie_category_four = new NerkhrozBourse("Thriller", Arrays.asList(movie_one, movie_five, movie_nine,movie_tweleve));

                List<NerkhrozBourse> nerkhrozBourses = new ArrayList<>();//= Arrays.asList(molvie_category_one,  molvie_category_two, molvie_category_three,molvie_category_four);


                if (founderArray != null) {
                    for (NerkhrozBourse nerkhrozBourse : founderArray) {
                        NerkhrozBourse nerkhrozBourse1 = new NerkhrozBourse(nerkhrozBourse, nerkhrozBourse.getChildItemList());
                        nerkhrozBourses.add(nerkhrozBourse1);
                    }

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("آخرین تغییرات تا");
                    stringBuilder.append(nerkhrozBourses.get(0).getDate());
                    stringBuilder.append("\n");
                    stringBuilder.append("منبع: http://www.tsetmc.com");
                    stringBuilder.append("\n");

                    textView.setText(stringBuilder.toString());
                    textView.setVisibility(View.VISIBLE);


                    recyclerView = (RecyclerView) fragmentRootView.findViewById(R.id.recyclerView);
                    mAdapter = new XAdapterNerkhRoz(getContext(), nerkhrozBourses);
                    mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                        @Override
                        public void onListItemExpanded(int position) {
                            NerkhrozBourse expandedMovieCategory = nerkhrozBourses.get(position);

//                        String toastMsg = getResources().getString(R.string.app_name, expandedMovieCategory.getName());
//                        Toast.makeText(getContext(),
//                                toastMsg,
//                                Toast.LENGTH_SHORT)
//                                .show();
                        }

                        @Override
                        public void onListItemCollapsed(int position) {
                            NerkhrozBourse collapsedMovieCategory = nerkhrozBourses.get(position);

//                        String toastMsg = getResources().getString(R.string.app_name, collapsedMovieCategory.getName());
//                        Toast.makeText(getContext(),
//                                toastMsg,
//                                Toast.LENGTH_SHORT)
//                                .show();
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
                ((TubelessActivity)getContext()).progressDialog.hide();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        };
        Global.retrofitHelperNerkhRoz.getBourseDetailsService(callback);



        return fragmentRootView;
    }

}
