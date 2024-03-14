package model;

import java.util.ArrayList;

public class ProductosEstanco {

	private ArrayList<Estanco> listaProductos;
	
	public ProductosEstanco() {
		listaProductos = new ArrayList<>();
	}
	public void addProducto(Estanco est) {
		listaProductos.add(est);
	}
	public ArrayList<Estanco> getListaEstanco(){
		return listaProductos;
	}
}
