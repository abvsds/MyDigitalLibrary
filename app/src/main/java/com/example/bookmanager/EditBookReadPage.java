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

public class EditBookReadPage extends AppCompatActivity {
    private EditText RTitleEdit, RAuthorEdit, RdescEdit, RnotesEdit, RimpressionEdit, RdurationEdit;
    private Button updateReadBook;
    private SqliteDB db;
    String titleE, authorE, descE, notesE, ImpressionE, durationE, usernameE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_read_page);


        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);

        RTitleEdit=findViewById(R.id.EinputTitle1id);
        RAuthorEdit=findViewById(R.id.Einputauthor1id);
        RdescEdit=findViewById(R.id.Einputdescription1id);
        RnotesEdit=findViewById(R.id.Einputnotes1id);
        RimpressionEdit=findViewById(R.id.Einputimpressions1id);
        RdurationEdit=findViewById(R.id.Einputtime1id);


        db= new SqliteDB(EditBookReadPage.this);

        titleE=getIntent().getStringExtra("title");
        authorE=getIntent().getStringExtra("author");
        descE=getIntent().getStringExtra("description");
        notesE=getIntent().getStringExtra("notes");
        ImpressionE=getIntent().getStringExtra("impression");
        durationE=getIntent().getStringExtra("duration");
        usernameE=getIntent().getStringExtra("username");

        RTitleEdit.setText(titleE);
        RAuthorEdit.setText(authorE);
        RdescEdit.setText(descE);
        RnotesEdit.setText(notesE);
        RimpressionEdit.setText(ImpressionE);
        RdurationEdit.setText(durationE);

        updateReadBook=findViewById(R.id.editbookBtn1id);
        updateReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.UpdateReadBook(titleE,  RTitleEdit.getText().toString(), RAuthorEdit.getText().toString() , RdescEdit.getText().toString(), RnotesEdit.getText().toString() ,RimpressionEdit.getText().toString(), RdurationEdit.getText().toString() , usernameE);
                Toast.makeText(getBaseContext(),"The book was updated.",Toast.LENGTH_SHORT).show();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent (EditBookReadPage.this, ViewWishBooks.class);
                        startActivity(i);
                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(r, 1000);
            }
        });


    }
}