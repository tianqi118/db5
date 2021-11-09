package com.db.sample1;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-05
 */

public class JdbcT4DbPack {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接（里面内容依次是："jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码"）
        Connection connection = DriverManager.getConnection("jdbc:mysql://10.222.46.113:3306/aqv_bizs?connectTimeout=15&socketTimeout=15", "j_aqv_bizs", "1SczDMZixanw");
        //3.需要执行的sql语句（?是占位符，代表一个参数）
        String sql = "insert into v_log(name,levels) values(?,?)";
        //创建dbUtils里面的QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //存参数值的数组
        Object[] params = {UUID.randomUUID().toString().substring(0, 8), "11"};
        int i = queryRunner.update(connection, sql, params);
        System.out.println("插入结果：" + i);

        //sql语句
        String sqlQuery = "select * from v_log where id>?";
        //存参数值的数组
        Object[] paramsQuery = {1};
        //执行查询，并以数组的形式返回查询结果（new ArrayListHandler()返回所有查询到的记录）
        List<Object[]> lists = queryRunner.query(connection,sqlQuery, new ArrayListHandler(),paramsQuery);
        for(Object[] item:lists){
            System.out.println(Arrays.toString(item));
        }
        //6.关闭jdbc连接
        connection.close();
    }
}
