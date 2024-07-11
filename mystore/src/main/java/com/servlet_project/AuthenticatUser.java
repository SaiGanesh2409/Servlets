package com.servlet_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticatUser extends HttpServlet {
	

    public void service(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse) throws IOException {
    	
    	httpServletResponse.setContentType("text/html");
    	
        String userEmail = httpServletRequest.getParameter("email");
        String userPassword = httpServletRequest.getParameter("password");


        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xstore";
        String user = "system";
        String password = "Oracle_1";

        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("DB connected");

            String qry = "Select * from Users where email=?";
            stmt = connection.prepareStatement(qry);
            stmt.setString(1, userEmail);
            PrintWriter pw = httpServletResponse.getWriter();
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
            	String dbpwd = rs.getString("password");
            	
            	if(userPassword.equals(dbpwd)) {
            		pw.println("<h1>Log in successfull</h1>"+rs.getString("name"));
            	}else {
            		pw.println("<h1>Password is incorrect</h1>");
            	}
            }else {
            	pw.println("<h1>Email does not exists</h1>");
            }
            
        }catch(Exception e) {
        	
        }
    }

}
