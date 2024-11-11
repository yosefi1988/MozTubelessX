package ir.sajjadyosefi.android.xTubeless.bussines.post.activity;

import android.app.AlertDialog;
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

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewPostActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.post.SearchRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.ServerResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.PostRetrofitCallback;
import ir.sajjadyosefi.android.xTubeless.utility.CommonClass;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.FRAGMENT_POST_SEARCH_RESULT;


public class SearchByNationalCodeActivity extends TubelessTransparentStatusBarActivity {


    //val
    EditText editTextNationalCode;
    String searchType = "-1";


    //var
    public List<TubelessObject> searchResponse = new ArrayList<TubelessObject>();
     private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View progressLayout;
    private boolean isAttached ;
    private View mainProgress;
    private LinearLayout mainLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_national_code);
        setRootActivity((ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0));

        editTextNationalCode = (EditText) findViewById(R.id.editTextNationalCode);


//        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });

        //redirect to search by name
        ((TextView)findViewById(R.id.textViewSearchByName)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().startActivity(new Intent(getContext(), SearchByNameActivity.class));
//                getActivity().finish();
            }
        });



        ((Button)findViewById(R.id.button_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if (editTextNationalCode.getText().toString().length() != 10) {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.code_melli_not_true), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {

                    try {
                        if (editTextNationalCode.getText().toString().length() != 10) {
                            throw new TubelessException(TubelessException.ERR_CODE_NATIONAL_CODE_NOT_TRUE);
                        } else if (!CommonClass.IsNumber(editTextNationalCode.getText().toString())) {
                            throw new TubelessException(TubelessException.ERR_CODE_NATIONAL_CODE_NOT_TRUE);
                        } else {
//                            SearchRequest requestSearchRequest = new SearchRequest(editTextNationalCode.getText().toString(), searchType);
//                            Global.apiManagerPost.search(requestSearchRequest, new PostRetrofitCallback<Object>(getContext(), null,true,null, new Callback<Object>() {
//                                @Override
//                                public void onResponse(Call<Object> call, Response<Object> response) {
//                                    Gson gson = new Gson();
//                                    JsonElement jsonElement = gson.toJsonTree(response.body());
//                                    ServerResponse responseX = gson.fromJson(jsonElement, ServerResponse.class);
//
//
//                                    if (responseX.getType().equals("NoResult")) {
//                                        showNotAnyResultDialog(responseX);
//                                    } else if (responseX.getType().equals("SearchResult")) {
//                                        if (responseX.getData() != null) {
//                                            if (responseX.getData().size() == 1) {
//                                                goToResult(responseX);
//                                            } else if (responseX.getData().size() >= 2) {
//                                                showManyResultDialog(responseX);
//                                            } else {
//                                                Toast.makeText(getActivity(), responseX.getMessage(), Toast.LENGTH_LONG).show();
//                                            }
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<Object> call, Throwable t) {
//                                }
//                            }));


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


    private void showNotAnyResultDialog(final ServerResponse responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        textViewStatment.setText(getContext().getString(R.string.not_found_result));

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        buttonCancel.setText(getContext().getString(R.string.close));
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        buttonOk.setText(R.string.register_new);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(), RegNewPostActivity.class));
            }
        });
        alertDialog.show();
    }

    private void showManyResultDialog(final ServerResponse responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        textViewStatment.setText(responseX.getMessage());



        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                goToResult(responseX);
            }
        });
        alertDialog.show();
    }

    private void goToResult(ServerResponse responseX) {
//        for (PostSearchResponseItem item:responseX.getData()) {
//            item.setType(POST_ITEM_TYPE);
//            searchResponse.add(item);
//        }
//        Intent intent = new Intent(getContext(), SearchResultActivity.class);
//        Bundle bundle = new Bundle();
//
//        intent.putExtra("LIST", (Serializable) searchResponse);
//        intent.putExtras(bundle);
//        startActivity(intent);
        //                                    getActivity().overridePendingTransition(R.anim.fadeout, R.anim.fadein);



        Bundle bundle = new Bundle();
        bundle.putInt("type" , FRAGMENT_POST_SEARCH_RESULT);
        bundle.putSerializable("LIST", (Serializable) responseX.getData());
        getActivity().startActivity(ContainerActivity.getIntent(getContext(),bundle));

    }

}
