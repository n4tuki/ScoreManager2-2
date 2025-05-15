package display;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//s
public class DBConnection {
    private static final String URL = "jdbc:h2:~/test"; // ファイルベースの場合
    // private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; // メモリベースの場合
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
