package ir.sajjadyosefi.android.xTubeless.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.Amounts;


public class SubStractDialogClass extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public EditText editTextTitle, editTextValue;
    public Button buttonSave,buttonCancel;
    public static Amounts subscribeItem = null;

    public SubStractDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_substract_dialog);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextValue = (EditText) findViewById(R.id.editTextValue);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.buttonSave:
//                boolean valid = true;
//
//                if (editTextTitle.getText().toString().length() == 0){
//                    valid = false;
//                }
//
//                if (editTextValue.getText().toString().length() == 0){
//                    valid = false;
//                }
//
//                if(valid) {
//                    subscribeItem = new Amounts();
//                    subscribeItem.setText(editTextTitle.getText().toString());
//                    subscribeItem.setValue(editTextValue.getText().toString());
//                    dismiss();
//                }else {
//                    SubStractDialogClass.subscribeItem = null;
//                    Toast.makeText(getContext(),"مقادیر ورودی صحیح نیست" , Toast.LENGTH_LONG).show();
//                }
//
//                break;
//            case R.id.buttonCancel:
//                subscribeItem = null;
//                dismiss();
//                break;
//            default:
//                break;
        }
        //dismiss();
    }

}
