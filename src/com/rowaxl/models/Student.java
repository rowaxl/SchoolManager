package com.rowaxl.models;

import java.sql.Date;

public class Student {
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String cellPhone;
    private double balance;

    public Student(String email, String firstName, String lastName, Date birthDate, String cellPhone, double balance) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cellPhone = cellPhone;
        this.balance = balance;
    }

    public Student(String email, String firstName, String lastName, Date birthDate, String cellPhone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cellPhone = cellPhone;
        this.balance = 0;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public double getBalance() {
        return balance;
    }
}
