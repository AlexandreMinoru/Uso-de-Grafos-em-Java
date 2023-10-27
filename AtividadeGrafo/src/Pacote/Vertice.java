package Pacote;

import java.util.ArrayList;

public class Vertice<T> {
	private Filme dado;
	private ArrayList<Aresta<T>> arestaEntrada, arestaSaida;
	
	public Vertice(Filme dado) {
		this.dado = dado;
		this.arestaEntrada = new ArrayList<Aresta<T>>();
		this.arestaSaida = new ArrayList<Aresta<T>>();
	}

	public Vertice(Filme dado, ArrayList<Aresta<T>> arestaEntrada, ArrayList<Aresta<T>> arestaSaida) {
		super();
		this.dado = dado;
		this.arestaEntrada = arestaEntrada;
		this.arestaSaida = arestaSaida;
	}

	public Filme getDado() {
		return dado;
	}

	public void setDado(Filme dado) {
		this.dado = dado;
	}

	public ArrayList<Aresta<T>> getArestaEntrada() {
		return arestaEntrada;
	}

	public void setArestaEntrada(ArrayList<Aresta<T>> arestaEntrada) {
		this.arestaEntrada = arestaEntrada;
	}

	public ArrayList<Aresta<T>> getArestaSaida() {
		return arestaSaida;
	}

	public void setArestaSaida(ArrayList<Aresta<T>> arestaSaida) {
		this.arestaSaida = arestaSaida;
	}
	
	public void adicionarArestaEntrada(Aresta<T> arestaEntrada) {
		this.arestaEntrada.add(arestaEntrada);
	}
	
	public void adicionarArestaSaida(Aresta<T> arestaSaida) {
		this.arestaEntrada.add(arestaSaida);
	}
}
