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
    String title,  publishDate , descriptionBook, previewLink, infoLink, coverBook;
      int pageCount;
      String authors;
    TextView tv_title, tv_author, tv_description, tv_publishDate,  tv_pages;
    Button tv_infoLink, tv_previewLink;
    ImageView tv_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_api);
     //   String title =" ", author ="", publishDate ="", descriptionBook="", previewLink="", infoLink="", coverBook="";
    //    int pageCount =100;

       // Bundle extras = getIntent().getExtras();
     //   if (extras != null) {

         tv_title= findViewById(R.id.titlebookapiId);
         tv_author=findViewById(R.id.authorbookapiId);
         tv_description= findViewById(R.id.descriptionbookapiId);
         tv_publishDate = findViewById(R.id.pubDateid);
         tv_infoLink = findViewById(R.id.infoLinkId);
         tv_previewLink= findViewById(R.id.previewLinkId);
         tv_pages = findViewById(R.id.pageCountId);
         tv_cover = findViewById(R.id.bookCoverId);


           title = getIntent().getStringExtra("title");
       /*      authors = getIntent().getStringExtra("authors");
            descriptionBook = getIntent().getStringExtra("description");
             publishDate = getIntent().getStringExtra("publishedDate");
             pageCount=getIntent().getIntExtra("pageCount",0);
             infoLink = getIntent().getStringExtra("infoLink");
             previewLink = getIntent().getStringExtra("previewLink");
            coverBook= getIntent().getStringExtra("thumbnail");*/
     //   }



        tv_title.setText(title);
     /*   tv_author.setText( authors);
        tv_description.setText(descriptionBook);
        tv_publishDate.setText("Publish Date: "+ publishDate);
        tv_pages.setText(pageCount);
       Picasso.get().load(coverBook).into(tv_cover);*/



//final String finalinfo=infoLink;
//tv_infoLink.setOnClickListener(new View.OnClickListener() {
  //  @Override
    //public void onClick(View v) {
      //  Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalinfo));
        //startActivity(i);
    //}
//});

  //      final String finalprev=previewLink;
    //    tv_previewLink.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
          //      Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalprev));
            //    startActivity(i);
            //}
        //});

    //    RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

   //     Glide.with(this).load(coverBook).apply(requestOptions).into(tv_cover);
     /*   tv_infoLink.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View v) {
              Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(infoLink));
            startActivity(i);
            }
});
        tv_previewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(previewLink));
                startActivity(i);
            }
        });*/

    }
}