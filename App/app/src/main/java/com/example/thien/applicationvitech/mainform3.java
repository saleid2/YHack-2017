package com.example.thien.applicationvitech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class mainform3 extends AppCompatActivity implements View.OnClickListener{

    private RadioButton tobaccoYes, tobaccoNo;

    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7,
                    checkBox8, checkBox9;

    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainform3);

        //pass User object
        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");


        //checkbox value
        checkBox1 = (CheckBox)findViewById(R.id.precondition1);
        checkBox2 = (CheckBox)findViewById(R.id.precondition2);
        checkBox3 = (CheckBox)findViewById(R.id.precondition3);
        checkBox4 = (CheckBox)findViewById(R.id.precondition4);
        checkBox5 = (CheckBox)findViewById(R.id.precondition5);
        checkBox6 = (CheckBox)findViewById(R.id.precondition6);
        checkBox7 = (CheckBox)findViewById(R.id.precondition7);
        checkBox8 = (CheckBox)findViewById(R.id.precondition8);
        checkBox9 = (CheckBox)findViewById(R.id.precondition9);

        //smoker value
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

            /*
            case R.id.smokeYes:
                if(tobaccoYes.isChecked()) {
                    tobaccoNo.setSelected(false);
                    user.setTobacco("Yes");
                }
                else if(tobaccoNo.isChecked()){
                    tobaccoYes.setSelected(false);
                    user.setTobacco("No");
                }
                break;
            case R.id.smokeNo:
                if(tobaccoYes.isChecked()) {
                    tobaccoNo.setSelected(false);
                    user.setTobacco("Yes");
                }
                else if(tobaccoNo.isChecked()){
                    tobaccoYes.setSelected(false);
                    user.setTobacco("No");
                }
                break;
                */

            case R.id.precondition1:
                if(checkBox1.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Chronic viral hepatitis B without delta-agent\",\"ICD_CODE\":\"B18.1\",\"Risk_factor\":\"Medium\"}\'");}
                break;
            case R.id.precondition2:
                if(checkBox2.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Ataxic cerebral palsy\",\"ICD_CODE\":\"G80.4\",\"Risk_factor\":\"Medium\"}\'");}
                break;
            case R.id.precondition3:
                if(checkBox3.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Diarrhea, unspecified\",\"ICD_CODE\":\"R19.7\",\"Risk_factor\":\"Low\"}\'");}
                break;
            case R.id.precondition4:
                if (checkBox4.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Tachycardia, unspecified\",\"ICD_CODE\":\"R00.0\",\"Risk_factor\":\"Low\"}\'");}
                break;
            case R.id.precondition5:
                if(checkBox5.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Unspecified fracture of specified metacarpal bone with unspecified laterality\",\"ICD_CODE\":\"S62.308\",\"Risk_factor\":\"Low\"}\'");}
                break;
            case R.id.precondition6:
                if(checkBox6.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Other abnormalities of heart beat\",\"ICD_CODE\":\"R00.8\",\"Risk_factor\":\"Low\"}\'");}
                break;
            case R.id.precondition7:
                if(checkBox7.isChecked()){user.setPreconditions("\'{\"condition_name\":\"Type 2 diabetes mellitus with hyperglycemia\",\"ICD_CODE\":\"E11.65\",\"Risk_factor\":\"Medium\"}\'");}
                break;
            case R.id.precondition8:
                if(checkBox8.isChecked()){user.setPreconditions("\'{\"condition_name\":\"HIV disease resulting in other bacterial infections\",\"ICD_CODE\":\"B20.1\",\"Risk_factor\":\"High\"}\'");}
                break;
            case R.id.precondition9:
                if(checkBox9.isChecked()){user.setPreconditions("\'{\"condition_name\":\"cough with hemorrhage\",\"ICD_CODE\":\"R04.2\",\"Risk_factor\":\"Low\"}\'");}
        }
    }

}
