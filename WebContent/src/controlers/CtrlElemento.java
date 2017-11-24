package controlers;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.Conexion;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElemento;
import util.AppDataException;

import org.apache.logging.log4j.Level;

public class CtrlElemento {
	
private ArrayList<Elemento> elem;

	
	public ArrayList<Elemento>  buscarElemento(String fechaSelec, String teSelec, int tiempoRes) throws Exception{
		
		ArrayList<Elemento> elemDisp = new ArrayList<Elemento>();

		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("Select ele.idelemento, ele.nombre from elementos ele "
					+ "inner Join tipos tip on ele.tipoElem = tip.nombre "
					+ "left join reservas res on res.idElementos = ele.idelemento"
					+ " and (? Between res.fechayhora and Date_Add(res.fechayhora, INTERVAL res.tiempoUso HOUR)"
					+ "or Date_Add(?, INTERVAL ? HOUR) between res.fechayhora And Date_Add(res.fechayhora, INTERVAL res.tiempoUso HOUR))"
							+ "where res.idreservas is null and tip.nombre = ?");

			stmt.setString(1, fechaSelec);
			stmt.setString(2, fechaSelec);
			stmt.setInt(3, tiempoRes);
			stmt.setString(4, teSelec);
			rs = stmt.executeQuery();

		
			if(rs != null){
				while(rs.next()){
					Elemento e = new Elemento();
					e.setTipo(new TipoElemento());
					e.getTipo().setNombreTipo(teSelec);
					e.setIdElem(rs.getInt("ele.idelemento"));
					e.setNombre(rs.getString("ele.nombre"));					
					elemDisp.add(e);

				}
			}
			
		} catch (SQLException e) {
			
			AppDataException ade=new AppDataException(e, "Error al recuperar listado de Personas.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (AppDataException ade) {
			// TODO Auto-generated catch block
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
}
		
	return elemDisp;

}
	
	public void add(Elemento el) {
		this.elem.add(el);
	}
	
	public void delete(Elemento el){
		this.elem.remove(el);
	}
	
	public void update(Elemento el){
		this.delete(el);
		this.add(el);
	}

	public ArrayList<Elemento> mostrarElem() {
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> elem= new ArrayList<Elemento>();
		try {
			stmt = Conexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select * from elementos");
			if(rs!=null){
				while(rs.next()){
					Elemento el =new Elemento();
					el.setTipo(new TipoElemento());
					el.setIdElem(rs.getInt("idelemento"));
					el.getTipo().setNombreTipo(rs.getString("tipoElem"));
					el.setNombre(rs.getString("nombre"));
					
					elem.add(el);
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
		return elem;


		
		
		
	}

	public void eliminarElem(int idElem) {

	
		
		
		PreparedStatement stmt=null;

			try {
				stmt=Conexion.getInstancia().getConn()
						.prepareStatement(
						"delete from elementos where idelemento = ?"

						);
			stmt.setInt(1, idElem);
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

	public void nuevoElem(Elemento elem2, boolean editar) {
		
		if (elem2.getNombre().equals(""))
		{
			elem2.setNombre(elem2.getTipo().getNombreTipo());
		}
		
		
		if (editar == false){
		
		
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=Conexion.getInstancia().getConn()
					.prepareStatement(
					"insert into elementos(nombre, tipoElem)"
					+ " values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, elem2.getNombre());
			stmt.setString(2, elem2.getTipo().getNombreTipo());

			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				elem2.setIdElem(keyResultSet.getInt(1));
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

		} else{
			
			
			PreparedStatement stmt=null;

				try {
					stmt=Conexion.getInstancia().getConn()
							.prepareStatement(
					"update elementos set nombre = ?, tipoElem = ? where idelemento = ?;"


							);
				

				stmt.setString(1, elem2.getNombre());
				stmt.setString(2, elem2.getTipo().getNombreTipo());
				stmt.setInt(3, elem2.getIdElem());


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
	
    
	
		
	
		
		
		
	}
	
	public Elemento buscarElem(int id) {
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Elemento el = new Elemento();
		el.setIdElem(id);
		try {
			stmt=Conexion.getInstancia().getConn().prepareStatement("select * from elementos where idelemento=?");
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
			el.setNombre(rs.getString("nombre"));
			el.setTipo(new TipoElemento());
			el.getTipo().setNombreTipo(rs.getString("tipoElem"));
						
			}	
			
		} catch (SQLException | AppDataException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			Conexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		  }
		
		
		return el;


		
		
		
	}

}
