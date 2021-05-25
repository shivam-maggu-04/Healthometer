package com.shivam.healthometer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivam.healthometer.Models.mealModel;
import com.shivam.healthometer.R;

import java.util.ArrayList;

public class mealAdapter extends RecyclerView.Adapter<mealAdapter.viewHolder> {

    private ArrayList<mealModel> list;
    private Context context;

    public mealAdapter(ArrayList<mealModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.meal,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        mealModel model = list.get(position);
        holder.setdata(model.getMealNo(),model.getMeal(),model.getMacro());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView t1,t2,t3 ;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.tvmealNo);
            t2 = itemView.findViewById(R.id.tvmeal);
            t3 = itemView.findViewById(R.id.tvmacro);

        }

        private void setdata(String mealno, String meal,String macro) {
            this.t1.setText(mealno);
            this.t2.setText(meal);
            this.t3.setText(macro);
        }
    }
}
