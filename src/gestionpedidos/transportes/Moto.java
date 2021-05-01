package gestionpedidos.transportes;
import gestionpedidos.mapa.*;

public class Moto extends Transporte{
	private  double eurosPKm = 2;
	private static final double TARIFA_MIN = 10;
	public Moto(String codigo, Mapa mapa ) {
		super(codigo,mapa);
		
	}
	public double coste(String codPosDestino, String codPosOrigen) {
		return this.getMapa().distancia(codPosDestino, codPosOrigen) * eurosPKm + TARIFA_MIN;
		
	}
	

}
