package jdbc;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBService {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    public static final String DRIVER = "org.postgresql.Driver";
    private static Connection connection;


    // загрузка драйвера
    private void DBService() {
        try {
            Class.forName(DRIVER);
            // создание строки соединения
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void connect(String sql) throws SQLException {
        try {
            // Подключение к БД
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Создание объекта statement для работы с бд
            Statement statement = connection.createStatement();
            // обновление statement
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeQuery(String query) throws SQLException {
        //creates a statement object
        Statement stmt = this.connection.createStatement();

        //issues the query instruction
        ResultSet rs = stmt.executeQuery(query);
        int rowCount = 0;

        //iterates through the result set and count number of results.
        if (rs.next()) {
            rowCount++;
        }

        stmt.close();
        return rowCount;
    }

    public List<List<String>> executeQueryAndReturnResult(String query) throws SQLException {
        Statement stmt = this.connection.createStatement();


        ResultSet rs = stmt.executeQuery(query);


        ResultSetMetaData rsmd = rs.getMetaData();
        int numCol = rsmd.getColumnCount();
        int rowCount = 0;

        //iterates through the result set and saves the data returned by the query.
        boolean outputHeader = false;

        List<List<String>> result = new ArrayList<List<String>>();

        while (rs.next()) {
            List<String> record = new ArrayList<String>();
            for (int i = 1; i <= numCol; ++i) {
                record.add(rs.getString(i));
            }
            result.add(record);
        }
        stmt.close();
        return result;
    }
}






