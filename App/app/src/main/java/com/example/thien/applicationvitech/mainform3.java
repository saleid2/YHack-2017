package com.example.thien.applicationvitech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class mainform3 extends AppCompatActivity implements View.OnClickListener{

    private RadioButton tobaccoYes, tobaccoNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainform3);

        //smoker
        tobaccoYes = (RadioButton)findViewById(R.id.smokeYes);
        tobaccoNo = (RadioButton)findViewById(R.id.smokeNo);

        tobaccoYes.setOnClickListener(this);
        tobaccoNo.setOnClickListener(this);

        //button Quote
        Button btn = (Button)findViewById(R.id.Quote1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainform3.this, quote.class));
            }
        });
    }

    //smoker
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.smokeYes:
                if(tobaccoYes.isChecked()) {
                    tobaccoNo.setSelected(false);
                }
                else if(tobaccoNo.isChecked()){
                    tobaccoYes.setSelected(false);
                }
                break;
            case R.id.smokeNo:
                if(tobaccoNo.isChecked()){
                    tobaccoYes.setSelected(false);
                }
                else if(tobaccoYes.isChecked()){
                    tobaccoNo.setSelected(false);
                }
                break;
        }

    }


}
