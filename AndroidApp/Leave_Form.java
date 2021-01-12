package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Leave_Form extends AppCompatActivity {

    EditText roll, subject, detail;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave__form);

        roll = findViewById(R.id.rollno);
        subject = findViewById(R.id.subject);
        detail = findViewById(R.id.detail);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Leave_Form.this);
                myDB.addApplication(
                        roll.getText().toString().trim(),
                        subject.getText().toString().trim(),
                        detail.getText().toString().trim());
            }
        });
    }

//    public void chooseDate(View v)
//    {
//// builder.show();
//        final Calendar c = Calendar.getInstance();
//        int mYear = c.get(Calendar.YEAR);
//        int mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @SuppressLint("SetTextI18n")
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        int m = monthOfYear+1;
//                        String M = null;
//                        if(m == 1){ M = "January";}
//                        else if(m == 2){ M = "February";}
//                        else if(m == 3){ M = "March";}
//                        else if(m == 4){ M = "April";}
//                        else if(m == 5){ M = "May";}
//                        else if(m == 6){ M = "June";}
//                        else if(m == 7){ M = "July";}
//                        else if(m == 8){ M = "August";}
//                        else if(m == 9){ M = "September";}
//                        else if(m == 10){ M = "October";}
//                        else if(m == 11){ M = "November";}
//                        else if(m == 12){ M = "December";}
//
//                        date.setText(dayOfMonth + " " + M + " " + year);
//
//                    }
//                }, mYear, mMonth, mDay);
//        datePickerDialog.show();
//    }


}





