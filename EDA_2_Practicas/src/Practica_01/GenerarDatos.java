package Practica_01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerarDatos {
	
	public static void generarDatos() {
		
		String directorio = System.getProperty("user.dir") + File.separator
					+ "src" + File.separator + "Practica01" + File.separator;
		
		
		// Generacion de los casos medios
		for (int n = 1; n <= 9; n++) {
			String nombreFichero = directorio + "datos" + n + ".txt";
			try {
				PrintWriter f = new PrintWriter(new FileWriter(nombreFichero));
				int nCalles = (int) Math.pow(2, n) + 1;
				f.write(nCalles + "\n");
				int nAvenidas = (int) Math.pow(2, n) + 1;
				f.write(nAvenidas + "\n");
				for (int i = 0; i < nCalles - 1; i++) {
					for (int j = 0; j < nAvenidas - 1; j++) {
						int numero = (int) (Math.random() * 25);
						f.write(numero + "\n");
					}
				}
				f.close();
			} catch (IOException e) {
				System.out.println("Error de escritura en fichero: "
						+ nombreFichero);
			}
		}
		
		/*
		// generacion de los peores casos
		// suponemos que es cuando el 50% de las manzanas tienen 
		// una cantidad de absentismo alto y el otro 50% tiene
		// una cantidad de absentismo bajo
		for (int n = 1; n <= 9; n++) {
			String nombreFichero = directorio + "datosPeor" + n + ".txt";
			try {
				PrintWriter f = new PrintWriter(new FileWriter(nombreFichero));
				int nCalles = (int) Math.pow(2, n) + 1;
				f.write(nCalles + "\n");
				int nAvenidas = (int) Math.pow(2, n) + 1;
				f.write(nAvenidas + "\n");
				for (int i = 0; i < nCalles - 1; i++) {
					for (int j = 0; j < nAvenidas - 1; j++) {
						int numero;
						if (Math.random() < 0.5)
							numero = (int) (Math.random() * 10);
						else
							numero = (int) (Math.random() * 15 + 10);
						f.write(numero + "\n");
					}
				}
				f.close();
			} catch (IOException e) {
				System.out.println("Error de escritura en fichero: "
						+ nombreFichero);
			}
		}
		// generacion de los mejores casos
		// suponemos que es cuando manzanas vecinas tienen muy
		// poco absentismo 
		// y la mayor concentracion de absentismo se encuentra
		// en una zona delimitada
		for (int n = 1; n <= 9; n++) {
			String nombreFichero = directorio + "datosMejor" + n + ".txt";
			try {
				PrintWriter f = new PrintWriter(new FileWriter(nombreFichero));
				int nCalles = (int) Math.pow(2, n) + 1;
				f.write(nCalles + "\n");
				int nAvenidas = (int) Math.pow(2, n) + 1;
				f.write(nAvenidas + "\n");
				for (int i = 0; i < nCalles - 1; i++) {
					for (int j = 0; j < nAvenidas - 1; j++) {
						int numero;
						if (j < nAvenidas / 2)
							numero = (int) (Math.random() * 10);
						else
							numero = (int) (Math.random() * 15 + 10);
						f.write(numero + "\n");
					}
				}
				f.close();
			} catch (IOException e) {
				System.out.println("Error de escritura en fichero: "
						+ nombreFichero);
			}
		}
		*/
	}
}
