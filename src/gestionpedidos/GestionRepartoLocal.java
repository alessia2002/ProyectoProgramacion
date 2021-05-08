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
	/**
	 * Metodo que asigna el transporte a cada pedido dependiendo de su peso, en caso de no haber transporte dispponible lo agrega a 
	 * una lista de espera
	 * @param pedido pedido al que hay qeu asignar transporte
	 */
	public void asignarPedido(Pedido pedido) {
		if(pedido.getPeso()<PESO_MAX_MOTO && motosDisponibles.size()==0 ) {
			pedidosEsperandoMoto.add(pedido);
		}
		else if(pedido.getPeso()<PESO_MAX_MOTO){
				double min = pedido.coste(motosDisponibles.get(0));
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
			
		
		else if(furgonetasDisponibles.size()==0){
			pedidosEsperandoFurgoneta.add(pedido);
		}
		else {
			double min = pedido.coste(furgonetasDisponibles.get(0));
			int index = 0;
			for(int i = 1; i<furgonetasDisponibles.size(); i++) {
				if(pedido.coste(furgonetasDisponibles.get(i))< min) {
					min = pedido.coste(furgonetasDisponibles.get(i));
					index = i;
				}
			}
				pedido.setTransporte(furgonetasDisponibles.get(index));
				furgonetasDisponibles.removeElementAt(index);
		}
       

	
		 /*int seleccion = pedido.getPeso()<PESO_MAX_MOTO? 0 : 1;
	        if(motosDisponibles.size() == 0 && seleccion == 0) 
	            pedidosEsperandoMoto.add(pedido);
	         else if(furgonetasDisponibles.size() == 0 && seleccion == 1) {
	        	pedidosEsperandoFurgoneta.add(pedido);
	         }else {
	        	 int index = 0;
	             double min = seleccion == 0? pedido.coste(motosDisponibles.get(0)) : pedido.coste(furgonetasDisponibles.get(0));
	             for (int i=0;i < (seleccion == 0 ? motosDisponibles.size():furgonetasDisponibles.size());i++) {
	             	double comparar = seleccion == 0? pedido.coste(motosDisponibles.get(i)) : pedido.coste(furgonetasDisponibles.get(i));
	             	if(comparar<min) {
	             		min = comparar; 
	             		index = i;
	             	}
	             	
	             }if(seleccion == 0) {
		        	 pedido.setTransporte(motosDisponibles.get(index));
				     motosDisponibles.removeElementAt(index);
		         }else {
		        	 pedido.setTransporte(furgonetasDisponibles.get(index));
					 furgonetasDisponibles.removeElementAt(index);
		         }
	         }*/
	         
	        
	       

			
		
		
		
		
	}
	/**PRE: el pedido tiene asignado un transporte sino se lanza una excepción
	 * Metodo que notifica si un pedido ha sido entregado y el transporte que ha terminado su pedido atiende si hay pedidos que hay disponibles
	 * sino pasa a las listas de transportes disponibles.
	 * 
	 * @param pedido
	 * @throws PedidoSinTransporteAsignado
	 */
	
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