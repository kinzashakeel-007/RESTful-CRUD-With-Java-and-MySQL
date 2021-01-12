package com.example.form;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Application.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_ROLL = "roll_no";
    public static final String COLUMN_PASWORD = "password";
    public static final String COLUMN_ROLE = "role";

    public static final String TABLE_NAME_APPLICATION = "applications";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_SUBMITBY = "submit_by";
    public static final String COLUMN_STATUS = "status";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FULLNAME + " TEXT, " + COLUMN_ROLL + " TEXT, " + COLUMN_PASWORD + " TEXT, " + COLUMN_ROLE + " TEXT)";
        db.execSQL(query);

        String applications = "CREATE TABLE " + TABLE_NAME_APPLICATION + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_MESSAGE + " TEXT, " + COLUMN_SUBMITBY + " TEXT, " + COLUMN_STATUS + " TEXT)";
        db.execSQL(applications);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_APPLICATION);
        onCreate(db);
    }

    void registerData(String fullname, String rollno, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FULLNAME, fullname);
        cv.put(COLUMN_ROLL, rollno);
        cv.put(COLUMN_PASWORD, password);
        cv.put(COLUMN_ROLE, role);

        long result = db.insert(TABLE_NAME_USERS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean loginStudent(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USERS + " WHERE " + COLUMN_ROLL + " =? and " + COLUMN_PASWORD + " =? and " + COLUMN_ROLE + " =?", new String[]{username, password, "student"});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    void addApplication(String roll, String subject, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, subject);
        cv.put(COLUMN_MESSAGE, message);
        cv.put(COLUMN_SUBMITBY, roll);
        cv.put(COLUMN_STATUS, "NotApprove");

        long result = db.insert(TABLE_NAME_APPLICATION,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_APPLICATION;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    void updateData(String row_id, String name, String roll, String date, String subject, String detail){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_NAME, name);
//        cv.put(COLUMN_ROLL, roll);
//        cv.put(COLUMN_DATE, date);
//        cv.put(COLUMN_SUBJECT, subject);
//        cv.put(COLUMN_DETAIL, detail);
//
//        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
//        }
//
//    }

//    void deleteOneRow(String row_id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
//        if(result == -1){
//            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    void deleteAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + TABLE_NAME);
//    }

}
