package com.shivam.healthometer.Models;

public class categoryname {

    String catName;
    String url;

    public categoryname() {
    }

    public categoryname(String catName, String url) {
        this.catName = catName;
        this.url = url;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
