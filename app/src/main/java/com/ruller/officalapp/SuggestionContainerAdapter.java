package com.ruller.officalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestionContainerAdapter extends RecyclerView.Adapter<SuggestionContainerAdapter.SuggestionContainerViewHolder> {

    private Context context;
    public ArrayList suggestion_row_list;

    public SuggestionContainerAdapter(Context context, ArrayList suggestion_row_list){
        this.context = context;
        this.suggestion_row_list = suggestion_row_list;
    }

    @NonNull
    @Override
    public SuggestionContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.suggestion_row, parent, false);
        return new SuggestionContainerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionContainerViewHolder holder, int position) {
        holder.suggestion_row.setText(String.valueOf(suggestion_row_list.get(position)));
    }

    @Override
    public int getItemCount() {
        return suggestion_row_list.size();
    }

    public class SuggestionContainerViewHolder extends RecyclerView.ViewHolder{
        TextView suggestion_row;
        public SuggestionContainerViewHolder(@NonNull View itemView) {
            super(itemView);
            suggestion_row = itemView.findViewById(R.id.suggestion_row);
        }
    }
}
