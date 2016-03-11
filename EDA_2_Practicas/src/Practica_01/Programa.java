package Practica_01;

import java.io.*;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
/** 
 * 
 * @author Juan
 *
 */

public class Programa {

	public static final double FACTOR = 1.7;
	public static TreeMap<Integer, ArrayList<Manzana>> mapa;
	public static ArrayList<Manzana> listaMalos;

	public static void main(String[] args) {
		String directorio = System.getProperty("user.dir") + File.separator
				+ "src" + File.separator + "practica1" + File.separator;

		//generarDatos(directorio);
		System.out.println("Fichero                  Suma           Tiempo       Tiempo");
		for (int n = 1; n <= 2; n++) {

			Manzana [][]a = CargarDatos.leerDatos (n);
			int nCalles = a.length + 1;
			int nAvenidas = a[0].length + 1;
			mapa = new TreeMap<Integer, ArrayList<Manzana>> ();
			long tiempoAntes = System.nanoTime();
			int suma = sumar (a, nCalles-1, nAvenidas-1, 0);
			long tiempoDespues = System.nanoTime();
			long tiempo = tiempoDespues - tiempoAntes;
			//System.out.printf("\n%-18s %10d %16d", nombreFichero, 
			//				suma, tiempo);
			//System.out.println(mapa);
			Manzana []array = convertirMapaEnArray();
			double media = suma / (Math.pow(2, n) * Math.pow(2, n));
			double valorASuperar = media * FACTOR;
			listaMalos = new ArrayList<Manzana> ();
			tiempoAntes = System.nanoTime();
			calcularMalos (array, 0, valorASuperar);
			tiempoDespues = System.nanoTime();
			tiempo = tiempoDespues - tiempoAntes;
			
			/*
			System.out.printf("%13d", tiempo);
			nombreFichero = "datosSalida"+n+".txt";
			nombreCompleto = directorio + nombreFichero;
			guardarMalos (listaMalos, nombreCompleto);
			*/
		}
	}

	
	/**algoritmo para sumar el total y calcular el absentismo medio
	 * empiezo por ejemplo con una matriz de 4 x 4
	 * hago 4 matrices de 2 x 2
	 * para cada matriz de 2 x 2
	 * 		calculo 4 matrices de 1 x 1
	 * 		tengo el absentismo por manzana
	 * 		sumo los 4 absentismos de cada manzana
	 * 		y ya tengo la suma de la de 2 x 2
	 * 		sumo este valor al de la matriz
	 * fin para
	 */
	public static int sumar (Manzana [][]a, int fil, int col, int nivel) {
		//System.out.println("llamo "+fil+" "+col);
		if (fil==1 && col==1) {
			if ( ! mapa.containsKey(nivel))
				mapa.put(nivel, new ArrayList<Manzana> ());
			mapa.get(nivel).add(a[0][0]);
			return a[0][0].getAbsentismo();
		}
		//divido en 4 subproblemas
		Manzana [][]a1, a2, a3, a4;
		int fini, ffin, cini, cfin;
		fini = 0;
		ffin = fil/2 -1;
		cini = 0; 
		cfin = col/2 -1;
		a1 = dividir (a, fini, ffin, cini, cfin);
		int x1 = sumar (a1, fil/2, col/2, nivel+1);
		fini = fil/2;
		ffin = fil -1;
		a2 = dividir (a, fini, ffin, cini, cfin);
		int x2 = sumar (a2, fil/2, col/2, nivel+1);
		cini = col/2; 
		cfin = col -1;
		a3 = dividir (a, fini, ffin, cini, cfin);
		int x3 = sumar (a3, fil/2, col/2, nivel+1);
		fini = 0;
		ffin = fil/2 -1;
		a4 = dividir (a, fini, ffin, cini, cfin);
		int x4 = sumar (a4, fil/2, col/2, nivel+1);
		int total = x1 + x2 + x3 + x4;
		if ( ! mapa.containsKey(nivel))
			mapa.put(nivel, new ArrayList<Manzana> ());
		Manzana nueva = new Manzana (total);
		mapa.get(nivel).add(nueva);
		return total;
	}

	public static Manzana[][] dividir(Manzana[][] a, int fini, int ffin, 
							int cini, int cfin) {
		//System.out.println("dividir: ["+fini+".."+ffin+"]["+cini+".."+cfin+"]");
		Manzana [][]nueva = new Manzana [ffin-fini+1][cfin-cini+1];
		for (int i=0; i<nueva.length; i++) {
			for (int j=0; j<nueva[i].length; j++) {
				nueva[i][j] = a[fini+i][cini+j];
			}
		}
		return nueva;
	}
	
	public static Manzana [] convertirMapaEnArray() {
		int tamanio = 0;
		for (int i=0; i<mapa.size(); i++) {
			tamanio = tamanio + (int)Math.pow(4, i);
		}
		Manzana []array = new Manzana [tamanio];
		int i = 0;
		for (Entry<Integer, ArrayList<Manzana>> e : mapa.entrySet()) {
			for (Manzana m : e.getValue()) {
				m.setPosicion(i);
				array[i] = m;
				i++;
			}
		}
		return array;
	}
	
	public static void calcularMalos (Manzana []array, 
						int posicion, double valorASuperar) {
		if (array[posicion].getAbsentismo() <= valorASuperar)
			return;
		//si no existe hijo de la posicion, estoy en el ultimo 
		//nivel y ademas se cumple la condicion de que tiene
		//un absentismo mayor del limite y tengo que meterlo en 
		//el arraylist
		if (posicion*4 + 1 >= array.length) {
			listaMalos.add(array[posicion]);
			return;
		}
		calcularMalos (array, posicion*4+1, valorASuperar);
		calcularMalos (array, posicion*4+2, valorASuperar);
		calcularMalos (array, posicion*4+3, valorASuperar);
		calcularMalos (array, posicion*4+4, valorASuperar);
	}
	
	public static void guardarMalos (ArrayList<Manzana> listaMalos,
						String nombreFichero) {
		try {
			PrintWriter f = new PrintWriter (new FileWriter (nombreFichero));
			f.write(listaMalos.size()+"\n");
			for (Manzana m : listaMalos) {
				f.write(m+"\n");
			}
			f.close();
		}
		catch (IOException e) {
			System.out.println("Error en la escritura del fichero");
		}
	}
}
