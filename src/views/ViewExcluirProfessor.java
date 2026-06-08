package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.ProfessorController;
import models.Professor;

public class ViewExcluirProfessor extends JFrame {

	ProfessorController professorController = new ProfessorController();
	JFrame frameAnterior;
	JComboBox<Professor> combo;

	public ViewExcluirProfessor(ProfessorController professorController, JFrame frameAnterior,
			Professor[] professores) {
		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("EXCLUIR PROFESSOR");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EXCLUIR PROFESSOR", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 60, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER PROFESSOR:");
		labelEscolher.setBounds(250, 170, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Professor>();
		combo.setBounds(250, 195, 300, 35);
		combo.addItem(null);

		for (Professor professor : professores) {
			combo.addItem(professor);
		}

		panel.add(combo);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 280, 300, 45);
		btnExcluir.setVisible(false);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		combo.addActionListener(e -> {
			Professor professor = (Professor) combo.getSelectedItem();
			btnExcluir.setVisible(professor != null);
		});

		btnExcluir.addActionListener(e -> {
			Professor professor = (Professor) combo.getSelectedItem();

			if (professor != null) {
				professorController.excluirProfessor(professor.getCpf());
				professorController.mensagemView("EXCLUÍDO COM SUCESSO", this);
				combo.removeItem(professor);
				setVisible(false);
			}
		});

		setVisible(true);
	}
}