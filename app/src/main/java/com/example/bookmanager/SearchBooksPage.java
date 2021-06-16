package com.example.bookmanager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchBooksPage extends AppCompatActivity {
private RecyclerView recycler_v;
private RecyclerViewAdapter_api adapter;
  public ArrayList<BookFromAPI> GoogleBooks;

    private RequestQueue requestqueue;
    private static final String base_url="https://www.googleapis.com/books/v1/volumes?q=";


   TextView error_message;

    String value_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books_page);
        value_name = getIntent().getStringExtra("Username");

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);


        error_message = findViewById(R.id.message_display);

        recycler_v = findViewById(R.id.recyclerID);
        recycler_v.setHasFixedSize(true);
        recycler_v.setLayoutManager(new LinearLayoutManager(this));
        GoogleBooks = new ArrayList<>();
        requestqueue = Volley.newRequestQueue(this);

      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_bar_google_books, menu);
        MenuItem menuitem = menu.findItem(R.id.searchIcon);
        SearchView searchView = (SearchView) menuitem.getActionView();
        searchView.setQueryHint("Type some words here..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                GoogleBooks.clear();
                boolean is_connected = Read_network_state(SearchBooksPage.this);
                if(!is_connected)
                {
                    error_message.setText("Failed to load data. Please check your internet connection!");
                    recycler_v.setVisibility(View.INVISIBLE);
                    error_message.setVisibility(View.VISIBLE);
                    return true;
                }


                String final_query=query.replace(" ","+");
                Uri uri=Uri.parse(base_url+final_query);
                Uri.Builder builder = uri.buildUpon();
                parseJson(builder.toString());


                    return true;

                }


            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }






    private void parseJson(String key) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String title ="";
                      String author ="";
                      String publishedDate = "NoT Available";
                      String description = "No Description";
                     String pageCount="";
                      String categories = "No category ";
                      String thumbnail ="";
                      String previewLink="";
                      String infoLink =" ";

                       try {
                            JSONArray items = response.getJSONArray("items");

                            for (int i = 0 ; i< items.length() ;i++) {
                                JSONObject item = items.getJSONObject(i);
                                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                                try {
                                    title = volumeInfo.optString("title");
                                    JSONArray authors = volumeInfo.getJSONArray("authors");
                                    if (authors.length() == 1) {
                                        author = authors.optString(0);
                                    } else {
                                        author = authors.optString(0) + "/" + authors.getString(1);
                                    }
                                    publishedDate = volumeInfo.optString("publishedDate");
                                    pageCount = volumeInfo.optString("pageCount");
                                    description = volumeInfo.optString("description");
                                    categories = volumeInfo.getJSONArray("categories").optString(0);
                                } catch (JSONException e) {

                                }
                                thumbnail = volumeInfo.getJSONObject("imageLinks").optString("thumbnail");
                                previewLink = volumeInfo.optString("previewLink");
                                infoLink = volumeInfo.optString("infoLink");
                                GoogleBooks.add(new BookFromAPI(title, author, publishedDate, description, pageCount, categories, thumbnail, previewLink, infoLink, value_name));
                                adapter = new RecyclerViewAdapter_api(GoogleBooks, SearchBooksPage.this);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchBooksPage.this, RecyclerView.VERTICAL, false);
                                recycler_v.setLayoutManager(linearLayoutManager);
                                recycler_v.setAdapter(adapter);

                            }
                       } catch (JSONException e) {


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestqueue.add(request);
    }
    private boolean Read_network_state(Context context)
    {   boolean is_connected;
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }




}