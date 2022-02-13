package com.ruller.officalapp.MyVocabulary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;

import java.io.File;
import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder> {

    private Context context;
    public ArrayList myvocab_word_item_data;
    public String folder_name;
    DataBaseHelper db;
    String path;

    public FolderAdapter(Context context, ArrayList myvocab_word_item_data, String folder_name){
        this.context = context;
        this.myvocab_word_item_data = myvocab_word_item_data;
        this.folder_name = folder_name;
        db = new DataBaseHelper(context.getApplicationContext());
        db.createDataBase();
        this.path = "/storage/emulated/0/Android/data/com.ruller.officalapp/files/";
    }
    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myvocab_word_item, parent, false);
        return new FolderAdapter.FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdapter.FolderViewHolder holder, int position) {
        holder.word_item_btn.setText(String.valueOf(myvocab_word_item_data.get(position)));
        String word = holder.word_item_btn.getText().toString();

        holder.word_item_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word_search = String.valueOf(myvocab_word_item_data.get(position));
                String meaning = db.readData_fromFolder(folder_name, word_search);

                String img_path = path + "/" +folder_name+"/" +word_search+".jpg";

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(context);

                //final ImageView img = new ImageView(v.getContext());

                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                final View add_box = inflater.inflate(R.layout.myvocab_imageview, null);
                ImageView imageView = add_box.findViewById(R.id.myvocab_imageview);

                Bitmap myBitmap = BitmapFactory.decodeFile(img_path);
                imageView.setImageBitmap(myBitmap);
                builder.setView(add_box);
                builder.setTitle(word_search);
                builder.setMessage(meaning);
                builder.show();
            }
        });

        holder.word_item_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete_word_item(word,folder_name);
                String ct = v.getContext().toString();
                Log.i("test",ct);

                String dir_path = path + "/" +folder_name;
                File dir = new File(dir_path);
                File img_file = new File(dir,word +".jpg");
                img_file.delete();

                Intent intent = new Intent(v.getContext(),MyVocabularyContainer.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).finish();
                ((Activity)v.getContext()).finish();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Xóa thành công");
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myvocab_word_item_data.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {

        Button word_item_btn;
        ImageButton word_item_delete_btn;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            word_item_btn = itemView.findViewById(R.id.word_item_btn);
            word_item_delete_btn = itemView.findViewById(R.id.word_item_delete);
        }
    }
}
