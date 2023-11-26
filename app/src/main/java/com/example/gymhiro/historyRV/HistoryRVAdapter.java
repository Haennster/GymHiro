package com.example.gymhiro.historyRV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.Training;
import com.example.gymhiro.classes.Utilities;

import java.util.ArrayList;

public class HistoryRVAdapter extends RecyclerView.Adapter<HistoryRVAdapter.ViewHolder> {

    private final HistoryInterface historyInterface;
    Context context;
    ArrayList<Training> workouts;


    public HistoryRVAdapter(HistoryInterface historyInterface, Context context, ArrayList<Training> workouts) {
        this.historyInterface = historyInterface;
        this.context = context;
        this.workouts = workouts;
    }

    @NonNull
    @Override
    public HistoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_row,parent,false);

        return new ViewHolder(view, historyInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRVAdapter.ViewHolder holder, int position) {

        Training workout = workouts.get(position);
        Utilities utilities = new Utilities();

        holder.icon.setImageResource(R.drawable.history);
        holder.textBold.setText("TRENING - " + utilities.dateIn_Y_M_D_Format(workout.getDate()));
        holder.textSmall.setText(utilities.dateIn_Y_M_D_H_M_S_Format(workout.getDate()));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textBold;
        TextView textSmall;
        ImageView icon;

        public ViewHolder(@NonNull View itemView, HistoryInterface historyInterface) {
            super(itemView);
            textBold = itemView.findViewById(R.id.historyElementTimeFormated);
            textSmall = itemView.findViewById(R.id.historyElementTime);
            icon = itemView.findViewById(R.id.historyElementIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (historyInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            historyInterface.onItemClick(pos);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (historyInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            historyInterface.onLongItemClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
