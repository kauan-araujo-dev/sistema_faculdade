package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.InscricaoController;

public class ViewInscricao extends JFrame {

	InscricaoController inscricaoController = new InscricaoController();
	JFrame frameAnterior;

	public ViewInscricao(InscricaoController inscricaoController, JFrame frameAnterior) {

		this.inscricaoController = inscricaoController;
		this.frameAnterior = frameAnterior;

		setTitle("INSCRIÇÕES");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("INSCRIÇÕES", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(250, 140, 300, 45);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(250, 200, 300, 45);
		panel.add(btnEditar);

		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(250, 260, 300, 45);
		panel.add(btnConsultar);

		JButton btnConsultarDisciplina = new JButton("CONSULTAR POR DISCIPLINA");
		btnConsultarDisciplina.setBounds(250, 320, 300, 45);
		panel.add(btnConsultarDisciplina);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 380, 300, 45);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarBotao(btnConsultar);
		PadroesView.estilizarBotao(btnConsultarDisciplina);
		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {
			setVisible(false);
			inscricaoController.cadastrarView(this);
		});

		btnEditar.addActionListener(e -> {
			setVisible(false);
			inscricaoController.editarView(this);
		});
		btnConsultar.addActionListener(e -> {
			setVisible(false);
			inscricaoController.consultarView(this);
		});

		btnExcluir.addActionListener(e -> {
			setVisible(false);
			inscricaoController.excluirView(this);
		});

		btnConsultarDisciplina.addActionListener(e -> {
			setVisible(false);
			inscricaoController.consultarPorDisciplinasView(this);
		});
		setVisible(true);
	}
}