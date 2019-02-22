package interenetStore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String path = "jdbc:mysql://danit.cukm9c6zpjo8.us-west-2.rds.amazonaws.com:3306/fs5";
    private String username = "fs5_user";
    private String password = "bArceloNa";
    private Connection connection = null;


    private Connection connect() throws SQLException {
        return DriverManager.getConnection(path, username, password);
    }


    public Connection connection() throws SQLException {

        if (connection == null) {
            connection = connect();
        }

        return this.connection;
    }
}