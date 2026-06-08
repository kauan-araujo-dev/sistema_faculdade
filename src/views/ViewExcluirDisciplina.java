package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.DisciplinaController;
import models.Disciplina;

public class ViewExcluirDisciplina extends JFrame {

	DisciplinaController disciplinaController = new DisciplinaController();
	JFrame frameAnterior;
	JComboBox<Disciplina> combo;

	public ViewExcluirDisciplina(DisciplinaController disciplinaController, JFrame frameAnterior,
			Disciplina[] disciplinas) {
		this.disciplinaController = disciplinaController;
		this.frameAnterior = frameAnterior;

		setTitle("EXCLUIR DISCIPLINA");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EXCLUIR DISCIPLINA", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 60, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER DISCIPLINA:");
		labelEscolher.setBounds(250, 170, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Disciplina>();
		combo.setBounds(250, 195, 300, 35);
		combo.addItem(null);

		for (Disciplina disciplina : disciplinas) {
			combo.addItem(disciplina);
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
			Disciplina disciplina = (Disciplina) combo.getSelectedItem();

			if (disciplina != null) {
				btnExcluir.setVisible(true);
			} else {
				btnExcluir.setVisible(false);
			}
		});

		btnExcluir.addActionListener(e -> {
			Disciplina disciplina = (Disciplina) combo.getSelectedItem();

			if (disciplina != null) {
				disciplinaController.excluirDisciplina(disciplina.getCodigo());
				disciplinaController.mensagemView("EXCLUÍDO COM SUCESSO", this);
				combo.removeItem(combo.getSelectedItem());
				setVisible(false);
			}
		});

		setVisible(true);
	}
}