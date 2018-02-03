package com.app.armetech.ajudae.questions.domain;

/**
 * Created by roxac on 23/01/18.
 */

public class Question {

    private String name,
                   course,
                   title,
                   question,
                   tag;
    private long id, ownerId;

    public Question(String title, String question) {
        this.title = title;
        this.question = question;
    }

    public Question() {}


    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCourse() { return course; }

    public void setCourse(String course) { this.course = course; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getTag() {
        if(tag.isEmpty()) {
            return "EXEMPLO";
        } else {
            return tag;
        }
    }

    public void setTag(String tag) { this.tag = tag; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
