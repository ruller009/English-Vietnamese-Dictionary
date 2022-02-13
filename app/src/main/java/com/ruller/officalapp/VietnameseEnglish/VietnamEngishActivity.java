package com.ruller.officalapp.VietnameseEnglish;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;

public class VietnamEngishActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;
    DataBaseHelper dataBaseHelper;

    private String blockCharacterSet = "~#^|$%&*!?/" + "'" + '"' + "\n";

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viet_eng_main);

        button = findViewById(R.id.viet_eng_btn);
        editText = findViewById(R.id.viet_eng_edittext);


        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.createDataBase();
        //dataBaseHelper.testSearch();
        editText.setFilters(new InputFilter[] { filter });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = editText.getText().toString();
                String meaning = dataBaseHelper.searchWordVietEng(word);

                if (meaning == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(VietnamEngishActivity.this);
                    builder.setMessage("Không tìm thấy");
                    builder.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VietnamEngishActivity.this);

                    builder.setTitle(word);
                    builder.setMessage(Html.fromHtml(meaning));

                    AlertDialog alert = builder.create();
                    alert.setCanceledOnTouchOutside(false);
                    alert.show();

                    closeKeyboard();
                    editText.clearFocus();
                }

            }
        });

    }


    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
