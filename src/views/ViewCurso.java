package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.CursoController;

public class ViewCurso extends JFrame {
	CursoController cursoController = new CursoController();
	JFrame frameAnterior;

	public ViewCurso(CursoController cursoController, JFrame frameAnterior) {

		this.frameAnterior = frameAnterior;
		setTitle("CURSOS");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CURSOS", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(250, 150, 300, 50);
		panel.add(btnCadastrar);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(250, 220, 300, 50);
		panel.add(btnEditar);

		JButton btnConsultar = new JButton("CONSULTAR");
		btnConsultar.setBounds(250, 290, 300, 50);
		panel.add(btnConsultar);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 360, 300, 50);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		PadroesView.estilizarBotao(btnCadastrar);
		PadroesView.estilizarBotao(btnEditar);
		PadroesView.estilizarBotao(btnConsultar);
		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnCadastrar.addActionListener(e -> {
			cursoController.cadastrarView(this);
			setVisible(false);
		});
		btnEditar.addActionListener(e -> {
			cursoController.editarView(this);
			setVisible(false);
		});
		btnExcluir.addActionListener(e -> {
			cursoController.excluirView(this);
			setVisible(false);
		});

		btnConsultar.addActionListener(e -> {
			cursoController.consultarView(this);
			setVisible(false);
		});

		setVisible(true);
	}

}
