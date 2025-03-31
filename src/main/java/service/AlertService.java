package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AlertService {
	private static AlertService alertService = null;
	private AlertService() {}
	
	public static AlertService getInstance() {
		if(alertService == null) alertService = new AlertService();
		
		return alertService;
	}

	public void alertAndBack(HttpServletResponse res, String message) {
		try {
	        res.setContentType("text/html; charset=utf-8");
	        PrintWriter w = res.getWriter();
	        w.write("<script>alert('" + message + "');history.go(-1);</script>");
	        w.flush();
	        w.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void alertAndGo(HttpServletResponse res, String message, String url) {
		try {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter w = res.getWriter();
			w.write("<script>alert('" + message + "');location.href='" + url + "';</script>");
			w.flush();
			w.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}