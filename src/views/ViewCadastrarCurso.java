package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllers.CursoController;

public class ViewCadastrarCurso extends JFrame {
	CursoController cursoController;
	JButton btnCadastrar;
	JTextField txtNome;
	JTextField txtArea;
	JFrame frameAnterior;

	public ViewCadastrarCurso(CursoController cursoController, JFrame frameAnterior) {
		this.cursoController = cursoController;
		this.frameAnterior = frameAnterior;

		setTitle("CADASTRAR CURSO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CADASTRAR CURSO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 40, 400, 40);
		panel.add(titulo);

		JLabel labelNome = new JLabel("NOME:");
		labelNome.setBounds(250, 130, 300, 20);
		panel.add(labelNome);

		txtNome = new JTextField();
		txtNome.setBounds(250, 155, 300, 35);
		panel.add(txtNome);

		JLabel labelArea = new JLabel("ÁREA:");
		labelArea.setBounds(250, 220, 300, 20);
		panel.add(labelArea);

		txtArea = new JTextField();
		txtArea.setBounds(250, 245, 300, 35);
		panel.add(txtArea);

		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(325, 330, 150, 40);
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
			String nome = txtNome.getText();
			String area = txtArea.getText();
			if (!nome.equals("") && !area.equals("")) {
				cursoController.cadastrarCurso(nome, area);

				cursoController.mensagemView("CURSO CADASTRADO COM SUCESSO", this);
				setVisible(false);

				txtNome.setText("");
				txtArea.setText("");
			} else {
				setVisible(false);
				cursoController.mensagemView("PREENCHA OS CAMPOS CORRETAMENTE", this);
			}
		});

		setVisible(true);

	}

}
