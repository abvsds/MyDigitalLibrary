package com.example.bookmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookDetailsApi extends AppCompatActivity {

    //TextView tv_title, tv_author, tv_description, tv_publishDate,  tv_pages;
  //  Button tv_infoLink, tv_previewLink;
//    ImageView tv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_api);


     //   String title =" ", author ="", publishDate ="", descriptionBook="", previewLink="", infoLink="", coverBook="";
    //    int pageCount =100;

        Bundle extras = getIntent().getExtras();
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


        TextView tv_title= findViewById(R.id.titlebookapiId);
       TextView tv_author=findViewById(R.id.authorbookapiId);
         TextView tv_description= findViewById(R.id.descriptionbookapiId);
         TextView tv_publishDate = findViewById(R.id.pubDateid);
         TextView tv_infoLink = findViewById(R.id.infoLinkId);
         TextView tv_previewLink= findViewById(R.id.previewLinkId);
         TextView tv_pages = findViewById(R.id.pageCountId);
         ImageView tv_cover = findViewById(R.id.bookCoverId);




     //   }



        tv_title.setText(title);
     tv_author.setText( authors);
        tv_description.setText(descriptionBook);
        tv_publishDate.setText("Publish Date: "+ publishDate);
        tv_pages.setText(pageCount);




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
        final String finalinfo=infoLink;
        tv_infoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalinfo));
                startActivity(i);
            }
        });
        final String finalprev=previewLink;
        tv_previewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalprev));
                startActivity(i);
            }
        });
        Picasso.get().load(coverBook).into(tv_cover);
    }
}