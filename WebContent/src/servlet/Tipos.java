package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlTipoElem;
import entity.Persona;
import entity.TipoElemento;

/**
 * Servlet implementation class Tipos
 */
@WebServlet({ "/Tipos", "/tipos", "/tipo", "/Tipo" })
public class Tipos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tipos() {
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
			
			TipoElemento te = new TipoElemento();
			
			te.setIdTipo(0);

			request.setAttribute("tipo", te);

			request.getRequestDispatcher("WEB-INF/altaTipo.jsp").forward(request, response);

			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "modifTipo":
			this.modificacion(request,response);
			break;
		case "agregarTipo":
			TipoElemento nuevoTipo = new TipoElemento();
			String nombre = request.getParameter("nombre");
			int idTipo = Integer.parseInt(request.getParameter("idTipo"));
			int cantMaxRes = Integer.parseInt(request.getParameter("cantMaxRes"));
			int horasMax = Integer.parseInt(request.getParameter("horasMax"));
			int diasAntic = Integer.parseInt(request.getParameter("diasAntic"));
			int soloEncarg;
			
			 if (request.getParameter("soloEncarg").equals("true")){
				 
				 soloEncarg  = 1;
			 } else {
				 
				 soloEncarg  = 0;
			 }
			
			boolean editar;
			
			 if (request.getParameter("editar").equals("true")){
				 
				editar  = true;
			 } else {
				 
				 editar  = false;
			 }
			
		
			CtrlTipoElem ctrlTipo = new CtrlTipoElem();
			
			nuevoTipo.setNombreTipo(nombre);
			nuevoTipo.setIdTipo(idTipo);
			nuevoTipo.setCantMaxRes(cantMaxRes);
			nuevoTipo.setHorasMax(horasMax);
			nuevoTipo.setMaxDiasAnticip(diasAntic);
			nuevoTipo.setSoloEncarg(soloEncarg);
			
			ctrlTipo.nuevoTipo(nuevoTipo, editar);
			
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
		
		int idTipo = Integer.parseInt(request.getParameter("idTipo"));
		
		CtrlTipoElem ctrlTipo = new CtrlTipoElem();
		
		TipoElemento te = new TipoElemento();
		
		te = ctrlTipo.buscarTipo(idTipo); 
		
		request.setAttribute("tipo", te);
		
		try {
			request.getRequestDispatcher("WEB-INF/altaTipo.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		
		int idTipo = Integer.parseInt(request.getParameter("idTipo"));
		
		CtrlTipoElem ctrlTipo = new CtrlTipoElem();
		
		ctrlTipo.eliminarTipo(idTipo);
		
		try {
			request.getRequestDispatcher("WEB-INF/eliminarReserva.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}

/*	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		

		
	}*/
	
/*	private void agregarElem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		

		
		
		
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
