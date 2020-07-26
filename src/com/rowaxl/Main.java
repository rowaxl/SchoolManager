package com.rowaxl;

import java.sql.*;
import java.util.Scanner;

import com.rowaxl.dao.StudentDao;
import com.rowaxl.models.Student;
import com.rowaxl.printer.CoursePrinter;
import com.rowaxl.printer.StudentPrinter;

import static com.rowaxl.helper.Validator.validateEmail;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String printMenu() {
        System.out.println("Please choose one of options:");
        System.out.println("[1] Print list of courses");
        System.out.println("[2] Print list of students");
        System.out.println("[3] Add the information of a new student");
        System.out.println("[4] Enroll the course with course id");
        System.out.println("[5] Print list of students and course they enrolled");
        System.out.println("[q] Quit");
        System.out.println("");

        return scanner.next();
    }

    public static Student printNewStudentAttrs() {
        System.out.println("Please enter e-mail address of student:");
        String email = scanner.next();

        while (!validateEmail(email)) {
            System.out.println("Email format is invaild. Please enter valid email");
            email = scanner.next();
        }

        System.out.println("Please enter first name of student:");
        String firstName = scanner.next();
        System.out.println("Please enter last name of student:");
        String lastName = scanner.next();
        System.out.println("Please enter birth date of student [YYYY-MM-DD]:");
        Date birthDate = Date.valueOf(scanner.next());
        System.out.println("Please enter cell phone number of student:");
        String cellPhone = scanner.next();

        return new Student(email, firstName, lastName, birthDate, cellPhone);
    }

    public static void enrollCourse() {
        System.out.println("Enter e-mail of student to enroll:");
        String email = scanner.next();

        while (!validateEmail(email)) {
            System.out.println("Email format is invaild. Please enter valid email");
            email = scanner.next();
        }

        System.out.println("Enter the ID of Course to enroll:");
        int courseId = scanner.nextInt();

        System.out.println("Enter the amount of payment you want to:");
        double payment = scanner.nextDouble();

        StudentDao.enrollStudentToCourse(email, courseId, payment);
    }

    public static void main(String[] args) throws SQLException {
        boolean done = false;
        while(!done) {
            String input = Main.printMenu();

            switch (input) {
                case "1":
                    CoursePrinter.printAllCources();
                    break;
                case "2":
                    StudentPrinter.printAllStudents();
                    break;
                case "3":
                    StudentDao.addStudent(printNewStudentAttrs());
                    System.out.println("Add new student successfully! ");
                    break;
                case "4":
                    enrollCourse();
                    System.out.println("Enroll student to course successfully! ");
                    break;
                case "5":
                    StudentPrinter.printStudentsWithCourseName();
                    break;
                case "q":
                case "Q":
                    done = true;
                    break;
                default:
                    System.out.println("Please enter 1 ~ 6 or q");
                    break;
            }
        }

        scanner.close();
    }
}
