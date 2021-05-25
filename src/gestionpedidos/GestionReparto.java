package gestionpedidos;


import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Transporte;
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
 /**
  * Constructor que inicializa los atributos
  * @param mapa
  */
	public GestionReparto(Mapa mapa){
		this.mapa = mapa;
		gestoresLocales = new GestionRepartoLocal[4];
		for(int i=0;i<gestoresLocales.length;i++)
			gestoresLocales[i]=new GestionRepartoLocal();
	}
	/**
	 * Método que asigna el transporte a la localidad correspondiente segÚn su localización
	 * @param transporte transporte a asignar localidad
	 */
	public void addTransporteLocalidad(Transporte transporte) {
		int localidad = seleccionarLocalidad(mapa.getPosicion(transporte.getCodigo()));
		gestoresLocales[localidad].add(transporte);
	}
	/**
	 * Método que selecciona la localidad a la que pertenece la posicion 
	 * @param pos posicion de la que se desea conocer la llocalidad correspondiente
	 * @return 1 si pertenece a la localidad 1, 2 si es a la 2 y así sucesivamente para las 4 localidades.
	 */
	private int seleccionarLocalidad(PosicionXY pos){
		if(pos.getX() <= mapa.getMaxCoordX()/2) {
			if(pos.getY() <= mapa.getMaxCoordY()/2)
				return 0;
			return 2;
		}else {
			if(pos.getY() <= mapa.getMaxCoordY()/2)
				return 1;
			return 3;
		}
		
		   
		
		
	}
	/*
	 * Método que asigna al pedido la localizad correspondiente según la localización del cliente
	 * 
	 */

	public void asignarPedido(Pedido pedido) {
		int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
		gestoresLocales[localidad].asignarPedido(pedido);
		
	}
	/**
	 * Metodo que notifica la entrega de un pedido a la localidad a la que pertenence si el pedido no tiene asignado transporte 
	 * lanza una excepción
	 * @param pedido pedido que ha sido entregado
	 * @throws PedidoSinTransporteAsignado
	 */

	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{	
		int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
		gestoresLocales[localidad].notificarEntregaPedido(pedido);
		
	}

}
