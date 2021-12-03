package com.db.sample3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @description: 事务Demo
 * @author: wangtianqi20
 * @create: 2021-12-02
 **/
public class TransationT1 {
    private String driverClass = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://182.92.242.147:3306/test?connectTimeout=500&socketTimeout=3000&characterEncoding=UTF-8";
    private String userName = "root";
    private String password = "123456";

    public static void main(String[] args) {
        TransationT1 t1 = new TransationT1();
        t1.addOrder();

    }

    public void addOrder() {
        try {
            //加载驱动
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            //创建连接
            connection = DriverManager.getConnection(url, userName, password);
            //设置手动提交事务
            connection.setAutoCommit(false);
            //创建Statement对象
            Statement statement = connection.createStatement();
            // 执行SQL    //now()表示当前时间
            statement.execute("INSERT INTO v_log (name, effect_time, levels, creator, modifier, created_date, modified_date, remark) VALUES ('6a3803888', '2021-12-02 00:00:00', '8', 'creator-810567503', 'modifier--564869963', '2021-12-02 00:00:00', '2021-12-02 13:07:52', '1dd1d22b-c585-4fb4-bf77-e1e176d304d4')");
            statement.execute("update v_log set levels='12' where name='596161cdb'");
//            statement.execute("update v_log set aaa ='12' where name='596161cdb'");//error
            //关闭语句
            statement.close();
            //提交事务
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                //遇到异常，则回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                //关闭连接
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



