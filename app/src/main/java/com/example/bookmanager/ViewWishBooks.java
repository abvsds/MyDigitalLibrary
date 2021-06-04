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


public class ViewWishBooks extends AppCompatActivity {
SqliteDB db;
RecyclerView recycleview;
CustomAdapter customadapter;
ArrayList<WishBookModal> WBooks;
TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wish_books);

        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


        String username = getIntent().getStringExtra("Username");
        message=findViewById(R.id.tv_message);
        recycleview = findViewById(R.id.listaId);
        db = new SqliteDB(this);
     WBooks = new ArrayList<>();
     WBooks=db.ViewWishBook(username);
        customadapter= new CustomAdapter(WBooks, ViewWishBooks.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewWishBooks.this,RecyclerView.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(customadapter);

        if(WBooks.size()==0){
            message.setText("You have no books in this list. Please go back to the main page to add one. ");
        }
    }
}