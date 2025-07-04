package StudentAttPerTacker;
import java.sql.*;
import java.util.*;

public class StudentManager {

    public void addStudent(Student student) {
        try (Connection conn = DButil.getConnection()) {
            String sql = "INSERT INTO students (id, name, age, attendance_count, marks) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getAttendance());
            ps.setString(5, marksListToString(student.getMarkList()));
            ps.executeUpdate();
            System.out.println("Student added to DB.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllStudents() {
        try (Connection conn = DButil.getConnection()) {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int attendance = rs.getInt("attendance_count");
                String marksStr = rs.getString("marks");
                List<Integer> marks = stringToMarksList(marksStr);
                Student s = new Student(id, name, age, attendance, marks);
                System.out.println(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String name, int age) {
        try (Connection conn = DButil.getConnection()) {
            String sql = "UPDATE students SET name=?, age=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student updated." : " Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        try (Connection conn = DButil.getConnection()) {
            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markAttendance(int id) {
        try (Connection conn = DButil.getConnection()) {
            String selectSql = "SELECT attendance_count FROM students WHERE id=?";
            PreparedStatement selectPs = conn.prepareStatement(selectSql);
            selectPs.setInt(1, id);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                int current = rs.getInt("attendance_count");
                String updateSql = "UPDATE students SET attendance_count=? WHERE id=?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, current + 1);
                updatePs.setInt(2, id);
                updatePs.executeUpdate();
                System.out.println("Attendance marked.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMark(int id, int mark) {
        try (Connection conn = DButil.getConnection()) {
            String selectSql = "SELECT marks FROM students WHERE id=?";
            PreparedStatement selectPs = conn.prepareStatement(selectSql);
            selectPs.setInt(1, id);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                String marksStr = rs.getString("marks");
                List<Integer> marksList = stringToMarksList(marksStr);
                marksList.add(mark);
                String updatedMarks = marksListToString(marksList);

                String updateSql = "UPDATE students SET marks=? WHERE id=?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setString(1, updatedMarks);
                updatePs.setInt(2, id);
                updatePs.executeUpdate();

                System.out.println("Mark added.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility methods
    private String marksListToString(List<Integer> marks) {
        return String.join(",", marks.stream().map(String::valueOf).toArray(String[]::new));
    }

    private List<Integer> stringToMarksList(String data) {
        List<Integer> list = new ArrayList<>();
        if (data == null || data.isEmpty()) return list;
        for (String s : data.split(",")) list.add(Integer.parseInt(s.trim()));
        return list;
    }
}
