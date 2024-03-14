package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.EstancoListener;
import model.Estanco;
import model.ProductosEstanco;

public class VConsultar extends JPanel{
	private JButton btnMostarP;
	private JTable TablaProducts;
	private JScrollPane scrContenedorT;
	private DefaultTableModel tModel;
	private ProductosEstanco almacen;
	
	public VConsultar(ProductosEstanco almacen) {
		this.almacen = almacen;
		setLayout(null);
		
		JLabel lblLista = new JLabel("Lista de productos a comprar");
		lblLista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLista.setBounds(52, 91, 211, 19);
		add(lblLista);
		
		btnMostarP = new JButton("Mostrar productos");
		btnMostarP.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnMostarP.setBounds(400, 79, 211, 40);
		add(btnMostarP);
		
		scrContenedorT = new JScrollPane();
		scrContenedorT.setVisible(false);
		scrContenedorT.setBounds(38, 187, 721, 412);
		add(scrContenedorT);
		
		TablaProducts = new JTable();
		configurarTabla();
		scrContenedorT.setViewportView(TablaProducts);
	}

	private void configurarTabla() {
		tModel = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			
				}
		};
		TablaProducts.setModel(tModel);
		tModel.addColumn("Tipo");
		tModel.addColumn("Nombre");
		tModel.addColumn("Descripción");
		tModel.addColumn("Cantidad");
		tModel.addColumn("Precio");
		tModel.addColumn("Oferta");
		
		// indicamos el tamaño de las columnas
		TablaProducts.getColumn("Tipo").setPreferredWidth(75);
		TablaProducts.getColumn("Nombre").setPreferredWidth(50);
		TablaProducts.getColumn("Descripción").setPreferredWidth(300);
		TablaProducts.getColumn("Cantidad").setPreferredWidth(25);
		TablaProducts.getColumn("Precio").setPreferredWidth(50);
		TablaProducts.getColumn("Oferta").setPreferredWidth(30);

	}
	public void rellenarTabla(ArrayList<Estanco> listaProductos) {
	
		if (listaProductos != null) {
		tModel.getDataVector().clear();
		
		
		for (Estanco estanco : listaProductos) {
			Object[] fila = new Object[6];
			fila[0] = estanco.getTipoProducto();
			fila[1] = estanco.getNombreP();
			fila[2] = estanco.getDescrip();
			fila[3] = estanco.getCantidad();
			fila[4] = estanco.getPrecio();
			fila[5] = estanco.getOferta();

			tModel.addRow(fila);
		}
		}
	}

	public void setListener(EstancoListener listener) {
		btnMostarP.addActionListener(listener);
		
	}

	public JButton getBtnMostarP() {
		return btnMostarP;
	}

	public void hacerVisibleTabla(boolean b) {
		scrContenedorT.setVisible(b);
		
	}

	
}

