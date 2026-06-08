package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.DisciplinaController;

public class ViewDisciplina extends JFrame {

	DisciplinaController disciplinaController = new DisciplinaController();
	JFrame frameAnterior;

	public ViewDisciplina(DisciplinaController disciplinaController, JFrame frameAnterior) {

		this.disciplinaController = disciplinaController;
		this.frameAnterior = frameAnterior;
		setTitle("DISCIPLINAS");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("DISCIPLINAS", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 40, 400, 40);
		panel.add(titulo);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(250, 130, 300, 50);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(250, 190, 300, 50);
		panel.add(btnEditar);

		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(250, 250, 300, 50);
		panel.add(btnConsultar);

		JButton btnProcessos = new JButton("PROCESSOS ABERTOS");
		btnProcessos.setBounds(250, 310, 300, 50);
		panel.add(btnProcessos);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 370, 300, 50);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarBotao(btnConsultar);
		PadroesView.estilizarBotao(btnProcessos);
		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		btnCadastrar.addActionListener(e -> {
			setVisible(false);
			disciplinaController.cadastrarView(this);
		});
		btnEditar.addActionListener(e -> {
			setVisible(false);
			disciplinaController.editarView(this);
		});
		btnConsultar.addActionListener(e -> {
			setVisible(false);
			disciplinaController.editarView(this);
		});
		btnProcessos.addActionListener(e -> {
			setVisible(false);
			disciplinaController.consultarDisciplinasAbertasView(this);
		});
		btnExcluir.addActionListener(e -> {
			setVisible(false);
			disciplinaController.excluirView(this);
		});

		setVisible(true);
	}
}