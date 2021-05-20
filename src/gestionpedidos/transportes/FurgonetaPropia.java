package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.Mapa;
import anotacion.Programacion2; 
@Programacion2 (
	nombreAutor1 = "Carmen",
	apellidoAutor1 = "Clemente Lajo",
	emailUPMAutor1 = "carmen.clemente.lajo@alumnos.upm.es",
	nombreAutor2 = "Alessia",
	apellidoAutor2 = "Pérez Daniel", 
	emailUPMAutor2 = "alessia.perez@alumnos.upm.es"
)

public class FurgonetaPropia extends Furgoneta{
	
	private double velocidadMedia = 30;
	private static final double EUROS_P_HORA = 40;
	

	public FurgonetaPropia(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
	}


	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		double coste = this.getMapa().distancia(codPosDestino, codPosOrigen)*EUROS_P_HORA/velocidadMedia;
		if(this.getTara()>=500)
			coste *= 1.10;
		return coste;
	}
	
	public void setVelocidadMedia(double velocidadMedia) {
		this.velocidadMedia = velocidadMedia;
	}
	
	public double getVelocidadMedia() {
		return velocidadMedia;
	}

}
