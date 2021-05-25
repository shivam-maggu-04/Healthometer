package com.shivam.healthometer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivam.healthometer.Models.categoryname;
import com.shivam.healthometer.R;
import com.shivam.healthometer.mealPlan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categorynameAdapter extends RecyclerView.Adapter<categorynameAdapter.viewHolder> {

    private ArrayList<categoryname> list;
    private Context context;

    public categorynameAdapter(ArrayList<categoryname> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dietexercisename,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        categoryname model = list.get(position);
        holder.setdata(model.getUrl(),model.getCatName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgcat);
            textView = itemView.findViewById(R.id.tvcatname);
        }

        private void setdata(String url, final String  textView)
        {
            Picasso.get().load(url).placeholder(R.color.colorPrimaryDark).into(imageView);
            this.textView.setText(textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iteams = new Intent(context, mealPlan.class);
                    iteams.putExtra("title",textView);
                    itemView.getContext().startActivity(iteams);

                }
            });

        }


    }
}



