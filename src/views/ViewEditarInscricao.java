package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.InscricaoController;
import models.Disciplina;
import models.Inscricao;
import models.Professor;

public class ViewEditarInscricao extends JFrame {

	InscricaoController inscricaoController;
	JButton btnEditar;
	JFrame frameAnterior;

	JComboBox<Inscricao> combo;
	JComboBox<Professor> comboProfessor;
	JComboBox<Disciplina> comboDisciplina;

	public ViewEditarInscricao(InscricaoController inscricaoController, JFrame frameAnterior, Inscricao[] inscricoes,
			Professor[] professores, Disciplina[] disciplinas) {
		this.inscricaoController = inscricaoController;
		this.frameAnterior = frameAnterior;

		setTitle("EDITAR INSCRIÇÃO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EDITAR INSCRIÇÃO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER INSCRIÇÃO:");
		labelEscolher.setBounds(250, 130, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<>();
		combo.setBounds(250, 155, 300, 35);
		combo.addItem(null);

		for (Inscricao inscricao : inscricoes) {
			combo.addItem(inscricao);
		}

		panel.add(combo);

		JLabel labelProfessor = new JLabel("PROFESSOR:");
		labelProfessor.setBounds(250, 230, 300, 20);
		labelProfessor.setVisible(false);
		panel.add(labelProfessor);

		comboProfessor = new JComboBox<>();
		comboProfessor.setBounds(250, 255, 300, 35);
		comboProfessor.setVisible(false);
		comboProfessor.addItem(null);

		for (Professor professor : professores) {
			comboProfessor.addItem(professor);
		}

		panel.add(comboProfessor);

		JLabel labelDisciplina = new JLabel("DISCIPLINA:");
		labelDisciplina.setBounds(250, 315, 300, 20);
		labelDisciplina.setVisible(false);
		panel.add(labelDisciplina);

		comboDisciplina = new JComboBox<>();
		comboDisciplina.setBounds(250, 340, 300, 35);
		comboDisciplina.setVisible(false);
		comboDisciplina.addItem(null);

		for (Disciplina disciplina : disciplinas) {
			comboDisciplina.addItem(disciplina);
		}

		panel.add(comboDisciplina);

		btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(325, 430, 150, 40);
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
			Inscricao inscricao = (Inscricao) combo.getSelectedItem();

			if (inscricao != null) {
				labelProfessor.setVisible(true);
				comboProfessor.setVisible(true);

				labelDisciplina.setVisible(true);
				comboDisciplina.setVisible(true);

				for (int i = 0; i < professores.length; i++) {
					if (professores[i].getCpf().equals(inscricao.getCpf())) {
						comboProfessor.setSelectedIndex(i + 1);
					}
				}

				for (int i = 0; i < disciplinas.length; i++) {
					if (disciplinas[i].getCodigo() == inscricao.getCodigoDisciplina()) {
						comboDisciplina.setSelectedIndex(i + 1);
					}
				}

				btnEditar.setVisible(true);

			} else {
				labelProfessor.setVisible(false);
				comboProfessor.setSelectedIndex(0);
				comboProfessor.setVisible(false);

				labelDisciplina.setVisible(false);
				comboDisciplina.setSelectedIndex(0);
				comboDisciplina.setVisible(false);

				btnEditar.setVisible(false);
			}
		});

		btnEditar.addActionListener(e -> {
			Inscricao inscricao = (Inscricao) combo.getSelectedItem();
			Professor professor = (Professor) comboProfessor.getSelectedItem();
			Disciplina disciplina = (Disciplina) comboDisciplina.getSelectedItem();

			if (professor != null && disciplina != null) {
				inscricao.setCpf(professor.getCpf());
				inscricao.setCodigoDisciplina(disciplina.getCodigo());

				inscricaoController.editarInscricao(inscricao.getCpf(), inscricao.getCodigoDisciplina(),
						inscricao.getCodigoProcesso());

				setVisible(false);
				inscricaoController.mensagemView("INSCRIÇÃO EDITADA COM SUCESSO", this);

			} else {
				setVisible(false);
				inscricaoController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}

		});

		setVisible(true);
	}
}