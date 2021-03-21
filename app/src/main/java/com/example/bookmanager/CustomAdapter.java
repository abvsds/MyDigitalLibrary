package com.example.bookmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Mydisplay> {
    private Context context;
    SqliteDB db;
    private RecyclerView recyclerview;
  private List Wbook_title, Wbook_author;
  ArrayList<WishBookModal> WBookList;
  /*  CustomAdapter(Context context, List Wbook_title, List Wbook_author){
        this.context=context;
        this.Wbook_title=Wbook_title;
        this.Wbook_author=Wbook_author;
    }*/

    CustomAdapter(ArrayList<WishBookModal> WBookList, Context context){
        this.WBookList=WBookList;
        this.context=context;
    }

    @NonNull
    @Override
    public Mydisplay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_recycleview_lines, parent, false);
        return new Mydisplay(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Mydisplay holder, int position) {
        WishBookModal modal = WBookList.get(position);
        holder.Wbook_title_txt.setText(modal.getW_title());
        holder.Wbook_author_txt.setText(modal.getW_author());
        holder.ViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menuRecycleView = new PopupMenu(context,holder.ViewOptions);
                menuRecycleView.inflate(R.menu.menu_options_recicleview_wishbooks);
                menuRecycleView.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                            if( id == R.id.EditIdW){
                               Intent updateW= new Intent(context, EditWishBooksPage.class);

                               updateW.putExtra("title",modal.getW_title());
                                updateW.putExtra("author",modal.getW_author());
                                context.startActivity(updateW);
                            }
                            else if( id ==R.id.DeleteIdW) {

                                  AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                  alert.setTitle("Delete a book");
                                   alert.setMessage("Are you sure that you want to delete this book?");
                                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                   alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                       @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                           db = new SqliteDB(context);
                                           db.DeleteWishBook(modal.getW_title());
                                           WBookList.remove(position);
                                           notifyItemRemoved(position);
                                           Toast.makeText(context,"Your book was deleted", Toast.LENGTH_LONG).show();

                                         }
                                      });


                              alert.create().show();


                            }
                                return true;

                        }


                });
                menuRecycleView.show();                                                   }


        });
}

    @Override
    public int getItemCount() {
      return WBookList.size();
   }

    public class Mydisplay extends RecyclerView.ViewHolder {

        TextView Wbook_title_txt, Wbook_author_txt, ViewOptions;
        public Mydisplay(@NonNull View itemView) {
            super(itemView);
            Wbook_title_txt= itemView.findViewById(R.id.book_titleID);
            Wbook_author_txt= itemView.findViewById(R.id.book_authorID);
            ViewOptions = itemView.findViewById(R.id.WishBookOptions);

        }
    }
}
