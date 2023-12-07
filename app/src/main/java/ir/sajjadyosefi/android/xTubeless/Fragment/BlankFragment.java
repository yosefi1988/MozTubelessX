package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;
import ir.sajjadyosefi.android.xTubeless.R;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class BlankFragment extends Fragment {
    public static Context context;

    public static final String ARG_PAGE = "ARG_PAGE";
    private FirstFragmentsAdapter firstFragmentsAdapter;
    private FragmentManager mFragmentManager;


    public BlankFragment() {

    }

    public BlankFragment(FragmentManager supportFragmentManager ) {
        this.mFragmentManager = supportFragmentManager;
    }



    public BlankFragment newInstance(Context context) {
        this.context = context ;
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public BlankFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.blank_fragment, container, false);
//        ((Button)view.findViewById(R.id.button)).setOnClickListener(view1 -> {
////            mFragmentManager.beginTransaction().remove(fragmentx3).commit();
////            fragmentx3 = new ListFragment(context, TYPE_YAFTE);;
////            firstFragmentsAdapter.notifyDataSetChanged();
//        });

        return view;
    }



}