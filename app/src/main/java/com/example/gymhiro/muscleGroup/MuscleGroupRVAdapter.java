package com.example.gymhiro.muscleGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymhiro.R;
import com.example.gymhiro.classes.MuscleGroupModel;

import java.util.ArrayList;

public class MuscleGroupRVAdapter extends RecyclerView.Adapter<MuscleGroupRVAdapter.ViewHolder> {
    private final MuscleGroupInterface muscleGroupInterface;
    Context context;
    ArrayList<MuscleGroupModel> muscleGroupModels;
    int[] images = {R.drawable.chest, R.drawable.arms, R.drawable.back,R.drawable.shoulders,R.drawable.legs,R.drawable.clear};

    public MuscleGroupRVAdapter(Context context, ArrayList<MuscleGroupModel> muscleGroupModelArray,
                                MuscleGroupInterface muscleGroupInterface){
    this.context = context;
    this.muscleGroupModels = muscleGroupModelArray;
    this.muscleGroupInterface = muscleGroupInterface;
    }

    @NonNull
    @Override
    public MuscleGroupRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.muscle_group_row,parent, false);
        return new MuscleGroupRVAdapter.ViewHolder(view, muscleGroupInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleGroupRVAdapter.ViewHolder holder, int position) {

        holder.muscleGroup.setText(muscleGroupModels.get(position).getName());

        holder.imageView.setImageResource(images[position]);


    }

    @Override
    public int getItemCount() {
        return muscleGroupModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView muscleGroup;

        public ViewHolder(@NonNull View itemView, MuscleGroupInterface muscleGroupInterface) {
            super(itemView);
            muscleGroup = itemView.findViewById(R.id.muscle_group_name);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (muscleGroupInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            muscleGroupInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
