package entity;

import java.io.Serializable;

public class TipoElemento implements Serializable{
	
	private String nombreTipo;
	private int cantMaxRes;
	private int idTipo;
	private int maxDiasAnticip;
	private int soloEncarg;
	private int horasMax;
	
	
	public TipoElemento(int idTipo, String nombreTipo, int cantMaxRes, int maxDiasAnticip, int soloEncarg, int horasMax) {
		setIdTipo(idTipo);
		setCantMaxRes(cantMaxRes);
		setNombreTipo(nombreTipo);
		setMaxDiasAnticip(maxDiasAnticip);
		setSoloEncarg(soloEncarg);
		setHorasMax(horasMax);
		
	}

	public TipoElemento() {	}

	public int getCantMaxRes() {
		return cantMaxRes;
	}

	public void setCantMaxRes(int cantMaxRes) {
		this.cantMaxRes = cantMaxRes;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getMaxDiasAnticip() {
		return maxDiasAnticip;
	}

	public void setMaxDiasAnticip(int maxDiasAntip) {
		this.maxDiasAnticip = maxDiasAntip;
	}

	public int getSoloEncarg() {
		return soloEncarg;
	}

	public void setSoloEncarg(int soloEncarg) {
		this.soloEncarg = soloEncarg;
	}

	public int getHorasMax() {
		return horasMax;
	}

	public void setHorasMax(int horasMax) {
		this.horasMax = horasMax;
	}
	

}
