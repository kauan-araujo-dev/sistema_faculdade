package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controllers.CursoController;
import models.Curso;

public class ViewConsultarCurso extends JFrame {
	CursoController cursoController = new CursoController();
	JFrame frameAnterior;

	public ViewConsultarCurso(CursoController cursoController, JFrame frameAnterior, Curso[] cursos) {
		this.cursoController = cursoController;
		this.frameAnterior = frameAnterior;

		setTitle("CONSULTAR CURSOS");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CONSULTAR CURSOS", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		titulo.setBounds(200, 50, 400, 40);

		Object[][] dados = new Object[cursos.length][3];
		for (int i = 0; i < cursos.length; i++) {
			dados[i][0] = cursos[i].getCodigo();
			dados[i][1] = cursos[i].getNome();
			dados[i][2] = cursos[i].getArea();
		}

		String[] colunas = { "CÓDIGO", "NOME", "ÁREA" };

		JTable table = new JTable(dados, colunas);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
		table.getTableHeader().setBorder(null);
		;
		table.getTableHeader().setBackground(Color.decode("#252525"));
		table.getTableHeader().setForeground(Color.decode("#FFFFFF"));
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
