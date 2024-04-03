package ir.sajjadyosefi.android.xTubeless.bussines.post.fragment;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;


import ir.sajjadyosefi.accountauthenticator.activity.accounts.SignInActivity;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.post.activity.SearchByNationalCodeActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.SearchRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.ServerResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.PostRetrofitCallback;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.activity.payment.PrePaymentActivity.GO_TO_LOGIN;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class SearchByNameFragment extends Fragment {
    //val
    EditText editTextName,editTextFamily,editTextFather;
    String searchType = "-1";

    //var
    public List<TubelessObject> searchResponse = new ArrayList<TubelessObject>();

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View progressLayout;
    private boolean isAttached ;
    private View mainProgress;
    private LinearLayout mainLayout;


    public SearchByNameFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_search_by_name, container, false);
        super.onCreate(savedInstanceState);

//        setRootActivity((ViewGroup) ((ViewGroup) view.findViewById(android.R.id.content)).getChildAt(0));

        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextFamily = (EditText) view.findViewById(R.id.editTextFamily);
        editTextFather = (EditText) view.findViewById(R.id.editTextFather);


        ((TextView)view.findViewById(R.id.textViewSearchByNationalCode)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext().getPackageName().contains("moz")){
                    final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    getActivity().startActivity(new Intent(getContext(), SearchByNationalCodeActivity.class));
//                    getActivity().finish();
                }
            }
        });
        ((Button)view.findViewById(R.id.button_reg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.user2 == null){
                    Bundle bundle = new Bundle();
                    Toast.makeText(getContext(), "ابتدا وارد شوید", Toast.LENGTH_LONG).show();
                    Intent intent = SignInActivity.getIntent(getContext(),bundle);
                    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                    getActivity().startActivityForResult(intent, GO_TO_LOGIN);
                }else {
//                    if (Global.user2 != null && (Global.user2.isUserAdmin() || Global.user2.isUserCreator())) {
                        getContext().startActivity(new Intent(getContext(), RegNewPostActivity.class));
//                    } else {
//                        showUserRegPostDialog(getContext(), getActivity().findViewById(android.R.id.content));
//                    }
                }
            }
        });

        ((Button)view.findViewById(R.id.button_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if (editTextName.getText().toString().length() < 3 || editTextFamily.getText().toString().length() < 3 || editTextFather.getText().toString().length() < 3 ) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.data_not_true), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    try {
                        if (editTextName.getText().toString().length() < 3) {
                            throw new TubelessException(TubelessException.NAME_NOT_TRUE);
                        } else if (editTextFamily.getText().toString().length() < 3) {
                            throw new TubelessException(TubelessException.FAMILY_NOT_TRUE);
                        } else {
                            SearchRequest requestSearchRequest = new SearchRequest(
                                    editTextName.getText().toString(),
                                    editTextFamily.getText().toString(),
                                    editTextFather.getText().toString()
                                    , searchType);


                            Global.apiManagerPost.search(requestSearchRequest, new PostRetrofitCallback<Object>(getContext(), null, true, null, new Callback<Object>() {
                                @Override
                                public void onResponse(Call<Object> call, Response<Object> response) {


                                    Gson gson = new Gson();
                                    JsonElement jsonElement = gson.toJsonTree(response.body());
                                    ServerResponse responseX = gson.fromJson(jsonElement, ServerResponse.class);

                                    //jsonElement = سثقرثق

                                    if (responseX.getType().equals("NoResult")) {
                                        DialogUtil.showNotAnyResultDialog(getContext() , view,responseX);
                                    } else if (responseX.getType().equals("SearchResult")) {
                                        if (responseX.getData() != null) {
                                            if (responseX.getData().size() == 1) {
                                                DialogUtil.goToResult(getContext(), responseX);
                                            } else if (responseX.getData().size() >= 2) {
                                                DialogUtil.showManyResultDialog(getContext() ,view,responseX);
                                            } else {
                                                Toast.makeText(getActivity(), responseX.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Object> call, Throwable t) {
                                }
                            }));

                        }
                    } catch (TubelessException e) {
                        e.printStackTrace();
                        if (true)
                            TubelessException.handleClientMessage(getContext(), e.getCode());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        return view;
    }


    public SearchByNameFragment newInstance(Context context) {
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        SearchByNameFragment fragment = new SearchByNameFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }


}