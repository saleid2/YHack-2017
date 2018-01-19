package com.example.thien.applicationvitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class quote extends AppCompatActivity {

    //User user = new User();

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        //pass User object
        //Intent i = getIntent();
        final User user = (User)getIntent().getSerializableExtra("User");

        name = (TextView)findViewById(R.id.setName);

        //User user2 = new User();
        //user2.setName("BOB");

        name.setText(user.getName());


    }
}