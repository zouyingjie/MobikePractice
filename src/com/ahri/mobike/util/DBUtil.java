package com.ahri.mobike.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 */

/**
 * Created by zouyingjie on 2017/2/21.
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mobike_data";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection(){
        return conn;
    }

}
