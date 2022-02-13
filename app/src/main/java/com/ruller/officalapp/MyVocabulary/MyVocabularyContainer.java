package com.ruller.officalapp.MyVocabulary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;

import java.io.File;
import java.util.ArrayList;

public class MyVocabularyContainer extends AppCompatActivity {


    Button add_folder_box_btn;
    DataBaseHelper dataBaseHelper;



    private RecyclerView myvocabulary_container_recyclerview;
    ArrayList<String> folder;
    MyVocabularyContainerAdapter myVocabularyContainerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myvocabulary_container);

        dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.createDataBase();

        String path = this.getExternalFilesDir(null).toString();
        // Button add new folder
        add_folder_box_btn = findViewById(R.id.add_folder_box_btn);
        add_folder_box_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addbox = new AlertDialog.Builder(MyVocabularyContainer.this);
                addbox.setTitle("Thêm thư mục từ vựng mới");

                final EditText addfolder = new EditText(MyVocabularyContainer.this);
                addbox.setView(addfolder);




                addbox.setPositiveButton("Thêm mới", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String folder_name = addfolder.getText().toString();

                        Log.i("folder name", folder_name);
                        int exist = dataBaseHelper.check_folder_exist(folder_name);
                        File file = new File (path + "/" + folder_name);
                        if (!file.exists()) {
                            Log.i("State", "chưa tồn tại");
                            dataBaseHelper.add_folder_to_vocabulary_table(folder_name);
                            dataBaseHelper.createFolder(folder_name);

                            file.mkdirs();
                            Toast.makeText(MyVocabularyContainer.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MyVocabularyContainer.this, MyVocabularyContainer.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Log.i("State", "đã tồn tại");
                            Toast.makeText(MyVocabularyContainer.this, "Thư mục đã tồn tại", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                addbox.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = addbox.create();
                alert.setCanceledOnTouchOutside(false);
                alert.show();
            }
        });



        myvocabulary_container_recyclerview = findViewById(R.id.myvocabulary_container_recyclerview);
        folder = new ArrayList<>();
        storeData();
        myVocabularyContainerAdapter = new MyVocabularyContainerAdapter(this, folder);
        myvocabulary_container_recyclerview.setAdapter(myVocabularyContainerAdapter);
        myvocabulary_container_recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    //*******************************

    void storeData(){
        Cursor cursor = dataBaseHelper.ReadAllFolderName();

        while (cursor.moveToNext()) {
            folder.add(cursor.getString(0));
        }
    }
}
