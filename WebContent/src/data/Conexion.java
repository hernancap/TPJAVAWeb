package data;
import java.sql.*;

import org.apache.logging.log4j.Level;

import util.AppDataException;

public class Conexion {
	
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="newpass";
	private String db="nuevabd";
	
	private static Conexion instancia;
		
	private Conexion(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Conexion getInstancia(){
		if (Conexion.instancia == null){		
			Conexion.instancia=new Conexion();
		}
		return Conexion.instancia;
		
	}
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws SQLException,AppDataException{
		try {
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			}
		} catch (SQLException e) {
			throw new AppDataException(e, "Error al conectar a la base de datos", Level.ERROR);
		}
		cantConn++;
		System.out.println("Conectado");
		return conn;
	}
	
	public void releaseConn(){
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
