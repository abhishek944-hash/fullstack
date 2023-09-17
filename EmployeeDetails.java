package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeDetails extends HttpServlet {
      
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Connection connection;
	public static PreparedStatement statement;
	public static ResultSet resultSet;
	
	public static final String url="jdbc:mysql://localhost:3306/jdbcclasses";
	public static final String user="root";
	public static final String ps="root";
    
    public EmployeeDetails() {
        super();
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,ps);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        try {
	           

	            String query = "SELECT * FROM employee1";
	            statement = connection.prepareStatement(query);
	            resultSet = statement.executeQuery();

	            out.println("<html>");
	            out.println("<head><title>Employee Details</title></head>");
	            out.println("<body style=\"text-align: center; background-color: FDFCCE\">"); 

	            out.println("<h1 style=\"color: 09090B;\">Employee Details</h1>");

	            out.println("<table border='1' style=\"margin: 0 auto; background-color: FFFFFF; box-shadow: 0px 10px 10px rgb(0, 0, 0.1)\">"); 
	            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Salary</th><th>Department</th></tr>");

	            while (resultSet.next()) {
	                out.println("<tr>");
	                out.println("<td>" + resultSet.getInt("id") + "</td>");
	                out.println("<td>" + resultSet.getString("name") + "</td>");
	                out.println("<td>" + resultSet.getString("email") + "</td>");
	                out.println("<td>" + resultSet.getInt("salary") + "</td>");
	                out.println("<td>" + resultSet.getString("department") + "</td>");
	                out.println("</tr>");
	            }

	            out.println("</table>");

	            out.println("<a href=\"index.html\"><input type=\"button\" value=\"Home\" style=\"display: block; margin: 20px auto; background-color: #007bff; color: #fff; padding: 10px 20px; border: none; cursor: pointer; text-align: center; text-decoration: none; border-radius: 5px;\"> </a>");

	            out.println("</body>");
	            out.println("</html>");


	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

}
