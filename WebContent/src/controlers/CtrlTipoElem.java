package controlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import data.Conexion;
import entity.Elemento;
import entity.Persona;
import entity.TipoElemento;

public class CtrlTipoElem {
	
	private ArrayList<TipoElemento> tipos;
	
	public void add(TipoElemento te) {
		this.tipos.add(te);
	}
	
	public void delete(TipoElemento te){
		this.tipos.remove(te);
	}
	
	public void update(TipoElemento te){
		this.delete(te);
		this.add(te);
	}
	
	public ArrayList<TipoElemento> mostrarTipos(){
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tipos= new ArrayList<TipoElemento>();
		try {
			stmt = Conexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from tipos");
			if(rs!=null){
				while(rs.next()){
					TipoElemento te =new TipoElemento();
					te.setIdTipo(rs.getInt("idtipo"));
					te.setCantMaxRes(rs.getInt("cantMaxRes"));
					te.setNombreTipo(rs.getString("nombre"));
					te.setMaxDiasAnticip(rs.getInt("maxDiasAnticip"));
					te.setSoloEncarg(rs.getInt("soloEncarg"));
					te.setHorasMax(rs.getInt("horasMax"));
					
					tipos.add(te);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		return tipos;

}

	public boolean validarAntip(String año, String mes, String dia, String teSelec) {

		ArrayList<TipoElemento> tipos= new ArrayList<TipoElemento>();
		String fechaSel = (año+"-"+mes+"-"+dia);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calSelec  = Calendar.getInstance();
		try {
			calSelec.setTime(format.parse(fechaSel));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar fechaAct = Calendar.getInstance();
		long difDias = daysBetween(fechaAct, calSelec);
		tipos = mostrarTipos();
		for( int x = 0 ; x  < tipos.size(); x++){

			
            if(tipos.get(x).getNombreTipo().equals(teSelec)){
            	
            	if(tipos.get(x).getMaxDiasAnticip()<difDias || tipos.get(x).getMaxDiasAnticip() == 0){
            		
            		return true;
            	}
            	
            	else{return false;}
            }                      		
		}
		
		return false;
		
	}
	
	public static long daysBetween(Calendar startDate, Calendar endDate) {
	    long end = endDate.getTimeInMillis();
	    long start = startDate.getTimeInMillis();
	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	}

	public void eliminarTipo(int idTipo) {

	
		
		
		PreparedStatement stmt=null;

			try {
				stmt=Conexion.getInstancia().getConn()
						.prepareStatement(
						"delete from tipos where idtipo = ?"

						);
			stmt.setInt(1, idTipo);
			stmt.executeUpdate();				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null)stmt.close();
				Conexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
	
		
	}

	public void nuevoTipo(TipoElemento tip, boolean editar) {
		
		if (editar == false){
		
		
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=Conexion.getInstancia().getConn()
					.prepareStatement(
					"insert into tipos(nombre, cantMaxRes, horasMax, maxDiasAnticip, soloEncarg)"
					+ " values (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, tip.getNombreTipo());
			stmt.setInt(2, tip.getCantMaxRes());
			stmt.setInt(3, tip.getHorasMax());
			stmt.setInt(4, tip.getMaxDiasAnticip());
			stmt.setInt(5, tip.getSoloEncarg());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				tip.setIdTipo(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		} else{
			
			
			PreparedStatement stmt=null;

				try {
					stmt=Conexion.getInstancia().getConn()
							.prepareStatement(
					"update tipos set nombre = ? , cantMaxRes = ? ,horasMax = ? , maxDiasAnticip = ? , soloEncarg = ? where idtipo = ?;"


							);
				
				stmt.setString(1, tip.getNombreTipo());
				stmt.setInt(2, tip.getCantMaxRes());
				stmt.setInt(3, tip.getHorasMax());
				stmt.setInt(4, tip.getMaxDiasAnticip());
				stmt.setInt(5, tip.getSoloEncarg());
				stmt.setInt(6, tip.getIdTipo());

				stmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(stmt!=null)stmt.close();
					Conexion.getInstancia().releaseConn();
				} catch (SQLException e) {
					e.printStackTrace();
				}


			
		}
	
    
	
		
	}
	
	public TipoElemento buscarTipo(int id) {
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		TipoElemento te = new TipoElemento();
		te.setIdTipo(id);
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select * from tipos where idtipo=?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
			te.setIdTipo(rs.getInt("idtipo"));
			te.setCantMaxRes(rs.getInt("cantMaxRes"));
			te.setNombreTipo(rs.getString("nombre"));
			te.setMaxDiasAnticip(rs.getInt("maxDiasAnticip"));
			te.setSoloEncarg(rs.getInt("soloEncarg"));
			te.setHorasMax(rs.getInt("horasMax"));
						
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		  }
		
		
		return te;


		
		
		
	}
	
	public boolean validarCantMaxRes(String teSelec, int id){
		
		int cantMaxTipo = 0;
		int cantRes = 0;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select cantMaxRes from tipos where nombre = ?");
			
					stmt.setString(1, teSelec);
					rs = stmt.executeQuery();
					
					if(rs != null){
						while(rs.next()){
							cantMaxTipo = rs.getInt("cantMaxRes");

						}
					}
					
					stmt=null;
					rs=null;
					
					stmt=Conexion.getInstancia().getConn().prepareStatement("select idUsuario, nomTipo, count(nomTipo) cant "
							+ "from reservas where now() < fechayhora group by idUsuario, nomTipo having idUsuario = ? and nomTipo = ?");
					
					stmt.setInt(1,  id);
					stmt.setString(2, teSelec);
					rs = stmt.executeQuery();

					if(rs != null){
						while(rs.next()){
							
							cantRes = rs.getInt("cant");

						}
					}
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		
		if (cantRes < cantMaxTipo){
			
			return true;			
		} else { return false;}
			
	}
	
	public boolean validarEncarg(String teSelec, int id) {
		
		String cat = null;
		int soloEncarg = 0;
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select categoria from personas where idpersona = ?");
			
					stmt.setInt(1, id);
					rs = stmt.executeQuery();
					
					if(rs != null){
						while(rs.next()){
							cat = rs.getString("categoria");

						}
					}
					
					stmt=null;
					rs=null;
					
					stmt=Conexion.getInstancia().getConn().prepareStatement("select soloEncarg from tipos where nombre = ?");
					
					stmt.setString(1, teSelec);
					rs = stmt.executeQuery();

					if(rs != null){
						while(rs.next()){
							
							soloEncarg = rs.getInt("soloEncarg");

						}
					}
				
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		
		
		if( cat.equals("Encargado") || soloEncarg == 0 ){
			return true;					
		} else {return false;}
		
		
	}
	
	public boolean tiempoUso(String teSelec, int horasRes) {
		
		int horasMax = 0;
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select horasMax from tipos where nombre = ?");
			
					stmt.setString(1, teSelec);
					rs = stmt.executeQuery();
					
					if(rs != null){
						while(rs.next()){
							horasMax = rs.getInt("horasMax");

						}
					}
					
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		
		
		if( horasMax >= horasRes || horasMax == 0){
			return true;					
		} else {return false;}
		
		
	
		
	}
	

	
}
