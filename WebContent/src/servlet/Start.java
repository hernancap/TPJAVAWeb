package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlPersona;
import entity.Persona;

import util.AppDataException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;

    /**
     * Default constructor. 
     */
    public Start() {
    	logger = LogManager.getLogger(getClass());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				
		Persona p = new Persona();
		
		p = (Persona) request.getSession().getAttribute("user");
		
		if (p.getCategoria().equalsIgnoreCase("admin")){
			
			request.getRequestDispatcher("WEB-INF/menuAdmin.jsp").forward(request, response);
		} else {
		
		request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response); }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			
			CtrlPersona ctrl= new CtrlPersona();
			
			Persona pers=ctrl.login(user, pass);
			
			//request.setAttribute("listaPersonas", ctrl.mostrarPersona());
			
			request.getSession().setAttribute("user", pers);
			
			logger.log(Level.INFO,"log in "+pers.getDni());
			
			if (pers.getHabilitado() == 1)
				
			{
			
			if (pers.getCategoria().equalsIgnoreCase("admin")){
				
				request.getRequestDispatcher("WEB-INF/menuAdmin.jsp").forward(request, response);
			} else {
			
			request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response); }
			
			}
			
			else { response.sendRedirect("noHab.html");}
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}