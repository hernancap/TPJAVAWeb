package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlPersona;
import entity.Persona;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Start() {
        // TODO Auto-generated constructor stub
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
			
			if (pers.getCategoria().equalsIgnoreCase("admin")){
				
				request.getRequestDispatcher("WEB-INF/menuAdmin.jsp").forward(request, response);
			} else {
			
			request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response); }
			//response.getWriter().append(user).append(" ").append(pass);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}