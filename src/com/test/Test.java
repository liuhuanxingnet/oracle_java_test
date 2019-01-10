package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
	public static void main(String[] args) {
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
	        String url = "jdbc:oracle:" + "thin:@192.168.2.211:1522:lhx10g";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���
	        String user = "hh_gis";// �û���,ϵͳĬ�ϵ��˻���
	        String password = "admin123";// �㰲װʱѡ���õ�����
	        con = DriverManager.getConnection(url, user, password);// ��ȡ����
	        System.out.println("���ӳɹ���");
	        String sql = "CREATE TABLE hh_aa (gid number,geom SDO_GEOMETRY,o_ScaleRank number,o_LabelRank number,o_FeatureCla varchar2(100),o_SOVEREIGNT varchar2(100),o_SOV_A3 varchar2(100),o_ADM0_DIF number,o_LEVEL number,o_TYPE varchar2(100),o_ADMIN varchar2(100),o_ADM0_A3 varchar2(100),o_GEOU_DIF number,o_NAME varchar2(100),o_ABBREV varchar2(100),o_POSTAL varchar2(100),o_NAME_FORMA varchar2(100),o_TERR_ varchar2(100),o_NAME_SORT varchar2(100),o_MAP_COLOR number,o_POP_EST number,o_GDP_MD_EST number,o_FIPS_10_ number,o_ISO_A2 varchar2(100),o_ISO_A3 varchar2(100),o_ISO_N3 number)";
	        //String sql = "create table hh_test1 (gid number,name varchar2(10),geom mdsys.SDO_GEOMETRY)";
	        pre = con.prepareStatement(sql);
	        pre.execute();
	        System.out.println("����ɹ���");
	        /*
	        String sql = "select * from student where name=?";// Ԥ������䣬�������������
	        pre = con.prepareStatement(sql);// ʵ����Ԥ�������
	        pre.setString(1, "���԰�");// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������
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
