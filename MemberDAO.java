import java.sql.*;

public class MemberDAO {
    public void addMember(String name, String email, String phone) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.executeUpdate();
            System.out.println("✅ Member added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewMembers() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM members";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("👥 Member List:");
            while (rs.next()) {
                System.out.println(rs.getInt("member_id") + " | " + rs.getString("name") 
                                   + " | " + rs.getString("email") 
                                   + " | " + rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
