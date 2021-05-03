package gestionpedidos;


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
		this.mapa = mapa;
		gestoresLocales = new GestionRepartoLocal[4];
		for(int i=0;i<gestoresLocales.length;i++)
			gestoresLocales[i]=new GestionRepartoLocal();
	}
	
	public void addTransporteLocalidad(Transporte transporte) {
		int localidad = seleccionarLocalidad(mapa.getPosicion(transporte.getCodigo()));
		gestoresLocales[localidad].add(transporte);
	}
	
	private int seleccionarLocalidad(PosicionXY pos){
		if(pos.getX() <= mapa.getMaxCoordX()/2) {
			if(pos.getY() < mapa.getMaxCoordY()/2)
				return 0;
			return 2;
		}else {
			if(pos.getY() <= mapa.getMaxCoordY()/2)
				return 1;
			return 3;
		}
		
		   
		
		
	}

	public void asignarPedido(Pedido pedido) {
		int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
		gestoresLocales[localidad].asignarPedido(pedido);
		
	}

	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{
		if(pedido.getTransporte() == null)
			throw new PedidoSinTransporteAsignado();
		int localidad = seleccionarLocalidad(mapa.getPosicion(pedido.getCliente().getCodigo()));
		gestoresLocales[localidad].notificarEntregaPedido(pedido);
		
	}

}
