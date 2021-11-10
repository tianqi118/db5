package com.db.sample2;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-05
 */
public class DbcpUtils {
    //1.定义常量 保存数据库连接的相关信息
    public static final String DRIVERNAME = "com.mysql.jdbc.Driver";
    //    public static final String URL = "jdbc:mysql://10.222.46.113:3306/aqv_bizs?connectTimeout=60&socketTimeout=3000&characterEncoding=UTF-8";
//    public static final String USERNAME = "j_aqv_bizs";
//    public static final String PASSWORD = "1SczDMZixanw";
    public static final String URL = "jdbc:mysql://182.92.242.147:3306/test?connectTimeout=60&socketTimeout=3000&characterEncoding=UTF-8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

    private static final int MAXACTIVE = 10;
    private static final int MAXIDLE = 5;
    private static final int MINIDLE = 2;
    private static final int INITIALSIZE = 2;

    //2.创建连接池对象 (有DBCP提供的实现类)
    public static BasicDataSource dataSource = new BasicDataSource();

    //3.使用静态代码块进行配置
    static {
        dataSource.setDriverClassName(DRIVERNAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        //最大连接数量
        dataSource.setMaxActive(MAXACTIVE);
        //最大空闲连接
        dataSource.setMaxIdle(MAXIDLE);
        //最小空闲连接
        dataSource.setMinIdle(MINIDLE);
        //初始化连接
        dataSource.setInitialSize(INITIALSIZE);
    }

    //4.获取连接的方法
    public static Connection getConnection() throws SQLException {
        //从连接池中获取连接
        Connection connection = dataSource.getConnection();
        return connection;
    }

    //5.释放资源方法
    public static void close(Connection con, Statement statement) {
        if (con != null && statement != null) {
            try {
                statement.close();
                //归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, Statement statement, ResultSet resultSet) {
        if (con != null && statement != null && resultSet != null) {
            try {
                resultSet.close();
                statement.close();
                //归还连接
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

