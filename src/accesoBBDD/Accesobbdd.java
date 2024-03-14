package accesoBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Accesobbdd {
	
	static private Connection con;
	
	public static void main(String[] args) {

	try {
		Class.forName("oracle.jdbc.OracleDriver");
	}catch (ClassNotFoundException e){
		System.out.println("No se ha encontrado el driver para MySQL");
		return;
	}
	System.out.println("Se ha cargado el Driver de MySQL");
	
	 
	
	try {
		 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

	} catch (SQLException e) {
		System.out.println("Error en la conexión con la BD");
		System.out.println(e.getMessage());
		e.printStackTrace();
		return;
	}
	System.out.println("Se ha establecido la conexión con la Base de datos");
	
	//================================================================================
	
	
	try {
		con.close();
	} catch (SQLException e) {
		System.out.println("No se ha podido cerrar la conexión con la BD");
		System.out.println(e.getMessage());
		return;
	}
	
	//CONEXION
	
	}
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		return con;
	}
	
	
	public List<String> obtenerTiposDeProductos() throws ClassNotFoundException {
		List<String> tipos = new ArrayList<>();
		
		try {//---------TIPO------------------------
			
			con = getConexion();
			String query = "SELECT DISTINCT Tipo FROM Productss";
			 try (Statement st = con.createStatement();
					 ResultSet rs = st.executeQuery(query)) {
		            while (rs.next()) {
		                tipos.add(rs.getString("Tipo"));
		               
		            }
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar el listado");
			System.out.println(e.getMessage());
		}
		
		return tipos;
	
	}
	public List<String> obtenerNombresDeProductos() throws ClassNotFoundException  {
		List<String> nombres = new ArrayList<>();
		try {//---------NOMBRE------------------------
			
			con = getConexion();
			String query = "SELECT Nombre FROM Productss";
			 try (Statement st = con.createStatement();
					 ResultSet rs = st.executeQuery(query)) {
		            while (rs.next()) {
		                nombres.add(rs.getString("Nombre"));
		                
			}
			}
		} catch (SQLException e) {
			System.out.println("Error al realizar el listado de nombres");
			System.out.println(e.getMessage());
		}
		return nombres;
	}
}