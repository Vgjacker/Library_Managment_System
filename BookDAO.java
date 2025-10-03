import java.sql.*;
import java.util.*;

public class BookDAO {
    public void addBook(String title, String author, String category) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO books (title, author, category) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("📚 Book List:");
            while (rs.next()) {
                System.out.println(rs.getInt("book_id") + " | " + rs.getString("title") 
                                   + " | " + rs.getString("author") 
                                   + " | " + rs.getString("category") 
                                   + " | " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
