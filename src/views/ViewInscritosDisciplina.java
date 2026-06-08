package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.ProfessorController;
import models.Disciplina;
import models.Professor;

public class ViewInscritosDisciplina extends JFrame {

	ProfessorController professorController = new ProfessorController();
	JFrame frameAnterior;
	private JComboBox<Disciplina> comboDisciplina;

	public ViewInscritosDisciplina(ProfessorController professorController, JFrame frameAnterior,
			Disciplina[] disciplinas) {

		this.professorController = professorController;
		this.frameAnterior = frameAnterior;

		setTitle("CONSULTAR INSCRITOS");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();

		JLabel titulo = new JLabel("CONSULTAR INSCRITOS", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JLabel labelDisciplina = new JLabel("DISCIPLINA:");
		labelDisciplina.setBounds(250, 100, 300, 20);
		panel.add(labelDisciplina);

		comboDisciplina = new JComboBox<>();
		comboDisciplina.setBounds(250, 125, 300, 35);
		comboDisciplina.addItem(null);

		for (Disciplina disciplina : disciplinas) {
			comboDisciplina.addItem(disciplina);
		}

		panel.add(comboDisciplina);
		panel.setLayout(null);

		JTable table = new JTable(null);
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
		scroll.setBounds(50, 190, 700, 320);

		panel.add(scroll);
		scroll.setVisible(false);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		comboDisciplina.addActionListener(e -> {
			Disciplina disciplina = (Disciplina) comboDisciplina.getSelectedItem();

			if (disciplina != null) {

				Professor[] professores = professorController.consultarProfessoresDisciplina(disciplina.getCodigo());
				if (professores.length != 0) {

				}
				Object[][] dados = new Object[professores.length][4];
				String[] colunas = { "CPF", "NOME", "ÁREA", "PONTOS" };

				for (int i = 0; i < professores.length; i++) {
					dados[i][0] = professores[i].getCpf();
					dados[i][1] = professores[i].getNome();
					dados[i][2] = professores[i].getArea();
					dados[i][3] = professores[i].getQuantidadePontos();
				}
				table.setModel(new DefaultTableModel(dados, colunas));
				scroll.setVisible(true);
			} else {
				scroll.setVisible(false);
				table.setModel(null);
			}
		});

		setVisible(true);
	}
}
