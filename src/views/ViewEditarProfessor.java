package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.ProfessorController;
import models.Professor;

public class ViewEditarProfessor extends JFrame {

	ProfessorController professorController;
	JButton btnEditar;
	JTextField txtNome;
	JTextField txtArea;
	JTextField txtPontos;
	JFrame frameAnterior;
	JComboBox<Professor> combo;

	public ViewEditarProfessor(ProfessorController professorController, JFrame frameAnterior, Professor[] professores) {
		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("EDITAR PROFESSOR");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EDITAR PROFESSOR", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 40, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER PROFESSOR:");
		labelEscolher.setBounds(250, 120, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Professor>();
		combo.setBounds(250, 145, 300, 35);
		combo.addItem(null);

		for (Professor professor : professores) {
			combo.addItem(professor);
		}

		panel.add(combo);

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 210, 300, 20);
		labelNome.setVisible(false);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 235, 300, 35);
		txtNome.setVisible(false);
		panel.add(txtNome);

		JLabel labelArea = new JLabel("ÁREA:");
		labelArea.setBounds(250, 285, 300, 20);
		labelArea.setVisible(false);
		panel.add(labelArea);

		txtArea = new JTextField();
		txtArea.setBounds(250, 310, 300, 35);
		txtArea.setVisible(false);
		panel.add(txtArea);

		JLabel labelPontos = new JLabel("PONTOS:");
		labelPontos.setBounds(250, 360, 300, 20);
		labelPontos.setVisible(false);
		panel.add(labelPontos);

		txtPontos = new JTextField();
		txtPontos.setBounds(250, 385, 300, 35);
		txtPontos.setVisible(false);
		panel.add(txtPontos);

		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(325, 470, 150, 40);
		btnEditar.setVisible(false);
		panel.add(btnEditar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		combo.addActionListener(e -> {
			Professor professor = (Professor) combo.getSelectedItem();

			if (professor != null) {
				labelNome.setVisible(true);
				txtNome.setText(professor.getNome());
				txtNome.setVisible(true);

				labelArea.setVisible(true);
				txtArea.setText(professor.getArea());
				txtArea.setVisible(true);

				labelPontos.setVisible(true);
				txtPontos.setText(Double.toString(professor.getQuantidadePontos()));
				txtPontos.setVisible(true);

				btnEditar.setVisible(true);
			} else {
				labelNome.setVisible(false);
				txtNome.setText("");
				txtNome.setVisible(false);

				labelArea.setVisible(false);
				txtArea.setText("");
				txtArea.setVisible(false);

				labelPontos.setVisible(false);
				txtPontos.setText("");
				txtPontos.setVisible(false);

				btnEditar.setVisible(false);
			}
		});

		btnEditar.addActionListener(e -> {
			try {
				Professor professor = (Professor) combo.getSelectedItem();

				String nome = txtNome.getText();
				String area = txtArea.getText();
				double pontos = Double.parseDouble(txtPontos.getText());
				if (!(professor.getCpf().isEmpty() || nome.isEmpty() || area.isEmpty())) {

					professorController.editarProfessor(professor.getCpf(), nome, area, pontos);

					professor.setNome(nome);
					professor.setArea(area);
					professor.setQuantidadePontos(pontos);

					professorController.mensagemView("PROFESSOR EDITADO COM SUCESSO", this);
				} else {
					setVisible(false);
					professorController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
				}
				setVisible(false);
			} catch (Exception e1) {
				setVisible(false);
				professorController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}

		});

		setVisible(true);
	}
}