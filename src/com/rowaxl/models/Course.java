package com.rowaxl.models;

public class Course {
    private int courseId;
    private String courseName;
    private double courseTuition;

    public Course(int courseId, String courseName, double courseTuition) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTuition = courseTuition;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseTuition() {
        return courseTuition;
    }
}
