package com.test;

import   java.sql.*;  
import   oracle.sql.*;  
import   java.io.*;  
import   oracle.jdbc.driver.OracleResultSet; 

public   class   Oracle_insert_photos {  
public   static   void   main(String[]   args)   {  
try   {  
//   连接数据库  
//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
//Connection   conn   =   DriverManager.getConnection("jdbc:odbc:lhx12c","pda","pwpda");          
Class.forName("oracle.jdbc.driver.OracleDriver");                                                                
Connection   conn   =   DriverManager.getConnection("jdbc:oracle:thin:@192.168.2.16:1523:lhx12c","c##lhx_datatype","admin123");  
conn.setAutoCommit(false);  
//   获取源照片数据  
BLOB     blob   =   null;  
Blob     blob2   =   null;  
String   fileName   =   "c:\\01.jpg";  
File   f   =   new   File(fileName);  
//   先插入一条记录,用于后面获得一个Blob对象  
PreparedStatement   pstmt   =   conn.prepareStatement("insert   into   inst_photo(PICNAME,content)   values(?,empty_blob())");  
pstmt.setString(1,"lhx12c");    
pstmt.executeUpdate();  
pstmt.close();  
//   获得Blob对象(由于Blob是接口,不能实例化,所以只好用这种曲折方法获得)  
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
//   填充Blob值,用于提交到数据库  
FileInputStream   fin   =   new   FileInputStream(f);  
System.out.println("file   size   =   "   +   fin.available());  
OutputStream   out   =   blob.getBinaryOutputStream();  
byte[]   data   =   new   byte[(int)fin.available()];  
        //   获取  
        fin.read(data);  
        out.write(data);  
        //   关闭资源          
        fin.close();  
        out.close();  
        //   插入数据库  
        pstmt   =   conn.prepareStatement("update   inst_photo   set   content=?   where   PICNAME=?");  
        pstmt.setBlob(1,blob);  
        pstmt.setString(2,"lhx12c");  
        pstmt.executeUpdate();  
        pstmt.close();  
        //   提交  
        conn.commit();  
        //   获取数据库中的照片          
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
//   输出到磁盘  
//   获得  
FileOutputStream   fout   =   new   FileOutputStream(new   File("c:\\02.jpg"));  
InputStream   in   =   blob.asciiStreamValue();  
data   =   new   byte[(int)in.available()];  
System.out.println("数据库中照片的字节数:"+in.available());                       //   此句总是为   0   不知道为什么,有谁知道吗?  
        //   输出  
        in.read(data);  
        fout.write(data);  
        //   关闭资源          
        fin.close();  
        out.close();  
        conn.close();  
        //   打开  
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

