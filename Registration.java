package com.tap;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

  
    public Registration() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String department= request.getParameter("department");
		
		
		
		Employee e = new Employee(id,name,email,salary,department);
	
		DataBaseConn db = new DataBaseConn();
		
		int i = db.insertion(e);
		
		PrintWriter out=response.getWriter();
		
		if(i==1) {
			out.print("succesfully added the employee");
		}else {
			out.print("employee details not added to database!..please try again!!");
		}
		
	}

}
