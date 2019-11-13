package com.unlam.TP4.nodos;

public class ParNodos {

	private Nodo nodo1;
	private Nodo nodo2;

	/**
	 * Constructor de la clase
	 * 
	 * @param nodo1 Nodo 1
	 * @param nodo2 Nodo 2
	 */
	public ParNodos(Nodo nodo1, Nodo nodo2) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}

	public Nodo getNodo1() {
		return nodo1;
	}

	public void setNodo1(Nodo nodo1) {
		this.nodo1 = nodo1;
	}

	public Nodo getNodo2() {
		return nodo2;
	}

	public void setNodo2(Nodo nodo2) {
		this.nodo2 = nodo2;
	}

	public String toString() {
		return this.nodo1.getNroNodo() + " " + this.nodo2.getNroNodo();
	}

}
