package com.example.bookmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.Display> {
    private Context context;
    SqliteDB db;
    ArrayList<BookReadModal> RBookList;


    CustomAdapter1(ArrayList<BookReadModal> RBookList, Context context){
        this.RBookList=RBookList;
        this.context=context;
    }
    @NonNull
    @Override
    public Display onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_recyclerview_lines_1, parent, false);
        return new Display(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Display holder, int position) {
        BookReadModal modal = RBookList.get(position);
holder.Rbook_title_txt.setText(modal.getR_title() +", "+ modal.getR_author());
        holder.description_txt.setText("\n \n Description: "+ modal.getR_desc() +"\n Notes: "+
                modal.getR_notes() +"\n Impresssion: "+modal.getR_impression()
        +"\n Time period needed to read this (Days): "+modal.getR_duration());

        holder.date_txt.setText(modal.getDate());
        holder.Options_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menuRecycleView = new PopupMenu(context,holder.Options_txt);
                menuRecycleView.inflate(R.menu.menu_options_recyclerview_booksread);
                menuRecycleView.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        if( id == R.id.EditIdW){
                            Intent updateR= new Intent(context, EditBookReadPage.class);
                            updateR.putExtra("title",modal.getR_title());
                            updateR.putExtra("author",modal.getR_author());
                            updateR.putExtra("description",modal.getR_desc());
                            updateR.putExtra("notes",modal.getR_notes());
                            updateR.putExtra("impression",modal.getR_impression());
                            updateR.putExtra("duration",modal.getR_duration());
                            updateR.putExtra("username", modal.getR_username());
                            context.startActivity(updateR);
                        }
                        else if( id ==R.id.DeleteIdW) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                            alert.setTitle("Delete a book");
                            alert.setMessage("Are you sure you want to delete the book " +"'"+modal.getR_title()+"'"+" from your booklist?");
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
                                  db.DeleteReadBook(modal.getR_title(), modal.getR_username());
                                    RBookList.remove(position);
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
    public  int getItemCount() {
        return RBookList.size();
    }

    public class Display extends RecyclerView.ViewHolder {
        TextView Rbook_title_txt, description_txt, date_txt, Options_txt;
        public Display(View itemView) {
            super(itemView);
            Rbook_title_txt= itemView.findViewById(R.id.book_titleID1);
            description_txt = itemView.findViewById(R.id.descrip);
            date_txt=itemView.findViewById(R.id.date);
            Options_txt=itemView.findViewById(R.id.ReadBookOptions);
        }
    }
}
