package com.example.form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Normalizer;

public class Login_Form extends AppCompatActivity {
    EditText username, password;
    Button login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__form);
        getSupportActionBar().setTitle("Login Form");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Username",Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();

                    if(user.equals("hod") && pass.equals("hod"))
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login_Form.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if(!user.isEmpty() && !pass.isEmpty())
                    {
                        MyDatabaseHelper db = new MyDatabaseHelper(Login_Form.this);
                        Boolean result = db.loginStudent(user,pass);
                        if(result == true)
                        {
                            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_Form.this,StudentActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login_Form.this);
                            builder.setTitle("Error");
                            builder.setMessage("Invalid Username or Password...!");

                            builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Form.this);
                        builder.setTitle("Error");
                        builder.setMessage("Invalid Username or Password...!");

                        builder.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            }
        });
    }

    public void btn_signupform(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }

}