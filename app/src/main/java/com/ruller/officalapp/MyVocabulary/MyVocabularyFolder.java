package com.ruller.officalapp.MyVocabulary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;
import com.ruller.officalapp.Topic.ToeicAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyVocabularyFolder extends AppCompatActivity {

    TextView folder_title;
    ImageButton add_word_btn;



    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE = 2;
    static String FOLDER_NAME;

    static String WORD ;
    static String MEANING ;

    static String FOLDER_NAME_RESULT;
    //static String PATH = "/storage/emulated/0/Android/data/com.ruller.officalapp/files/";
    static String PATH = null;
    private RecyclerView mRecyclerView;
    ArrayList<String> myvocab_word_item_data;
    FolderAdapter folderAdapter;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myvocabulary_folder);

        PATH = this.getExternalFilesDir(null).toString();
        Log.i("test path",PATH);
        Intent intent = getIntent();
        folder_title = findViewById(R.id.folder_title);
        String folder_title_val = intent.getStringExtra(MyVocabularyContainerAdapter.FOLDER_TITLE);
        if (folder_title_val == null) folder_title_val = intent.getStringExtra(MyVocabularyFolder.FOLDER_NAME);
        folder_title.setText(folder_title_val);

        PATH = PATH + "/" + folder_title_val;


        mRecyclerView = findViewById(R.id.folder_recyclerview);
        //
        db = new DataBaseHelper(this);
        myvocab_word_item_data = new ArrayList<>();
        db.createDataBase();
        storeDataInArrays(folder_title_val);

        folderAdapter = new FolderAdapter(this, myvocab_word_item_data, folder_title_val);
        mRecyclerView.setAdapter(folderAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        FOLDER_NAME_RESULT = folder_title.getText().toString();

        add_word_btn = findViewById(R.id.add_new_word_btn);
        add_word_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyVocabularyFolder.this);
                final View add_box = getLayoutInflater().inflate(R.layout.folder_add_word, null);
                ImageView imageView = add_box.findViewById(R.id.myvocab_image);
                Button take_photo = add_box.findViewById(R.id.myvocab_take_photo);
                Button choose_photo = add_box.findViewById(R.id.myvocab_choose_photo);

                EditText word_edittext = add_box.findViewById(R.id.word_edittext);
                EditText meaning_edittext = add_box.findViewById(R.id.meaning_edittext);

                builder.setView(add_box);


                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Thêm từ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent reload  = new Intent(MyVocabularyFolder.this, MyVocabularyFolder.class);
                        String folder_name = folder_title.getText().toString();
                        reload.putExtra(FOLDER_NAME, folder_name);

                        String word_edit = word_edittext.getText().toString();

                        if (word_edit!=null&&word_edit.length()!=0){
                            Log.i("test path","oke");
                            File dir = new File(PATH);
                            File img_external = new File(dir,word_edit + ".jpg");
                            if (!img_external.exists()){
                                String meaning_edit = meaning_edittext.getText().toString();
                                db.add_word_item(word_edit,meaning_edit,folder_name);

                                imageView.setImageResource(R.drawable.black_img);

                                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                                Bitmap bitmap = drawable.getBitmap();
                                OutputStream outputStream = null;
                                try {
                                    outputStream = new FileOutputStream(img_external);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                                startActivity(reload);
                                finish();
                            }
                            else{
                                dialog.dismiss();
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                                builder1.setMessage("Thêm từ thất bại \n Từ đã tồn tại");
                                builder1.show();
                            }

                        }
                        else {
                            dialog.dismiss();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                            builder1.setMessage("Thêm từ thất bại \n Nhập từ đúng yêu cầu");
                            builder1.show();
                        }


                    }
                });


                AlertDialog alert = builder.create();
                alert.show();

                // set button take_photo
                take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WORD = word_edittext.getText().toString();
                        MEANING = meaning_edittext.getText().toString();
                        alert.dismiss();

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                });

                // set button choose_photo
                choose_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WORD = word_edittext.getText().toString();
                        MEANING = meaning_edittext.getText().toString();
                        alert.dismiss();

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                    }
                });

            }
        });

    }

    // put data to recyclerview
    void storeDataInArrays(String text) {
        Cursor cursor = db.readAllData_fromFolder(text);

        while (cursor.moveToNext()) {
            myvocab_word_item_data.add(cursor.getString(0));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.i("onactivity","oke cam");
            Log.i("camera_word",WORD);
            Log.i("camera_meaning",MEANING);

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //////////////////
            AlertDialog.Builder builder = new AlertDialog.Builder(MyVocabularyFolder.this);
            final View add_box = getLayoutInflater().inflate(R.layout.folder_add_word, null);
            ImageView imageView = add_box.findViewById(R.id.myvocab_image);
            Button take_photo = add_box.findViewById(R.id.myvocab_take_photo);
            Button choose_photo = add_box.findViewById(R.id.myvocab_choose_photo);

            EditText word_edittext = add_box.findViewById(R.id.word_edittext);
            EditText meaning_edittext = add_box.findViewById(R.id.meaning_edittext);

            builder.setView(add_box);

            word_edittext.setText(WORD);
            meaning_edittext.setText(MEANING);
            imageView.setImageBitmap(imageBitmap);

            builder.setPositiveButton("Thêm từ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String word_edit = word_edittext.getText().toString();
                    if (word_edit!=null&&word_edit.length()!=0){
                        File dir = new File(PATH);
                        File img_external = new File(dir,word_edit + ".jpg");
                        if (!img_external.exists()){
                            String meaning_edit = meaning_edittext.getText().toString();
                            db.add_word_item(word_edit,meaning_edit,FOLDER_NAME_RESULT);

                            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();
                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(img_external);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

                            Intent reload  = new Intent(MyVocabularyFolder.this, MyVocabularyFolder.class);
                            reload.putExtra(FOLDER_NAME, FOLDER_NAME_RESULT);
                            startActivity(reload);
                            finish();
                        }
                        else{
                            dialog.dismiss();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                            builder1.setMessage("Thêm từ thất bại \n Từ đã tồn tại");
                            builder1.show();
                        }
                    }
                    else {
                        dialog.dismiss();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                        builder1.setMessage("Thêm từ thất bại \n Nhập từ đúng yêu cầu");
                        builder1.show();
                    }
                }
            });

            builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

            ///////

            // set button take_photo
            take_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WORD = word_edittext.getText().toString();
                    MEANING = meaning_edittext.getText().toString();
                    alert.dismiss();

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            });

            // set button choose_photo
            choose_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WORD = word_edittext.getText().toString();
                    MEANING = meaning_edittext.getText().toString();
                    alert.dismiss();

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
            });

        }

        //////////////
        else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            Log.i("onactivity","oke picked");

            /////////////////////
            Uri selectedImageUri = data.getData();
            //////////////////
            AlertDialog.Builder builder = new AlertDialog.Builder(MyVocabularyFolder.this);
            final View add_box = getLayoutInflater().inflate(R.layout.folder_add_word, null);
            ImageView imageView = add_box.findViewById(R.id.myvocab_image);
            Button take_photo = add_box.findViewById(R.id.myvocab_take_photo);
            Button choose_photo = add_box.findViewById(R.id.myvocab_choose_photo);

            EditText word_edittext = add_box.findViewById(R.id.word_edittext);
            EditText meaning_edittext = add_box.findViewById(R.id.meaning_edittext);

            builder.setView(add_box);

            word_edittext.setText(WORD);
            meaning_edittext.setText(MEANING);
            imageView.setImageURI(selectedImageUri);

            builder.setPositiveButton("Thêm từ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String word_edit = word_edittext.getText().toString();
                    if (word_edit!=null&&word_edit.length()!=0){
                        File dir = new File(PATH);
                        File img_external = new File(dir,word_edit + ".jpg");
                        if (!img_external.exists()){
                            String meaning_edit = meaning_edittext.getText().toString();
                            db.add_word_item(word_edit,meaning_edit,FOLDER_NAME_RESULT);

                            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                            Bitmap bitmap = drawable.getBitmap();
                            OutputStream outputStream = null;
                            try {
                                outputStream = new FileOutputStream(img_external);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

                            Intent reload  = new Intent(MyVocabularyFolder.this, MyVocabularyFolder.class);
                            reload.putExtra(FOLDER_NAME, FOLDER_NAME_RESULT);
                            startActivity(reload);
                            finish();
                        }
                        else{
                            dialog.dismiss();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                            builder1.setMessage("Thêm từ thất bại \n Từ đã tồn tại");
                            builder1.show();
                        }
                    }
                    else {
                        dialog.dismiss();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MyVocabularyFolder.this);
                        builder1.setMessage("Thêm từ thất bại \n Nhập từ đúng yêu cầu");
                        builder1.show();
                    }
                }
            });

            builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

            /////

            // set button take_photo
            take_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WORD = word_edittext.getText().toString();
                    MEANING = meaning_edittext.getText().toString();
                    alert.dismiss();

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            });

            // set button choose_photo
            choose_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WORD = word_edittext.getText().toString();
                    MEANING = meaning_edittext.getText().toString();
                    alert.dismiss();

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
            });
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
