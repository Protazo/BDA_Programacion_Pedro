package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url="jdbc:mariadb://localhost:3306/sistema_reservas";
    private static final String user="root";
    private static final String password="admin";

    public static Connection getConnection() throws SQLException {
        Connection cnx= DriverManager.getConnection(url,user,password);
        return cnx;
    }
}
