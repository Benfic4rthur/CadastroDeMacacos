package model;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cadastro.cadastro_JDBC.SingleConnection;


public class CadastroDB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField deField;
	private JTextField emailToField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDB frame = new CadastroDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Connection connection;

	public CadastroDB() {
		setTitle("Cadastro Macacos");
		setBounds(100, 100, 470, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Configura o tamanho da janela
		setSize(390, 140);
		// Obtém as dimensões da tela
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		// Calcula a posição da janela no centro da tela
		int x = (tela.width - getWidth()) / 2;
		int y = (tela.height - getHeight()) / 2;
		// Configura a posição da janela
		setLocation(x, y);
		// Configura o comportamento padrão do botão fechar da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Labels */

		JLabel lblde = new JLabel("Nome de macaco:");
		lblde.setBounds(10, 10, 120, 14);
		contentPane.add(lblde);

		JLabel lblPara = new JLabel("E-mail de macaco:");
		lblPara.setBounds(10, 35, 120, 14);
		contentPane.add(lblPara);

		/* Text Fields */

		deField = new JTextField();
		deField.setBounds(120, 7, 250, 20);
		contentPane.add(deField);
		deField.setColumns(10);

		emailToField = new JTextField();
		emailToField.setBounds(120, 32, 250, 20);
		contentPane.add(emailToField);
		emailToField.setColumns(10);

		/* Mensagem */

		/* Botão para anexar arquivo */

		/* Listener do botão de anexo */

		JButton btnEnviar = new JButton("Salvar macaco");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					salvarCadastro();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(30, 70, 150, 23);
		contentPane.add(btnEnviar);
		// Atribui à variável connection uma instância de Connection obtida através do
		// método getConnection() da classe SingleConnection
		connection = SingleConnection.getConnection();

		JButton btnLimpar = new JButton("Limpar Macaco");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deField.setText("");
				emailToField.setText("");
			}
		});
		btnLimpar.setBounds(200, 70, 150, 23);
		contentPane.add(btnLimpar);

	}

	public int buscarUltimoId() throws SQLException {
		String sql = "SELECT MAX(id) FROM cadastro_de_macacos;";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();

		if (resultado.next()) {
			int ultimoId = resultado.getInt("max");
			return ultimoId;
		} else {
			throw new SQLException("Não foi possível buscar o último ID gerado.");
		}
	}

	private void salvarCadastro() throws IOException, Exception {
		String nome = deField.getText();
		String email = emailToField.getText();

		if (nome.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
			return;
		}

		String[] imagensDeErro = { "C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_De_olho.jpg",
									"C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_pistola.jpg",
									"C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_vaitelasca.jpg",
									"C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_sad.jpg",
									"C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_pito.jpg"};
				//substitua os caminhos pelas localizações das suas imagens

		String[] titulosDeErro = { "Macaco apenas observa seus erros!", "Ops, Você irritou o macaco!",
				"Cuidado com o macaco, ele não tolera erros!","DANGER! LO MACACO ESTAS BRAVITO!" , "O macaco não esta nada surpreso com seu erro!" ,
				"Se você continuar errando, não poderá ser um macaco nunca!" , "Errar é humano, macacos não cometem esta falha!"};

		if (!nome.contains("macaco") && !nome.contains("Macaco") && !nome.contains("MACACO")) {
			JOptionPane.showMessageDialog(null,
					"Por favor, preencha com dados de macaco");
			Random rand = new Random();
			int index = rand.nextInt(imagensDeErro.length);
			ImageIcon icon = new ImageIcon(imagensDeErro[index]);
			JDialog dialog = new JDialog();
			int tituloIndex = rand.nextInt(titulosDeErro.length);
			dialog.setTitle(titulosDeErro[tituloIndex]);
			JLabel label = new JLabel(icon);
			dialog.getContentPane().add(label);
			dialog.pack();
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			return;
		} else {
			Long ultimoId = (long) buscarUltimoId();
			MinhaUserPosJava minhaUserposJava = new MinhaUserPosJava(ultimoId, nome, email);
			try {
				boolean enviadoComSucesso = minhaUserposJava.salvaCadastro();
				if (enviadoComSucesso) {
					if (enviadoComSucesso) {
						JOptionPane.showMessageDialog(null, "Você cadastrou " + nome + " com sucesso!");
						ImageIcon icon = new ImageIcon(
								"C:\\workspace-java\\cadastro-JDBC\\src\\main\\java\\images\\macaco_feliz.jpg"); 
						// substitua o caminho pela localização da sua imagem
						JDialog dialog = new JDialog();
						dialog.setTitle("Você alegrou um macaco"); // define o título da janela
						JLabel label = new JLabel(icon);
						dialog.getContentPane().add(label);
						dialog.pack();
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null); // centralizar a imagem na tela
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao Salvar o Cadastro.");

				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao Salvar o Cadastro:" + ex.getMessage());
			}
		}
	}
}