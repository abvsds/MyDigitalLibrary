package com.example.bookmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    CustomAdapter1(Context context, ArrayList Rbook_title, ArrayList Rbook_author, ArrayList description, ArrayList notes, ArrayList impressions, ArrayList time, ArrayList date){
        this.context=context;
        this.Rbook_title=Rbook_title;
        this.Rbook_author=Rbook_author;
        this.description=description;
        this.notes=notes;
        this. impressions=impressions;
        this.time=time;
        this.date=date;
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
holder.Rbook_title_txt.setText(String.valueOf(Rbook_title.get(position))+", "+String.valueOf(Rbook_author.get(position)));
//holder.Rbook_author_txt.setText(String.valueOf(Rbook_author.get(position)));
        holder.description_txt.setText("Description: "+String.valueOf(description.get(position)));
        holder.notes_txt.setText("Notes: "+String.valueOf(notes.get(position)));
        holder.impressions_txt.setText("Impresssion: "+String.valueOf(impressions.get(position)));
        holder.time_txt.setText("Time: "+String.valueOf(time.get(position)));
     //   SimpleDateFormat formatter = new SimpleDateFormat("Month DD, YYYY");
     // ArrayList date1= formatter.format(Date.parse(date.toString()));
        holder.date_txt.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return Rbook_title.size();
    }

    public class Display extends RecyclerView.ViewHolder {
        TextView Rbook_title_txt, Rbook_author_txt, description_txt, notes_txt, impressions_txt, time_txt, date_txt;
        public Display(View itemView) {
            super(itemView);
            Rbook_title_txt= itemView.findViewById(R.id.book_titleID1);
          //  Rbook_author_txt= itemView.findViewById(R.id.book_authorID1);
            description_txt = itemView.findViewById(R.id.descrip);
            notes_txt = itemView.findViewById(R.id.notes);
            impressions_txt=itemView.findViewById(R.id.impression);
            time_txt=itemView.findViewById(R.id.timeperiod);
            date_txt=itemView.findViewById(R.id.date);

        }
    }
}
