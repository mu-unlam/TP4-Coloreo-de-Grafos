package com.unlam.TP4;

public class ParNodos {

	private int nodo1;
	private int nodo2;

	/**
	 * Constructor de la clase
	 * 
	 * @param n1 Nodo 1
	 * @param n2 Nodo 2
	 */
	public ParNodos(int n1, int n2) {
		this.nodo1 = n1;
		this.nodo2 = n2;
	}

	public int getNodo1() {
		return nodo1;
	}

	public void setNodo1(int nodo1) {
		this.nodo1 = nodo1;
	}

	public int getNodo2() {
		return nodo2;
	}

	public void setNodo2(int nodo2) {
		this.nodo2 = nodo2;
	}

	public String toString() {
		return this.nodo1 + " " + this.nodo2;
	}

}
