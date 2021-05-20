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

public class FurgonetaSubcontratada extends Furgoneta {
	
	private double eurosPKm = 1;
	private  static final double  MAX_TARA = 1000;
	
	public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
	}

	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		if(this.getTara()< MAX_TARA)
			return this.getMapa().distancia(codPosDestino, codPosOrigen)*eurosPKm;
		return this.getMapa().distancia(codPosDestino, codPosOrigen)*eurosPKm*1.10;
	}
	
	public double getEurosPKm() {
		return eurosPKm;
	}

	public void setEurosPKm(double eurosPKm){
		this.eurosPKm = eurosPKm;
	}
}
