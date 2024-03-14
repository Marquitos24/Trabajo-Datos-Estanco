package model;

public class Estanco {

	private String tipoProducto;
	private String nombreP;
	private String descrip;
	private int cantidad;
	private String precio;
	private String oferta;
	
	
	public Estanco(String tipoProducto, String nombreP, String descrip, int cantidad, String precio, String oferta) {
		this.tipoProducto = tipoProducto;
		this.nombreP = nombreP;
		this.descrip = descrip;
		this.cantidad = cantidad;
		this.precio = precio;
		this.oferta = oferta;
	}

	

	public String getTipoProducto() {
		return tipoProducto;
	}

	public String getNombreP() {
		return nombreP;
	}

	public String getDescrip() {
		return descrip;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getPrecio() {
		return precio;
	}
	
	public String getOferta() {
		return oferta;
	}
	
	
}
