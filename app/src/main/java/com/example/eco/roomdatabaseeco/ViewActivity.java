package com.example.eco.roomdatabaseeco;

import android.app.Activity;
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

import java.security.cert.TrustAnchor;

public class ViewActivity extends AppCompatActivity {

    private PersonModel personModel;
    private AppDatabase appDatabase;
    private EditText et_firstname, et_middlename, et_lastname;
    private Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Persondb1").build();

        et_firstname = findViewById(R.id.edt_view_firstname);
        et_middlename = findViewById(R.id.edt_view_middlename);
        et_lastname = findViewById(R.id.edt_view_lastname);
        bt_submit = findViewById(R.id.btn_view_submit);

        et_firstname.setEnabled(false);
        et_middlename.setEnabled(false);
        et_lastname.setEnabled(false);
        bt_submit.setVisibility(View.GONE);

        personModel = (PersonModel) getIntent().getSerializableExtra("data");

        if(personModel!=null){

            et_firstname.setEnabled(true);
            et_middlename.setEnabled(true);
            et_lastname.setEnabled(true);
            bt_submit.setVisibility(View.VISIBLE);

            et_firstname.setText(personModel.getFirstName());
            et_middlename.setText(personModel.getMiddleName());
            et_lastname.setText(personModel.getLastName());

            bt_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    update();
                }
            });
        }
    }

    private void update(){

        personModel.setFirstName(et_firstname.getText().toString());
        personModel.setMiddleName(et_middlename.getText().toString());
        personModel.setLastName(et_lastname.getText().toString());

        updateData(personModel);

        Intent intent = new Intent(getApplicationContext(), ViewAllActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateData(final PersonModel personModel){

        new AsyncTask<Void, Void, Long>(){

            @Override
            protected Long doInBackground(Void... voids) {
                long status = appDatabase.personDAO().updatePerson(personModel);
                return status;
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(ViewActivity.this, "Update status row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    public static Intent getActivityIntent(Activity activity) {

        return new Intent(activity, ViewActivity.class);
    }
}
