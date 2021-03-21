package com.example.Kalyani_bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    protected Context context;
    protected static  final int DataBaseVersion=3;
    protected static final String dbname="db.db";
    protected static final String table_name="BankApp";
    protected static final String table_name1="Transaction_table";
    protected SQLiteDatabase  mydb;
    private SQLiteOpenHelper sqLiteOpenHelper;
    protected static final String col0="Mobile_no";
    protected static final String col1="name";
    protected static final String col2="email";
    protected static final String col3="AccNO";
    protected static final String col4="IFSC_code";
    protected static final String col5="Balance";
    String DB_path;
  //  String DB_path="src/main/assets/db";
    public DataBaseHelper( Context context) {
        super(context,dbname, null, DataBaseVersion);
            this.context = context;
            this.DB_path=context.getDatabasePath(dbname).toString();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table BankApp (Mobile_no INTEGER PRIMARY KEY  ,name Text,email Varchar, AccNO varchar Unique,IFSC_code Text,Balance Integer)");
        db.execSQL("Create table Transaction_table(Sender Text,amount Double,Receiver Text)");

        db.execSQL("Insert into BankApp values(9923243433,'Sonal Kulkarni','Sonal@gmail.com','12345','AB1234xxxxx',25000)");
        db.execSQL("Insert into BankApp values(8234355789,'Neha Kakkar','Neha@gmail.com','67890','AA1294xxxxx',60000)");
        db.execSQL("Insert into BankApp values(8994355789,'Divya Jadhav','Divya@gmail.com','98760','AA9874xxxxx',4500)");
        db.execSQL("Insert into BankApp values(8748435789,'Piju Shelke','Piju@gmail.com','11223','DA8290xxxxx',50000)");
        db.execSQL("Insert into BankApp values(9884557892,'Manali Karre','Manali@gmail.com','87890','AA1804xxxxx',15000)");
        db.execSQL("Insert into BankApp values(7894355789,'Aditya Dasare','Aditya@gmail.com','90234','CA1974xxxxx',75000)");
        db.execSQL("Insert into BankApp values(3454546789,'Viki Khanaj','Viki@gmail.com','45321','ABc294xxxxx',25000)");
        db.execSQL("Insert into BankApp values(4944535789,'Sush Sen','Sush@gmail.com','60833','AD9094xxxxx',7000)");
        db.execSQL("Insert into BankApp values(9034565689,'Raghav Juyal','Raghav@gmail.com','34500','EA87294xxxxx',48000)");
        db.execSQL("Insert into BankApp values(9534565689,'Remo Kapoor','Remo@gmail.com','47650','FE0994xxxxx',60000)");
       }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + table_name);
        db.execSQL("drop table if exists " + table_name1);
        onCreate(db);
    }
    public  Cursor  getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select  * from " + table_name,null);
        return cursor;
    }
    public  Cursor  getTransData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select  * from " + table_name1,null);
        return cursor;
    }

    public Cursor getName(String acc){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select name from Bankapp where AccNO="+acc,null);
        return cursor;
    }
    public boolean insertData(Double amt, String send, String receive){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("amount",amt);
        cv.put("Sender",send);
        cv.put("Receiver",receive);
        long res=db.insert(table_name1,null,cv);
        db.close();
        if (res >0) {
            return true;
        } else {
            return false;
        }

    }
    public void updateData(Double amt,String acc){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update BankApp set Balance=Balance+"+amt+" where AccNO="+acc);
    }
    public void DeductAmount(Double amt,String acc){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update BankApp set Balance=Balance-"+amt+" where AccNO="+acc);
    }
    public boolean checkip(String acc){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select name from Bankapp where AccNO="+acc,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean bal(Double amt,String acc){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select name from BankApp where  Balance>"+amt+" group by name having AccNO="+acc,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

