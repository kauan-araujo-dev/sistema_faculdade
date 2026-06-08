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

import controllers.DisciplinaController;
import models.Curso;
import models.Disciplina;

public class ViewConsultarDisciplinaAbertas extends JFrame {

	DisciplinaController disciplinaController = new DisciplinaController();
	JFrame frameAnterior;

	public ViewConsultarDisciplinaAbertas(DisciplinaController disciplinaController, JFrame frameAnterior,
			Object[][] disciplinasCursos) {
		this.disciplinaController = disciplinaController;
		this.frameAnterior = frameAnterior;

		setTitle("CONSULTAR DISCIPLINAS");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CONSULTAR DISCIPLINAS", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		Object[][] dados = new Object[disciplinasCursos.length][7];

		for (int i = 0; i < disciplinasCursos.length; i++) {
			Disciplina disciplina = (Disciplina) disciplinasCursos[i][0];
			Curso curso = (Curso) disciplinasCursos[i][1];
			dados[i][0] = disciplina.getCodigo();
			dados[i][1] = disciplina.getNome();
			dados[i][2] = disciplina.getDiaSemana();
			dados[i][3] = disciplina.getHorario();
			dados[i][4] = disciplina.getQuantidadeHorasDiarias();
			dados[i][5] = curso.getNome();
			dados[i][6] = curso.getArea();
		}

		String[] colunas = { "CÓDIGO", "NOME", "DIA", "HORÁRIO", "HORAS", "CURSO", "ÁREA" };

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