package MarksheetService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import Marksheet.Vo.LoginVo;



public class LoginService {

	public boolean login(LoginVo vo) throws Exception {
		boolean b = false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		String sql = "CREATE TABLE login " +
                   "(id INTEGER not NULL AUTO_INCREMENT, " +
                   " userId VARCHAR(255), " + 
                   " password VARCHAR(255), " + 
                   " PRIMARY KEY ( id ))"; 

         stmt.executeUpdate(sql);
         
         PreparedStatement preparedStatement = conn
 				.prepareStatement("insert into login (userId,password)values(?,?)");
         						 
 		preparedStatement.setString(1, vo.getUserId());
 		preparedStatement.setString(2, vo.getPassword());
 		preparedStatement.executeUpdate();
		
		
		preparedStatement = conn
				.prepareStatement("select * from login  where  userId=? and password =?");
		preparedStatement.setString(1, vo.getUserId());
		preparedStatement.setString(2, vo.getPassword());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			b = true;
		}
		return b;

	}
public boolean checkTable() {
		
		boolean value=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/table1", "root", "root");

		conn.setAutoCommit(false);
		
		//Statement stmt = conn.createStatement();
		
		PreparedStatement ps=conn.prepareStatement("show tables like '%login'");  
		ResultSet rs=ps.executeQuery();  
		System.out.println("rs.getFetchSize() "+rs.getFetchSize());
		System.out.println("rs.getFetchSize() "+rs.getStatement());
		
		
		//ResultSetMetaData rsmd=rs.getMetaData();  
		//System.out.println("Total columns: "+rsmd.getTableName(0));
		
		if(rs.getFetchSize() == 0){
			value =true;
		}
		else {
			value =false;
		//	System.out.println("rsmd.getColumnCount()---"+rsmd.getColumnCount());
		}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return value;
		
	}

}
