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
	 * һ���ǳ���׼������Oracle���ݿ��ʾ������
	 */
	public static void testOracle()
	{
	    Connection con = null;// ����һ�����ݿ�����
	    PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
	    ResultSet result = null;// ����һ�����������
	    try
	    {
	        Class.forName("oracle.jdbc.driver.OracleDriver");// ����Oracle��������
	        System.out.println("��ʼ�����������ݿ⣡");
	        String url = "jdbc:oracle:" + "thin:@192.168.0.118:1522:orcl";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
	        String user = "hsp_bbzyy_test_1";// �û���,ϵͳĬ�ϵ��˻���
	        String password = "labway123";// �㰲װʱѡ���õ�����
	        con = DriverManager.getConnection(url, user, password);// ��ȡ����
	        System.out.println("���ӳɹ���");
	        
	        /*String sql1 = "create table lhx_java_test1(id number,name varchar2(20),cretime date)";
	        pre = con.prepareStatement(sql1);
	        pre.execute();
	        System.out.println("����ɹ���");
	        */
	        
	        String sql2 = "insert into lhx_java_test1(id,name,cretime)values(1,'������Java����',sysdate)";
	        pre = con.prepareStatement(sql2);
	        pre.execute();
	        System.out.println("�������ݳɹ���");
	        /*
	        String sql = "select * from student where name=?";// Ԥ������䣬�������������
	        pre = con.prepareStatement(sql);// ʵ����Ԥ�������
	        pre.setString(1, "������");// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
	        result = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
	        while (result.next())
	            // ���������Ϊ��ʱ
	            System.out.println("ѧ��:" + result.getInt("id") + "����:"
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
	            // ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
	            // ע��رյ�˳�����ʹ�õ����ȹر�
	            if (result != null)
	                result.close();
	            if (pre != null)
	                pre.close();
	            if (con != null)
	                con.close();
	            System.out.println("���ݿ������ѹرգ�");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	}
}

