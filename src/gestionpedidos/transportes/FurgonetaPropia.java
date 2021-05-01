package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;

public class FurgonetaPropia extends Furgoneta{
	
	private static double velocidadMedia = 30;
	private static final double EUROS_P_HORA = 40;
	

	public FurgonetaPropia(String codigo, Mapa mapa, int tara) {
		super(codigo, mapa, tara);
	}


	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		if(this.getTara()<500)
			return this.getMapa().distancia(codPosDestino, codPosOrigen)*EUROS_P_HORA/velocidadMedia;
		else
			return this.getMapa().distancia(codPosDestino, codPosOrigen)*EUROS_P_HORA/velocidadMedia*1.10;
	}

}
