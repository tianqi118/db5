package com.db.sample1;

import java.sql.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-04
 */
public class JdbcT1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接(超时时间，connect与socketTime分开，只要建立连接成功，sql执行过程看的是socketTime)
        //        Connection connection = DriverManager.getConnection("jdbc:mysql://10.222.46.113:3306/aqv_bizs?connectTimeout=6&socketTimeout=300", "j_aqv_bizs", "1SczDMZixanw");
        Connection connection = DriverManager.getConnection("jdbc:mysql://182.92.242.147:3306/test?connectTimeout=500&socketTimeout=3000&characterEncoding=UTF-8", "root", "123456");
        //3.需要执行的sql语句（?是占位符，代表一个参数）
        String sql = "insert into  v_log(name,levels,effect_time,creator,modifier,created_date,remark) values(?,?,?,?,?,?,?)";
        //4.获取预处理对象，并依次给参数赋值
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setString(1, UUID.randomUUID().toString().substring(0, 10).replace("-",""));    //数据库字段类型是String，就是setString；2代表第二个参数
        statement.setString(2, ""+new Random().nextInt(9)); //
        statement.setDate(3, new Date(System.currentTimeMillis())); //
        statement.setString(4, "creator-" + new Random().nextInt()); //
        statement.setString(5, "modifier-" + new Random().nextInt()); //
        statement.setDate(6, new Date(System.currentTimeMillis())); //
        statement.setString(7, UUID.randomUUID().toString()); //

        statement.setQueryTimeout(1);//
        int s = statement.executeUpdate();
        System.out.println("插入结果：" + s);


        //6.关闭jdbc连接
        statement.close();
        connection.close();
    }

}
