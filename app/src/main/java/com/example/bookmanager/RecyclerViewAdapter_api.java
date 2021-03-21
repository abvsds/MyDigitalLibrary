package com.example.bookmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_api extends RecyclerView.Adapter<RecyclerViewAdapter_api.MyView> {
    private Context context;
    private List<BookFromAPI> GBooks;
  // private RequestOptions options;
    public RecyclerViewAdapter_api(Context context, List<BookFromAPI> GBooks){
        this.context=context;
        this.GBooks=GBooks;
    //   options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_raw_api,parent,false);
      //  LayoutInflater inflater = LayoutInflater.from(context);
        //view = inflater.inflate(R.layout.book_item_raw_api , parent , false);
        return new MyView(view);
        /*final MyView viewHolder =  new MyView(view);
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , BookDetailsApi.class);
                int pos = viewHolder.getAdapterPosition();
                i.putExtra("book_title", GBooks.get(pos).getgTitle());
                      i.putExtra("book_authors", GBooks.get(pos).getgAuthor());
                        i.putExtra("book_publishedDate", GBooks.get(pos).getGpublishDate());
                        i.putExtra("book_description", GBooks.get(pos).getgDescription());
                        i.putExtra("book_pageCount", GBooks.get(pos).getPageCount());
                        i.putExtra("book_thumbnail", GBooks.get(pos).getgThumbnail());
                        i.putExtra("book_previewLink", GBooks.get(pos).getgPreviewLink());
                        i.putExtra("book_infoLink", GBooks.get(pos).getgInfoLink());
                     context.startActivity(i);



    }
       });
        return viewHolder;*/

    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int pos) {
        BookFromAPI book = GBooks.get(pos);
        holder.tvTitle.setText(book.getgTitle());
        holder.tvAuthor.setText(book.getgAuthor());
        holder.tvCategory.setText( book.getgCategory());


    //    Glide.with(context).load(book.getgThumbnail()).into(holder.image);
        Picasso.get().load(book.getgThumbnail()).into(holder.image);

      holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, BookDetailsApi.class);
                i.putExtra("title", book.getgTitle());
                i.putExtra("author", book.getgAuthor());
                i.putExtra("publishedDate", book.getGpublishDate());
                i.putExtra("description", book.getgDescription());
                i.putExtra("pageCount", book.getPageCount());
                i.putExtra("thumbnail", book.getgThumbnail());
                i.putExtra("previewLink", book.getgPreviewLink());
                i.putExtra("infoLink", book.getgInfoLink());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return GBooks.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        ImageView image;
        TextView tvTitle, tvAuthor, tvCategory;
       // LinearLayout card;
        //LinearLayout container;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor= itemView.findViewById(R.id.author);
            tvCategory=itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.thumbnailLink);
          //  container = itemView.findViewById(R.id.bookLine);
         //   card= itemView.findViewById(R.id.bookLine);

        }
    }
}
