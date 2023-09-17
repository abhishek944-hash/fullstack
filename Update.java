package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.css.CSSStyleDeclaration;

public class Update extends HttpServlet {

    public static Connection connection;
    public static PreparedStatement statement;

    public static final String url = "jdbc:mysql://localhost:3306/jdbcclasses";
    public static final String user = "root";
    public static final String password = "root";

    public Update() {
        super();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("employee_id"));
        String option = request.getParameter("option");
        

        try {
            if (option.equalsIgnoreCase("Salary")) {
                statement = connection.prepareStatement("UPDATE `employee1` SET `salary` = `salary` + ? WHERE `id` = ?");
                String input = request.getParameter("value");
                int intValue = 0;
                
                
                if (input != null) {
                    try {
                        intValue = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                statement.setInt(1, intValue);
                statement.setInt(2, id);
                
                
            } else if (option.equalsIgnoreCase("Name")) {
            	
                statement = connection.prepareStatement("UPDATE `employee1` SET `name` = ? WHERE `id` = ?");
                String input = request.getParameter("value");
                statement.setString(1, input);
                statement.setInt(2, id);
                
            } else if (option.equalsIgnoreCase("Email")) {
            	
                statement = connection.prepareStatement("UPDATE `employee1` SET `email` = ? WHERE `id` = ?");
                String input = request.getParameter("value");
                statement.setString(1, input);
                statement.setInt(2, id);
                
            } else if (option.equalsIgnoreCase("Department")) {
            	
                statement = connection.prepareStatement("UPDATE `employee1` SET `Department` = ? WHERE `id` = ?");
                String input = request.getParameter("value");
                statement.setString(1, input);
                statement.setInt(2, id);                
                
            }
            
            int i = statement.executeUpdate();
            

            PrintWriter out = response.getWriter();
            
            if (i == 1) {
                out.print("Successfully updated " + option);
            } else {
                out.print("Updation did not happen.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

