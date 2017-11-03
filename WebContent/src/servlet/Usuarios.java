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
 * Servlet implementation class Usuarios
 */
@WebServlet({ "/Usuarios", "/Usuario", "/usuario", "/usuarios" })
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
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
			
			request.getRequestDispatcher("WEB-INF/gestionUsuarios.jsp").forward(request, response);
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
			
			Persona pe = new Persona();
			
			pe.setId(0);
			

			request.setAttribute("persona", pe);

			request.getRequestDispatcher("WEB-INF/altaUsuarios.jsp").forward(request, response);

			break;
			
		case "baja":
			this.baja(request,response);
			break;
			
		case "modifPer":
			this.modificacion(request,response);
			break;
		case "agregarPer":
			Persona nuevaPer = new Persona();
			String dni = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String usu = request.getParameter("usu");
			String pass = request.getParameter("pass");
			String categ = request.getParameter("categ");
			int habilitado;
			
			int idPer;
						
			if (request.getParameter("idPer") == null){
				
				idPer = 0;
			} else {
				
				idPer = Integer.parseInt(request.getParameter("idPer"));
			}
			
			
			 if (request.getParameter("habilitado").equals("true")){
				 
				 habilitado  = 1;
			 } else {
				 
				 habilitado  = 0;
			 }
			
			boolean editar;
			
			 if (request.getParameter("editar").equals("true")){
				 
				editar  = true;
			 } else {
				 
				 editar  = false;
			 }
			
		
			CtrlPersona ctrlPer = new CtrlPersona();
			
			nuevaPer.setApellido(apellido);
			nuevaPer.setCategoria(categ);
			nuevaPer.setContraseña(pass);
			nuevaPer.setDni(dni);
			nuevaPer.setHabilitado(habilitado);
			nuevaPer.setId(idPer);
			nuevaPer.setNombre(nombre);
			nuevaPer.setUsuario(usu);
			
			
			ctrlPer.nuevaPersona(nuevaPer, editar);
			
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
		
		int idPer = Integer.parseInt(request.getParameter("idPer"));
		
		CtrlPersona ctrlPer = new CtrlPersona();
		
		Persona per = new Persona();
		
		per = ctrlPer.buscarPer(idPer); 
		
		request.setAttribute("persona", per);
		
		try {
			request.getRequestDispatcher("WEB-INF/altaUsuarios.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		
		int idPer = Integer.parseInt(request.getParameter("idPer"));
		
		CtrlPersona ctrlPer = new CtrlPersona();
		
		ctrlPer.eliminarPer(idPer);
		
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
