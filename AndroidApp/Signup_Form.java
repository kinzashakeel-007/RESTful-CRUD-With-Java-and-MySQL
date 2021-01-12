package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Signup_Form extends AppCompatActivity {
    EditText fullname,rollno,password;
    Button submit;
    RadioButton applicant,coordinator;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Signup Form");

        fullname = findViewById(R.id.fullname);
        rollno = findViewById(R.id.rollno);
        password = findViewById(R.id.password);
        applicant = findViewById(R.id.applicant);
        coordinator = findViewById(R.id.coordinator);
        submit = findViewById(R.id.register_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fullname.getText().toString().isEmpty() || rollno.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                }else
                {
                    if(applicant.isChecked())
                    {
                        Toast.makeText(getApplicationContext(),"Resgistered Successfully..",Toast.LENGTH_SHORT).show();
                        MyDatabaseHelper db = new MyDatabaseHelper(Signup_Form.this);
                        role = "student";
                        db.registerData(fullname.getText().toString(),rollno.getText().toString(),password.getText().toString(),role.toString());
                        finish();
                    }
                    else if(coordinator.isChecked())
                    {
                        MyDatabaseHelper db = new MyDatabaseHelper(Signup_Form.this);
                        role = "coodinator";
                        db.registerData(fullname.getText().toString(),rollno.getText().toString(),password.getText().toString(),role.toString());
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Select Any Option",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}