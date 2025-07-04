# 📘 Student Attendance and Performance Tracker (Java + JDBC + MySQL)

This is a simple console-based CRUD application built using Core Java and JDBC that allows you to manage student records, track their attendance, and evaluate performance through marks. 

This is extension to my previous project which is just a console application and not linked to any database.

## 🚀 Features

➕ Add new students

📋 View all student records

📝 Update student name and age

🗑️ Delete a student

📅 Mark attendance

📊 Add performance marks

📈 View average marks per student


## 🏗️ Database Setup (MySQL)

Create Database and Table

CREATE DATABASE IF NOT EXISTS student_tracker;

USE student_tracker;

CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    attendance_count INT,
    marks TEXT
);

Add MySQL Connector

Download: https://dev.mysql.com/downloads/connector/j/

Add the .jar to your project via:

Eclipse: Right-click → Build Path → Add External JARs

## 🔌 JDBC Configuration

In your DBUtil.java, configure:

private static final String URL = "jdbc:mysql://localhost:3306/student_tracker";

private static final String USER = "root";

private static final String PASSWORD = "your_password"; // Replace with your MySQL password

## 🧪 How to Run

Import the project into Eclipse/IntelliJ.

Ensure your MySQL server is running.

Run Main.java to launch the console app.

Use the menu to add/view/update/delete students, mark attendance, and add marks.
