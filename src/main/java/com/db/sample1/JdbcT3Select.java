package com.db.sample1;

import java.sql.*;

/**
 * @author wangtianqi20
 * @Description
 * @date 2021-11-05
 */

public class JdbcT3Select {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册数据库的驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接(超时时间，connect与socketTime分开，只要建立连接成功，查询过程看的是socketTime)
        Connection connection = DriverManager.getConnection("jdbc:mysql://182.92.242.147:3306/test?connectTimeout=500&socketTimeout=15000", "root", "123456");
        //3.需要执行的sql语句（睡眠2s）
        String sql = "select  sleep(10) id,name,levels from v_log ";
//        String sql = "select sleep(2)";
        //4.获取预处理对象，并依次给参数赋值
        PreparedStatement statement = connection.prepareCall(sql);
        //即使设置了statement timeout，当网络出错时，应用也无法从错误中恢复。
        statement.setQueryTimeout(4);//单位 秒（创建异步线程监控，超时后发送取消查询命令，可能是测试用例查询时间过段，模拟不出来）
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println("id:" + rs.getString(1) + " name:" + rs.getString(2) + " age:" + rs.getString("levels"));
        }
        //6.关闭jdbc连接
        statement.close();
        connection.close();
    }

}
