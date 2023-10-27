package Pacote;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		
		
		Filme velozes = new Filme("Velozes e furiosos", "tom cruise","acao","asdajsdhash", 5);
		Filme indiana = new Filme("Indiana Jones", "luke","acao","asdasd",4);
		Filme killbill = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3);
		
		grafo.adicionarVertice(velozes);
		grafo.adicionarVertice(killbill);
		grafo.adicionarVertice(indiana);
		
		grafo.adicionarAresta(indiana, killbill);
		grafo.adicionarAresta(velozes, killbill);
		
		grafo.buscaEmLargura(killbill);
		
	}

}
