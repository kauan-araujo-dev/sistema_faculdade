package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.CursoController;
import controllers.DisciplinaController;
import controllers.InscricaoController;
import controllers.ProfessorController;

public class ViewPrincipal extends JFrame {
	public ViewPrincipal() {
		setTitle("HOME");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("HOME", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JButton btnCursos = new JButton("CURSOS");
		btnCursos.setBounds(250, 150, 300, 50);
		panel.add(btnCursos);

		JButton btnDisciplinas = new JButton("DISCIPLINAS");
		btnDisciplinas.setBounds(250, 220, 300, 50);
		panel.add(btnDisciplinas);

		JButton btnProfessores = new JButton("PROFESSORES");
		btnProfessores.setBounds(250, 290, 300, 50);
		panel.add(btnProfessores);

		JButton btnInscricoes = new JButton("INSCRIÇÕES");
		btnInscricoes.setBounds(250, 360, 300, 50);
		panel.add(btnInscricoes);

		PadroesView.estilizarBotao(btnCursos);
		PadroesView.estilizarBotao(btnDisciplinas);
		PadroesView.estilizarBotao(btnProfessores);
		PadroesView.estilizarBotao(btnInscricoes);

		btnCursos.addActionListener(e -> {
			setVisible(false);
			new ViewCurso(new CursoController(), this);
		});

		btnDisciplinas.addActionListener(e -> {
			setVisible(false);
			new ViewDisciplina(new DisciplinaController(), this);
		});

		btnProfessores.addActionListener(e -> {
			setVisible(false);
			new ViewProfessor(new ProfessorController(), this);
		});

		btnInscricoes.addActionListener(e -> {
			setVisible(false);
			new ViewInscricao(new InscricaoController(), this);
		});

		setVisible(true);
	}

}
