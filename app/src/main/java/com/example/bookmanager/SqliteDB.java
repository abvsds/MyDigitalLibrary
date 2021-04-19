package com.example.bookmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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


    public Boolean updatePass(String username, String password){
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

    public ArrayList<WishBookModal> ViewWishBook(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select titleWishBook, authorWishBook from WishBook where username = ?", new String[]{username});
        ArrayList<WishBookModal> WishBookList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                WishBookList.add(new WishBookModal(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        else if(cursor.getCount()==0){

        }
        cursor.close();
        return WishBookList;
    }

    public String format_date(Date date){

        String format=  new SimpleDateFormat("yyyy, month dd").format(date);
        return format;
    }
    public ArrayList<BookReadModal> ViewReadBook(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select title, author, description, notes, impressions , time_period,  date from ReadBook where username = ?", new String[]{username});
        ArrayList<BookReadModal> ReadBookList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ReadBookList.add(new BookReadModal(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6)));
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

    //editare wish book
    public void UpdateWishBook(String orig_title,  String title, String author ){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("titleWishBook", title);
        contentValues.put("authorWishBook", author);
        MyDatabase.update("WishBook", contentValues, "titleWishBook= ?",  new String[]{orig_title});

       //MyDatabase.close();
    }
    public void DeleteWishBook(String title){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("WishBook", "titleWishBook=?", new String[]{title});

    }

    public void UpdateReadBook(String orig_title,  String title, String author, String description, String notes, String impression, String duration ){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("description", description);
        contentValues.put("notes", notes);
        contentValues.put("impressions", impression);
        contentValues.put("time_period", duration);
        MyDatabase.update("ReadBook", contentValues, "title= ?",  new String[]{orig_title});

        //MyDatabase.close();
    }

    public void DeleteReadBook(String title){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("ReadBook", "title=?", new String[]{title});

    }

    public ArrayList<BarEntry> getBarEntries(String username) {
      //  String income_sum_column = "income_sum";
        SQLiteDatabase db = this.getReadableDatabase();
     //   String[] columns = new String[]{"SUM(income) AS " + income_sum_column, "income_category"};
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
//        Cursor csr = db.rawQuery("select round(1.0*100*count(date)/(select count(*) from ReadBook where username=?), 2) as percent_books_number, strftime('%m', date) as month  from ReadBook where username =? group by  month", new String[] {username});
    public ArrayList<PieEntry> getPieEntries(String username) {

        SQLiteDatabase db = this.getReadableDatabase();
        //   String[] columns = new String[]{"SUM(income) AS " + income_sum_column, "income_category"};
    Cursor csr = db.rawQuery("select round(1.0*100*count(date)/(select count(*) from ReadBook where username=?), 2) as percent_books_number, strftime('%m', date) as month  from ReadBook where username =? group by  month", new String[] {username});
        ArrayList<PieEntry> data_pie_chart = new ArrayList<>();


        while (csr.moveToFirst()) {
            String month = csr.getString(1);
            float percentage = csr.getFloat(2);
            data_pie_chart.add(new PieEntry(percentage, month));
        }

        csr.close();
        return data_pie_chart;
    }
    }
