package Pacote;

import java.util.ArrayList;


public class Grafo <T>{
	private ArrayList<Vertice<T>> vertices;
	private ArrayList<Aresta<T>> arestas;
	
	public Grafo() {
		this.arestas = new ArrayList<Aresta<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}
	
	public void adicionarVertice(Filme dado) {
		Vertice<T> novoVertice = new Vertice<T>(dado);
		this.vertices.add(novoVertice);
	}
	
	public Vertice<T> getVertice(Filme dado){
		Vertice<T> vertice = null;
		for (int i = 0; i < this.vertices.size(); i++) {
			if(this.vertices.get(i).getDado().equals(dado)) {
				vertice = this.vertices.get(i);
				break;
			}
		}
		return vertice;
	}
	
	public void adicionarAresta(Filme dadoInicio, Filme dadoFim) {
		Vertice<T> fim = this.getVertice(dadoFim);
		Vertice<T> inicio = this.getVertice(dadoInicio);
		int peso = calcularPeso(dadoInicio, dadoFim);
		Aresta<T> aresta = new Aresta<T>(peso, inicio, fim);
		inicio.adicionarArestaSaida(aresta);
		fim.adicionarArestaEntrada(aresta);
		this.arestas.add(aresta);
		
	}
	
	public int calcularPeso(Filme inicio, Filme fim) {
		int peso = 0;
		Filme verticeInicio = this.getVertice(inicio).getDado();
		Filme verticeFim = this.getVertice(fim).getDado();
		if(verticeInicio.getNome().equalsIgnoreCase(verticeFim.getNome())) {
			peso ++;
		}
		if(verticeInicio.getDiretor().equalsIgnoreCase(verticeFim.getDiretor())) {
			peso ++;
		}
		if(verticeInicio.getGenero().equalsIgnoreCase(verticeFim.getGenero())) {
			peso ++;
		}
		return peso;
	}
	
	public void buscaEmLargura(Filme dado){
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        Vertice<T> atual = this.getVertice(dado);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<T> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestaSaida().size(); i++){
                Vertice<T> proximo = visitado.getArestaSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ 
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
	
	
	public ArrayList<Filme> buscaEmLarguraArray(Filme dado){
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        ArrayList<Filme> filmes = new ArrayList<Filme>();
        Vertice<T> atual = this.getVertice(dado);
        marcados.add(atual);
        filmes.add(atual.getDado());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<T> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestaSaida().size(); i++){
                Vertice<T> proximo = visitado.getArestaSaida().get(i).getFim();
                if (!marcados.contains(proximo)){ 
                    marcados.add(proximo);
                    filmes.add(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
        return filmes;
    }
}
