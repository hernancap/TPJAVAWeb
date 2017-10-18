package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlElemento;
import controlers.CtrlPersona;
import entity.Persona;

/**
 * Servlet implementation class Reservas
 */
@WebServlet({ "/Reservas", "/reservas", "/reserva", "/Reserva" })
public class Reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/eliminarReserva.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		if(request.getParameter("param").equals("welcome"))
		{		request.getRequestDispatcher("WEB-INF/agregarReserva.jsp").forward(request, response);
		
		} else {
		if(request.getParameter("param").equals("buscarElem")){
			
			String nomTipo=request.getParameter("nomTipo");
			String fechaRes=request.getParameter("datetimepicker");
			int tiempoUso= Integer.parseInt(request.getParameter("tiempoUso"));
			String horaRes=request.getParameter("horaRes");
			
			String fechaSelec = (fechaRes+" "+horaRes+":00");
			
			CtrlElemento ctrlElem = new CtrlElemento();
			
			
			request.setAttribute("listaElementos", ctrlElem.buscarElemento(fechaSelec, nomTipo, tiempoUso));
			
			request.getRequestDispatcher("WEB-INF/buscarElem.jsp").forward(request, response);
			

			
			
		}}
		//request.getRequestDispatcher("WEB-INF/prueba.jsp").forward(request, response);

		
		
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

		request.getRequestDispatcher("WEB-INF/eliminarReserva.jsp").forward(request, response);
		
	}

}