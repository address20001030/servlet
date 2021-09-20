package com.nguyenmauhuy.orm.pool;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private HikariConfiguration hikariConfiguration;
    private HikariDataSource hikariDataSource;

    public ConnectionPool(){
        this.hikariConfiguration = new HikariConfiguration();
        this.hikariDataSource = new HikariConfiguration().dataSource();
    }

    public Connection getConnection() throws SQLException {

        return hikariDataSource.getConnection();
    }
}
