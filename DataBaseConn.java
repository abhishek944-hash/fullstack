package com.tap;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConn {
	
	
	public static Connection connection;
	public static PreparedStatement statement;
	
	public static final String url="jdbc:mysql://localhost:3306/jdbcclasses";
	public static final String user="root";
	public static final String ps="root";
	public static final String INSERT_QUERY="INSERT INTO `employee1`(`id`,`name`,`email`,`salary`,`department`) VALUES(?,?,?,?,?)";
	
	public DataBaseConn() {
		super();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,ps);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insertion(Employee e) {
			 try {
				statement = connection.prepareStatement(INSERT_QUERY);
				
				
				statement.setInt(1,e.getId());
				statement.setString(2, e.getName());
				statement.setString(3, e.getEmail());
				statement.setInt(4, e.getSalary());
				statement.setString(5, e.getDepartment());
				
				int i = statement.executeUpdate();
				
				return i;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
	}
}
