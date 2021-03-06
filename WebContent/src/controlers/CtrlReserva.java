package controlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.internal.matchers.SubstringMatcher;

import data.Conexion;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElemento;
import util.AppDataException;

public class CtrlReserva {
	
	
	private ArrayList<Reserva> listrs;
	private Reserva nr;

	
	
	
	public ArrayList<Reserva> mostrarReservas(){
		
		

		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> res= new ArrayList<Reserva>();
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select * from reservas where fechayhora >= NOW()");
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reserva r =new Reserva();
					r.setTipo(new TipoElemento());
					r.setElemento(new Elemento());
					r.setPersona(new Persona());
					r.setId(rs.getInt("idreservas"));
					r.setFechayhora((rs.getString("fechayhora")).substring(0, 16));
					r.setTiempoUso(rs.getInt("tiempoUso"));
					r.getElemento().setIdElem(rs.getInt("idElementos"));
					r.getPersona().setId(rs.getInt("idUsuario"));
					r.getTipo().setNombreTipo(rs.getString("nomTipo"));
					r.setDetalle(rs.getString("detalle"));
					res.add(r);
				}
			}
		} catch (SQLException | AppDataException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		
		return res;

}
	
	public ArrayList<Reserva> mostrarReservas(int id){
		
		

		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Reserva> res= new ArrayList<Reserva>();
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select * from reservas where fechayhora >= NOW() and idUsuario = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reserva r =new Reserva();
					r.setTipo(new TipoElemento());
					r.setElemento(new Elemento());
					r.setPersona(new Persona());
					r.setId(rs.getInt("idreservas"));
					r.setFechayhora((rs.getString("fechayhora")).substring(0, 16));
					r.setTiempoUso(rs.getInt("tiempoUso"));
					r.getElemento().setIdElem(rs.getInt("idElementos"));
					r.getPersona().setId(rs.getInt("idUsuario"));
					r.getTipo().setNombreTipo(rs.getString("nomTipo"));
					r.setDetalle(rs.getString("detalle"));
					res.add(r);
				}
			}
		} catch (SQLException | AppDataException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		

		
		return res;

}
	
	public void nuevaReserva(Reserva nuevaRes){
		
		
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=Conexion.getInstancia().getConn()
					.prepareStatement(
					"insert into reservas(nomTipo, idElementos , fechayhora, tiempoUso, idUsuario, detalle)"
					+ " values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, nuevaRes.getTipo().getNombreTipo());
			stmt.setInt(2, nuevaRes.getElemento().getIdElem());
			stmt.setString(3, nuevaRes.getFechayhora());
			stmt.setInt(4, nuevaRes.getTiempoUso());
			stmt.setInt(5, nuevaRes.getPersona().getId());
			stmt.setString(6, nuevaRes.getDetalle());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				nuevaRes.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException | AppDataException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	
    
     

}
	
	
	public void eliminarRes(int ID){

	
		
		
		PreparedStatement stmt=null;

			try {
				stmt=Conexion.getInstancia().getConn()
						.prepareStatement(
						"delete from reservas where idreservas = ?"

						);
			stmt.setInt(1, ID);
			stmt.executeUpdate();				
				
			} catch (SQLException | AppDataException e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null)stmt.close();
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
		
	}
	
	public String textoReserva(Reserva r) throws Exception {
		String texto="ID\tTipo\tID Elemento\tFecha y Hora\tTiempo de Uso (hs)\tDetalle\n";


			texto= texto + r.getId() +"\t"+ r.getTipo().getNombreTipo() +"\t"+ r.getElemento().getIdElem() +"\t"+
			r.getFechayhora()+"\t"+r.getTiempoUso()+"\t"+r.getDetalle();

		return texto;
	}
	
	public String textoEliminarRes(int id) throws Exception {
		String texto="La Reserva con el ID: "+id+" fu� cancelada.";

		return texto;
	}
	
}
