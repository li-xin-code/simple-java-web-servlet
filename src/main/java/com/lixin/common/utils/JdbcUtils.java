package com.lixin.common.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lixin
 */
public class JdbcUtils {

    private JdbcUtils() {
    }

    private static final DataSource DATA_SOURCE;

    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("datasource.properties");
            Properties properties = new Properties();
            properties.load(in);
            DATA_SOURCE = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException("druid连接池初始化失败...", e);
        }
    }

    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
