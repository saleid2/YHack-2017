package com.example.thien.applicationvitech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mainform2 extends AppCompatActivity {

    private EditText editAddr,editCity,editState,editEmployment,editIncome;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainform2);

        //pass User object
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");

        editAddr = (EditText)findViewById(R.id.addr);
        editCity = (EditText)findViewById(R.id.city);
        editState = (EditText)findViewById(R.id.state);
        editEmployment = (EditText)findViewById(R.id.employment);
        editIncome = (EditText)findViewById(R.id.income);

        String contenuAddr, contenuCity, contenuState, contenuEmployment, contenuIncome;

        contenuAddr = editAddr.getText().toString();
        contenuCity = editCity.getText().toString();
        contenuState = editState.getText().toString();
        contenuEmployment = editEmployment.getText().toString();
        contenuIncome = editIncome.getText().toString();

        //set attribut

        user.setAddr(contenuAddr);
        user.setCity(contenuCity);
        user.setState(contenuState);
        user.setEmploy(contenuEmployment);
        user.setIncome(contenuIncome);


        Button btn = (Button)findViewById(R.id.Next2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainform2.this, mainform3.class));
            }
        });
    }

}
