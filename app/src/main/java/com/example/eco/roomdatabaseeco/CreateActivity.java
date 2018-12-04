package com.example.eco.roomdatabaseeco;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eco.roomdatabaseeco.Data.AppDatabase;
import com.example.eco.roomdatabaseeco.Model.PersonModel;

public class CreateActivity extends AppCompatActivity {

    private EditText et_firstname, et_middlename, et_lastname;
    private Button bt_submit;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Persondb1").build();

        //final PersonModel personModel = (PersonModel)getIntent().getSerializableExtra("data");

        et_firstname = findViewById(R.id.edt_firstname);
        et_middlename = findViewById(R.id.edt_middlename);
        et_lastname = findViewById(R.id.edt_lastname);

        bt_submit = findViewById(R.id.btn_submit);

        /*
        if(personModel!=null){

            et_firstname.setText(personModel.getFirstName());
            et_middlename.setText(personModel.getMiddleName());
            et_lastname.setText(personModel.getLastName());

            updateData(personModel);
        }
        */

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();
            }
        });
    }

    private void insert(){

        PersonModel personModel = new PersonModel();
        personModel.setFirstName(et_firstname.getText().toString());
        personModel.setMiddleName(et_middlename.getText().toString());
        personModel.setLastName(et_lastname.getText().toString());

        insertData(personModel);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void insertData(final PersonModel personModel){

        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = appDatabase.personDAO().insertPerson(personModel);
                return status;
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(CreateActivity.this, "Insert status row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
