package com.mystore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Products extends HttpServlet {

    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        String productId = httpServletRequest.getParameter("pid");
        String productName = httpServletRequest.getParameter("pname");
        String productCat = httpServletRequest.getParameter("cat");
        String productQty = httpServletRequest.getParameter("qty");

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xstore";
        String userName = "system";
        String password = "Oracle_1";

        Connection connection = null;
        PreparedStatement stmt = null;
        PrintWriter pw = httpServletResponse.getWriter();

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);

            System.out.println("DB connected");

            String qry = "insert into products values(?,?,?,?)";
            stmt = connection.prepareStatement(qry);
            stmt.setString(1, productId);
            stmt.setString(2, productName);
            stmt.setString(3, productCat);
            stmt.setString(4, productQty);

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

        pw.println("ProductId : " + productId);
        pw.println("Product Name : " + productName);
        pw.println("Product Catagory : " + productCat);
        pw.println("Product Quantity : " + productQty);
    }
}
