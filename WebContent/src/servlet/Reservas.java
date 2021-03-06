package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlers.CtrlElemento;
import controlers.CtrlPersona;
import controlers.CtrlReserva;
import controlers.CtrlTipoElem;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElemento;
import util.AppDataException;
import util.Emailer;

/**
 * Servlet implementation class Reservas
 */
@WebServlet({ "/Reservas", "/reservas", "/reserva", "/Reserva" })
public class Reservas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Reserva nuevaRes = new Reserva();
       
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
		
		int idRes = Integer.parseInt(request.getParameter("idRes"));
		
		CtrlReserva ctrlRes = new CtrlReserva();
		
		ctrlRes.eliminarRes(idRes);
		
		ctrlRes.eliminarRes(idRes);
		
		try {
			Emailer.getInstance().send("hernancaparros@hotmail.com","Reserva cancelada",ctrlRes.textoEliminarRes(idRes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
			CtrlTipoElem ctrlTipo = new CtrlTipoElem();

			String a�oRes = fechaRes.substring(0, 4);
			String mesRes = fechaRes.substring(5, 7);
			String diaRes = fechaRes.substring(8, 10);
			
			Persona pers = (Persona) request.getSession().getAttribute("user");
			
			request.setAttribute("nomTipo", nomTipo);
			
			if (!ctrlTipo.validarAntip(a�oRes, mesRes, diaRes, nomTipo))
			{
				request.setAttribute("motivoError", "validarAntip");
				request.getRequestDispatcher("WEB-INF/problemaRes.jsp").forward(request, response);
				
			} else { if(!ctrlTipo.validarCantMaxRes(nomTipo, pers.getId())){
				
						request.setAttribute("motivoError", "validarCantMaxRes");
						request.getRequestDispatcher("WEB-INF/problemaRes.jsp").forward(request, response);
				
						} else { if(!ctrlTipo.validarEncarg(nomTipo, pers.getId())){
							
								request.setAttribute("motivoError", "validarEncarg");
								request.getRequestDispatcher("WEB-INF/problemaRes.jsp").forward(request, response);
							
								} else { if(!ctrlTipo.tiempoUso(nomTipo, tiempoUso)){
									
									request.setAttribute("motivoError", "tiempoUso");
									request.getRequestDispatcher("WEB-INF/problemaRes.jsp").forward(request, response);
									
										 } else {
											 
												

												
												String detalle =request.getParameter("detalle");
												
												
												//nuevaRes.setDetalle(textDet);				
												nuevaRes.setFechayhora(fechaSelec);
												nuevaRes.setTiempoUso(tiempoUso);
												nuevaRes.setTipo(new TipoElemento());
												nuevaRes.getTipo().setNombreTipo(nomTipo);
												nuevaRes.setPersona(new Persona());
												nuevaRes.getPersona().setId(pers.getId());
												nuevaRes.setDetalle(detalle);
												
												
												try {
													request.setAttribute("listaElementos", ctrlElem.buscarElemento(fechaSelec, nomTipo, tiempoUso));
												} catch (AppDataException ade) {
													request.setAttribute("Error", ade.getMessage());
												} catch (Exception e) {
													response.setStatus(502);
									}
												
												request.getRequestDispatcher("WEB-INF/buscarElem.jsp").forward(request, response);
											 
										 }
								 }
						 }
				
			 }


			

			
			
		}else {
			if(request.getParameter("param").equals("nuevaRes")){
			
				int idElem = Integer.parseInt(request.getParameter("idElem"));
				
				nuevaRes.setElemento(new Elemento());
				nuevaRes.getElemento().setIdElem(idElem);

				CtrlReserva ctrlRes = new CtrlReserva();
				
				
				ctrlRes.nuevaReserva(nuevaRes);
				
				try {
					Emailer.getInstance().send("hernancaparros@hotmail.com","Nueva reserva realizada",ctrlRes.textoReserva(nuevaRes));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("WEB-INF/confirmarReserva.jsp").forward(request, response);
				
			}

		}
		
		
		
		
		}
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

		
		
	}

}