package com.rowaxl.dao;

import com.rowaxl.helper.DBConnection;
import com.rowaxl.models.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDao {
    public static ArrayList<Course> getAllCourses() throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet res = statement.executeQuery("SELECT * FROM Courses");

            while(res.next()) {
                courses.add(
                    new Course(
                        res.getInt("course_id"),
                        res.getString("course_name"),
                        res.getDouble("course_tuition")
                    ));
            }

            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return courses;
    }

    public static double getTuition(int courseId) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement prsm = conn.prepareStatement("SELECT course_tuition FROM Courses WHERE course_id = ?");
            prsm.setInt(1, courseId);

            ResultSet res = prsm.executeQuery();

            double tuition = 0;
            if (res.next()) {
                tuition = res.getDouble("course_tuition");
            }

            conn.close();

            return tuition;
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        }
    }
}
