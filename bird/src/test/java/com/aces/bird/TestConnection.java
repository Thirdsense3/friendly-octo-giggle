package com.aces.bird;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    @Autowired
    private DataSource dataSource;

    public void testConnection() {
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("MariaDB 연결 성공!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("MariaDB 연결 실패...");
            e.printStackTrace();
        }
    }

}
