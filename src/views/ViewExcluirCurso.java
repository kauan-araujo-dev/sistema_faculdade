package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.CursoController;
import models.Curso;

public class ViewExcluirCurso extends JFrame {
	CursoController cursoController = new CursoController();
	JFrame frameAnterior;
	JComboBox<Curso> combo;

	public ViewExcluirCurso(CursoController cursoController, JFrame frameAnterior, Curso[] cursos) {
		this.cursoController = cursoController;
		this.frameAnterior = frameAnterior;

		setTitle("EXCLUIR CURSO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EXCLUIR CURSO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 60, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER CURSO:");
		labelEscolher.setBounds(250, 170, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Curso>();
		combo.setBounds(250, 195, 300, 35);
		combo.addItem(null);
		for (Curso curso : cursos) {
			combo.addItem(curso);
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
			Curso curso = (Curso) combo.getSelectedItem();

			if (curso != null) {
				btnExcluir.setVisible(true);
			} else {
				btnExcluir.setVisible(false);
			}
		});

		btnExcluir.addActionListener(e -> {
			Curso curso = (Curso) combo.getSelectedItem();

			if (curso != null) {
				cursoController.excluirCurso(curso.getCodigo());
				cursoController.mensagemView("EXCLUÍDO COM SUCESSO", this);
				combo.removeItem(combo.getSelectedItem());
				setVisible(false);
			}
		});

		setVisible(true);
	}
}
