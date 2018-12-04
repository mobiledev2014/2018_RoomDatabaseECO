package com.example.eco.roomdatabaseeco;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.eco.roomdatabaseeco.Adapter.PersonAdapter;
import com.example.eco.roomdatabaseeco.Data.AppDatabase;
import com.example.eco.roomdatabaseeco.Model.PersonModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewAllActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private RecyclerView r_personDetails;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PersonModel> getAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        getAllData = new ArrayList<>();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Persondb1").allowMainThreadQueries().build();

        r_personDetails = findViewById(R.id.rv_persondetails);
        r_personDetails.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        r_personDetails.setLayoutManager(layoutManager);

        getAllData.addAll(Arrays.asList(appDatabase.personDAO().selectAllPersons()));
        adapter = new PersonAdapter(getAllData, this);
        r_personDetails.setAdapter(adapter);

    }

    public static Intent getViewAllActivity(Activity activity){

        return new Intent(activity, ViewAllActivity.class);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
