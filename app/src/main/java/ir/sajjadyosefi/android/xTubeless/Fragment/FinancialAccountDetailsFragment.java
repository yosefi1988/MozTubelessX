package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ir.sajjadyosefi.android.xTubeless.R;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FinancialAccountDetailsFragment extends Fragment {
    public static Context context;
    public static int mIndex;

    public static final String ARG_PAGE = "ARG_PAGE";


    public FinancialAccountDetailsFragment() {

    }

    public FinancialAccountDetailsFragment newInstance(Context context) {
        this.context = context ;
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        FinancialAccountDetailsFragment fragment = new FinancialAccountDetailsFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public FinancialAccountDetailsFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.financial_account_detail_fragment, container, false);

        return view;
    }

}