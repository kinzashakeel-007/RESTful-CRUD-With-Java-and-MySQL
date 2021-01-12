package com.example.form;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    FloatingActionButton addApplication;
    RecyclerView recyclerView;
    MyDatabaseHelper db;
    ArrayList<String> id, subject,message,status;
    CustomAdapter customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        addApplication = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.studentRecyclerView);

        addApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this,Leave_Form.class);
                startActivity(intent);
            }
        });

        db = new MyDatabaseHelper(StudentActivity.this);
        id =new ArrayList<>();
        subject =new ArrayList<>();
        message =new ArrayList<>();
        status =new ArrayList<>();

        displayData();
        customAdaptor = new CustomAdapter(StudentActivity.this,id,subject,message,status);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));
    }

    void displayData()
    {
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(getApplicationContext(),"No Records", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                id.add(cursor.getString(0));
                subject.add(cursor.getString(1));
                message.add(cursor.getString(2));
                status.add(cursor.getString(4));
            }

        }
    }
}