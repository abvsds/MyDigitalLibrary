package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewReadBooks extends AppCompatActivity {
    SqliteDB db;
    ArrayList<String> Rbook_title, Rbook_author, description, impressions, notes, time, date;
    RecyclerView recyclerView;
    CustomAdapter1 customadapter;
    ArrayList<BookReadModal> RBooks;
    TextView message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_read_books);
        String username = getIntent().getStringExtra("Username");
        message1=findViewById(R.id.tv_message_1);
        recyclerView = findViewById(R.id.listaId1);
        db = new SqliteDB(this);

        RBooks= new ArrayList<>();
        RBooks=db.ViewReadBook(username);
        customadapter = new CustomAdapter1(RBooks, ViewReadBooks.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewReadBooks.this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customadapter);

        if(RBooks.size()==0){
            message1.setText("You have no books in this list. Please go back to the main page to add one. ");
        }

        //------------------------------------------------------------------------------------
        /*Rbook_title=new ArrayList<>();
        Rbook_author=new ArrayList<>();
        description=new ArrayList<>();
        impressions=new ArrayList<>();
        notes = new ArrayList<>();
        time=new ArrayList<>();
        date=new ArrayList<>();
        Cursor c = db.viewReadBooks(username);
        if(c.getCount()==0){
            Toast.makeText(ViewReadBooks.this, "You didn't save any book in this list.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (c.moveToNext()) {
                Rbook_title.add(c.getString(0));
                Rbook_author.add(c.getString(1));
description.add(c.getString(2));
notes.add(c.getString(3));
impressions.add(c.getString(4));
time.add(c.getString(5));
date.add(c.getString(6));
            }
            customadapter= new CustomAdapter1(ViewReadBooks.this, Rbook_title,Rbook_author,description, notes, impressions, time, date);
            recyclerView.setAdapter(customadapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(ViewReadBooks.this));
        }*/
    }
}
