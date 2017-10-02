package entity;

import java.io.Serializable;

public class Reserva implements Serializable{
	
	private int id;
	private TipoElemento tipo;
	private Elemento elem;
	private String fechayhora;
	private int tiempoUso;
	private Persona per;
	private String detalle;
	
	public TipoElemento getTipo(){
		return tipo;
	}
	
	public void setTipo(TipoElemento tipo){
		this.tipo=tipo;
	}
	
	public Elemento getElemento(){
		return elem;
	}
	
	public void setElemento(Elemento elem){
		this.elem=elem;
	}	
	
	public Persona getPersona(){
		return per;
	}
	
	public void setPersona(Persona per){
		this.per=per;
	}	
	
	public Reserva (int id,String fechayhora, int tiempoUso, String detalle){
		
		this.setId(id);
		this.setFechayhora(fechayhora);
		this.setTiempoUso(tiempoUso);
		this.setDetalle(detalle);

	}
	

	
	public Reserva(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechayhora() {
		return fechayhora;
	}

	public void setFechayhora(String fechayhora) {
		this.fechayhora = fechayhora;
	}

	public int getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(int tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


}
