package gestionpedidos.transportes;
import gestionpedidos.mapa.*;
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
public class Moto extends Transporte{
	private double eurosPKm = 2;
	public static final double TARIFA_MIN = 10;
	public Moto(String codigo, Mapa mapa ) {
		super(codigo,mapa);
		
	}
	@Override
	public double coste(String codPosDestino, String codPosOrigen) {
		return this.getMapa().distancia(codPosDestino, codPosOrigen) * eurosPKm + TARIFA_MIN;
		
	}
	public void setEurosPKm(double cantidad) {
		eurosPKm = cantidad;
	}
	public double getEurosPKm() {
		return eurosPKm;
	}

}
