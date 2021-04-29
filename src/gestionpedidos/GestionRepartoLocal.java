package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import list.IList;
import queues.IQueue;
import queues.CircularQueue;


public class GestionRepartoLocal {	
	// CÓDIGO DE APOYO
	private IList<Moto> motosDisponibles;
	private IList<Furgoneta> furgonetasDisponibles;

	private IQueue<Pedido> pedidosEsperandoMoto;
	private IQueue<Pedido> pedidosEsperandoFurgoneta;	
	
	
	// CÓDIGO DE APOYO
	public String getDisponibles(){
		return "Motos Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
				GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles)) + System.lineSeparator() +
			"Furgonetas Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
					GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles)) + System.lineSeparator();
			
	}
	
	// CÓDIGO DE APOYO
	public String getEsperando(){
		return "Pedidos esperando moto:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
				GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto)) + System.lineSeparator() +
				"Pedidos esperando furgoneta:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
						GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
	}
	
	// CÓDIGO DE APOYO
	public IList<String> getCodMotosDisponibles(){
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles);
	}	
	
	// CÓDIGO DE APOYO
	public IList<String> getCodFurgoDisponibles(){
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles);
			
	}
	
	// CÓDIGO DE APOYO
	public IList<String[]> getCodEsperandoMoto(){
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto);
	}
	
	public IList<String[]> getCodEsperandoFurgo(){
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESO_MAX_MOTO = 20;

	// CÓDIGO DE APOYO
	public GestionRepartoLocal(){		
		
		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new CircularQueue<>();
		this.pedidosEsperandoMoto = new CircularQueue<>();
	}

	public void add(Transporte transporte){
		//TO-DO
	}
	
	public void asignarPedido(Pedido pedido) {
		//TO-DO		
	}
	
	//PRE: el pedido tiene asignado un transporte
	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado {	
		//TO-DO	
	}
}