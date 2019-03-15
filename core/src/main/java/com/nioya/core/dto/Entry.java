package com.did.core.dto;

import java.util.Date;

/**
 * @author Emrah Onder on 3/15/2019
 * @project moteo
 */
public class Entry {
    private int id;
    private Date date;
    private String title;
    private String excerpt;
    private String link;

    public Entry(int id, Date date, String title, String excerpt, String link) {

        this.id = id;
        this.date = date;
        this.title = title;
        this.excerpt = excerpt;
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getLink() {
        return link;
    }


}