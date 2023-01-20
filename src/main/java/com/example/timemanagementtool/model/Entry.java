package com.example.timemanagementtool.model;

import java.util.Date;

public class Entry {
    private String project;
    private String description;
    private Date date;
    private String duration;
    private String id;

    public Entry(String project, String description, Date date, String duration, String id){
        this.project = project;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
