package com.example.bookmanager;

import org.json.JSONArray;
import org.json.JSONObject;

public class BookFromAPI {
    private String gTitle;
    private String gAuthor;
    private String gDescription;
    private String gPreviewLink;
    private String gCategory;
    private int pageCount;
    private String gInfoLink;
    private String gpublishDate;
    private String gThumbnail;

    public BookFromAPI(String gTitle, String gAuthor, String gDescription, int pageCount, String gCategory, String gPreviewLink, String gInfoLink,String gpublishDate, String gThumbnail){
        this.gTitle=gTitle;
        this.gAuthor=gAuthor;
        this.gDescription=gDescription;
        this.gCategory=gCategory;
        this.pageCount=pageCount;
        this.gInfoLink=gInfoLink;
        this.gPreviewLink=gPreviewLink;
        this.gpublishDate=gpublishDate;
        this.gThumbnail=gThumbnail;
    }

    //getteri

    public String getgTitle(){
        return this.gTitle;
    }
    public String getgAuthor(){
        return this.gAuthor;
    }
    public String getgDescription(){
        return this.gDescription;
    }
    public String getgInfoLink(){
        return this.gInfoLink;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public String getgPreviewLink(){
        return this.gPreviewLink;
    }
    public String getgCategory(){
        return this.gCategory;
    }
public String getGpublishDate(){
        return this.gpublishDate;
}
public  String getgThumbnail(){
        return this.gThumbnail;
}
}
