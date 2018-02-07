package com.example.sfikeni.remindmi.database.entity;

import android.support.annotation.Nullable;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by SFikeni on 2018/01/31.
 */

@RealmClass
public class ReminderEntity implements RealmModel, Reminder {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String description;
    private int priorityLevel;
    @Required
    private String dateString;
    @Required
    private String timeString;
    @Nullable
    private String status;


    public ReminderEntity(){}

    public ReminderEntity(int id, String title, String description, int priorityLevel, String dateString, String timeString, @Nullable String status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.priorityLevel = priorityLevel;
        this.dateString = dateString;
        this.timeString = timeString;
        this.status = status;
    }

    public ReminderEntity(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
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
