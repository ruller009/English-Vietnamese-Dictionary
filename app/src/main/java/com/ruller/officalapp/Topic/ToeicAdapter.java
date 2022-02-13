package com.ruller.officalapp.Topic;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ruller.officalapp.DataBaseHelper;
import com.ruller.officalapp.IrregularVerbAdapter;
import com.ruller.officalapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ToeicAdapter extends RecyclerView.Adapter<ToeicAdapter.ToeicViewHolder> {

    private Context context;
    public ArrayList toeic_word;
    DataBaseHelper db;

    public ToeicAdapter(Context context, ArrayList toeic_word){
        this.context = context;
        this.toeic_word = toeic_word;
        db = new DataBaseHelper(context.getApplicationContext());
        db.createDataBase();
    }
    @NonNull
    @Override
    public ToeicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ielts_toeic_row, parent, false);
        return new ToeicAdapter.ToeicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToeicAdapter.ToeicViewHolder holder, int position) {
        String toeic_word_string = String.valueOf(toeic_word.get(position));
        holder.toeic_word_btn.setText(String.valueOf(toeic_word.get(position)));

        holder.toeic_word_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word_search = String.valueOf(toeic_word.get(position));
                String meaning = db.searchword(word_search);

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(context);


                builder.setTitle(word_search);
                builder.setMessage(Html.fromHtml(meaning));

                builder.show();
            }
        });

        int state = db.get_learned_TOEIC(toeic_word_string);
        if(state == 0){
            holder.learned.setVisibility(View.GONE);
        }else {
            holder.not_learned.setVisibility(View.GONE);
        }

        holder.not_learned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.not_learned.setVisibility(View.GONE);
                holder.learned.setVisibility(View.VISIBLE);
                db.updateTOEIC_learned(1,toeic_word_string);
            }
        });

        holder.learned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.not_learned.setVisibility(View.VISIBLE);
                holder.learned.setVisibility(View.GONE);
                db.updateTOEIC_learned(0,toeic_word_string);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toeic_word.size();
    }

    public class ToeicViewHolder extends RecyclerView.ViewHolder {

        Button toeic_word_btn;
        ImageButton learned, not_learned;

        public ToeicViewHolder(@NonNull View itemView) {
            super(itemView);
            toeic_word_btn = itemView.findViewById(R.id.ielts_toeic_btn);
            learned = itemView.findViewById(R.id.ielts_toeic_learned);
            not_learned = itemView.findViewById(R.id.ielts_toeic_not_learned);
        }
    }
}
