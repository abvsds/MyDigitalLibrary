package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookDetailsApi extends AppCompatActivity {
 String title , author , publishDate, descriptionBook, previewLink, infoLink, coverBook;
 int pageCount;


    TextView tv_title, tv_author, tv_description, tv_publishDate,  tv_pages;
    Button tv_infoLink, tv_previewLink;
    ImageView tv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_api);




    /*    Bundle extras = getIntent().getExtras();
        String title="", authors="", publishDate="" , descriptionBook="", previewLink="", infoLink="", coverBook="";
        int pageCount=0;


        if (extras != null) {
            title = extras.getString("book_title");
            authors = extras.getString("book_authors");
            descriptionBook = extras.getString("book_description");
            publishDate = extras.getString("book_publishedDate");
            pageCount=extras.getInt("book_pageCount");
            infoLink = extras.getString("book_infoLink");
            previewLink = extras.getString("book_previewLink");
            coverBook= extras.getString("book_thumbnail");
        }
*/

         tv_title= findViewById(R.id.titlebookapiId);
        tv_author=findViewById(R.id.authorbookapiId);
         tv_description= findViewById(R.id.descriptionbookapiId);
          tv_publishDate = findViewById(R.id.pubDateid);
          tv_infoLink = findViewById(R.id.infoLinkId);
          tv_previewLink= findViewById(R.id.previewLinkId);
          tv_pages = findViewById(R.id.pageCountId);
          tv_cover = findViewById(R.id.bookCoverId);




     //   }

        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        publishDate = getIntent().getStringExtra("publishedDate");
        descriptionBook = getIntent().getStringExtra("description");
        pageCount = getIntent().getIntExtra("pageCount", 0);
        coverBook = getIntent().getStringExtra("thumbnail");
        previewLink = getIntent().getStringExtra("previewLink");
        infoLink = getIntent().getStringExtra("infoLink");



        tv_title.setText(title);
     tv_author.setText( author);
        tv_description.setText(descriptionBook);
        tv_publishDate.setText("Publish Date: "+ publishDate);
        tv_pages.setText(pageCount);
        Picasso.get().load(coverBook).into(tv_cover);



/*final String finalinfo=infoLink;
tv_infoLink.setOnClickListener(new View.OnClickListener() {
   @Override
    public void onClick(View v) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalinfo));
        startActivity(i);
    }
});

      final String finalprev=previewLink;
       tv_previewLink.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
          //      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalprev));
            //    startActivity(i);
            //}
        //});*/

    //    RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

   //     Glide.with(this).load(coverBook).apply(requestOptions).into(tv_cover);
     //   final String finalinfo=infoLink;
        tv_infoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(infoLink.isEmpty()){
                    //below toast message is displayed when preview link is not present.
                    Toast.makeText(BookDetailsApi.this, "No info Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if the link is present we are opening that link via an intent.
                Uri uri = Uri.parse(infoLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
       // final String finalprev=previewLink;
        tv_previewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previewLink.isEmpty()){
                    //below toast message is displayed when preview link is not present.
                    Toast.makeText(BookDetailsApi.this, "No preview Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                //if the link is present we are opening that link via an intent.
                Uri uri = Uri.parse(previewLink);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

    }
}