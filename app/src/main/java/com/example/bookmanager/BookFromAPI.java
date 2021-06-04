package com.example.bookmanager;



public class BookFromAPI {
    private final String gTitle;
   private final String gAuthor;

    private final String gDescription;
    private final String gPreviewLink;
   private final String gCategory;

    private int pageCount;
    private final String gInfoLink;
    private final String gpublishDate;
    private final String gThumbnail;
    private final String username;





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

    public String getUsername() {
        return username;
    }


public BookFromAPI(String gTitle, String gAuthor,  String gpublishDate,String gDescription,int pageCount, String gCategory, String gThumbnail,String gPreviewLink, String gInfoLink, String username){
    this.gTitle=gTitle;
    this.gAuthor=gAuthor;
    this.gpublishDate=gpublishDate;
    this.gDescription=gDescription;
    this.gCategory=gCategory;
    this.pageCount=pageCount;
    this.gInfoLink=gInfoLink;
    this.gPreviewLink=gPreviewLink;
    this.username=username;
    this.gThumbnail=gThumbnail;
}
}
