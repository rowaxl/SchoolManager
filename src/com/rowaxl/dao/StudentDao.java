package com.rowaxl.dao;

import com.rowaxl.helper.DBConnection;
import com.rowaxl.models.Course;
import com.rowaxl.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDao {
    public static String insertQuery =
            "INSERT INTO Students (email, first_name, last_name, birth_date, cell_phone) VALUES (?, ?, ?, ?, ?)";

    public static String enrollQuery =
            "UPDATE Students SET course_id = ?, balance = ? WHERE email = ?";

    public static String balanceQuery =
            "UPDATE Student SET balance = ? WHERE email = ?";

    public static ArrayList<Student> getAllStudents() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet res = stmt.executeQuery("SELECT * FROM Students");

            while(res.next()) {
                students.add(
                    new Student(
                        res.getString("email"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getDate("birth_date"),
                        res.getString("cell_phone"),
                        res.getDouble("balance")
                    ));
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return students;
    }

    public static void addStudent(Student student) {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery);

            stmt.setString(1, student.getEmail());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setDate(4, student.getBirthDate());
            stmt.setString(5, student.getCellPhone());

            stmt.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void enrollStudentToCourse(String email, int courseId, double payment) {
        try {
            double tuition = CourseDao.getTuition(courseId);

            Connection conn = DBConnection.getConnection();
            PreparedStatement prsm = conn.prepareStatement(enrollQuery);

            prsm.setInt(1, courseId);
            prsm.setDouble(2, tuition - payment);
            prsm.setString(3, email);
            prsm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static HashMap<Student, Course> getStudentsWithCourseName() {
        HashMap<Student, Course> studentsWithCourse = new HashMap<>();

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet res = stmt.executeQuery("SELECT Students.first_name, Students.last_name, Courses.course_name FROM Students, Courses WHERE Students.course_id = Courses.course_id");

            while(res.next()) {
                studentsWithCourse.put(
                        new Student(res.getString("first_name"), res.getString("last_name")),
                        new Course(res.getString("course_name"))
                );
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return studentsWithCourse;
    }
}
