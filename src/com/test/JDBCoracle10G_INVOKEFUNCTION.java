package com.test;

import java.sql.*;  
import oracle.jdbc.OracleCallableStatement;  
import oracle.jdbc.OracleTypes;  
  
/* 
 /* ������ͨ������oracle�ĺ��������ؽ����: 
 * oracle 9i��10G ��jdbc��1��jar����ɣ�classes12.zip   
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
        // oracle �û�  
        user = "lab_cs";  
        // oracle ����  
        pwd = "labway123";  
        init();  
        // mysid������ΪҪ���ӻ�����sid���ƣ����������´�  
        // java.sql.SQLException: Io �쳣: Connection  
        // refused(DESCRIPTION=(TMP=)(VSNNUM=169870080)(ERR=12505)(ERROR_STACK=(ERROR=(CODE=12505)(EMFI=4))))  
        // �ο����ӷ�ʽ:  
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
            // �������  
            in_price = "5.0";  
            // ���ú���  
            stmt = conn.prepareCall("{? = call F_GET_PRICE(?)}");  
            // stmt.registerOutParameter(1, java.sql.Types.FLOAT);  
            // stmt.registerOutParameter(2, java.sql.Types.CHAR);  
            stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);  
            stmt.setString(2, in_price);  
            stmt.executeUpdate();  
            // ȡ�Ľ�����ķ�ʽһ��  
            rs = ((OracleCallableStatement) stmt).getCursor(1);  
            // ȡ�Ľ�����ķ�ʽ����  
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
  
    public static void main(String args[])// �Լ��滻�ۣ�  
    {  
        new JDBCoracle10G_INVOKEFUNCTION();  
    }  
}  