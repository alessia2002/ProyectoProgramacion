package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import list.IList;
import queues.IQueue;
import queues.exceptions.EmptyQueueException;
import queues.CircularQueue;


public class GestionRepartoLocal {	
	// C�DIGO DE APOYO
	private IList<Moto> motosDisponibles;
	private IList<Furgoneta> furgonetasDisponibles;
	private IQueue<Pedido> pedidosEsperandoMoto;
	private IQueue<Pedido> pedidosEsperandoFurgoneta;	
	
	
	// C�DIGO DE APOYO
	public String getDisponibles(){
		return "Motos Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
				GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles)) + System.lineSeparator() +
			"Furgonetas Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
					GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles)) + System.lineSeparator();
			
	}
	
	// C�DIGO DE APOYO
	public String getEsperando(){
		return "Pedidos esperando moto:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
				GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto)) + System.lineSeparator() +
				"Pedidos esperando furgoneta:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
						GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
	}
	
	// C�DIGO DE APOYO
	public IList<String> getCodMotosDisponibles(){
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles);
	}	
	
	// C�DIGO DE APOYO
	public IList<String> getCodFurgoDisponibles(){
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles);
			
	}
	
	// C�DIGO DE APOYO
	public IList<String[]> getCodEsperandoMoto(){
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto);
	}
	
	public IList<String[]> getCodEsperandoFurgo(){
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESO_MAX_MOTO = 20;

	// C�DIGO DE APOYO
	public GestionRepartoLocal(){		
		
		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new CircularQueue<>();
		this.pedidosEsperandoMoto = new CircularQueue<>();
	}

	public void add(Transporte transporte){
		if(transporte instanceof Furgoneta) {
			furgonetasDisponibles.add(0, (Furgoneta)transporte);
		}
		else{
			motosDisponibles.add(0, (Moto)transporte);
		}
	}
	
	public void asignarPedido(Pedido pedido) {
		if(pedido.getPeso()<PESO_MAX_MOTO) {
			if(motosDisponibles.size()==0) {
				pedidosEsperandoMoto.add(pedido);
			}
			else {
				double min = motosDisponibles.get(i).coste(pedido.getRestaurante().getCodigo())+
						   motosDisponibles.get(i).coste(pedido.getRestaurante().getCodigo(), pedido.getCliente().getCodigo());;
				int index = 0;
				for(int i = 1; i<motosDisponibles.size(); i++) {
					if(pedido.coste(motosDisponibles.get(i))< min) {
						min = pedido.coste(motosDisponibles.get(i));
						index = i;
					}
				}
				pedido.setTransporte(motosDisponibles.get(index));
				motosDisponibles.removeElementAt(index);
			}
			
		}
		else {
			if(furgonetasDisponibles.size()==0) {
				pedidosEsperandoFurgoneta.add(pedido);
			}
			else {
				double min = motosDisponibles.get(i).coste(pedido.getRestaurante().getCodigo())+
						   motosDisponibles.get(i).coste(pedido.getRestaurante().getCodigo(), pedido.getCliente().getCodigo());;
				int index = 0;
				for(int i = 1; i<motosDisponibles.size(); i++) {
					if(pedido.coste(motosDisponibles.get(i))< min) {
						min = pedido.coste(motosDisponibles.get(i));
						index = i;
					}
				}
				pedido.setTransporte(furgonetasDisponibles.get(index));
				furgonetasDisponibles.removeElementAt(index);
			}
			
		}
		
		
		
	}
	
	//PRE: el pedido tiene asignado un transporte
	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado {	
		if(pedido.getTransporte() == null) {
			throw new PedidoSinTransporteAsignado("Transporte no asignado");
		}
		
		if(pedido.getTransporte() instanceof Moto) {
				try {
					pedidosEsperandoMoto.poll().setTransporte(pedido.getTransporte());
				}catch(EmptyQueueException e) {
					motosDisponibles.add(0, (Moto) pedido.getTransporte());
				}
			
		}
		
		
		
		else {
				try {
					pedidosEsperandoFurgoneta.poll().setTransporte(pedido.getTransporte());
				}catch(EmptyQueueException e) {
					furgonetasDisponibles.add(0, (Furgoneta) pedido.getTransporte());
				}
			
		}
	}
}