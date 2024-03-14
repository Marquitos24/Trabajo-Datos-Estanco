package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Estanco;
import model.ProductosEstanco;
import view.VConsultar;
import view.VIntroducir;
import view.VPEstanco;

public class EstancoListener implements ActionListener {

	private VPEstanco vpes;
	private VIntroducir vi;
	private VConsultar vc;
	private ProductosEstanco pe;

	public EstancoListener(VPEstanco vpes, VIntroducir vi, VConsultar vc, ProductosEstanco pe) {
		this.vpes = vpes;
		this.vi = vi;
		this.vc = vc;
		this.pe = pe; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JMenuItem) {
			if(((JMenuItem)e.getSource()).getText().equals(vpes.MNTN_INTRO)) {
				vpes.cargarPanel(vi);
			}else if(((JMenuItem)e.getSource()).getText().equals(vpes.MNTN_CONSULT)) {
				vpes.cargarPanel(vc);
				vc.hacerVisibleTabla(false);
			}else if (((JMenuItem)e.getSource()).getText().equals(vpes.MNTN_SALIR)) {
				int opcion = JOptionPane.showConfirmDialog(vpes, "¿Seguro que quieres salir?", 
						"Confirmación salida", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		
		}else if(e.getSource().equals(vi.getBtnComprar())) {
				Estanco estanco = vi.obtenerProductos();
				
				if(estanco != null) {
					pe.addProducto(estanco);
					
					JOptionPane.showMessageDialog(vi, "El producto se ha guardado con exito", "Productos", 
							JOptionPane.INFORMATION_MESSAGE);
					
					vi.limpiarProductos();
				}else {
					JOptionPane.showMessageDialog(vi, "Los productos no han podido ser guardados", "ERROR", 
							JOptionPane.WARNING_MESSAGE);
				}
				
			}else if(e.getSource().equals(vc.getBtnMostarP())){
				ArrayList<Estanco> listaProductos = pe.getListaEstanco();
				vc.rellenarTabla(listaProductos);
				vc.hacerVisibleTabla(true);
			}
		}	
	}



