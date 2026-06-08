package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.ProfessorController;
import models.Professor;

public class ViewConsultarProfessor extends JFrame {

	ProfessorController professorController = new ProfessorController();
	JFrame frameAnterior;

	public ViewConsultarProfessor(ProfessorController professorController, JFrame frameAnterior,
			Professor[] professores) {
		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("CONSULTAR PROFESSORES");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CONSULTAR PROFESSORES", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		Object[][] dados = new Object[professores.length][4];

		for (int i = 0; i < professores.length; i++) {
			dados[i][0] = professores[i].getCpf();
			dados[i][1] = professores[i].getNome();
			dados[i][2] = professores[i].getArea();
			dados[i][3] = professores[i].getQuantidadePontos();
		}

		String[] colunas = { "CPF", "NOME", "ÁREA", "PONTOS" };

		JTable table = new JTable(dados, colunas);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
		table.getTableHeader().setBorder(null);
		table.getTableHeader().setBackground(Color.decode("#252525"));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

		table.setBorder(null);
		table.setRowHeight(35);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(null);
		scroll.setBounds(50, 120, 700, 380);
		panel.add(scroll);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		PadroesView.estilizarVoltar(btnVoltar);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		setVisible(true);
	}
}