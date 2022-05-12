package com.lixin.test;

import com.lixin.common.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lixin
 */
public class JdbcTest {
    public static void main(String[] args) {
        Connection connection = JdbcUtils.getConnection();
        String sql = "select * from book";
        ResultSet res = null;
        PreparedStatement pre = null;
        try {
            assert connection != null;
            pre = connection.prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                System.out.print(res.getString(1) + "  ");
                System.out.println(res.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
                assert pre != null;
                pre.close();
                assert res != null;
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
