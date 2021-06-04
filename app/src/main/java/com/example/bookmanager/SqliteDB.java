package com.example.bookmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.PieEntry;



import java.util.ArrayList;


public class SqliteDB extends SQLiteOpenHelper {
    public static final String DBNAME ="LoginUsers.db";


    public SqliteDB(Context context) {
        super(context, "LoginUsers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
MyDatabase.execSQL("create Table Users(username Text primary key, password text)");
MyDatabase.execSQL("create Table ReadBook(idBook Integer primary key autoincrement, title text, author text, description text, notes text, " +
        "impressions text, time_period text, date datetime default current_date, username text, foreign key (username) references Users(username))");
MyDatabase.execSQL("create Table WishBook(idWishBook  Integer primary key autoincrement, titleWishBook  text, authorWishBook text, username text, " +
        "foreign key(username) references Users(username))");
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

    public boolean insertData(String username, String password){
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


    public boolean updatePass(String username, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDatabase.update ("Users", contentValues,"username=?", new String[] {username});

        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertReadBook(String title, String author, String description, String notes, String impressions, String period, String username){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("description", description);
        contentValues.put("notes", notes);
        contentValues.put("impressions", impressions);
        contentValues.put("time_period", period);
        contentValues.put("username", username);
        long result = MyDatabase.insert ("ReadBook", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public ArrayList<WishBookModal> ViewWishBook(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select titleWishBook, authorWishBook from WishBook where username = ?", new String[]{username});
        ArrayList<WishBookModal> WishBookList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                WishBookList.add(new WishBookModal(cursor.getString(0), cursor.getString(1), username));
            } while (cursor.moveToNext());
        }
        else if(cursor.getCount()==0){

        }
        cursor.close();
        return WishBookList;
    }

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
    public void UpdateReadBook(String orig_title,  String title, String author, String description, String notes, String impression, String duration, String username ){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("description", description);
        contentValues.put("notes", notes);
        contentValues.put("impressions", impression);
        contentValues.put("time_period", duration);
        MyDatabase.update("ReadBook", contentValues, "title= ? and username=?",  new String[]{orig_title, username});
    }
    public void DeleteWishBook(String title, String username){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("WishBook", "titleWishBook=? and username=?", new String[]{title, username});

    }
    public ArrayList<BookReadModal> ViewReadBook(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select title, author, description, notes, impressions , time_period,  date from ReadBook where username = ?", new String[]{username});
        ArrayList<BookReadModal> ReadBookList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ReadBookList.add(new BookReadModal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6), username));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ReadBookList;
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

    public ArrayList<BarEntry> getBarEntries(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr = db.rawQuery("select count(date) as books_number, strftime('%m', date) as month  from ReadBook where username =? group by  month", new String[] {username});
        ArrayList<BarEntry> data_bar_chart = new ArrayList<>();
        while (csr.moveToNext()) {
            data_bar_chart.add(new BarEntry(
                    csr.getInt(csr.getColumnIndex("month")),
                    csr.getInt(csr.getColumnIndex("books_number"))
            ));
        }
        csr.close();
        return data_bar_chart;
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

    public boolean UpdateWishBook( String orig_title, String title, String author, String username ){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("titleWishBook", title);
        contentValues.put("authorWishBook", author);
        long result= MyDatabase.update("WishBook", contentValues, " titleWishBook =? and username=?",  new String[]{ orig_title, username});
        if(result==-1)
            return false;
        else
            return true;
    }




    public void DeleteReadBook(String title, String username){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("ReadBook", "title=? and username=?", new String[]{title, username});

    }



    public ArrayList<BarEntry> getBarEntries_noPages(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor csr1 = db.rawQuery("select sum(time_period) as total_days, strftime('%m', date) as month  from ReadBook where username =? group by  month", new String[] {username});
        ArrayList<BarEntry> data_bar_chart1 = new ArrayList<>();
        while (csr1.moveToNext()) {
            data_bar_chart1.add(new BarEntry(
                    csr1.getFloat(csr1.getColumnIndex("month")),
                    csr1.getFloat(csr1.getColumnIndex("total_days"))
            ));
        }
        csr1.close();
        return data_bar_chart1;
    }

    public ArrayList<PieEntry> getPieEntries(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
    Cursor csr = db.rawQuery("select round(1.0*100*count(date)/(select count(*) from ReadBook where username=?), 2) as" +
            " percent_books_number, strftime('%m', date) as month  from ReadBook where username =? group by  month", new String[] {username});
        ArrayList<PieEntry> data_pie_chart = new ArrayList<>();


        while (csr.moveToNext()) {
            String month = csr.getString(1);
            float percentage = csr.getFloat(0);
            data_pie_chart.add(new PieEntry(percentage, month));
        }

        csr.close();
        return data_pie_chart;
    }
    }
