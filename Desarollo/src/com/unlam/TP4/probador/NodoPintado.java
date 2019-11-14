package com.unlam.TP4.probador;

public class NodoPintado {

	private int nodo;
	private int color;

	public NodoPintado(int numero, int color) {
		this.nodo = numero;
		this.color = color;
	}

	public int getNodo() {
		return nodo;
	}

	public void setNodo(int nodo) {
		this.nodo = nodo;
	}

	public int getColor() {
		return this.color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
