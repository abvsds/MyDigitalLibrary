package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddReadBooksPage extends AppCompatActivity {
Button saveReadBook;
EditText titleReadB, authorReadB, description, notes, impressions, time_period;
SqliteDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_read_books_page);


        titleReadB = findViewById(R.id.inputTitle1id);
        authorReadB= findViewById(R.id.inputauthor1id);
        description= findViewById(R.id.inputdescription1id);
        notes= findViewById(R.id.inputnotes1id);
        impressions= findViewById(R.id.inputimpressions1id);
        time_period= findViewById(R.id.inputtime1id);
        db = new SqliteDB(this);
        saveReadBook = findViewById(R.id.savebookBtn1id);
        saveReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title= titleReadB.getText().toString();
                String author=authorReadB.getText().toString();
                String descrip = description.getText().toString();
                String note = notes.getText().toString();
                String impress = impressions.getText().toString();
                String period = time_period.getText().toString();
                String username = getIntent().getStringExtra("Username");

                boolean insertation = db.insertReadBook(title, author, descrip, note, impress, period ,username );
                if (insertation == true) {
                    Toast.makeText(AddReadBooksPage.this, " Book added successful.", Toast.LENGTH_SHORT).show();
                    titleReadB.setText("");
                    authorReadB.setText("");
                    description.setText("");
                    notes.setText("");
                    impressions.setText("");
                    time_period.setText("");
                }



            }
        });
    }
}