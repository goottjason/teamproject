package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConn {
	
	
	public static Connection getConnection() throws NamingException, SQLException {
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/mySQL");
		Connection conn = ds.getConnection();
		
		System.out.println(conn.toString());
		return conn;
		
	}
	
	public static void closeDB(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		rs.close();
		stmt.close();
		con.close();
	}
	
	public static void closeDB(Statement stmt, Connection con) throws SQLException {
		stmt.close();
		con.close();
	}
}
