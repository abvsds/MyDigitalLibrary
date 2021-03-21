package com.example.bookmanager;

public class WishBookModal {
    private String w_title;
    private String w_author;
    private Integer w_Id;
    private String w_username;

    public String getW_title(){
        return w_title;
    }
    public void setW_title(String w_title){
        this.w_title=w_title;
    }
    public String getW_author(){
        return w_author;
    }
    public void setW_author(String w_author){
        this.w_author=w_author;
    }
    public Integer getW_Id(){
        return w_Id;
    }
    public void setW_Id(Integer w_Id){
        this.w_Id=w_Id;
    }

    public String getW_username() {
        return w_username;
    }

    public void setW_username(String w_username) {
        this.w_username = w_username;
    }

    public WishBookModal(String w_title, String w_author){
        this.w_title=w_title;
        this.w_author=w_author;

    }
}
