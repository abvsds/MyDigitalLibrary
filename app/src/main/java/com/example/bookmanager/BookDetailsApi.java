package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

public class BookDetailsApi extends AppCompatActivity {
 String title , author , publishDate=" no data", descriptionBook, previewLink, infoLink, coverBook, category;
 String pageCount;


    TextView tv_title, tv_author, tv_description, tv_publishDate,  tv_pages, tv_category;
    Button tv_infoLink, tv_previewLink;
    ImageView tv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_api);

        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable= new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


         tv_title= findViewById(R.id.titlebookapiId);
        tv_author=findViewById(R.id.authorbookapiId);
      tv_description= findViewById(R.id.descriptionbookapiId);
     tv_publishDate = findViewById(R.id.pubDateid);
        tv_category=findViewById(R.id.categoryid);
           tv_infoLink = findViewById(R.id.infoLinkId);
          tv_previewLink= findViewById(R.id.previewLinkId);
         tv_pages = findViewById(R.id.pageCountId);
          tv_cover = findViewById(R.id.bookCoverId);


        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        publishDate = getIntent().getStringExtra("publishedDate");
         descriptionBook = getIntent().getStringExtra("description");
      pageCount = getIntent().getStringExtra("pageCount");
         category= getIntent().getStringExtra("category");
         coverBook = getIntent().getStringExtra("thumbnail");
         previewLink = getIntent().getStringExtra("previewLink");
         infoLink = getIntent().getStringExtra("infoLink");



        tv_title.setText(title);
        tv_author.setText( author);
        if(descriptionBook.equals("") ||descriptionBook.isEmpty()){
            tv_description.setText( "No description");
        }
        else {
        tv_description.setText( descriptionBook);}

        if(publishDate.equals("") ||publishDate.isEmpty()){
            tv_publishDate.setVisibility(View.GONE);
        }
        else {
        tv_publishDate.setText("Published on: "+publishDate);}

        if(category.equals("") || category.isEmpty()){
            tv_category.setText( "No category");
        }
        else {
        tv_category.setText("Category: " +category);}
        if(pageCount.equals("")||pageCount.isEmpty()){
            tv_pages.setVisibility(View.GONE);
        }
        else {
            tv_pages.setText("No. of pages: " +pageCount);
        }
        Picasso.get().load(coverBook).into(tv_cover);

      tv_infoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoLink.isEmpty()){
                    Toast.makeText(BookDetailsApi.this, "No info Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(infoLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        tv_previewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewLink.isEmpty()){
                    Toast.makeText(BookDetailsApi.this, "No preview Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(previewLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

    }
}