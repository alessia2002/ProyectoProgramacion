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
			furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta)transporte);
		}
		else{
			motosDisponibles.add(motosDisponibles.size(), (Moto)transporte);
		}
	}
	/**
	 * Metodo que asigna el transporte a cada pedido dependiendo de su peso, en caso de no haber transporte dispponible lo agrega a 
	 * una lista de espera
	 * @param pedido pedido al que hay qeu asignar transporte
	 */
	public void asignarPedido(Pedido pedido) {
		if(pedido.getPeso()<=PESO_MAX_MOTO) {
			if(motosDisponibles.size()==0)
				pedidosEsperandoMoto.add(pedido);
			pedido.setTransporte(costeMin(motosDisponibles, pedido));
		}
		else {
			if(furgonetasDisponibles.size()==0)
				pedidosEsperandoFurgoneta.add(pedido);
			pedido.setTransporte(costeMin(furgonetasDisponibles, pedido));
		}
			

	}
	
	private Transporte costeMin(IList<?> transporte, Pedido pedido) {
		double min = pedido.coste((Transporte)transporte.get(0));
		int index = 0;
		for(int i = 1; i<transporte.size(); i++) {
			if(pedido.coste((Transporte)transporte.get(i))< min) {
				min = pedido.coste((Transporte)transporte.get(i));
				index = i;
			}
		}
		return (Transporte) transporte.get(index);
	}
	/**PRE: el pedido tiene asignado un transporte sino se lanza una excepción
	 * Metodo que notifica si un pedido ha sido entregado y el transporte que ha terminado su pedido atiende si hay pedidos que hay disponibles
	 * sino pasa a las listas de transportes disponibles.
	 * 
	 * @param pedido
	 * @throws PedidoSinTransporteAsignado
	 */
	
	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado {	
		if(pedido.getTransporte() == null)
			throw new PedidoSinTransporteAsignado();
		
		
	/*	if(pedido.getTransporte() instanceof Moto) {
			if(this.pedidosEsperandoMoto.isEmpty())
				this.add(pedido.getTransporte());
			else {
				try {
					pedidosEsperandoMoto.poll().setTransporte(pedido.getTransporte());
				}catch(EmptyQueueException e) {
					e.printStackTrace();
				} 
			}
		}
		else {
			if(this.pedidosEsperandoFurgoneta.isEmpty())
				this.add(pedido.getTransporte());
			else {
				try {
					pedidosEsperandoFurgoneta.poll().setTransporte(pedido.getTransporte());
				}catch(EmptyQueueException e) {
					e.printStackTrace();
				}
			
			}
		}
		*/
		
		
		if(pedido.getTransporte() instanceof Moto && !pedidosEsperandoMoto.isEmpty()) {
			try {
				pedidosEsperandoMoto.poll().setTransporte(pedido.getTransporte());
			}catch(EmptyQueueException e) {
				e.printStackTrace();
			} 
		}
		else if(pedido.getTransporte() instanceof Furgoneta && !pedidosEsperandoFurgoneta.isEmpty()) {
			try {
				pedidosEsperandoFurgoneta.poll().setTransporte(pedido.getTransporte());
			}catch(EmptyQueueException e) {
				e.printStackTrace();
			}
		}
		else {
			this.add(pedido.getTransporte());
		}
	}
}