package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;

public class FurgonetaSubcontratada extends Furgoneta {
	
	private static double eurosPKm = 1;
	
	public FurgonetaSubcontratada(String codigo, Mapa mapa, int tara) {
		super(codigo, mapa, tara);
	}

	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		return this.getMapa().distancia(codPosDestino, codPosOrigen)*eurosPKm*1.10;
	}
	
	public static double getEurosPKm() {
		return eurosPKm;
	}

}
