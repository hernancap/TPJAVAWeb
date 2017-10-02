package entity;

import java.io.Serializable;

public class Elemento implements Serializable{
	
	private int idElem;
	private String nombre;
	private TipoElemento tipo;

	
	public Elemento(int IdElem, String nombre){	
		setNombre(nombre);
		setIdElem(IdElem);
		
	}

	public Elemento() {}

	public void setTipo(TipoElemento tipo){
		this.tipo=tipo;
	}
	
	public TipoElemento getTipo(){
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdElem() {
		return idElem;
	}

	public void setIdElem(int idElem) {
		this.idElem = idElem;
	}
	
	

}
