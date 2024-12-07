package com.ed.ecommerce.mvcDemo.Pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=Pizzeriadb4;TrustServerCertificate=true";
    private static String userName = "user02";
    private static String password = "user02";
    private static Connection con;

    public static Connection getConexion() throws SQLException {

        con = DriverManager.getConnection(url,userName, password);

        return con;
    }

}
