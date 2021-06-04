package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;

public class ViewReadBooks extends AppCompatActivity {
    SqliteDB db;
    RecyclerView recyclerView;
    CustomAdapter1 customadapter;
    ArrayList<BookReadModal> RBooks;
    TextView message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_read_books);

        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


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


    }
}
