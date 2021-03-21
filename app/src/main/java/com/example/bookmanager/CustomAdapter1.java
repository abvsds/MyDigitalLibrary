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

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.text.DateFormat.FULL;

public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.Display> {
    private Context context;
    SqliteDB db;
    private ArrayList Rbook_title, Rbook_author, description, notes, impressions, time, date;
    ArrayList<BookReadModal> RBookList;
    /*CustomAdapter1(Context context, ArrayList Rbook_title, ArrayList Rbook_author, ArrayList description, ArrayList notes, ArrayList impressions, ArrayList time, ArrayList date){
        this.context=context;
        this.Rbook_title=Rbook_title;
        this.Rbook_author=Rbook_author;
        this.description=description;
        this.notes=notes;
        this. impressions=impressions;
        this.time=time;
        this.date=date;
    }*/

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
//holder.Rbook_author_txt.setText(modal.getR_author());
        holder.description_txt.setText("Description: "+ modal.getR_desc());
        holder.notes_txt.setText("Notes: "+modal.getR_notes());
        holder.impressions_txt.setText("Impresssion: "+modal.getR_impression());
        holder.time_txt.setText("Time: "+modal.getR_duration());
     //   SimpleDateFormat formatter = new SimpleDateFormat("Month DD, YYYY");
     // ArrayList date1= formatter.format(Date.parse(date.toString()));
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
                            context.startActivity(updateR);
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
                                  db.DeleteReadBook(modal.getR_title());
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
        TextView Rbook_title_txt, Rbook_author_txt, description_txt, notes_txt, impressions_txt, time_txt, date_txt, Options_txt;
        public Display(View itemView) {
            super(itemView);
            Rbook_title_txt= itemView.findViewById(R.id.book_titleID1);
         //  Rbook_author_txt= itemView.findViewById(R.id.book_authorID1);
            description_txt = itemView.findViewById(R.id.descrip);
            notes_txt = itemView.findViewById(R.id.notes);
            impressions_txt=itemView.findViewById(R.id.impression);
            time_txt=itemView.findViewById(R.id.timeperiod);
            date_txt=itemView.findViewById(R.id.date);
            Options_txt=itemView.findViewById(R.id.ReadBookOptions);
        }
    }
}
