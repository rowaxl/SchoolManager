package com.rowaxl.printer;

import com.rowaxl.dao.CourseDao;
import com.rowaxl.models.Course;

import java.sql.SQLException;
import java.util.ArrayList;

public class CoursePrinter {
    public static void printAllCources() {
        try {
            ArrayList<Course> courses = CourseDao.getAllCourses();
            System.out.printf("%-10s %10s %20s \n", "Id", "Name", "Tuition");
            for (Course course: courses) {
                System.out.printf("%-10s %10s %20s \n",
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseTuition());
            }
            System.out.println("");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
