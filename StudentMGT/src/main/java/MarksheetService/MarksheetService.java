package MarksheetService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Marksheet.Vo.LoginVo;
import Marksheet.Vo.MarksheetVo;




//	import com.mysql.jdbc.ResultSet;

public class MarksheetService {
	
	private static final String INSERT_Marksheet_SQL = "insert into marksheet " + "  (rollNo,name,physics,maths,chemistry,hindi,english) VALUES "
			+ " (?,?,?,?,?,?,?);";
	
	MarksheetVo vo=new MarksheetVo();

	public void save(MarksheetVo vo) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement(INSERT_Marksheet_SQL);
		preparedStatement.setInt(1, vo.getRollNo());
		preparedStatement.setString(2, vo.getName());
		preparedStatement.setInt(3, vo.getPhysics());
		preparedStatement.setInt(4, vo.getMaths());
		preparedStatement.setInt(5, vo.getChemistry());
		preparedStatement.setInt(6, vo.getHindi());
		preparedStatement.setInt(7, vo.getEnglish());
		preparedStatement.executeUpdate();

	}
	public boolean createTable() throws Exception {
		boolean b = false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		
		Statement stmt = conn.createStatement();
		
		String sql = "CREATE TABLE marksheet " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
				  " rollNo INTEGER, " + 
                 " name VARCHAR(255), " + 
                 " physics INTEGER, " + 
				   " maths INTEGER, " + 
				   " chemistry INTEGER, " + 
				   " hindi INTEGER, " + 
				   " english INTEGER, " + 
                " PRIMARY KEY ( id ))";  

         stmt.executeUpdate(sql);
		return b;
	}
public boolean checkTable() throws Exception {
	boolean value=false;
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/table1", "root", "root");

		conn.setAutoCommit(false);
		
		Statement stmt = conn.createStatement();
		
			 String sql = "show tables like 'marksheet'";
        System.out.println("sql is ----"+sql);
		ResultSet rs= stmt.executeQuery(sql);
        //System.out.println("Created table in given database..."+y);
		if(rs.getFetchSize() == 0){
			value =true;
		}
		else {
			value =false;
		//	System.out.println("rsmd.getColumnCount()---"+rsmd.getColumnCount());
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
		
	}

	public void delete(MarksheetVo vo) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement ps = connection
				.prepareStatement("delete from marksheet where RollNo=?");
		ps.setInt(1, vo.getRollNo());
		ps.executeUpdate();
	}

	public void update(MarksheetVo vo) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");

		PreparedStatement preparedStatement = connection
				.prepareStatement("update marksheet set name=?,physics=?,maths=?,chemistry=?, hindi=?,english=? where rollNo=?");
		preparedStatement.setString(1, vo.getName());
		preparedStatement.setInt(2, vo.getPhysics());
		preparedStatement.setInt(3, vo.getMaths());
		preparedStatement.setInt(4, vo.getChemistry());
		preparedStatement.setInt(5, vo.getHindi());
		preparedStatement.setInt(6, vo.getEnglish());
		preparedStatement.setInt(7, vo.getRollNo());
		preparedStatement.executeUpdate();

	}

	public MarksheetVo get(MarksheetVo vo) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from marksheet where rollNo=?");
		preparedStatement.setInt(1, vo.getRollNo());
		java.sql.ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			vo.setRollNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setPhysics(rs.getInt(3));
			vo.setMaths(rs.getInt(4));
			vo.setChemistry(rs.getInt(5));
			vo.setHindi(rs.getInt(6));
			vo.setEnglish(rs.getInt(7));

		}

		return vo;

	}

	public ArrayList getList() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from Marksheet");

		java.sql.ResultSet rs = preparedStatement.executeQuery();

		ArrayList list = new ArrayList();

		while (rs.next()) {
			MarksheetVo vo = new MarksheetVo();
			vo.setRollNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setMaths(rs.getInt(3));
			vo.setPhysics(rs.getInt(4));
			vo.setChemistry(rs.getInt(5));
			vo.setHindi(rs.getInt(6));
			vo.setEnglish(rs.getInt(7));

			list.add(vo);
		}

		return list;

	}

	public ArrayList search1(MarksheetVo vo) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from marksheet WHERE name LIKE ?");

		preparedStatement.setString(1, "%" + vo.getName() + "%");

		java.sql.ResultSet rs = preparedStatement.executeQuery();

		ArrayList list = new ArrayList();

		while (rs.next()) {
			vo = new MarksheetVo();
			vo.setRollNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setMaths(rs.getInt(3));
			vo.setPhysics(rs.getInt(4));
			vo.setChemistry(rs.getInt(5));
			vo.setHindi(rs.getInt(6));
			vo.setEnglish(rs.getInt(7));

			list.add(vo);
		}

		return list;

	}

	public ArrayList getListByPage(int first, int max) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/table1", "root", "root");
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from marksheet limit ?, ?");

		preparedStatement.setInt(1, first);
		preparedStatement.setInt(2, max);

		java.sql.ResultSet rs = preparedStatement.executeQuery();

		ArrayList list = new ArrayList();

		while (rs.next()) {
			MarksheetVo vo = new MarksheetVo();
			vo.setRollNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setMaths(rs.getInt(3));
			vo.setPhysics(rs.getInt(4));
			vo.setChemistry(rs.getInt(5));
			vo.setHindi(rs.getInt(6));
			vo.setEnglish(rs.getInt(7));

			list.add(vo);
		}

		return list;
	}
}
