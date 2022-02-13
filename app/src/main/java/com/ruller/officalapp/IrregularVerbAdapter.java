package com.ruller.officalapp;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IrregularVerbAdapter extends RecyclerView.Adapter<IrregularVerbAdapter.IrregularVerbViewHolder> {

    //
    private Context context;
    public ArrayList present,past,perfect_present;
    DataBaseHelper db;

    public IrregularVerbAdapter(Context context, ArrayList present, ArrayList past, ArrayList perfect_present){
        this.context = context;
        this.present = present;
        this.past = past;
        this.perfect_present = perfect_present;
        db = new DataBaseHelper(context.getApplicationContext());
        db.createDataBase();
    }

    @NonNull
    @Override
    public IrregularVerbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.irregular_verb_item, parent, false);
        return new IrregularVerbViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IrregularVerbViewHolder holder, int position) {
        String present_data = String.valueOf(present.get(position));
        String past_data = String.valueOf(past.get(position));
        String perfect_data = String.valueOf(perfect_present.get(position));

        holder.present_btn.setText(present_data + "\n \n" + past_data + "\n \n" + perfect_data);
        holder.present_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word_search = String.valueOf(present.get(position));
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
        return present.size();
    }
    //

    public class IrregularVerbViewHolder extends RecyclerView.ViewHolder{

        Button present_btn;

        public IrregularVerbViewHolder(@NonNull View itemView) {
            super(itemView);
            present_btn = itemView.findViewById(R.id.present_btn);
        }
    }
}
