package com.app.armetech.ajudae.aulas.domain;

/**
 * Created by roxac on 17/12/17.
 */

public class Subject {

    private String subjectName, courseClass,department;
    private int idSubject;

    public Subject(String courseClass, String subjectName) {
        this.courseClass = courseClass;
        this.subjectName = subjectName;
    }

    public String getFormattedCourseClass() {
        return "TURMA: " + courseClass;
    }

    public String getCourseClass() { return courseClass; }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setDepartment(String nomeDepartamento){
        this.department = nomeDepartamento;
    }

    public String getDepartment(){
        return this.department;
    }

    public void setIdSubject(int id){
        this.idSubject = id;
    }
    public int getIdSubject(){
        return idSubject;
    }
}

