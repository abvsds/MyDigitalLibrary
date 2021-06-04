package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditWishBooksPage extends AppCompatActivity {
 private EditText WTitleEdit, WAuthorEdit;
 private Button updateWishBook;
 private SqliteDB db;
 String titleE, authorE, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish_books_page);


        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


        WTitleEdit= findViewById(R.id.EinputTitle2id);
        WAuthorEdit=findViewById(R.id.Einputauthor2id);

        db= new SqliteDB(EditWishBooksPage.this);

        username = getIntent().getStringExtra("username");
        titleE=getIntent().getStringExtra("title");
        authorE=getIntent().getStringExtra("author");

       WTitleEdit.setText(titleE);
       WAuthorEdit.setText(authorE);
        updateWishBook=findViewById(R.id.editbookBtn2id);
        updateWishBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (WTitleEdit.getText().toString().equals(" ") || WAuthorEdit.getText().toString().equals("")){
                    Toast.makeText(EditWishBooksPage.this, " You need to complete the both fields.", Toast.LENGTH_SHORT).show();
            }
                else {
                    boolean editWBook = db.UpdateWishBook( titleE, WTitleEdit.getText().toString(), WAuthorEdit.getText().toString(), username);
                    if(editWBook==true) {
                        Toast.makeText(getBaseContext(), "The book was updated.", Toast.LENGTH_SHORT).show();
                        Runnable r = new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(EditWishBooksPage.this, ViewWishBooks.class);
                                startActivity(i);
                            }
                        };
                        Handler handler = new Handler();
                        handler.postDelayed(r, 1000);
                    }
                }

            }
        });

    }
}