package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.DisciplinaController;
import models.Curso;

public class ViewCadastrarDisciplina extends JFrame {

	DisciplinaController disciplinaController;
	JButton btnCadastrar;
	JTextField txtNome;
	JComboBox<String> comboDiaSemana;
	JTextField txtHorario;
	JTextField txtQuantidadeHoras;
	JFrame frameAnterior;
	JComboBox<Curso> combo;

	public ViewCadastrarDisciplina(DisciplinaController disciplinaController, JFrame frameAnterior, Curso[] cursos) {
		this.disciplinaController = disciplinaController;
		this.frameAnterior = frameAnterior;

		setTitle("CADASTRAR DISCIPLINA");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		combo = new JComboBox<Curso>();
		combo.addItem(null);
		for (Curso curso : cursos) {
			combo.addItem(curso);
		}

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CADASTRAR DISCIPLINA", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 30, 400, 40);
		panel.add(titulo);

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 100, 300, 20);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 125, 300, 35);
		panel.add(txtNome);

		JLabel labelDiaSemana = new JLabel("DIA DA SEMANA:");
		labelDiaSemana.setBounds(250, 170, 300, 20);
		panel.add(labelDiaSemana);

		comboDiaSemana = new JComboBox<String>();

		comboDiaSemana.addItem(null);
		comboDiaSemana.addItem("SEGUNDA-FEIRA");
		comboDiaSemana.addItem("TERÇA-FEIRA");
		comboDiaSemana.addItem("QUARTA-FEIRA");
		comboDiaSemana.addItem("QUINTA-FEIRA");
		comboDiaSemana.addItem("SEXTA-FEIRA");
		comboDiaSemana.addItem("SÁBADO");
		comboDiaSemana.addItem("DOMINGO");

		comboDiaSemana.setBounds(250, 195, 300, 35);
		panel.add(comboDiaSemana);

		JLabel labelHorario = new JLabel("HORÁRIO:");
		labelHorario.setBounds(250, 240, 300, 20);
		panel.add(labelHorario);

		txtHorario = new JTextField();
		txtHorario.setBounds(250, 265, 300, 35);
		panel.add(txtHorario);

		JLabel labelQuantidadeHoras = new JLabel("QUANTIDADE DE HORAS DIÁRIAS:");
		labelQuantidadeHoras.setBounds(250, 310, 300, 20);
		panel.add(labelQuantidadeHoras);

		txtQuantidadeHoras = new JTextField();
		txtQuantidadeHoras.setBounds(250, 335, 300, 35);
		panel.add(txtQuantidadeHoras);

		JLabel labelCodigoCurso = new JLabel("CURSO:");
		labelCodigoCurso.setBounds(250, 380, 300, 20);
		panel.add(labelCodigoCurso);

		combo.setBounds(250, 405, 300, 35);
		panel.add(combo);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(325, 470, 150, 40);

		panel.add(btnCadastrar);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {

			try {
				String nome = txtNome.getText();
				String diaSemana = (String) comboDiaSemana.getSelectedItem();
				String horario = txtHorario.getText();
				int quantidadeHoras = Integer.parseInt(txtQuantidadeHoras.getText());
				Curso curso = (Curso) combo.getSelectedItem();
				int codigoCurso = curso.getCodigo();

				if (!(nome.isEmpty() || horario.isEmpty() || quantidadeHoras == 0 || diaSemana == null
						|| curso == null)) {
					disciplinaController.cadastrarDisciplina(nome, diaSemana, horario, quantidadeHoras, codigoCurso);

					disciplinaController.mensagemView("DISCIPLINA CADASTRADA COM SUCESSO", this);

					setVisible(false);

					txtNome.setText("");
					comboDiaSemana.setSelectedIndex(0);
					txtHorario.setText("");
					txtQuantidadeHoras.setText("");
					combo.setSelectedIndex(0);
				} else {
					setVisible(false);
					disciplinaController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
				}

			} catch (Exception erro) {
				setVisible(false);
				disciplinaController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}
		});

		setVisible(true);
	}

}