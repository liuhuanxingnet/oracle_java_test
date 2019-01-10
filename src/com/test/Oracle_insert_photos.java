package com.test;

import   java.sql.*;  
import   oracle.sql.*;  
import   java.io.*;  
import   oracle.jdbc.driver.OracleResultSet; 

public   class   Oracle_insert_photos {  
public   static   void   main(String[]   args)   {  
try   {  
//   �������ݿ�  
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
//Connection   conn   =   DriverManager.getConnection("jdbc:odbc:lhx12c","pda","pwpda");          
Class.forName("oracle.jdbc.driver.OracleDriver");                                                                
Connection   conn   =   DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.16:1523:lhx12c","c##lhx_datatype","admin123");  
conn.setAutoCommit(false);  
//   ��ȡԴ��Ƭ����  
BLOB     blob   =   null;  
Blob     blob2   =   null;  
String   fileName   =   "c:\\01.jpg";  
File   f   =   new   File(fileName);  
//   �Ȳ���һ����¼,���ں�����һ��Blob����  
PreparedStatement   pstmt   =   conn.prepareStatement("insert   into   inst_photo(PICNAME,content)   values(?,empty_blob())");  
pstmt.setString(1,"lhx12c");    
pstmt.executeUpdate();  
pstmt.close();  
//   ���Blob����(����Blob�ǽӿ�,����ʵ����,����ֻ�����������۷������)  
pstmt   =   conn.prepareStatement("SELECT   content   FROM   inst_photo   WHERE   trim(PICNAME)=?   ");  
pstmt.setString(1,"lhx12c");  
ResultSet   rset   =   pstmt.executeQuery();  
if   (rset.next())   blob   =   (BLOB)rset.getBlob(1);  
pstmt.close();  
if   (blob   ==   null){  
System.out.println("blob   is   null");  
conn.close();  
return   ;  
}  
//   ���Blobֵ,�����ύ�����ݿ�  
FileInputStream   fin   =   new   FileInputStream(f);  
System.out.println("file   size   =   "   +   fin.available());  
OutputStream   out   =   blob.getBinaryOutputStream();  
byte[]   data   =   new   byte[(int)fin.available()];  
        //   ��ȡ  
        fin.read(data);  
        out.write(data);  
        //   �ر���Դ          
        fin.close();  
        out.close();  
        //   �������ݿ�  
        pstmt   =   conn.prepareStatement("update   inst_photo   set   content=?   where   PICNAME=?");  
        pstmt.setBlob(1,blob);  
        pstmt.setString(2,"lhx12c");  
        pstmt.executeUpdate();  
        pstmt.close();  
        //   �ύ  
        conn.commit();  
        //   ��ȡ���ݿ��е���Ƭ          
pstmt   =   conn.prepareStatement("SELECT   content   FROM   inst_photo   WHERE   trim(PICNAME)=?   ");  
pstmt.setString(1,"lhx12c");  
rset   =   pstmt.executeQuery();  
if   (rset.next())   blob   =   (BLOB)((OracleResultSet)rset).getBLOB(1);  
pstmt.close();  
if   (blob   ==   null){  
System.out.println("blob2   is   null");  
conn.close();  
return   ;  
}  
//   ���������  
//   ���  
FileOutputStream   fout   =   new   FileOutputStream(new   File("c:\\02.jpg"));  
InputStream   in   =   blob.asciiStreamValue();  
data   =   new   byte[(int)in.available()];  
System.out.println("���ݿ�����Ƭ���ֽ���:"+in.available());                       //   �˾�����Ϊ   0   ��֪��Ϊʲô,��˭֪����?  
        //   ���  
        in.read(data);  
        fout.write(data);  
        //   �ر���Դ          
        fin.close();  
        out.close();  
        conn.close();  
        //   ��  
        //Process   pro   =   Runtime.getRuntime().exec("cmd   /c   start   c:\\02.jpg");    
        }   catch   (SQLException   e)   {  
        System.err.println(e.getMessage());  
        e.printStackTrace();  
        }   catch   (IOException   e)   {  
        System.err.println(e.getMessage());  
        }   catch(ClassNotFoundException   e){  
        e.printStackTrace();  
        }  
        }  
}

