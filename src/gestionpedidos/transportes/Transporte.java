package gestionpedidos.transportes;
import gestionpedidos.mapa.*;

public abstract class Transporte {
	private String codigo;
	private Mapa mapa;
	protected Transporte(String codigo, Mapa mapa) { 
		this.codigo = codigo;
		this.mapa =mapa;
	}
	public double coste( String codPosDestino ) {
		return coste(codPosDestino, codigo);
	}
	public abstract double coste(String codPosDestino, String codPosOrigen) ;
	public String getCodigo() {
		return codigo;
	}
	protected Mapa getMapa() { 
		return mapa;
	}
	

}
