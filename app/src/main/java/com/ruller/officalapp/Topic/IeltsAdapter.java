package com.ruller.officalapp.Topic;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.R;

import java.util.ArrayList;

public class IeltsAdapter extends RecyclerView.Adapter<IeltsAdapter.IeltsViewHolder> {

    private Context context;
    public ArrayList ielts_word;
    DataBaseHelper db;

    public IeltsAdapter(Context context, ArrayList toeic_word){
        this.context = context;
        this.ielts_word = toeic_word;
        db = new DataBaseHelper(context.getApplicationContext());
        db.createDataBase();
    }
    @NonNull
    @Override
    public IeltsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ielts_toeic_row, parent, false);
        return new IeltsAdapter.IeltsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IeltsAdapter.IeltsViewHolder holder, int position) {
        holder.ielts_word_btn.setText(String.valueOf(ielts_word.get(position)));

        holder.ielts_word_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word_search = String.valueOf(ielts_word.get(position));
                String meaning = db.searchword(word_search);

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(context);


                builder.setTitle(word_search);
                builder.setMessage(Html.fromHtml(meaning));

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ielts_word.size();
    }

    public class IeltsViewHolder extends RecyclerView.ViewHolder {

        Button ielts_word_btn;

        public IeltsViewHolder(@NonNull View itemView) {
            super(itemView);
            ielts_word_btn = itemView.findViewById(R.id.ielts_toeic_btn);
        }
    }
}
