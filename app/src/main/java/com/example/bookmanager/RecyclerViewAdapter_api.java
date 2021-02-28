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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
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
    public RecyclerViewAdapter_api.MyView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.book_item_raw_api , parent , false);
        final MyView viewHolder =  new MyView(view);
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , BookDetailsApi.class);
                int pos = viewHolder.getAdapterPosition();
                i.putExtra("title", GBooks.get(pos).getgTitle());
                     /*   i.putExtra("authors", GBooks.get(pos).getgAuthor());
                        i.putExtra("publishedDate", GBooks.get(pos).getGpublishDate());
                        i.putExtra("description", GBooks.get(pos).getgDescription());
                        i.putExtra("pageCount", GBooks.get(pos).getPageCount());
                        i.putExtra("thumbnail", GBooks.get(pos).getgThumbnail());
                        i.putExtra("previewLink", GBooks.get(pos).getgPreviewLink());
        i.putExtra("infoLink", GBooks.get(pos).getgInfoLink());*/
                     context.startActivity(i);



    }
       });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        BookFromAPI book = GBooks.get(position);
        holder.tvTitle.setText(book.getgTitle());
        holder.tvAuthor.setText(book.getgAuthor());
        holder.tvCategory.setText( book.getgCategory());


    //    Glide.with(context).load(book.getgThumbnail()).into(holder.image);
        Picasso.get().load(book.getgThumbnail()).into(holder.image);

     /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        });*/

    }

    @Override
    public int getItemCount() {
        return GBooks.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        ImageView image;
        TextView tvTitle, tvAuthor, tvCategory;
        LinearLayout card;
        //LinearLayout container;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor= itemView.findViewById(R.id.author);
            tvCategory=itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.thumbnailLink);
          //  container = itemView.findViewById(R.id.bookLine);
            card= itemView.findViewById(R.id.bookLine);

        }
    }
}
