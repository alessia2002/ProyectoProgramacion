package gestionpedidos.transportes;

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
public abstract class Furgoneta extends Transporte{
	
	private double tara;
	
	/**
	 * Constructor que inicializa los atributos
	 * @param codigo
	 * @param mapa 
	 * @param tara
	 */
	protected Furgoneta(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa);
		this.tara = tara;
		
	}

	@Override
	public abstract double coste(String codPosDestino, String codPosOrigen);	

	
	public double getTara() {
		return tara;
	}

	
}
