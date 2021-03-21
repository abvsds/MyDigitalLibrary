package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
 String titleE, authorE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wish_books_page);

        WTitleEdit= findViewById(R.id.EinputTitle2id);
        WAuthorEdit=findViewById(R.id.Einputauthor2id);
       // String username = getIntent().getStringExtra("Username");
        db= new SqliteDB(EditWishBooksPage.this);


        titleE=getIntent().getStringExtra("title");
        authorE=getIntent().getStringExtra("author");

        WTitleEdit.setText(titleE);
        WAuthorEdit.setText(authorE);
        updateWishBook=findViewById(R.id.editbookBtn2id);
        updateWishBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.UpdateWishBook(titleE,  WTitleEdit.getText().toString(), WAuthorEdit.getText().toString() );
                Toast.makeText(getBaseContext(),"The book was updated.",Toast.LENGTH_SHORT).show();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent (EditWishBooksPage.this, ViewWishBooks.class);
                        startActivity(i);
                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(r, 1000);



            }
        });

    }
}