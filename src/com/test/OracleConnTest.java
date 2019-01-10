package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleConnTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     System.out.println("ok");
     
     testOracle();
     
	}
	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */
	public static void testOracle()
	{
	    Connection con = null;// 创建一个数据库连接
	    PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	    ResultSet result = null;// 创建一个结果集对象
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        System.out.println("开始尝试连接数据库！");
	        String url = "jdbc:oracle:" + "thin:@192.168.0.118:1522:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = "hsp_bbzyy_test_1";// 用户名,系统默认的账户名
	        String password = "labway123";// 你安装时选设置的密码
	        con = DriverManager.getConnection(url, user, password);// 获取连接
	        System.out.println("连接成功！");
	        
	        /*String sql1 = "create table lhx_java_test1(id number,name varchar2(20),cretime date)";
	        pre = con.prepareStatement(sql1);
	        pre.execute();
	        System.out.println("建表成功！");
	        */
	        
	        String sql2 = "insert into lhx_java_test1(id,name,cretime)values(1,'刘欢欣Java测试',sysdate)";
	        pre = con.prepareStatement(sql2);
	        pre.execute();
	        System.out.println("插入数据成功！");
	        /*
	        String sql = "select * from student where name=?";// 预编译语句，“？”代表参数
	        pre = con.prepareStatement(sql);// 实例化预编译语句
	        pre.setString(1, "刘欢欣");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引
	        result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
	        while (result.next())
	            // 当结果集不为空时
	            System.out.println("学号:" + result.getInt("id") + "姓名:"
	                    + result.getString("name"));
	        */
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    finally
	    {
	        try
	        {
	            // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
	            // 注意关闭的顺序，最后使用的最先关闭
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("数据库连接已关闭！");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
}

