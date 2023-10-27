package Pacote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Interface<T> extends JFrame implements ActionListener{
	
	Grafo<T> grafo = new Grafo<T>();
	ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	  JMenuBar barraMenu;
	  JMenu principal, atualizar;
	  JMenuItem main, adicionar;
	  JPanel inicial, add, menu, listaFilme;
	  JLabel recomendacao;
	public Interface() {
		Filme velozes = new Filme("Velozes e furiosos", "tom cruise","acao","asdajsdhash", 5);
		Filme indiana = new Filme("Indiana Jones", "luke","acao","asdasd",4);
		Filme killbill = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3);
		filmes.add(killbill);
		filmes.add(velozes);
		filmes.add(indiana);
		inicial =  new JPanel();
		inicial.setLayout(new BorderLayout());
		setTitle("Ado 1 - Projeto Integrador");
		setSize(800, 400);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.lightGray);
		menu = new JPanel();
		menu.setLayout(new GridLayout(0,1));
		listaFilme = new JPanel();
		recomendacao = criarLabel("");
		listaFilme.setLayout(new GridLayout(0,3));
		add(menu, BorderLayout.NORTH);
		
		inicial.add(listaFilme, BorderLayout.CENTER);
		renderizarLista();

		criarMenu();
		menu.add(recomendacao);
		add(inicial);
		this.revalidate();
	}
	
	public static void main(String[] args) {
		new Interface();
		
		

	}
	
	public JPanel criarPanelFilme(Filme filme) {
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0,1));
		painel.setAlignmentX(SwingConstants.CENTER);;
		painel.setBackground(Color.lightGray);
		JLabel nome = criarLabel("Nome: " +filme.getNome());
		JLabel genero = criarLabel("Genero: "+ filme.getGenero());
		JLabel diretor = criarLabel("Diretor: "+filme.getDiretor());
		JLabel nota = criarLabel("Nota: "+Integer.toString(filme.getNota())+"/5");
		JButton like = new JButton("Gostei");
		like.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				novaPágina(filme);
				
			}

			private void novaPágina(Filme filme) {
				filmes = grafo.buscaEmLarguraArray(filme);
				recomendacao.setText("Recomendações");
				recomendacao.setForeground(Color.red);
				listaFilme.removeAll();
				renderizarLista();
			}
		});
		painel.add(nome);
		painel.add(genero);
		painel.add(diretor);
		painel.add(nota);
		painel.add(like);
		return painel;
	}
	
	public void criarMenu() {
		barraMenu = new JMenuBar();
		principal = new JMenu("Principal");
		atualizar = new JMenu("Atualizar");
		main = new JMenuItem("Main");
		adicionar = new JMenuItem("Adicionar");
		
		adicionar.addActionListener(this);
		main.addActionListener(this);
		
		barraMenu.add(principal);
		barraMenu.add(atualizar);
		principal.add(main);
		atualizar.add(adicionar);
		menu.add(barraMenu);
	}
	
	public JLabel criarLabel(String texto) {
		JLabel label = new JLabel(texto);

		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		return label;
	}
	
	public void renderizarLista() {
		listaFilme.removeAll();
		for (int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			listaFilme.add(criarPanelFilme(filme));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adicionar) {
			paginaAdicionar();
		}
		if(e.getSource() == main) {
			paginaInicial();
		}
		
	}

	private void paginaInicial() {
		this.renderizarLista();
		this.add(inicial);
		this.revalidate();
		this.repaint();
		
	}

	private void paginaAdicionar() {
		this.remove(inicial);
		renderizarPaginaAdicionar();
		this.revalidate();
		this.repaint();
		
	}

	private void renderizarPaginaAdicionar() {
		add = new JPanel();
		add.setBackground(Color.LIGHT_GRAY);
		recomendacao.setText("Adicionar novo filme");
		recomendacao.setForeground(Color.BLUE);
		add.setLayout(new GridLayout(0,4));
		
		
	}

}
