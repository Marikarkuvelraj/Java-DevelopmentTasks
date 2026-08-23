import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {

    static final String URL = "jdbc:mysql://localhost:3306/library_db";
    static final String USER = "root";
    static final String PASSWORD = "";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            while (true) {
                System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> viewBooks();
                    case 3 -> borrowBook();
                    case 4 -> returnBook();
                    case 5 -> {
                        System.out.println("Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    static void addBook() throws SQLException {

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        String sql = "INSERT INTO books(title, author) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.executeUpdate();

            System.out.println("Book added successfully!");
        }
    }

    
    static void viewBooks() throws SQLException {

        String sql = "SELECT * FROM books";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\nID | TITLE | AUTHOR | AVAILABLE");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getBoolean("available")
                );
            }
        }
    }

    
    static void borrowBook() throws SQLException {

        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Connection con = getConnection();
        con.setAutoCommit(false);

        try {
            String checkSql = "SELECT available FROM books WHERE id = ?";
            PreparedStatement check = con.prepareStatement(checkSql);
            check.setInt(1, id);

            ResultSet rs = check.executeQuery();

            if (rs.next() && rs.getBoolean("available")) {

                String updateSql =
                        "UPDATE books SET available = FALSE WHERE id = ?";

                PreparedStatement update =
                        con.prepareStatement(updateSql);

                update.setInt(1, id);
                update.executeUpdate();

                con.commit();
                System.out.println("Book borrowed successfully!");

            } else {
                con.rollback();
                System.out.println("Book is not available!");
            }

        } catch (Exception e) {
            con.rollback();
            System.out.println("Borrow failed: " + e.getMessage());
        } finally {
            con.close();
        }
    }

    
    static void returnBook() throws SQLException {

        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Connection con = getConnection();
        con.setAutoCommit(false);

        try {
            String sql =
                    "UPDATE books SET available = TRUE WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("Book returned successfully!");
            } else {
                con.rollback();
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            con.rollback();
            System.out.println("Return failed: " + e.getMessage());
        } finally {
            con.close();
        }
    }
}

