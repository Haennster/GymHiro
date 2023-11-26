package com.example.gymhiro.viewPager2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Serie;

import java.util.ArrayList;

public class SeriesRVAdapter extends RecyclerView.Adapter<SeriesRVAdapter.ViewHolder> {

    Context context;

    public ArrayList<Serie> serieArrayList;

    public SeriesRVAdapter(Context context, ArrayList<Serie> serieArrayList) {
        this.context = context;
        this.serieArrayList = serieArrayList;
    }



    @NonNull
    @Override
    public SeriesRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_training_view_pager_rv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesRVAdapter.ViewHolder holder, int position) {

        Serie serie = serieArrayList.get(position);


        holder.noSeries.setText("" + (position + 1) + " seria");
        holder.reps.setText(String.valueOf(serie.getNumberOfRepetitions()));
        holder.weight.setText(String.valueOf(serie.getWeight()));

        holder.reps.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    serieArrayList.get(position).setNumberOfRepetitions(Integer.parseInt(holder.reps.getText().toString()));
                }
            }
        });

        holder.weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    serieArrayList.get(position).setWeight(Integer.parseInt(holder.weight.getText().toString()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return serieArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noSeries;
        EditText reps;
        EditText weight;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noSeries = itemView.findViewById(R.id.viewPager2RVSeria);
            reps = itemView.findViewById(R.id.viewPager2RVEditReps);
            weight = itemView.findViewById(R.id.viewPager2RVEditWeight);
        }
    }

}
