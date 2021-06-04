package com.example.bookmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class RecyclerViewAdapter_api extends RecyclerView.Adapter<RecyclerViewAdapter_api.MyView> {
    private Context context;
    private ArrayList<BookFromAPI> GBooks;
    SqliteDB db;
    public RecyclerViewAdapter_api( ArrayList<BookFromAPI> GBooks, Context context){
        this.context=context;
        this.GBooks=GBooks;

    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_raw_api,parent,false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int pos) {
        BookFromAPI book = GBooks.get(pos);

        holder.tvTitle.setText(book.getgTitle());
        holder.tvAuthor.setText(book.getgAuthor());
        holder.tvCategory.setText( book.getgCategory());
        holder.tvCategory.setVisibility(View.GONE);
        holder.tvpubDate.setText(book.getGpublishDate());
        holder.tvpubDate.setVisibility(View.GONE);
        holder.tvdescription.setText(book.getgDescription());
        holder.tvdescription.setVisibility(View.GONE);
        //holder.tvnoPages.setText(book.getPageCount());

        holder.tvInfolink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book.getgInfoLink().isEmpty()){
                    Toast.makeText(context, "No info Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(book.getgInfoLink());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(i);
            }
        });
     holder.tvInfolink.setVisibility(View.GONE);
        holder.tvPreviewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(book.getgPreviewLink().isEmpty()){
                    Toast.makeText(context, "No preview Link present", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri uri = Uri.parse(book.getgPreviewLink());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(i);
            }
        });
        holder.tvPreviewLink.setVisibility(View.GONE);

        Picasso.get().load(book.getgThumbnail()).into(holder.image);

      holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookDetailsApi.class);
                intent.putExtra("title", book.getgTitle());
                intent.putExtra("author", book.getgAuthor());
                intent.putExtra("publishedDate", book.getGpublishDate());
                intent.putExtra("description", book.getgDescription());
              //  intent.putExtra("pageCount", book.getPageCount());
                intent.putExtra("category", book.getgCategory());
                intent.putExtra("thumbnail", book.getgThumbnail());
             intent.putExtra("previewLink", book.getgPreviewLink());
             intent.putExtra("infoLink", book.getgInfoLink());
                context.startActivity(intent);
            }
        });
      holder.check_mark.setVisibility(View.GONE);
      holder.addtoWishlist.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              db = new SqliteDB(context);
              boolean insertation = db.insertWishBook(book.getgTitle(), book.getgAuthor(), book.getUsername());
              if (insertation == true) {
                  Toast.makeText(context, " The book was added successful in WishList", Toast.LENGTH_SHORT).show();
                 holder.addtoWishlist.setVisibility(View.GONE);
                  holder.check_mark.setVisibility(View.VISIBLE);


              }
          }
      });
    }

    @Override
    public int getItemCount() {
        return GBooks.size();
    }

    public class MyView extends RecyclerView.ViewHolder {


        ImageView image;
        TextView tvTitle, tvAuthor, tvCategory, tvpubDate, tvdescription, tvnoPages, check_mark;
                Button tvInfolink, tvPreviewLink, addtoWishlist;

     CardView card;

        public MyView(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor= itemView.findViewById(R.id.author);
            tvCategory=itemView.findViewById(R.id.category);
            tvpubDate=itemView.findViewById(R.id.pubDateidd);
            tvdescription=itemView.findViewById(R.id.descriptionbookapiIdd);
           // tvnoPages=itemView.findViewById(R.id.pageCountIdd);
            image = itemView.findViewById(R.id.thumbnailLink);
            tvInfolink=itemView.findViewById(R.id.infoLinkIdd);
            tvPreviewLink=itemView.findViewById(R.id.previewLinkIdd);
            card= itemView.findViewById(R.id.bookLine);
            addtoWishlist=itemView.findViewById(R.id.addtoWishlist);
            check_mark=itemView.findViewById(R.id.check_mark);


        }
    }
}
