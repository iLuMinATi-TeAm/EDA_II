package Practica_01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CargarDatos {
	
	
	//n es la potencia de 2, devuelve la matrix ya cargada
	public static Manzana [][] leerDatos (int n) {
		
		String directorio = System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "Practica01" + File.separator;
		
		String nombreFichero = directorio + "datos" + n + ".txt";
		
		Manzana [][]a = null;
		try {
			Scanner f = new Scanner (new File (nombreFichero));
			int nCalles = f.nextInt();
			int nAvenidas = f.nextInt();
			a = new Manzana [nCalles-1][nAvenidas-1];
			for (int i=0; i<nCalles-1; i++) {
				for (int j=0; j<nAvenidas-1; j++) {
					int abse = f.nextInt();
					a[i][j] = new Manzana (i+1, j+1, abse);
				}
			}
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error de lectura del fichero "+nombreFichero);
		}
		return a;
	}
}
