import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        TransactionDAO txnDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add Member");
            System.out.println("4. View Members");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    bookDAO.addBook(title, author, category);
                }
                case 2 -> bookDAO.viewBooks();
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    memberDAO.addMember(name, email, phone);
                }
                case 4 -> memberDAO.viewMembers();
                case 5 -> {
                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    txnDAO.issueBook(memberId, bookId);
                }
                case 6 -> {
                    System.out.print("Enter transaction ID: ");
                    int txnId = sc.nextInt();
                    txnDAO.returnBook(txnId);
                }
                case 7 -> {
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }
}
