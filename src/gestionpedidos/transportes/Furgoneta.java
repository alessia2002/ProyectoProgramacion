package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;

public abstract class Furgoneta extends Transporte{
	
	private int tara;
	
	public Furgoneta(String codigo, Mapa mapa, int tara) {
		super(codigo, mapa);
		this.tara = tara;
		
	}

	@Override
	public abstract double coste(String codPosDestino, String codPosOrigen);	

	public double getTara() {
		return tara;
	}

	
}
