package com.db.sample2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-09
 */

public class TestDbcp {

    public static void main(String[] args) throws SQLException {
        //1.从DBCP连接池中拿到连接
        Connection con = DbcpUtils.getConnection();
        //2.获取Statement对象
        Statement statement = con.createStatement();
        //3.查询
        String sql = "select  sleep(1) id,name,levels from v_log where name ='596161cdb' ";
        ResultSet resultSet = statement.executeQuery(sql);
        //4.处理结果集
        while (resultSet.next()) {
            String ename = resultSet.getString("name");
            System.out.println("name:  " + ename+" levels: "+ resultSet.getString("levels") );
        }
        //5.释放资源
        DbcpUtils.close(con, statement, resultSet);
    }

}
