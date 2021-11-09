package com.db.sample1;

import java.sql.*;
import java.util.UUID;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-04
 */

public class JdbcT2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接（里面内容依次是："jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码"）
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.222.46.113:3306/aqv_bizs", "j_aqv_bizs", "1SczDMZixanw");
        //3.需要执行的sql语句（?是占位符，代表一个参数）
        String sql = "insert into v_log(name,levels) values(?,?)";
        //4.获取预处理对象，并依次给参数赋值
//        int i = doInsert(connection, sql);
//        System.out.println("插入结果：" + i);
        doSelect(connection, "select id,name,levels from v_log");
        //6.关闭jdbc连接
        connection.close();
    }

    private static void doSelect(Connection connection, String sql) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall(sql);
//            statement.setString(1, UUID.randomUUID().toString().substring(0, 8));    //数据库字段类型是String，就是setString；2代表第二个参数
//            statement.setString(2, "3"); //
            //5.执行sql语句
            rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("id:" + rs.getString(1) + " name:" + rs.getString(2) + " age:" + rs.getString("levels"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入数据
     *
     * @param connection
     * @param sql
     * @return
     */
    private static int doInsert(Connection connection, String sql) {
        int i = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall(sql);
            statement.setString(1, UUID.randomUUID().toString().substring(0, 8));    //数据库字段类型是String，就是setString；2代表第二个参数
            statement.setString(2, "3"); //
            //5.执行sql语句（执行了几条记录，就返回几）
            i = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return i;
    }

}
