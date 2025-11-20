package quizpro;

import java.sql.*;

public class DBUtil {
    static final String URL = "jdbc:mysql://localhost:3306/quizapp";
    static final String USER = "root";
    static final String PASSWORD = "root"; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Driver error: " + e);
        }
    }

    public static boolean registerUser(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false; // username taken
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

    public static void saveResult(String username, int score) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO quiz_results (username, score) VALUES (?, ?)");
            pst.setString(1, username);
            pst.setInt(2, score);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
