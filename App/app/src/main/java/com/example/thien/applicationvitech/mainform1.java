package com.example.thien.applicationvitech;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.RadioButton;


public class mainform1 extends AppCompatActivity implements View.OnClickListener{

    private RadioButton radioMale, radioFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainform1);

        radioMale = (RadioButton)findViewById(R.id.male);
        radioFemale = (RadioButton)findViewById(R.id.female);

        radioMale.setOnClickListener(this);
        radioFemale.setOnClickListener(this);

    }



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.male:
                if(radioMale.isChecked()) {
                    radioFemale.setSelected(false);
                }
                else if(radioFemale.isChecked()){
                    radioMale.setSelected(false);
                }
            case R.id.female:
                if(radioFemale.isChecked()){
                    radioMale.setSelected(false);
                }
                else if(radioMale.isChecked()){
                    radioFemale.setSelected(false);
                }
        }

    }
}



