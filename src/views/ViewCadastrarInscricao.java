package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.InscricaoController;
import models.Disciplina;
import models.Professor;

public class ViewCadastrarInscricao extends JFrame {

	private InscricaoController inscricaoController;
	private JFrame frameAnterior;

	private JComboBox<Professor> comboProfessor;
	private JComboBox<Disciplina> comboDisciplina;

	private JButton btnCadastrar;

	public ViewCadastrarInscricao(InscricaoController inscricaoController, JFrame frameAnterior,
			Professor[] professores, Disciplina[] disciplinas) {

		this.inscricaoController = inscricaoController;
		this.frameAnterior = frameAnterior;

		setTitle("CADASTRAR INSCRIÇÃO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CADASTRAR INSCRIÇÃO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JLabel labelProfessor = new JLabel("PROFESSOR:");
		labelProfessor.setBounds(250, 150, 300, 20);
		panel.add(labelProfessor);

		comboProfessor = new JComboBox<>();
		comboProfessor.setBounds(250, 175, 300, 35);
		comboProfessor.addItem(null);

		for (Professor professor : professores) {
			comboProfessor.addItem(professor);
		}

		panel.add(comboProfessor);

		JLabel labelDisciplina = new JLabel("DISCIPLINA:");
		labelDisciplina.setBounds(250, 250, 300, 20);
		panel.add(labelDisciplina);

		comboDisciplina = new JComboBox<>();
		comboDisciplina.setBounds(250, 275, 300, 35);
		comboDisciplina.addItem(null);

		for (Disciplina disciplina : disciplinas) {
			comboDisciplina.addItem(disciplina);
		}

		panel.add(comboDisciplina);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(325, 380, 150, 40);
		PadroesView.estilizarBotao(btnCadastrar);
		panel.add(btnCadastrar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		PadroesView.estilizarVoltar(btnVoltar);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {

			Professor professor = (Professor) comboProfessor.getSelectedItem();

			Disciplina disciplina = (Disciplina) comboDisciplina.getSelectedItem();

			if (professor != null && disciplina != null) {

				inscricaoController.cadastrarInscricao(professor.getCpf(), disciplina.getCodigo());

				inscricaoController.mensagemView("INSCRIÇÃO CADASTRADA COM SUCESSO", this);

				setVisible(false);

				comboProfessor.setSelectedIndex(0);
				comboDisciplina.setSelectedIndex(0);
			} else {
				setVisible(false);
				inscricaoController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}
		});

		setVisible(true);
	}
}