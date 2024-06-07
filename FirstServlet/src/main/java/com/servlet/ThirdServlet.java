package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ThirdServlet extends HttpServlet{
	public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {

		PrintWriter printWriter = httpServletResponse.getWriter();

		printWriter.write("Hello Third Servlet");
	}
}
