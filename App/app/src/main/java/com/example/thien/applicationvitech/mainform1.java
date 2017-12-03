package com.example.thien.applicationvitech;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;


public class mainform1 extends AppCompatActivity implements View.OnClickListener{
    //attribut du User
    final User user = new User();

    //attribut pour sex
    private RadioButton radioMale, radioFemale;

    //attribut name
    private EditText editName, editHeight, editWeight;
    //attribut date
    private TextView editDate;

    //attribut pour date
    private int mYear;
    private int mMonth;
    private int mDay;
    private TextView mDateDisplay;
    private Button mPickDate;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainform1);

        //pour le sex//
        radioMale = (RadioButton)findViewById(R.id.male);
        radioFemale = (RadioButton)findViewById(R.id.female);

        radioMale.setOnClickListener(this);
        radioFemale.setOnClickListener(this);

        //pour date//
        mDateDisplay = (TextView) findViewById(R.id.showMyDate);
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);

        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateDisplay();

        //set les attributs//

        editName = (EditText)findViewById(R.id.editName);
        String contentName = editName.getText().toString();
        user.setName(contentName);

        editDate = (TextView)findViewById(R.id.showMyDate);
        String contentDate = editDate.getText().toString();
        user.setDob(contentDate);

        editHeight = (EditText) findViewById(R.id.height);
        String contentHeight = editHeight.getText().toString();
        user.setHeight(contentHeight);

        editWeight = (EditText) findViewById(R.id.weight);
        String contentWeight = editWeight.getText().toString();
        user.setWeight(contentWeight);


        //bouton next//
        Button btn = (Button)findViewById(R.id.Next1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainform1.this, mainform2.class);
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });

    }

    //methode pour sex//
    @Override
    public void onClick(View v){
        switch (v.getId()){

            case R.id.male:
                if(radioMale.isChecked()) {
                    radioFemale.setSelected(false);
                    user.setSex("Male");

                }
                else if(radioFemale.isChecked()){
                    radioMale.setSelected(false);
                    user.setSex("Female");
                }
                break;
            case R.id.female:
                if(radioMale.isChecked()) {
                    radioFemale.setSelected(false);
                    user.setSex("Male");

                }
                else if(radioFemale.isChecked()){
                    radioMale.setSelected(false);
                    user.setSex("Female");
                }
                break;
        }

    }

    //method pour date//

    private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

}



