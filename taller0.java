package taller0;
import java.io.IOException;

import ucn.*;

public class taller0 {							//Comentario del pablo

	public static void main(String[] args) throws IOException {
		/*
		 * Arrays for personas.txt
		 */
		String[] nombre_completo = new String[1000]; // Revisar cantidad del vector
		String[] ruts = new String[1000];
		String[] contrasenas = new String[1000];
		int[] saldos = new int[1000];
		String[][] inventario = new String[20][1000]; // Revisar filas y columnas
		/*
		 *  Arrays for tiendas.txt
		 */
		String[] tiendas = new String[1000];
		int[] total_por_tienda = new int[1000];
		/*
		 * Arrays for productos.txt
		 */
		String[] mangas = new String[1000];
		String[] comics = new String[1000];
		int[][] inventario_tiendas = new int [1000][1000]; //Check numbers
		String[] productos = new String[1000];
		
		Leer1(nombre_completo,ruts,contrasenas,saldos,inventario);
		Leer2(tiendas,total_por_tienda);
		Leer3(mangas,comics,inventario_tiendas,productos,tiendas);
		
		IniciarSesion(nombre_completo,ruts,contrasenas);
		
		
	}

	public static void Leer1(String[]nombre_completo,String[]ruts,String[]contrasenas,int[]saldos,String[][]inventario) throws IOException {
		ArchivoEntrada arch1 = new ArchivoEntrada("personas.txt");
		int indi = 0;
		while(!arch1.isEndFile()) {
			Registro regEnt = arch1.getRegistro();
			
			String nombre = regEnt.getString();
			String apellido = regEnt.getString();
			String rut = regEnt.getString();
			String rut1 = rut.replace(".", "");
			String rut_definitivo = rut1.replace("-", "");
			String contrasena = regEnt.getString();
			int saldo = regEnt.getInt();
			String[] inventario1 = new String[20];
			
			 try {								// Se leen todos los productos del usuario
				 for (int i = 0; i < 20; i++) {
			      String producto = regEnt.getString();
			      inventario1[i] = producto;
				 }
			    } catch (Exception e) {
			      System.out.println("Something went wrong.");
			   }
	
			nombre_completo[indi] = nombre + " " +  apellido;
			ruts[indi] = rut_definitivo;
			contrasenas[indi] = contrasena;
			saldos[indi] = saldo;
			
			for (int i = 0; i <20; i++) {
				inventario[i][indi] = inventario1[i];
			}
			indi++;
			
		}
	}
	
	public static void Leer2(String[]tiendas,int[]total_por_tienda) throws IOException {
		ArchivoEntrada arch2 = new ArchivoEntrada("tiendas.txt");
		int indi = 0;
		while(!arch2.isEndFile()) {
			Registro regEnt2 = arch2.getRegistro();
			
			String tienda = regEnt2.getString();
			int monto_recaudado = regEnt2.getInt();
			
			tiendas[indi] = tienda;
			total_por_tienda[indi] = monto_recaudado;
			
			indi++;
			
		}
	}
	
	public static void Leer3(String[]mangas,String[]comics,int[][]inventario_tiendas,String[]productos,String[]tiendas) throws IOException {
		ArchivoEntrada arch3 = new ArchivoEntrada("productos.txt");
		while(!arch3.isEndFile()) {
			Registro regEnt3 = arch3.getRegistro();
			
			String tipo = regEnt3.getString();
			String nom_producto = regEnt3.getString();
			int total_producto = regEnt3.getInt(); // Cantidad total de producto en la tienda
			int valor_por_unidad = regEnt3.getInt(); 
			String tienda = regEnt3.getString();
			
			int indi_productos = 0;
			for (int i = 0; i<1000; i++) {
				if(productos[i] == null || productos[i].equals(nom_producto)) {
					productos[i] = nom_producto;
					indi_productos = i;
					break;
				}
			}
			int indi_tiendas = 0;
			for(int j = 0; j <1000; j++) {
				if(tiendas[j].equals(tienda)) {
					indi_tiendas = j;
					StdOut.println(j);
					break;
				}
			}
			inventario_tiendas[indi_productos][indi_tiendas] += total_producto;
			
		}
	}
	// Leer3 is uncompleted
	public static void IniciarSesion(String[]nombre_completo,String[]ruts,String[]contrasenas) {
		boolean error = true;
		StdOut.println("-----------------------------");
		StdOut.println("    Bienvenido al Sistema");
		StdOut.println("-----------------------------");
		String rut_definitivo;
		String contrasena_ingresada;
		do {
			StdOut.print("Ingrese su rut: ");
			String rut_ingresado = StdIn.readString();
			String rut1 = rut_ingresado.replace(".", ""); //Se eliminan puntos y comas del rut
			rut_definitivo = rut1.replace("-", "");
			StdOut.print("Ingrese su contrasena");
			contrasena_ingresada = StdIn.readString();
			
			int indi = 0;
			for (int i = 0; i < 1000; i++) {
				try {
				if (ruts[i].equals(rut_definitivo)) {
					indi = i;
					if (!contrasenas[indi].equals(contrasena_ingresada)) {
						StdOut.println("La contrasena ingresada no coincide");
						error = true;
					}
					if (contrasenas[indi].equals(contrasena_ingresada)) {
						error = false;
					}
					
					break;
				}
				}catch(Exception e) {
					}
				if (i == 999) {
					StdOut.println("El rut no se encuentra registrado");
					error = true;
				}
			}
			if (error == true) {
				StdOut.println("Datos mal ingresados vuelva a iniciar sesion.");
				StdOut.println("---------------------------------------------");
			}
		
		}while(error);
		
		StdOut.println("Menu para registrarse: ");
		StdOut.println("------------------------");
		StdOut.print("Ingrese su rut: ");
		String rut_nuevo = StdIn.readString();
		String rut_nuevo1 = rut_nuevo.replace(".", "");
		String rut_nuevo_definitivo = rut_nuevo1.replace("-", "");
		StdOut.print("Ingrese su nombre: ");
		String nombre_nuevo = StdIn.readString();
		StdOut.print("Ingrese su apellido: ");
		String apellido_nuevo = StdIn.readString();
		StdOut.print("Ingrese su contrasena: ");
		String contrasena_nueva = StdIn.readString();
		
		int indi_a_usar = 0;
		for (int i = 0; i < 1000; i++) {
			if (nombre_completo[i] == null) {
				indi_a_usar = i;
				break;
			}
		}
		nombre_completo[indi_a_usar] = nombre_nuevo + " " + apellido_nuevo;
		ruts[indi_a_usar] = rut_nuevo_definitivo;
		contrasenas[indi_a_usar] = contrasena_nueva;
	}
}
