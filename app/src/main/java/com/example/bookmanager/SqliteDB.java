package com.example.bookmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SqliteDB extends SQLiteOpenHelper {
    public static final String DBNAME ="LoginUsers.db";


    public SqliteDB(Context context) {
        super(context, "LoginUsers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
MyDatabase.execSQL("create Table Users(username Text primary key, password text)");
MyDatabase.execSQL("create Table ReadBook(idBook Integer primary key autoincrement, title text, author text, description text, notes text, impressions text, time_period text, date datetime default current_date, username text, foreign key (username) references Users(username))");
MyDatabase.execSQL("create Table WishBook(idWishBook  Integer primary key autoincrement, titleWishBook  text, authorWishBook text, username text, foreign key(username) references Users(username))");
    }

    @Override
    public void onConfigure(SQLiteDatabase MyDatabase) {
        super.onConfigure(MyDatabase);
        MyDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {

            MyDatabase.execSQL("drop Table if exists Users");
            MyDatabase.execSQL("drop Table if exists ReadBook");
            MyDatabase.execSQL("drop Table if exists WishBook");

    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.insert ("Users", null, contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }
 // insert into ReadBooks

    public boolean insertReadBook(String title, String author, String description, String notes, String impressions, String period, String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("description", description);
        contentValues.put("notes", notes);
        contentValues.put("impressions", impressions);
        contentValues.put("time_period", period);
    //  contentValues.put("date", String.valueOf(date));
        contentValues.put("username", username);
        long result = MyDatabase.insert ("ReadBook", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;


        // result = MyDatabase.execSQL("insert into ReadBook (title, author, description, notes, impressions, period, date, username) values(title, author, description, notes, impressions, period, datetime('now'), username)");
    }



    // insert into WishBooks
    public boolean insertWishBook( String title, String author,String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("titleWishBook", title);
        contentValues.put("authorWishBook", author);

        long result = MyDatabase.insert ("WishBook", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }



    public boolean checkusername(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from Users where username = ?", new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("select * from Users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

 public Cursor viewWishBooks(String username){
     SQLiteDatabase MyDatabase = this.getWritableDatabase();
     Cursor wishBooks = MyDatabase.rawQuery("select titleWishBook, authorWishBook from WishBook where username =?", new String[] {username});
     return wishBooks;
 }
public String FormatData(Date date){
    SimpleDateFormat format = new SimpleDateFormat("MM DD, YY");
    String currentDate = format.format(date);
    return currentDate;
}
 public Cursor viewReadBooks(String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor wishBooks = MyDatabase.rawQuery("select title, author,description, notes, impressions, time_period,  date from ReadBook where username =?", new String[] {username});
        return wishBooks;
    }





 public void DeleteWishBook( Integer id){
     SQLiteDatabase MyDatabase = this.getWritableDatabase();
 //  Cursor w_book = MyDatabase.rawQuery("delete from WishBook where titleWishBook =?", new String[]{title});
    MyDatabase.execSQL("delete from WihBook where idWishBook="+id+"");

//return true;

 }

    public int countReadBooks(String username){
        int count=0;
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor c=MyDatabase.rawQuery("select count (*) from ReadBook where username =?", new String[] {username});
        if(c.getCount()>0) {
            c.moveToLast();
            count= c.getInt(0);
        }
        return count;
    }
    public int countWishBooks(String username){
        int count=0;
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor c=MyDatabase.rawQuery("select count (*) from WishBook where username =?", new String[] {username});
        if(c.getCount()>0) {
         while(c.moveToNext()) {
             count = c.getInt(0);
         }
        }
        return count;
    }
}
