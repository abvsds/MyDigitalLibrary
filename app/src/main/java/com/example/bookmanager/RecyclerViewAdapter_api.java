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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class RecyclerViewAdapter_api extends RecyclerView.Adapter<RecyclerViewAdapter_api.MyView> {
    private Context context;
    private List<BookFromAPI> GBooks;
   private RequestOptions options;
    public RecyclerViewAdapter_api(Context context, List<BookFromAPI> GBooks){
        this.context=context;
        this.GBooks=GBooks;
     //   options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }
    @NonNull
    @Override
    public RecyclerViewAdapter_api.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.book_item_raw_api , parent , false);
        final MyView viewHolder =  new MyView(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , BookDetailsApi.class);
                int pos = viewHolder.getAdapterPosition();
                i.putExtra("book_title" ,GBooks.get(pos).getgTitle());
                i.putExtra("book_author" ,GBooks.get(pos).getgAuthor());
                i.putExtra("book_desc" ,GBooks.get(pos).getgDescription());
                i.putExtra("book_categories" ,GBooks.get(pos).getgCategory());
                i.putExtra("book_publish_date" ,GBooks.get(pos).getGpublishDate());
                i.putExtra("book_info" ,GBooks.get(pos).getgInfoLink());
                i.putExtra("book_pages" ,GBooks.get(pos).getPageCount());
                i.putExtra("book_preview" ,GBooks.get(pos).getgPreviewLink());
                i.putExtra("book_thumbnail" ,GBooks.get(pos).getgThumbnail());


                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_api.MyView holder, int position) {
        BookFromAPI book = GBooks.get(position);
        holder.tvTitle.setText(book.getgTitle());
        holder.tvAuthor.setText(book.getgAuthor());
        holder.tvCategory.setText(book.getgCategory());
        Glide.with(context).load(book.getgThumbnail()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return GBooks.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        ImageView image;
        TextView tvTitle, tvAuthor, tvCategory;
        LinearLayout container;
        public MyView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor= itemView.findViewById(R.id.author);
            tvCategory=itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.thumbnailLink);
            container = itemView.findViewById(R.id.bookLine);

        }
    }
}
