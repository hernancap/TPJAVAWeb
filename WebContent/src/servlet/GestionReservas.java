package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlReserva;
import entity.Persona;
import util.Emailer;

/**
 * Servlet implementation class GestionReservas
 */
@WebServlet({ "/GestionReservas", "/gestionReservas", "/gestionReserva", "/GestionReserva" })
public class GestionReservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionReservas() {
        super();
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
			
			request.getRequestDispatcher("WEB-INF/gestionReservas.jsp").forward(request, response);
		} else {
		
		request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response); }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getParameter("param")) {
		case "baja":
			this.baja(request,response);
			break;
		default:
			this.error(request,response);
			break;
		}
	}

	private void error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
		//redirigir a página de error
	}
	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
int idRes = Integer.parseInt(request.getParameter("idRes"));
		
		CtrlReserva ctrlRes = new CtrlReserva();
		
		ctrlRes.eliminarRes(idRes);
		
		try {
			Emailer.getInstance().send("hernancaparros@hotmail.com","Reserva cancelada",ctrlRes.textoEliminarRes(idRes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			request.getRequestDispatcher("WEB-INF/eliminarReserva.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
