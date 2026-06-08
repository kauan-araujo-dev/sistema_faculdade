package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.CursoController;
import models.Curso;

public class ViewEditarCurso extends JFrame {
	CursoController cursoController;
	JButton btnEditar;
	JTextField txtNome;
	JTextField txtArea;
	JFrame frameAnterior;
	JComboBox<Curso> combo;

	public ViewEditarCurso(CursoController cursoController, JFrame frameAnterior, Curso[] cursos) {
		this.cursoController = cursoController;
		this.frameAnterior = frameAnterior;

		setTitle("EDITAR CURSO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EDITAR CURSO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 40, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER CURSO:");
		labelEscolher.setBounds(250, 110, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Curso>();
		combo.setBounds(250, 135, 300, 35);
		combo.addItem(null);
		for (Curso curso : cursos) {
			combo.addItem(curso);
		}

		panel.add(combo);

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 200, 300, 20);
		labelNome.setVisible(false);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 225, 300, 35);
		txtNome.setVisible(false);
		panel.add(txtNome);

		JLabel labelArea = new JLabel("ÁREA:");
		labelArea.setBounds(250, 280, 300, 20);
		labelArea.setVisible(false);
		panel.add(labelArea);

		txtArea = new JTextField();
		txtArea.setBounds(250, 305, 300, 35);
		txtArea.setVisible(false);
		panel.add(txtArea);

		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(325, 390, 150, 40);
		btnEditar.setVisible(false);
		panel.add(btnEditar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		combo.addActionListener(e -> {
			Curso curso = (Curso) combo.getSelectedItem();

			if (curso != null) {
				labelNome.setVisible(true);
				txtNome.setText(curso.getNome());
				txtNome.setVisible(true);

				labelArea.setVisible(true);
				txtArea.setText(curso.getArea());
				txtArea.setVisible(true);

				btnEditar.setVisible(true);

			} else {
				labelNome.setVisible(false);
				txtNome.setText(null);
				txtNome.setVisible(false);

				labelArea.setVisible(false);
				txtArea.setVisible(false);
				txtArea.setText(null);

				btnEditar.setVisible(false);
			}
		});

		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnEditar.addActionListener(e -> {
			Curso curso = (Curso) combo.getSelectedItem();
			String nome = txtNome.getText();
			String area = txtArea.getText();

			if (!nome.equals("") && !area.equals("")) {

				cursoController.editarCurso(curso.getCodigo(), nome, area);

				cursoController.mensagemView("CURSO CADASTRADO COM SUCESSO", this);
				setVisible(false);

				curso.setNome(txtNome.getText());
				curso.setArea(txtArea.getText());

				combo.setSelectedItem(0);
				txtNome.setText("");
				txtArea.setText("");
			} else {
				setVisible(false);
				cursoController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}
		});

		setVisible(true);

	}

}
