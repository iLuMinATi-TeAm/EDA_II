package Practica_01;

public class Manzana {

	private int calle;
	private int avenida;
	private int absentismo;
	private int posicion;
	
	public Manzana(int calle, int avenida, int absentismo) {
		this.calle = calle;
		this.avenida = avenida;
		this.absentismo = absentismo;
		posicion = 0;
	}
	
	public Manzana (int absentismo) {
		calle = 0;
		avenida = 0;
		this.absentismo = absentismo;
		posicion = 0;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getCalle() {
		return calle;
	}

	public void setCalle(int calle) {
		this.calle = calle;
	}

	public int getAvenida() {
		return avenida;
	}

	public void setAvenida(int avenida) {
		this.avenida = avenida;
	}

	public int getAbsentismo() {
		return absentismo;
	}

	public void setAbsentismo(int absentismo) {
		this.absentismo = absentismo;
	}
	
	public String toString () {
		return "("+posicion+")  ("+avenida+","+calle+") --> "+absentismo;
	}
}
