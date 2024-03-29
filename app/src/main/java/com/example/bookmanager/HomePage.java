package com.example.bookmanager;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

TextView welcome_message;
TextView info_books;
SqliteDB db;
DrawerLayout drawer;
ActionBarDrawerToggle bardrawer;
Toolbar toolbar;
NavigationView nav_view;
String value_name;
Button add1, add2;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        welcome_message = (TextView) findViewById(R.id.welcomeId);
         value_name = getIntent().getStringExtra("Username");
        welcome_message.setText("Welcome "+ value_name+ ",");

        info_books=findViewById(R.id.noBooksID);
        db = new SqliteDB(this);
      info_books.setText("Now, you have "+ db.countReadBooks(value_name) +" books in BookList and " +
              db.countWishBooks(value_name) + " books in WishList. " +
              "Press the button from top-left corner in order to see your lists or another functionalities of the app. \n" +
              "If you want to add another books in the two kind of list, please use the buttons below.");

       toolbar = findViewById(R.id.toolbarId);
       setSupportActionBar(toolbar);

        drawer= (DrawerLayout)findViewById(R.id.drawerId);
        nav_view  = (NavigationView)findViewById(R.id.nav_view);


        bardrawer= new ActionBarDrawerToggle(this, drawer,toolbar, R.string.Open, R.string.Close);
        drawer.addDrawerListener(bardrawer);
        bardrawer.setDrawerIndicatorEnabled(true);
        bardrawer.syncState();


       nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                      @Override
                                                      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                          int id_option = item.getItemId();
                                                          if (id_option == R.id.logoutBtn) {
                                                              Intent i = new Intent(getApplicationContext(), LoginPage.class);
                                                              i.putExtra("finish", true);
                                                              i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                              startActivity(i);
                                                              finish();
                                                          } else if (id_option == R.id.aboutBtn) {
                                                              startActivity(new Intent(HomePage.this, AboutPage.class));
                                                          }
                                                          else if(id_option == R.id.suggestionsBtn){
                                                              Intent intent = new Intent(HomePage.this,SearchBooksPage.class);
                                                              intent.putExtra("Username", value_name);
                                                              startActivity(intent);

                                                          }
                                                          else if(id_option==R.id.wishlistBtn)
                                                          {
                                                              Intent intent = new Intent(HomePage.this,ViewWishBooks.class);
                                                              intent.putExtra("Username", value_name);
                                                              startActivity(intent);
                                                          }
                                                          else if(id_option == R.id.readbooksBtn){
                                                              Intent intent = new Intent(HomePage.this,ViewReadBooks.class);
                                                              intent.putExtra("Username", value_name);
                                                              startActivity(intent);
                                                          }
                                                          else if(id_option == R.id.statisticsBtn){
                                                              Intent intent = new Intent(HomePage.this,StatisticsPage.class);
                                                              intent.putExtra("Username", value_name);
                                                              startActivity(intent);
                                                          }
                                                          else if(id_option ==R.id.googlemapsLibraries){
                                                              Intent intent = new Intent(HomePage.this,GoogleMaps.class);
                                                              startActivity(intent);
                                                          }
                                                          else if(id_option==R.id.recomandations){
                                                              String recommandations_link="https://www.goodreads.com/shelf/show/2021";
                                                              Uri uri = Uri.parse(recommandations_link);
                                                              Intent i = new Intent(Intent.ACTION_VIEW, uri);
                                                              startActivity(i);
                                                          }
                                                          return true;
                                                      }

                                                  }
        );

nav_view.setBackgroundColor(Color.TRANSPARENT);

add1= findViewById(R.id.readId);
add2= findViewById(R.id.wishId);
add1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomePage.this, AddReadBooksPage.class);
        intent.putExtra("Username", value_name);
             startActivity(intent);
    }
});
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, AddWishBooksPage.class);
                intent.putExtra("Username", value_name);
                startActivity(intent);
            }
        });



}

      @Override
      public void onBackPressed() {
          Intent i = new Intent(getApplicationContext(), LoginPage.class);
          i.putExtra("finish", true);
          i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
          finish();
      }

    }

