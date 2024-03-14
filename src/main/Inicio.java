package main;

import accesoBBDD.Accesobbdd;
import control.EstancoListener;
import model.ProductosEstanco;
import view.VConsultar;
import view.VIntroducir;
import view.VPEstanco;

public class Inicio {
	public static void main(String[] args) {
		Accesobbdd acceso = new Accesobbdd(); 
		java.awt.EventQueue.invokeLater (new Runnable() {
		ProductosEstanco almacen = new ProductosEstanco();	
			
			public void run() {
				VPEstanco vpes = new VPEstanco();
				VIntroducir	vi = null;
				try {
						vi = new VIntroducir(acceso, almacen);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				VConsultar vc = new VConsultar(almacen);
				
				EstancoListener listener = new EstancoListener(vpes, vi, vc, almacen);
				
				vpes.setListener(listener);
				vi.setListener(listener);
				vc.setListener(listener);
				
				vpes.hacerVisible();
			}
			});
	}
}
