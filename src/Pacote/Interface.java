package Pacote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Interface<T> extends JFrame implements ActionListener{
	
	Grafo<T> grafo = new Grafo<T>();
	ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	  JMenuBar barraMenu;
	  JMenu principal, atualizar;
	  JMenuItem main, adicionar;
	  JPanel inicial, painelAdicionar, menu, listaFilme;
	  JLabel recomendacao;
	public Interface() {
		Filme velozes = new Filme("Velozes e furiosos", "tom cruise","acao","https://pbs.twimg.com/media/FluIoz2XoAYkeYn.jpg:large", 5,2030);
		Filme indiana = new Filme("Indiana Jones", "luke","acao","https://static.wikia.nocookie.net/cyberpunk/images/a/ac/David_Infobox_CPEDGE.png/revision/latest?cb=20220915120459",4,2302);
		Filme killbill = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3,2302);
		Filme pokemon = new Filme("Kill bill", "tom cruise", "acao", "sadasd", 3,2302);
		filmes.add(killbill);
		filmes.add(velozes);
		filmes.add(indiana);
		filmes.add(pokemon);
		inicial =  new JPanel();
		inicial.setLayout(new BorderLayout());
		setTitle("Ado 1 - Projeto Integrador");
		setSize(800, 800);
		getContentPane().setLayout(new BorderLayout());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.lightGray);
		menu = new JPanel();
		menu.setLayout(new GridLayout(0,1));
		listaFilme = new JPanel();
		recomendacao = criarLabel("");
		listaFilme.setLayout(new FlowLayout());
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
		try {
			BufferedImage bufferedImage = ImageIO.read(new URL(filme.getFt()));
			Image image = bufferedImage.getScaledInstance(70, 80, Image.SCALE_DEFAULT);
			JLabel img = new JLabel(new ImageIcon(image));
			img.setSize(1,1);
			painel.add(img);
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		JLabel nome = criarLabel("Nome: " +filme.getNome());
		JLabel genero = criarLabel("Genero: "+ filme.getGenero());
		JLabel diretor = criarLabel("Diretor: "+filme.getDiretor());
		JLabel ano = criarLabel("Ano: " +filme.getAno());
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
		painel.add(ano);
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
		this.remove(painelAdicionar);
		this.renderizarLista();
		recomendacao.setText("");
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
		painelAdicionar = new JPanel();
		JPanel botao = new JPanel();
		JPanel inputs = new JPanel();
		painelAdicionar.setLayout(new GridLayout(0,1));
		inputs.setBackground(Color.LIGHT_GRAY);
		recomendacao.setText("Adicionar novo filme");
		recomendacao.setForeground(Color.BLUE);
		inputs.setLayout(new GridLayout(0,2));
		JLabel nomeLabel = criarLabel("Nome");
		JLabel diretorLabel = criarLabel("Diretor");
		JLabel generoLabel = criarLabel("Gênero");
		JLabel anoLabel = criarLabel("Ano lançamento");
		JLabel notaLabel = criarLabel("Nota: ");
		JLabel urlLabel = criarLabel("URL da imagem");
		
		JTextField nomeInput = criarInput();
		JTextField diretorInput = criarInput();
		JTextField generoInput = criarInput();
		JTextField anoInput = criarInput();
		JTextField notaInput = criarInput();
		JTextField urlInput = criarInput();
		
		inputs.add(criarPainelLables(painelAdicionar,nomeLabel,nomeInput));
		inputs.add(criarPainelLables(painelAdicionar,diretorLabel,diretorInput));
		inputs.add(criarPainelLables(painelAdicionar,generoLabel,generoInput));
		inputs.add(criarPainelLables(painelAdicionar,anoLabel,anoInput));
		inputs.add(criarPainelLables(painelAdicionar,notaLabel,notaInput));
		inputs.add(criarPainelLables(painelAdicionar,urlLabel,urlInput));
		
		JButton adicionar = new JButton("Adicionar");
		
		adicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == adicionar) {
					
					Filme novo = new Filme(nomeInput.getText(), diretorInput.getText(), generoInput.getText(),urlInput.getText(),Integer.parseInt(notaInput.getText()),Integer.parseInt(anoInput.getText()));
					filmes.add(novo);
					int decisao =0;
					do {
						decisao = Integer.parseInt(JOptionPane.showInputDialog(listaFilmesIndex()));
						decisao -= 1;
						
						if(decisao == -1) {
							break;
						}else {
							grafo.adicionarVertice(novo);
							grafo.adicionarAresta(novo, filmes.get(decisao));
							
							
						}
					}while(decisao != -1);
				}
				
			}
		});
		
		botao.add(adicionar);
		
		painelAdicionar.add(inputs);
		painelAdicionar.add(botao);
		add(painelAdicionar);
		this.revalidate();
		this.repaint();
	}
	
	private JPanel criarPainelLables(JPanel painel, JLabel label, JTextField Input) {
		JPanel novoPainel = new JPanel();
		novoPainel.setLayout(null);
		label.setBounds(40, 50, 100, 40);
		Input.setBounds(140, 50, 100, 30);

		novoPainel.add(label);
		novoPainel.add(Input);
		return novoPainel;
	}

	private JTextField criarInput() {
		JTextField texto = new JTextField();
		texto.setSize(1, 1);
		return texto;
	}
	
	
	private String listaFilmesIndex() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Lista de filmes registrados: \n");
		
		for (int i = 0; i < filmes.size(); i++) {
			builder.append("[" + (i +1) +"] " + filmes.get(i).getNome()  +"\n");
		}
		
		builder.append("[0] Caso esse filme não tenha relação com nenhum outro");
		String texto = builder.toString();
		return texto;
	}
}
