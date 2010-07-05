package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EntryPoint
 */
public class EntryPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntryPoint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 * Siehe: http://kodejava.org/examples/32.html
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletContext context = getServletContext();
//	    WebApplicationContext applicationContext =
//	            WebApplicationContextUtils
//	            .getWebApplicationContext(context);

	    //Long userId = Long.valueOf(request.getParameter("user_id"));
	    //IUserService userService = (IUserService) applicationContext.getBean("userService");
	    // ersten User aus DB laden
	    //String username = userService.getCurrentAuthenticatedUser();

	    response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
	    //pw.print("Username: " + username);
	    pw.flush();
	} //doGet

}
