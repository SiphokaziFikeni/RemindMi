package com.example.sfikeni.remindmi.model;

import android.support.annotation.Nullable;

/**
 * Created by SFikeni on 2018/01/31.
 */

public class Reminder {

    private int id;
    private String title;
    private String description;
    private int priorityLevel;
    private String dateString;
    private String timeString;
    @Nullable
    private String status;

    public Reminder(){
        id = -1;
        title = "";
        description = "";
        priorityLevel = -1;
        dateString = "";
        timeString = "";
        status = "";
    }

    public Reminder(int id, String title, String description, int priorityLevel, String dateString, String timeString, @Nullable String status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.dateString = dateString;
        this.timeString = timeString;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
