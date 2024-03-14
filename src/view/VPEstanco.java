package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import control.EstancoListener;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;

public class VPEstanco extends JFrame {
	
	private static final int ANCHO = 800;
	private static final int ALTO = 650;
	
	public static final String MNTN_INTRO ="Introduce los productos";
	public static final String MNTN_CONSULT="Consultar productos";
	public static final String MNTN_SALIR = "Salir del estanco";
	
	private EstancoListener listener;
	private JMenuItem mntmIntroducir;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmSalir;
	private JScrollPane scrpContenedor;
	private JLabel lblNewLabel;
	
	public VPEstanco() {
		super("ESTANCO" );//añadir ciudad
		setForeground(new Color(255, 255, 255));
		
		menu(); 
				
		iniciar();
		
	}

	private void iniciar() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("         BIENVENIDO AL MEJOR ESTANCO DE ESPAÑA");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrpContenedor.setColumnHeaderView(lblNewLabel);
		
		
		
		centrarVentana();
		
	}
		private void menu() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmIntroducir = new JMenuItem(MNTN_INTRO);
		mntmIntroducir.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mntmIntroducir);
		
		mntmIntroducir.addActionListener(listener);
		
		
		mntmConsultar = new JMenuItem(MNTN_CONSULT);
		mntmConsultar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mntmConsultar);
		
		mntmConsultar.addActionListener(listener);
		
		
		mntmSalir = new JMenuItem(MNTN_SALIR);
		mntmSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mntmSalir);
		
		mntmSalir.addActionListener(listener);
	}
		private void centrarVentana() { 
			Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();              
	       Dimension ventana = new Dimension(ANCHO, ALTO);
	       setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
			

		
	}
		public void hacerVisible() {
			setSize(ANCHO, ALTO);
			
			setLocationRelativeTo(null);
			setVisible(true);
			
		}

		public void cargarPanel(JPanel panel) {
			scrpContenedor.setViewportView(panel);
			
		}
		
		public void setListener(EstancoListener listener) {
			mntmIntroducir.addActionListener(listener);
			mntmConsultar.addActionListener(listener);
			mntmSalir.addActionListener(listener);
			
		}

}
