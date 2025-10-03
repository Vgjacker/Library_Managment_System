import java.sql.*;
import java.time.LocalDate;

public class TransactionDAO {
    public void issueBook(int memberId, int bookId) {
        try (Connection conn = DBConnection.getConnection()) {
            String check = "SELECT status FROM books WHERE book_id=?";
            PreparedStatement ps1 = conn.prepareStatement(check);
            ps1.setInt(1, bookId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next() && rs.getString("status").equals("available")) {
                String sql = "INSERT INTO transactions (member_id, book_id, issue_date) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ps.setInt(2, bookId);
                ps.setDate(3, Date.valueOf(LocalDate.now()));
                ps.executeUpdate();

                String update = "UPDATE books SET status='issued' WHERE book_id=?";
                PreparedStatement ps2 = conn.prepareStatement(update);
                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                System.out.println("✅ Book issued successfully!");
            } else {
                System.out.println("❌ Book not available!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int txnId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE transactions SET return_date=? WHERE txn_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, txnId);
            ps.executeUpdate();

            String update = "UPDATE books SET status='available' WHERE book_id=(SELECT book_id FROM transactions WHERE txn_id=?)";
            PreparedStatement ps2 = conn.prepareStatement(update);
            ps2.setInt(1, txnId);
            ps2.executeUpdate();

            System.out.println("✅ Book returned successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
