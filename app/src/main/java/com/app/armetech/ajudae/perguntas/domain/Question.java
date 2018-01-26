package com.app.armetech.ajudae.perguntas.domain;

/**
 * Created by roxac on 23/01/18.
 */

public class Question {

    private String name,
                   course,
                   title,
                   question,
                   tag;

    public Question(String name, String course, String title, String question, String tag) {
        this.name = name;
        this.course = course;
        this.title = title;
        this.question = question;
        this.tag = tag;
    }


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
}
