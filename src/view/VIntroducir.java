package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import accesoBBDD.Accesobbdd;
import control.EstancoListener;
import model.Estanco;
import model.ProductosEstanco;

import java.util.List;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class VIntroducir extends JPanel {
	private JComboBox <String> cmbNombre;
	private JSpinner spnCant;
	private JButton btnComprar;
	private JComboBox <String> cmbTipo;
	private JLabel lblAñadirOferta;
	private JLabel lblAñadirPrecio;
	private JTextArea lblAñadirDescr;
	private JTextArea lblAñadirDescr_1;
	private JLabel lblPonerCliente;
	
	private Accesobbdd acceso;
	private ProductosEstanco almacen;
	
	public VIntroducir(Accesobbdd acceso, ProductosEstanco almacen) throws ClassNotFoundException {
		this.acceso = acceso;
		this.almacen = almacen;
		setLayout(null);
		cmbNombre = new JComboBox<>();
        cmbTipo = new JComboBox<>();
        lblAñadirOferta = new JLabel();
        lblAñadirPrecio = new JLabel();
        lblAñadirDescr = new JTextArea();
        
        cargarNombreDeProductos();
        cargarTiposDeProductos();// Asigna la instancia de Accesobbdd proporcionada
		
		
		JLabel lblTipo = new JLabel("Tipo de producto:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(211, 65, 103, 13);
		add(lblTipo);
		
		JLabel lblNombre = new JLabel("Nombre del producto:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(202, 127, 126, 13);
		add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(202, 196, 73, 13);
		add(lblDescripcion);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCantidad.setBounds(237, 330, 63, 13);
		add(lblCantidad);
		
		JLabel lblOfert = new JLabel("Oferta:");
		lblOfert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOfert.setBounds(237, 431, 56, 13);
		add(lblOfert);
		
		cmbTipo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					actualizarDescripcionPrecioOferta();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} // Lógica para actualizar la descripción, precio y oferta al seleccionar un tipo
			}
		
		});
		
		cmbNombre.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
	            // Lógica para actualizar la descripción, precio y oferta al seleccionar un nombre
				try {
					actualizarDescripcionPrecioOferta();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		cmbTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbTipo.setBounds(374, 59, 177, 25);
		add(cmbTipo);
		
	
		cmbNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cmbNombre.setBounds(374, 123, 177, 21);
		add(cmbNombre);
		
		spnCant = new JSpinner();
		spnCant.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spnCant.setBounds(374, 328, 52, 20);
		add(spnCant);
		
		lblAñadirOferta =new JLabel(" ");
		lblAñadirOferta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAñadirOferta.setBounds(374, 425, 162, 25);
		add(lblAñadirOferta);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnComprar.setBounds(298, 506, 139, 46);
		add(btnComprar);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecio.setBounds(241, 381, 39, 13);
		add(lblPrecio);
		
		lblAñadirPrecio = new JLabel(" ");
		lblAñadirPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAñadirPrecio.setBounds(374, 373, 63, 21);
		add(lblAñadirPrecio);
		
		lblAñadirDescr_1 = new JTextArea(" ");
		lblAñadirDescr_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAñadirDescr_1.setBounds(168, 219, 383, 80);
		lblAñadirDescr_1.setLineWrap(true);  // Hace que el texto se ajuste automáticamente al final de la línea
		lblAñadirDescr_1.setWrapStyleWord(true);  // Rompe palabras si son demasiado largas
		add(lblAñadirDescr_1);
		
		JLabel lblNewLabel = new JLabel("€");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(406, 372, 39, 21);
		add(lblNewLabel);
	}

	

	private void actualizarDescripcionPrecioOferta() throws ClassNotFoundException {
		
			String tipoSeleccionado = cmbTipo.getSelectedItem().toString();
		    String nombreSeleccionado = cmbNombre.getSelectedItem().toString();

		    // Obtener la descripción, precio y oferta de la base de datos utilizando el tipo y nombre seleccionados
		    String descripcion = obtenerDescripcionDeBD(tipoSeleccionado, nombreSeleccionado);
		    String precio = obtenerPrecioDeBD(tipoSeleccionado, nombreSeleccionado);
		    String oferta = obtenerOfertaDeBD(tipoSeleccionado, nombreSeleccionado);

		    // Actualizar las etiquetas con la información obtenida
		    lblAñadirDescr_1.setText(descripcion);
		    lblAñadirPrecio.setText(precio);
		    lblAñadirOferta.setText(oferta);
		
		
	}



	private String obtenerOfertaDeBD(String tipo, String nombre) throws ClassNotFoundException {
		 String oferta = "";

		    try {
		        String query = "SELECT Oferta FROM Productss WHERE Tipo = ? AND Nombre = ?";
		        try (PreparedStatement ps = acceso.getConexion().prepareStatement(query)) {
		            ps.setString(1, tipo);
		            ps.setString(2, nombre);
		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                oferta = rs.getString("Oferta");
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al obtener la oferta de la base de datos");
		        System.out.println(e.getMessage());
		    }

		    return oferta;
	}



	private String obtenerPrecioDeBD(String tipo, String nombre) throws ClassNotFoundException {
		String precio = "";

	    try {
	        String query = "SELECT Precio FROM Productss WHERE Tipo = ? AND Nombre = ?";
	        try (PreparedStatement ps = acceso.getConexion().prepareStatement(query)) {
	            ps.setString(1, tipo);
	            ps.setString(2, nombre);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                precio = rs.getString("Precio");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener el precio de la base de datos");
	        System.out.println(e.getMessage());
	    }

	    return precio;
	}



	private String obtenerDescripcionDeBD(String tipo, String nombre) throws ClassNotFoundException {
		String descripcion = "";
		 try {
		        String query = "SELECT Descripcion FROM Productss WHERE Tipo = ? AND Nombre = ?";
		        try (PreparedStatement ps = acceso.getConexion().prepareStatement(query)) {
		            ps.setString(1, tipo);
		            ps.setString(2, nombre);
		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                descripcion = rs.getString("Descripcion");
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("Error al obtener la descripción de la base de datos");
		        System.out.println(e.getMessage());
		    }
	
	 return descripcion;
	}


	private void cargarNombreDeProductos() throws ClassNotFoundException {
		cmbNombre.removeAllItems();// Limpiar elementos previos
		List<String> nombres = acceso.obtenerNombresDeProductos();
	        for (String nombre : nombres) {
	        	cmbNombre.addItem(nombre);
	        	cmbNombre.repaint();
		        cmbNombre.revalidate();
	        	System.out.println("Nombre agregado: " + nombre);
	        
	        }
	        
	        
	}

	private void cargarTiposDeProductos() throws ClassNotFoundException {
		cmbTipo.removeAllItems();
		List<String> tipos = acceso.obtenerTiposDeProductos();
	        for (String tipo : tipos) {
	            cmbTipo.addItem(tipo);
	            cmbTipo.repaint();
		        cmbTipo.revalidate();
	            System.out.println("Tipo agregado: " + tipo);
	            
	}
	       
	}

	public void setListener(EstancoListener listener) {
		btnComprar.addActionListener(listener);
		
	}

	public JButton getBtnComprar() {
		return btnComprar;
	}

	public Estanco obtenerProductos() {
		Estanco estanco = null;
		String tipo = cmbTipo.getSelectedItem().toString();
		String nombre = cmbNombre.getSelectedItem().toString();
		String descrip = lblAñadirDescr_1.getText();
		int cantidad = (int) spnCant.getValue();
		String precio = lblAñadirPrecio.getText();
		String oferta = lblAñadirOferta.getText();
		if(nombre.isEmpty() || tipo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debes introducir los datos del producto");
		}
		estanco = new Estanco(tipo, nombre, descrip, cantidad, precio, oferta);
		
		almacen.addProducto(estanco);
		
		limpiarProductos();
		
		return estanco;
	}

	public void limpiarProductos() {
		cmbTipo.setSelectedIndex(0);
		cmbNombre.setSelectedIndex(0);
		lblAñadirDescr_1.setText(" ");
		spnCant.setValue(0);
		lblAñadirPrecio.setText(" ");
		lblAñadirOferta.setText(" ");
	}
}
