package com.rowaxl.printer;

import com.rowaxl.dao.StudentDao;
import com.rowaxl.models.Course;
import com.rowaxl.models.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentPrinter {
    public static void printAllStudents() {
        try {
            ArrayList<Student> students = StudentDao.getAllStudents();
            System.out.printf("%-25s %-20s %-15s %10s %20s %23s \n", "Email", "First name", "Last name", "Birth date", "Phone number", "Balance");
            for (Student student: students) {
                System.out.printf("%-25s %-20s %-15s %10s %20s %23s \n",
                    student.getEmail(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getBirthDate(),
                    student.getCellPhone(),
                    student.getBalance()
                );
            }
            System.out.println("");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void printStudentsWithCourseName() {
        HashMap<Student, Course> studentsWithCourse = StudentDao.getStudentsWithCourseName();
        System.out.printf("%-10s %10s %20s\n", "First name", "Last name", "Course name");
        for (Student student: studentsWithCourse.keySet()) {
            System.out.printf("%10s %10s %20s \n",
                    student.getFirstName(),
                    student.getLastName(),
                    studentsWithCourse.get(student).getCourseName()
            );
        }
        System.out.println("");
    }
}
