package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.ProfessorController;

public class ViewProfessor extends JFrame {

	ProfessorController professorController = new ProfessorController();
	JFrame frameAnterior;

	public ViewProfessor(ProfessorController professorController, JFrame frameAnterior) {

		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("PROFESSORES");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("PROFESSORES", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(250, 150, 300, 50);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(250, 220, 300, 50);
		panel.add(btnEditar);

		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(250, 290, 300, 50);
		panel.add(btnConsultar);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 360, 300, 50);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarBotao(btnConsultar);
		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {
			setVisible(false);
			professorController.cadastrarView(this);
		});

		btnEditar.addActionListener(e -> {
			setVisible(false);
			professorController.editarView(this);
		});

		btnConsultar.addActionListener(e -> {
			setVisible(false);
			professorController.consultarView(this);
		});

		btnExcluir.addActionListener(e -> {
			setVisible(false);
			professorController.excluirView(this);
		});

		setVisible(true);
	}

}