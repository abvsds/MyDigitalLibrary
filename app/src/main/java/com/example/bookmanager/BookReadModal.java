package com.example.bookmanager;

import java.util.Date;

public class BookReadModal {
    private String r_title;
    private String r_author;
    private String r_desc;
    private String r_notes;
    private String r_impression;
    private String r_duration;
    private String date;

    public String getR_title() {
        return r_title;
    }

    public void setR_title(String r_title) {
        this.r_title = r_title;
    }

    public String getR_author() {
        return r_author;
    }

    public void setR_author(String r_author) {
        this.r_author = r_author;
    }

    public String getR_desc() {
        return r_desc;
    }

    public void setR_desc(String r_desc) {
        this.r_desc = r_desc;
    }

    public String getR_notes() {
        return r_notes;
    }

    public void setR_notes(String r_notes) {
        this.r_notes = r_notes;
    }

    public String getR_impression() {
        return r_impression;
    }

    public void setR_impression(String r_impression) {
        this.r_impression = r_impression;
    }

    public String getR_duration() {
        return r_duration;
    }

    public void setR_duration(String r_duration) {
        this.r_duration = r_duration;
    }
public String getDate(){
        return date;
}
public void setDate(String date){
        this.date=date;
}
    BookReadModal(String r_title, String r_author, String r_desc, String r_notes, String r_impression, String r_duration, String date){
        this.r_title=r_title;
        this.r_author=r_author;
        this.r_desc=r_desc;
        this.r_notes=r_notes;
        this.r_impression=r_impression;
        this.r_duration=r_duration;
        this.date=date;

    }
}
