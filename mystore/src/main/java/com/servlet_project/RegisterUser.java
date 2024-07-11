package com.servlet_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterUser extends HttpServlet {

    private static final long serialVersionUID = -7852276964641102825L;

    public void service(HttpServletRequest httpServletRequest, 
            HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/html");
    	
        String userName = httpServletRequest.getParameter("name");
        String userContact = httpServletRequest.getParameter("contact");
        String userEmail = httpServletRequest.getParameter("email");
        String userAddress = httpServletRequest.getParameter("address");
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

            String qry = "INSERT INTO users(name, contact, email, address, password) VALUES (?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(qry);
            stmt.setString(1, userName);
            stmt.setString(2, userContact);
            stmt.setString(3, userEmail);
            stmt.setString(4, userAddress);
            stmt.setString(5, userPassword); // Corrected this line to set the password

            stmt.executeUpdate();
            System.out.println("Query executed");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        PrintWriter pw = httpServletResponse.getWriter();
        pw.println("User Registered Successfully");
        pw.println("<h1><a href = \"/servlet_project/Login.html\">Log in</a></h1>");

    }
}
