package Pacote;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		
		Filme velozes = new Filme("Velozes e furiosos", "tom cruise","acao","asdajsdhash", 5,123123);
		Filme indiana = new Filme("Indiana Jones", "luke","acao","asdasd",4,123123);
		Filme killbill = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3,123123);
		
		grafo.adicionarVertice(velozes);
		grafo.adicionarVertice(killbill);
		grafo.adicionarVertice(indiana);
		
		grafo.adicionarAresta(indiana, killbill);
		grafo.adicionarAresta(velozes, killbill);
		
		grafo.buscaEmLargura(killbill);
		
	}

}
