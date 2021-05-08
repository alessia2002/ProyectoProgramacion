package gestionpedidos.pedido;

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
public class Pedido {
	// C�DIGO DE APOYO
	private Cliente cliente;
	private PlatoComida[] comidas;
	private Restaurante restaurante;
	private double importe;	
	private Transporte transporte;
	private double peso;
	
	public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante) {		
		this.cliente = cliente;
		this.comidas = comidas;
		this.restaurante = restaurante;
		for(PlatoComida plato : comidas) {
			importe += plato.getPrecio();
			peso += plato.getPeso();
		}
		
		
			
	}

	
	public double getPeso(){		
		return peso;
	}
	/**
	 * Metodo que calcula el coste final del pedido
	 * @param transporte Tranporte con el que se repartirá el pedido
	 * @return coste del pedido.
	 */
	
	public double coste(Transporte transporte){
		/* double coste = transporte.coste(restaurante.getCodigo()) + transporte.coste(restaurante.getCodigo(),cliente.getCodigo()) + importe;
		 * if(importe > 100)
		 *  coste *=1.10
		 *  
		 *  return coste;
		 */
		if(importe < 100) {
			return transporte.coste(restaurante.getCodigo()) + transporte.coste(restaurante.getCodigo(),cliente.getCodigo()) + importe;
		}else
		    return (transporte.coste(restaurante.getCodigo()) + transporte.coste(restaurante.getCodigo(),cliente.getCodigo()) + importe)*1.10;
	}
	
	// C�DIGO DE APOYO
	public double getImporte(){
		return importe;
	}	

	// C�DIGO DE APOYO
	public Transporte getTransporte() {
		return transporte;
	}

	// C�DIGO DE APOYO
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}
	
	// C�DIGO DE APOYO
	public Cliente getCliente(){
		return cliente;
	}
	
	// C�DIGO DE APOYO
	public Restaurante getRestaurante(){
		return restaurante;
	}
}
