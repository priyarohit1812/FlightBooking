package org.simplilearn.fms.utilities;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utility {
	public static void ShowAlert(HttpServletRequest req, HttpServletResponse resp, String dispatcherPath, String msg,
			String redirectPath) throws IOException, ServletException {
		RequestDispatcher rd = req.getRequestDispatcher(dispatcherPath);
		PrintWriter pw = resp.getWriter();
		pw.println("<script type=\"text/javascript\">");
		pw.println("alert('" + msg + "');");
		if (redirectPath != null && !redirectPath.trim().isEmpty()) {
			pw.println("window.location = '" + redirectPath + "';");
		}
		pw.println("</script>");
		rd.include(req, resp);
	}

}
