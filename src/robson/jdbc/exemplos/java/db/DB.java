package robson.jdbc.exemplos.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static Connection conn = null;
    

    public static Connection getConnection(){
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/coursejdbc";
                String user = "root";
                String password = "automa";

                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return conn;
    }
    
    public static void closeConnection(){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
