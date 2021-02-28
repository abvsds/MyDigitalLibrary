package com.example.bookmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {
Button logout;
TextView welcome_message;
TextView info_books;
SqliteDB db;
DrawerLayout drawer;
ActionBarDrawerToggle bardrawer;
Toolbar toolbar;
NavigationView nav_view;

Button add1, add2;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        welcome_message = (TextView) findViewById(R.id.welcomeId);
        String value_name = getIntent().getStringExtra("Username");
        welcome_message.setText("Welcome "+ value_name+ ",");

        info_books=findViewById(R.id.noBooksID);
        db = new SqliteDB(this);
      info_books.setText("Now, you have "+ db.countReadBooks(value_name) +" books in Read Books List and " +
              db.countWishBooks(value_name) + " books in Wish Books List. Press the button from top-left corner in order to see your lists or if you want to see the another functionalities of the app");

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
                                                              startActivity(new Intent(HomePage.this, SearchBooksPage.class));
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
                                                          return true;
                                                      }

                                                  }
        );






    //    logout = (Button)findViewById(R.id.logoutId);
     //   logout.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
      //          Intent intent = new Intent(getApplicationContext(), LoginPage.class);
      //          intent.putExtra("finish", true);
     //           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     //           startActivity(intent);
     //           finish();
     //       }

    //    });
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
  //  @Override
  //  public boolean onCreateOptionsMenu(Menu menu){
  //      MenuInflater inflater = getMenuInflater();
   //     inflater.inflate(R.menu.menu_action_bar, menu);
   //     return true;
 //   }

  //  @Override
  //  public boolean onOptionsItemSelected(MenuItem item) {
    //    switch(item.getItemId()){
      //      case android.R.id.home:
      //          NavUtils.navigateUpFromSameTask(this);
      //          return true;

      //      case R.id.menuButton:
       //         NavigationView nav_view  = (NavigationView)findViewById(R.id.nav_view);
       ///         startActivity( new Intent(HomePage.this, NavigationView nav_view));
       //         Toast.makeText(HomePage.this, "asdfghjk ", Toast.LENGTH_SHORT).show();
        //         return true;
      //  }
 //       return bardrawer.onOptionsItemSelected(item);
   //     }

    }

