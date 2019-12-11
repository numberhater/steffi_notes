package com.example.steffi;

import java.util.ArrayList;

public class Note {

    String title;


    String jotting;
    ArrayList<String> tags;

    public Note(String title, String jotting, ArrayList<String> tags) {
        this.title = title;
        this.jotting = jotting;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJotting() {
        return jotting;
    }

    public void setJotting(String jotting) {
        this.jotting = jotting;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }


}