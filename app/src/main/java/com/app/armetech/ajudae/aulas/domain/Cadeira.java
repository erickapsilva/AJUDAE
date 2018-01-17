package com.app.armetech.ajudae.aulas.domain;

/**
 * Created by roxac on 17/12/17.
 */

public class Cadeira {

    private String subjectName, courseClass;

    public Cadeira(String courseClass, String subjectName) {
        this.courseClass = courseClass;
        this.subjectName = subjectName;
    }

    public String getCourseClass() {
        return "TURMA: " + courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
