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
            // создание таблицы в БД
            String createTableQuery = "CREATE TABLE customers ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "first_name VARCHAR(50) NOT NULL,"
                    + "last_name VARCHAR(50) NOT NULL,"
                    + "telephone VARCHAR(11)";
            statement.executeUpdate(createTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}






