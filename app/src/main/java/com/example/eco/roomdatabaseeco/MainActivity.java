package com.example.eco.roomdatabaseeco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_create, bt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_create = findViewById(R.id.btn_create);
        bt_view = findViewById(R.id.btn_view);

        bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ViewAllActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
