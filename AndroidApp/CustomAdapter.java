package com.example.form;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList app_id, app_name, app_roll, app_subject, app_detail;

    CustomAdapter(Context context, ArrayList id, ArrayList subject, ArrayList message,
                  ArrayList status){
        this.context = context;
        this.app_id = id;
        this.app_name = subject;
        this.app_roll = status;
        this.app_subject = message;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.app_id_txt.setText(String.valueOf(app_id.get(position)));
        holder.app_name_txt.setText(String.valueOf(app_name.get(position)));
        holder.app_roll_txt.setText(String.valueOf(app_roll.get(position)));
        holder.app_subject_txt.setText(String.valueOf(app_subject.get(position)));

    }

    @Override
    public int getItemCount() {
        return app_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView app_id_txt, app_name_txt, app_roll_txt, app_subject_txt, app_detail_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            app_id_txt = itemView.findViewById(R.id.app_id_txt);
            app_name_txt = itemView.findViewById(R.id.app_name_txt);
            app_roll_txt = itemView.findViewById(R.id.app_roll_txt);
            app_subject_txt = itemView.findViewById(R.id.app_subject_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
//            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
//            mainLayout.setAnimation(translate_anim);
        }

    }

}
