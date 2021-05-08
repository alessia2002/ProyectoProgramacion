package gestionpedidos.transportes;
import gestionpedidos.mapa.*;

public class Moto extends Transporte{
	private  double eurosPKm = 2;
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
