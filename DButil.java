package StudentAttPerTacker;

import java.sql.*;

public class DButil {
    private static final String URL = "jdbc:mysql://localhost:3306/student_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "chamu"; // 

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
