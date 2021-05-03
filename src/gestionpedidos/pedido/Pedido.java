package gestionpedidos.pedido;

import gestionpedidos.transportes.Transporte;

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
	
	
	public double coste(Transporte transporte){
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
