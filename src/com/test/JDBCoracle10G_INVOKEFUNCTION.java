package com.test;

import java.sql.*;  
import oracle.jdbc.OracleCallableStatement;  
import oracle.jdbc.OracleTypes;  
  
/* 
 /* 本例是通过调用oracle的函数来返回结果集: 
 * oracle 9i、10G 的jdbc由1个jar包组成：classes12.zip   
 */  
public class JDBCoracle10G_INVOKEFUNCTION {  
    Connection conn = null;  
    Statement statement = null;  
    ResultSet rs = null;  
    CallableStatement stmt = null;  
  
    String driver;  
    String url;  
    String user;  
    String pwd;  
    String sql;  
    String in_price;  
  
    public JDBCoracle10G_INVOKEFUNCTION()  
    {  
        driver = "oracle.jdbc.driver.OracleDriver";  
        url = "jdbc:oracle:thin:@192.168.0.144:1523:csljdev";  
        // oracle 用户  
        user = "lab_cs";  
        // oracle 密码  
        pwd = "labway123";  
        init();  
        // mysid：必须为要连接机器的sid名称，否则会包以下错：  
        // java.sql.SQLException: Io 异常: Connection  
        // refused(DESCRIPTION=(TMP=)(VSNNUM=169870080)(ERR=12505)(ERROR_STACK=(ERROR=(CODE=12505)(EMFI=4))))  
        // 参考连接方式:  
        // Class.forName( "oracle.jdbc.driver.OracleDriver" );  
        // cn = DriverManager.getConnection(  
        // "jdbc:oracle:thin:@MyDbComputerNameOrIP:1521:ORCL", sUsr, sPwd );  
    }  
  
    public void init() {  
        System.out.println("oracle jdbc test");  
        try {  
            Class.forName(driver);  
            System.out.println("driver is ok");  
            conn = DriverManager.getConnection(url, user, pwd);  
            System.out.println("conection is ok");  
            statement = conn.createStatement();  
            // conn.setAutoCommit(false);  
            // 输入参数  
            in_price = "5.0";  
            // 调用函数  
            stmt = conn.prepareCall("{? = call F_GET_PRICE(?)}");  
            // stmt.registerOutParameter(1, java.sql.Types.FLOAT);  
            // stmt.registerOutParameter(2, java.sql.Types.CHAR);  
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);  
            stmt.setString(2, in_price);  
            stmt.executeUpdate();  
            // 取的结果集的方式一：  
            rs = ((OracleCallableStatement) stmt).getCursor(1);  
            // 取的结果集的方式二：  
            // rs = (ResultSet) stmt.getObject(1);  
            String ric;  
            String price;  
            String updated;  
  
            while (rs.next()) {  
                ric = rs.getString(1);  
                price = rs.getString(2);  
                updated = rs.getString(3);  
                System.out.println("ric:" + ric + ";-- price:" + price + "; --"  
                        + updated + "; ");  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            System.out.println("close ");  
        }  
    }  
  
    public static void main(String args[])// 自己替换［］  
    {  
        new JDBCoracle10G_INVOKEFUNCTION();  
    }  
}  