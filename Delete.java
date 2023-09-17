package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	
	public static Connection connection;
	public static PreparedStatement statement;

    public static final String url = "jdbc:mysql://localhost:3306/jdbcclasses";
    public static final String user = "root";
    public static final String password = "root";

    public static String sql="DELETE FROM `employee1` WHERE `id`= ?";
    
    public Delete() {
        super();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    

    
    public static int delete(int id) {
    	
    	int i=0;
    	try {
    		statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			
			i = statement.executeUpdate();
			
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
    	
    }
    
    
    
    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int i = delete(id);
		
		PrintWriter out = response.getWriter();
		
		if(i==1) {
			out.print("successfully deleted the recodes.....!");
			
		}else {
			out.print("recorde is not deleted.....");
		}

	}

}
