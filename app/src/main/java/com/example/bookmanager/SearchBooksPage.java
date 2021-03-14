package com.example.bookmanager;

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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

public class SearchBooksPage extends AppCompatActivity {
private RecyclerView recycler_v;
private RecyclerViewAdapter_api adapter;
  public ArrayList<BookFromAPI> GoogleBooks;
    private RequestQueue requestqueue;
    private static final String base_url="https://www.googleapis.com/books/v1/volumes?q=";
   // private SearchView searchBar;
    EditText search_text;
    Button searchbtn;
  //  ProgressBar loading;
   TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books_page);
    //    searchBar=findViewById(R.id.searchbarID);
        search_text=findViewById(R.id.search_box);
        searchbtn=findViewById(R.id.searchBTN);
      //  loading=findViewById(R.id.loading_indicator);
       error_message= findViewById(R.id.message_display);

      recycler_v=findViewById(R.id.recyclerID);
      recycler_v.setHasFixedSize(true);
        recycler_v.setLayoutManager(new LinearLayoutManager(this));
        GoogleBooks=new ArrayList<>();
        requestqueue= Volley.newRequestQueue(this);

        searchbtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               GoogleBooks.clear();
                search();
            }
        });


    }
    private void search(){
     // String search_word= searchBar.getTooltipText().toString();
      String search_word = search_text.getText().toString();

       boolean is_connected = Read_network_state(this);
        if(!is_connected)
        {
            error_message.setText("Failed to Load data. Please check your internet connection!");
            recycler_v.setVisibility(View.INVISIBLE);
            error_message.setVisibility(View.VISIBLE);
            return;
        }

        //  Log.d("QUERY",search_query);


        if(search_word.equals(""))
        {
           Toast.makeText(this,"Please enter your query",Toast.LENGTH_SHORT).show();
            return;
        }
        String final_query=search_word.replace(" ","+");
        Uri uri=Uri.parse(base_url+final_query);
        Uri.Builder buider = uri.buildUpon();

        parseJson(buider.toString());
    }

    private void parseJson(String key) {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                     //  loading.setVisibility(View.GONE);
                        String title ="";
                      String author ="";
                      String publishedDate = "NoT Available";
                      String description = "No Description";
                        int pageCount = 100;
                      String categories = "No category ";
                      String thumbnail ="http://books.google.com/books/content?id=SM7CDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";
                      String previewLink="";
                      String infoLink =" ";


                        try {
                            JSONArray items = response.getJSONArray("items");

                            for (int i = 0 ; i< items.length() ;i++){
                                JSONObject item = items.getJSONObject(i);
                                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
try {
    title = volumeInfo.getString("title");

    JSONArray authors = volumeInfo.getJSONArray("authors");

    if (authors.length() == 1) {
        author = authors.getString(0);
    } else {
        author = authors.getString(0) + "/" + authors.getString(1);
    }


    publishedDate = volumeInfo.getString("publishedDate");
    pageCount = volumeInfo.getInt("pageCount");



                                  //  JSONObject saleInfo = item.getJSONObject("saleInfo");
                                    description = volumeInfo.getString("description");
                                   // categories = volumeInfo.getString("categories");
                                  categories = volumeInfo.getJSONArray("categories").getString(0);
                            }catch (JSONException e){
   // Toast.makeText(SearchBooksPage.this, "error:  "+ e, Toast.LENGTH_SHORT).show();
                            }


             thumbnail = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");

    //   thumbnail=volumeInfo.optString("thumbnail");
    previewLink = volumeInfo.getString("previewLink");
    infoLink = volumeInfo.getString("infoLink");






                                GoogleBooks.add(new BookFromAPI(title , author ,  publishedDate,description, pageCount, categories, thumbnail,previewLink, infoLink));


                                adapter = new RecyclerViewAdapter_api(SearchBooksPage.this , GoogleBooks);
                             recycler_v.setAdapter(adapter);
                            }


                        } catch (JSONException e) {
                            //e.printStackTrace();
                          //  Log.e("TAG" , e.toString());
                           // Toast.makeText(SearchBooksPage.this, "error:  "+ e, Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //error.printStackTrace();
            }
        });
        requestqueue.add(request);
    }
    private boolean Read_network_state(Context context)
    {    boolean is_connected;
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }


}