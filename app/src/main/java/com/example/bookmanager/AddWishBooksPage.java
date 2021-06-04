package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWishBooksPage extends AppCompatActivity {
Button saveWishBook;
EditText titleWishB, authorWishB;
SqliteDB DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish_books_page);


        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


        titleWishB= findViewById(R.id.inputTitle2id);
        authorWishB= findViewById(R.id.inputauthor2id);
        saveWishBook = (Button) findViewById(R.id.savebookBtn2id);
        DB = new SqliteDB(this);
        saveWishBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleWishB.getText().toString();
                String author = authorWishB.getText().toString();
                String username = getIntent().getStringExtra("Username");

                if (title.equals(" ") || author.equals(""))
                    Toast.makeText(AddWishBooksPage.this, " You need to complete the both fields.", Toast.LENGTH_SHORT).show();
                else {
                    boolean insertation = DB.insertWishBook(title, author, username);
                    if (insertation == true) {
                        Toast.makeText(AddWishBooksPage.this, " Your book was added successful.", Toast.LENGTH_SHORT).show();
                        titleWishB.setText("");
                        authorWishB.setText("");
                    }

                }
            }
        });

    }
}