package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.ProfessorController;

public class ViewCadastrarProfessor extends JFrame {

	private ProfessorController professorController;

	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtArea;
	private JTextField txtPontos;
	private JButton btnCadastrar;
	JFrame frameAnterior;

	public ViewCadastrarProfessor(ProfessorController professorController, JFrame frameAnterior) {

		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("CADASTRAR PROFESSOR");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CADASTRAR PROFESSOR", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 30, 400, 40);
		panel.add(titulo);

		JLabel labelCpf = new JLabel("CPF:");
		labelCpf.setBounds(250, 100, 300, 20);
		panel.add(labelCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(250, 125, 300, 35);
		panel.add(txtCpf);

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 170, 300, 20);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 195, 300, 35);
		panel.add(txtNome);

		JLabel labelArea = new JLabel("ÁREA:");
		labelArea.setBounds(250, 240, 300, 20);
		panel.add(labelArea);

		txtArea = new JTextField();
		txtArea.setBounds(250, 265, 300, 35);
		panel.add(txtArea);

		JLabel labelPontos = new JLabel("PONTOS:");
		labelPontos.setBounds(250, 310, 300, 20);
		panel.add(labelPontos);

		txtPontos = new JTextField();
		txtPontos.setBounds(250, 335, 300, 35);
		panel.add(txtPontos);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(325, 420, 150, 40);
		panel.add(btnCadastrar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {
			try {
				String cpf = txtCpf.getText();
				String nome = txtNome.getText();
				String area = txtArea.getText();
				double pontos = Double.parseDouble(txtPontos.getText());

				if (!(cpf.isEmpty() || nome.isEmpty() || area.isEmpty())) {

					professorController.cadastrarProfessor(cpf, nome, area, pontos);
					professorController.mensagemView("PROFESSOR CADASTRADO COM SUCESSO", this);
					setVisible(false);
					txtCpf.setText("");
					txtNome.setText("");
					txtArea.setText("");
					txtPontos.setText("");
				} else {
					setVisible(false);
					professorController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
				}
			} catch (Exception e1) {
				setVisible(false);
				professorController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}
		});

		setVisible(true);
	}
}