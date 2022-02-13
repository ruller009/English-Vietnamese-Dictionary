package com.ruller.officalapp.MyVocabulary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.IrregularVerbAdapter;
import com.ruller.officalapp.MainActivity;
import com.ruller.officalapp.R;
import com.ruller.officalapp.Topic.IrregularVerbActivity;

import java.io.File;
import java.util.ArrayList;

public class MyVocabularyContainerAdapter extends
        RecyclerView.Adapter<MyVocabularyContainerAdapter.MyVocabularyContainerViewHolder> {

    private Context context;
    public ArrayList folder;
    static String FOLDER_TITLE = "folder_title";
    DataBaseHelper db;
    String path;


    public MyVocabularyContainerAdapter(Context context, ArrayList folder){
        this.context = context;
        this.folder = folder;
        db = new DataBaseHelper(context.getApplicationContext());
        db.createDataBase();
        this.path = "/storage/emulated/0/Android/data/com.ruller.officalapp/files/";
    }
    @NonNull
    @Override
    public  MyVocabularyContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.folder_btn, parent, false);
        return new MyVocabularyContainerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVocabularyContainerViewHolder holder, int position) {
        holder.folder_btn.setText(String.valueOf(folder.get(position)));
        String folder_name = holder.folder_btn.getText().toString();

        holder.folder_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete_folder(folder_name);
                File dir = new File(path+"/" + folder_name);

                if (dir.exists()){
                    File[] files = dir.listFiles();
                    for(int i=0; i<files.length; i++) {
                        files[i].delete();
                    }

                    dir.delete();
                }

                Intent intent = new Intent(v.getContext(),MyVocabularyContainer.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
            }
        });

        holder.folder_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cập nhật tên thư mục");

                final EditText editTexttt = new EditText(context);
                builder.setView(editTexttt);

                builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String new_name =  editTexttt.getText().toString();
                        db.edit_folder(folder_name,new_name);

                        File old_dir = new File(path , folder_name);
                        File new_dir = new File(path , new_name);
                        old_dir.renameTo(new_dir);

                        Intent intent = new Intent(v.getContext(),MyVocabularyContainer.class);
                        v.getContext().startActivity(intent);
                        ((Activity)v.getContext()).finish();
                    }
                });

                builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return folder.size();
    }

    public class MyVocabularyContainerViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{
        Button folder_btn;
        ImageButton folder_delete_btn, folder_edit_btn;
        public MyVocabularyContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            folder_btn = itemView.findViewById(R.id.folder_btn);
            folder_delete_btn = itemView.findViewById(R.id.folder_delete);
            folder_edit_btn = itemView.findViewById(R.id.folder_edit);

            folder_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), MyVocabularyFolder.class);
            String folder_title = folder_btn.getText().toString();
            intent.putExtra(FOLDER_TITLE,folder_title);
            Log.i("Folder Title",folder_title);
            //Log.i("Class",v.getContext().toString());
            v.getContext().startActivity(intent);
        }
    }
}
