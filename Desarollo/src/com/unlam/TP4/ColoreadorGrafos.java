package com.unlam.TP4;

public class ColoreadorGrafos {
	/**
	 * El orden en que se colorean los v�rtices se decide antes de que se empiece a
	 * colorearlos. Dada una ordenaci�n de los v�rtices del grafo, los algoritmos
	 * secuenciales asignan el m�nimo color posible al siguiente v�rtice. Es decir,
	 * si queremos colorear V, teniendo ordenados num�ricamente los colores,
	 * asignamos a V el color m�s peque�o que no aparece entre los asignados a los
	 * vecinos de V ya coloreados.
	 * 
	 * @param cantEjecuciones
	 */
	public void coloreoSecuencialAleatorio(int cantEjecuciones) {
	}

	/**
	 * Es una variante del algoritmo de coloraci�n secuencial b�sico, tambi�n
	 * conocida como "Primero el de mayor grado". Es debida a Welsh y Powell, y en
	 * este algoritmo, los v�rtices se ordenan inicialmente de acuerdo a sus grados.
	 * Es decir, ordenamos de forma que d(V1) >= d(V2) >= ... >= d(Vn).
	 * 
	 * @param cantEjecuciones
	 */
	public void coloreoWelshPowell(int cantEjecuciones) {
	}

	/**
	 * Es una variante del algoritmo de coloraci�n secuencial b�sico, tambi�n
	 * conocida como "El de menor grado el �ltimo". Se debe a Marble, Matula e
	 * Isaacson, y en este algoritmo, los v�rtices se ordenan en orden inverso.
	 * Primero se elige vn como el v�rtice de menor grado, luego se elige Vn-1 como
	 * el v�rtice de menor grado en G-{Vn}, y as� se contin�a recursivamente,
	 * examinando los v�rtices de menor grado y elimin�ndolos del grafo
	 * 
	 * @param cantEjecuciones
	 */
	public void coloreoMatula(int cantEjecuciones) {
	}

}
