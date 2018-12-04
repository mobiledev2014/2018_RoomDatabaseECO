package com.example.eco.roomdatabaseeco.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.eco.roomdatabaseeco.Data.AppDatabase;
import com.example.eco.roomdatabaseeco.Model.PersonModel;
import com.example.eco.roomdatabaseeco.R;
import com.example.eco.roomdatabaseeco.ViewActivity;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private ArrayList<PersonModel>allData;
    private Context context;
    private AppDatabase appDatabase;

    public PersonAdapter(ArrayList<PersonModel> personModels, Context context1){

        allData = personModels;
        context = context1;

        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "Persondb1").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_person);
            cvMain = itemView.findViewById(R.id.cv_main);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_person, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final String name = allData.get(i).getFirstName();

        viewHolder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonModel personModel = appDatabase.personDAO().selectSinglePerson(allData.get(i).getPersonId());
                context.startActivity(ViewActivity.getActivityIntent((Activity) context).putExtra("data", personModel));
            }
        });

        viewHolder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Edit or Delete");
                dialog.show();

                Button bt_edit = dialog.findViewById(R.id.btn_edit);
                Button bt_delete = dialog.findViewById(R.id.btn_delete);

                bt_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onEditPerson(i);
                        dialog.dismiss();
                    }
                });

                bt_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDeletePerson(i);
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });

        viewHolder.tvTitle.setText(name);
    }

    private void onDeletePerson(int position){
        appDatabase.personDAO().deletePerson(allData.get(position));
        allData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, allData.size());
    }

    private void onEditPerson(int position){
        context.startActivity(ViewActivity.getActivityIntent((Activity) context).putExtra("data", allData.get(position)));
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }
}
