//package com.example.form;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.DatePickerDialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.Calendar;
//
//public class UpdateActivity extends AppCompatActivity {
//
//    EditText app_name, app_roll, app_subject, app_detail;
//    Button update, delete;
//    TextView dates,app_date;
//
//    String id, name, date , roll, subject, detail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
//
//        app_name = findViewById(R.id.name2);
//        app_roll = findViewById(R.id.roll2);
//        app_date = findViewById(R.id.date2);
//        app_subject = findViewById(R.id.subject2);
//        app_detail = findViewById(R.id.detail2);
//        update = findViewById(R.id.update);
//        delete = findViewById(R.id.delete);
//
//        //First we call this
//        getAndSetIntentData();
//
//        //Set actionbar title after getAndSetIntentData method
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setTitle(name);
//        }
//
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //And only then we call this
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                name = app_name.getText().toString().trim();
//                roll = app_roll.getText().toString().trim();
//                date = app_date.getText().toString().trim();
//                subject = app_subject.getText().toString().trim();
//                detail = app_detail.getText().toString().trim();
//                myDB.updateData(id, name, roll,date , subject, detail);
//            }
//        });
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                confirmDialog();
//            }
//        });
//
//    }
//
//    void getAndSetIntentData(){
//        if(getIntent().hasExtra("id")  && getIntent().hasExtra("name")&& getIntent().hasExtra("roll") && getIntent().hasExtra("date") &&
//                getIntent().hasExtra("subject") && getIntent().hasExtra("detail")){
//            //Getting Data from Intent
//            id = getIntent().getStringExtra("id");
//            name = getIntent().getStringExtra("name");
//            roll = getIntent().getStringExtra("roll");
//            date = getIntent().getStringExtra("date");
//            subject = getIntent().getStringExtra("subject");
//            detail = getIntent().getStringExtra("detail");
//
//            //Setting Intent Data
//            app_name.setText(name);
//            app_roll.setText(roll);
//            app_date.setText(date);
//            app_subject.setText(subject);
//            app_detail.setText(detail);
//            Log.d("stev", name+" "+roll+" "+date+" "+subject+" "+detail);
//        }else{
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void confirmDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Delete " + name + " ?");
//        builder.setMessage("Are you sure you want to delete " + name + " ?");
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                myDB.deleteOneRow(id);
//                finish();
//            }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        builder.create().show();
//    }
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
//                        dates.setText(dayOfMonth + " " + M + " " + year);
//
//                    }
//                }, mYear, mMonth, mDay);
//
//        datePickerDialog.show();
//    }
//}
