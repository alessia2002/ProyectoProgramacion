package gestionpedidos;
//borrar Borrar Alessia Carmen 2 Alessia 3

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Transporte;

public class GestionReparto {
	// C�DIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;

	//C�DIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}

	// C�DIGO DE APOYO
	public String getEstadoGestorLocal(int i){
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}

	// C�DIGO DE APOYO
	public String getEstadoGestorLocalNum(int i){
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
	}

	public GestionReparto(Mapa mapa){
		//TO-DO
	}
	
	public void addTransporteLocalidad(Transporte transporte) {
		//TO-DO
	}
	
	private int seleccionarLocalidad(PosicionXY pos){
		//TO-DO
		return 0;
	}

	public void asignarPedido(Pedido pedido) {
		//TO-DO
	}

	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{
		//TO-DO
	}

}
