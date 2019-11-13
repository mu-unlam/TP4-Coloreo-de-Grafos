package com.unlam.TP4.nodos;

public class ParNodos {

	private Nodo nodo1;
	private Nodo nodo2;

	/**
	 * Constructor de la clase
	 * 
	 * @param n1 Nodo 1
	 * @param n2 Nodo 2
	 */
	public ParNodos(int n1, int n2) {
		this.nodo1 = new Nodo(n1);
		this.nodo2 = new Nodo(n2);
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
