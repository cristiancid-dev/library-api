package com.cristiancid.library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static Connection connect() throws SQLException {
            String url = "jdbc:postgresql://localhost:5432/library";
            String uname = "postgres";
            String pass = "1234";

            return DriverManager.getConnection(url, uname, pass);
    }

}
