package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewWishBooks extends AppCompatActivity {
SqliteDB db;
List<String> Wbook_title, Wbook_author;
RecyclerView recycleview;
CustomAdapter customadapter;
ArrayList<WishBookModal> WBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wish_books);
        String username = getIntent().getStringExtra("Username");

        recycleview = findViewById(R.id.listaId);
        db = new SqliteDB(this);
     WBooks = new ArrayList<>();
     WBooks=db.ViewWishBook(username);
        customadapter= new CustomAdapter(WBooks, ViewWishBooks.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ViewWishBooks.this,RecyclerView.VERTICAL, false);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(customadapter);


     //-------------------------------------------------------------------
        /*Wbook_title=new ArrayList<>();
        Wbook_author=new ArrayList<>();
        Cursor list = db.viewWishBooks(username);
        if(list.getCount()==0){
                Toast.makeText(ViewWishBooks.this, "You don't save any book in this list.", Toast.LENGTH_SHORT).show();
            }
            else {
            while (list.moveToNext()) {
                Wbook_title.add(list.getString(0));
                Wbook_author.add(list.getString(1));

            }
            customadapter= new CustomAdapter(ViewWishBooks.this, Wbook_title,Wbook_author);
            recycleview.setAdapter(customadapter);
            recycleview.setLayoutManager(new LinearLayoutManager(ViewWishBooks.this));
        }*/

    //---------------------------------------------------------------------------------------------------partea de sus merge
      //  ListView listview = (ListView) findViewById(R.id.listaId);
     //   db = new SqliteDB(this);

    //    ArrayList<String> mylist = new ArrayList<>();


    //    Cursor list = db.viewWishBooks(username);
   //     if(list.getCount()==0){
    //        Toast.makeText(ViewWishBooks.this, "You don't save any book in this list.", Toast.LENGTH_SHORT).show();
    //    }
    //    else{
     //       while(list.moveToNext()){
           //     mylist.add(list.getString(0));
               // mylist.addAll();

       //         ListAdapter listadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, mylist);
       //         listview.setAdapter(listadapter);


         //   }
   //     }
    }
}