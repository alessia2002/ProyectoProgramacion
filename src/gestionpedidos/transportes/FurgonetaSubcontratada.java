package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;

public class FurgonetaSubcontratada extends Furgoneta {
	
	private double eurosPKm = 1;
	
	public FurgonetaSubcontratada(String codigo, Mapa mapa, int tara) {
		super(codigo, mapa, tara);
	}

	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		return this.getMapa().distancia(codPosDestino, codPosOrigen)*eurosPKm*1.10;
	}
	
	public double getEurosPKm() {
		return eurosPKm;
	}
<<<<<<< HEAD
	public void setEuroPKm(double cantidad){
		this.eurosPKm = cantidad;
=======
	public void setEuroPKm(double eurosPKm){
		this.eurosPKm = eurosPKm;
>>>>>>> 4841f8d8214758371726983803f7b5cc43398cfd
	}
}
