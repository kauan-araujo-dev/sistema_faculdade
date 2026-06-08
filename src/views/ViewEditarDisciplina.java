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
import models.Disciplina;

public class ViewEditarDisciplina extends JFrame {

	DisciplinaController disciplinaController;
	JButton btnEditar;
	JTextField txtNome;
	JComboBox<String> comboDiaSemana;
	JTextField txtHorario;
	JTextField txtQuantidadeHoras;
	JFrame frameAnterior;
	JComboBox<Curso> comboCurso;
	JComboBox<Disciplina> combo;

	public ViewEditarDisciplina(DisciplinaController disciplinaController, JFrame frameAnterior,
			Disciplina[] disciplinas, Curso[] cursos) {
		this.disciplinaController = disciplinaController;
		this.frameAnterior = frameAnterior;

		setTitle("EDITAR DISCIPLINA");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EDITAR DISCIPLINA", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 30, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER CURSO:");
		labelEscolher.setBounds(250, 110, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Disciplina>();
		combo.setBounds(250, 135, 300, 35);

		combo.addItem(null);
		for (Disciplina disciplina : disciplinas) {
			combo.addItem(disciplina);
		}
		panel.add(combo);

		comboCurso = new JComboBox<Curso>();
		comboCurso.addItem(null);
		for (Curso curso : cursos) {
			comboCurso.addItem(curso);
		}

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 190, 300, 20);
		labelNome.setVisible(false);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 215, 300, 35);
		txtNome.setVisible(false);
		panel.add(txtNome);

		JLabel labelDiaSemana = new JLabel("DIA DA SEMANA:");
		labelDiaSemana.setBounds(250, 255, 300, 20);
		labelDiaSemana.setVisible(false);
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

		comboDiaSemana.setBounds(250, 280, 300, 35);
		comboDiaSemana.setVisible(false);
		panel.add(comboDiaSemana);

		JLabel labelHorario = new JLabel("HORÁRIO:");
		labelHorario.setBounds(250, 320, 300, 20);
		labelHorario.setVisible(false);
		panel.add(labelHorario);

		txtHorario = new JTextField();
		txtHorario.setBounds(250, 345, 300, 35);
		txtHorario.setVisible(false);
		panel.add(txtHorario);

		JLabel labelQuantidadeHoras = new JLabel("QUANTIDADE DE HORAS DIÁRIAS:");
		labelQuantidadeHoras.setBounds(250, 385, 300, 20);
		labelQuantidadeHoras.setVisible(false);
		panel.add(labelQuantidadeHoras);

		txtQuantidadeHoras = new JTextField();
		txtQuantidadeHoras.setBounds(250, 410, 300, 35);
		txtQuantidadeHoras.setVisible(false);
		panel.add(txtQuantidadeHoras);

		JLabel labelCurso = new JLabel("CURSO:");
		labelCurso.setBounds(250, 450, 300, 20);
		labelCurso.setVisible(false);
		panel.add(labelCurso);

		comboCurso.setBounds(250, 475, 300, 35);
		comboCurso.setVisible(false);
		panel.add(comboCurso);

		btnEditar = new JButton("CADASTRAR");
		btnEditar.setBounds(325, 525, 150, 35);
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

			Disciplina disciplina = (Disciplina) combo.getSelectedItem();

			if (disciplina != null) {

				labelNome.setVisible(true);
				txtNome.setText(disciplina.getNome());
				txtNome.setVisible(true);

				labelDiaSemana.setVisible(true);
				comboDiaSemana.setSelectedItem(disciplina.getDiaSemana());
				comboDiaSemana.setVisible(true);

				labelHorario.setVisible(true);
				txtHorario.setText(disciplina.getHorario());
				txtHorario.setVisible(true);

				labelQuantidadeHoras.setVisible(true);
				txtQuantidadeHoras.setText(Integer.toString(disciplina.getQuantidadeHorasDiarias()));
				txtQuantidadeHoras.setVisible(true);

				labelCurso.setVisible(true);

				for (int i = 0; i < cursos.length; i++) {

					if (cursos[i].getCodigo() == disciplina.getCodigoCurso()) {
						comboCurso.setSelectedIndex(i + 1);
						System.out.println(cursos[i].getCodigo() + " " + cursos[i].getNome());
					}
				}

				comboCurso.setVisible(true);

				btnEditar.setVisible(true);

			} else {

				labelNome.setVisible(false);
				txtNome.setText("");
				txtNome.setVisible(false);

				labelDiaSemana.setVisible(false);
				comboDiaSemana.setSelectedIndex(0);
				comboDiaSemana.setVisible(false);

				labelHorario.setVisible(false);
				txtHorario.setText("");
				txtHorario.setVisible(false);

				labelQuantidadeHoras.setVisible(false);
				txtQuantidadeHoras.setText("");
				txtQuantidadeHoras.setVisible(false);

				labelCurso.setVisible(false);
				comboCurso.setSelectedIndex(0);
				comboCurso.setVisible(false);

				btnEditar.setVisible(false);
			}
		});
		btnEditar.addActionListener(e -> {
			try {
				Disciplina disciplina = (Disciplina) combo.getSelectedItem();

				String nome = txtNome.getText();
				String diaSemana = (String) comboDiaSemana.getSelectedItem();
				String horario = txtHorario.getText();
				int quantidadeHoras = Integer.parseInt(txtQuantidadeHoras.getText());
				Curso curso = (Curso) comboCurso.getSelectedItem();
				int codigoCurso = curso.getCodigo();

				disciplinaController.editarDisciplina(disciplina.getCodigo(), nome, diaSemana, horario, quantidadeHoras,
						codigoCurso);
				if (!(nome.isEmpty() || horario.isEmpty() || quantidadeHoras == 0 || diaSemana == null)) {
					disciplinaController.mensagemView("DISCIPLINA EDITADA COM SUCESSO", this);

					setVisible(false);

					txtNome.setText("");
					comboDiaSemana.setSelectedIndex(0);
					txtHorario.setText("");
					txtQuantidadeHoras.setText("");
					combo.setSelectedIndex(0);

					disciplina.setNome(txtNome.getText());

					disciplina.setDiaSemana((String) comboDiaSemana.getSelectedItem());

					disciplina.setHorario(txtHorario.getText());

					disciplina.setQuantidadeHorasDiarias(quantidadeHoras);

					disciplina.setCodigoCurso((curso.getCodigo()));
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