package ru.danko.testtask.dao.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbcpDataSource {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:postgresql://localhost:5432/studentsdatabase");
        ds.setUsername("postgres");
        ds.setPassword("root");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DbcpDataSource(){ }
}
