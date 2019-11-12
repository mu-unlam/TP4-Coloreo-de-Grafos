package com.unlam.TP4;

public class RandomParNodos implements Comparable<RandomParNodos> {
	private ParNodos nodos;
	private double probabilidad;

	/**
	 * Constructor de la clase
	 * 
	 * @param n
	 * @param prob
	 */
	public RandomParNodos(ParNodos n, double prob) {
		this.nodos = n;
		this.probabilidad = prob;
	}

	public ParNodos getNodos() {
		return this.nodos;
	}

	public double getProbabilidad() {
		return this.probabilidad;
	}

	public String toString() {
		return this.nodos.getNodo1() + " " + this.nodos.getNodo2() + " " + this.probabilidad;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(RandomParNodos o) {
		if (this.probabilidad > o.probabilidad)
			return -1;
		if (this.probabilidad < o.probabilidad)
			return 1;
		return 0;
	}

}
