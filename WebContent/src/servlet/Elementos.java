package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlElemento;
import controlers.CtrlReserva;
import entity.Persona;

/**
 * Servlet implementation class Elementos
 */
@WebServlet({ "/Elementos", "/Elemento", "/elemento", "/elementos" })
public class Elementos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Elementos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Persona p = new Persona();
		
		p = (Persona) request.getSession().getAttribute("user");
		
		if (p.getCategoria().equalsIgnoreCase("admin")){
			
			request.getRequestDispatcher("WEB-INF/gestionElementos.jsp").forward(request, response);
		} else {
		
		request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response); }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (request.getParameter("param")) {
		case "alta":
			this.alta(request,response);
			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "/modificacion":
			this.modificacion(request,response);
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

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
		//crear el controlador y ejecutar el modificar/update
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		
		int idElem = Integer.parseInt(request.getParameter("idElem"));
		
		CtrlElemento ctrlElem = new CtrlElemento();
		
		ctrlElem.eliminarElem(idElem);
		
		try {
			request.getRequestDispatcher("WEB-INF/eliminarReserva.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		
		try {
			request.getRequestDispatcher("WEB-INF/altaElem.jsp").forward(request, response);
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
