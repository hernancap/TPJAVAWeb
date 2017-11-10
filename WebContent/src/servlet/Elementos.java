package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlElemento;
import controlers.CtrlReserva;
import entity.Elemento;
import entity.Persona;
import entity.TipoElemento;

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
			
			Elemento el = new Elemento();
			
			el.setIdElem(0);

			request.setAttribute("elemento", el);

			request.getRequestDispatcher("WEB-INF/altaElem.jsp").forward(request, response);

			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "modifElem":
			this.modificacion(request,response);
			break;
		case "agregarElem":
			Elemento nuevoElem = new Elemento();
			String nomTipo = request.getParameter("nomTipo");
			String nombre = request.getParameter("nombre");
			int idElem;
			
			if (request.getParameter("idElem") == null){
				
				idElem = 0;
			} else {
				
				idElem = Integer.parseInt(request.getParameter("idElem"));
			}
			
			
			 
			
			boolean editar;
			
			 if (request.getParameter("editar").equals("true")){
				 
				editar  = true;
			 } else {
				 
				 editar  = false;
			 }
			
		
			CtrlElemento ctrlElem = new CtrlElemento();
			
			nuevoElem.setNombre(nombre);
			nuevoElem.setTipo(new TipoElemento());
			nuevoElem.getTipo().setNombreTipo(nomTipo);
			nuevoElem.setIdElem(idElem);
			
			ctrlElem.nuevoElem(nuevoElem, editar);
			
			request.getRequestDispatcher("WEB-INF/confirmarReserva.jsp").forward(request, response);
			
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
		
		int idElem = Integer.parseInt(request.getParameter("idElem"));
		
		CtrlElemento ctrlElem = new CtrlElemento();
		
		Elemento el = new Elemento();
		
		el = ctrlElem.buscarElem(idElem);
		
		request.setAttribute("elemento", el);
		
		try {
			request.getRequestDispatcher("WEB-INF/altaElem.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
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

/*	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		
		Elemento el = new Elemento();

		request.setAttribute("elemento", el);
		
		
		try {
			request.getRequestDispatcher("WEB-INF/altaElem.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}*/
	
/*	private void agregarElem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		
		Elemento nuevoElem = new Elemento();
		String nomTipo = request.getParameter("nomTipo");
		String nombre = request.getParameter("nombre");
		
		boolean editar;
		
		 if (request.getParameter("editar").equals("true")){
			 
			editar  = true;
		 } else {
			 
			 editar  = false;
		 }
		
	
		CtrlElemento ctrlElem = new CtrlElemento();
		
		nuevoElem.setNombre(nombre);
		nuevoElem.setTipo(new TipoElemento());
		nuevoElem.getTipo().setNombreTipo(nomTipo);
		
		ctrlElem.nuevoElem(nuevoElem, editar);
		
		
		
	}*/
	

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
