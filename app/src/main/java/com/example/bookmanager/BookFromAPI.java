package com.example.bookmanager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class BookFromAPI implements Serializable {
    private String gTitle;
   private String gAuthor;

    private String gDescription;
    private String gPreviewLink;
   private String gCategory;

    private int pageCount;
    private String gInfoLink;
    private String gpublishDate;
    private String gThumbnail;



    //getteri

    public String getgTitle(){
        return gTitle;
    }
    public String getgAuthor(){
        return gAuthor;
    }
    public String getgDescription(){
        return gDescription;
    }
    public String getgInfoLink(){
        return gInfoLink;
    }
    public int getPageCount(){
        return pageCount;
    }
    public String getgPreviewLink(){
        return gPreviewLink;
    }
    public String getgCategory(){
        return gCategory;
    }
public String getGpublishDate(){
        return gpublishDate;
}
public  String getgThumbnail(){
        return gThumbnail;
}

//setteri
public BookFromAPI(String gTitle, String gAuthor,  String gpublishDate,String gDescription,int pageCount, String gCategory, String gThumbnail,String gPreviewLink, String gInfoLink){
    this.gTitle=gTitle;
    this.gAuthor=gAuthor;
    this.gpublishDate=gpublishDate;
    this.gDescription=gDescription;
    this.gCategory=gCategory;
    this.pageCount=pageCount;
    this.gInfoLink=gInfoLink;
    this.gPreviewLink=gPreviewLink;

    this.gThumbnail=gThumbnail;
}
}
