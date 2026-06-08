package views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controllers.InscricaoController;
import models.Inscricao;

public class ViewExcluirInscricao extends JFrame {

	InscricaoController inscricaoController = new InscricaoController();
	JFrame frameAnterior;
	JComboBox<Inscricao> combo;

	public ViewExcluirInscricao(InscricaoController inscricaoController, JFrame frameAnterior, Inscricao[] inscricoes) {
		this.inscricaoController = inscricaoController;
		this.frameAnterior = frameAnterior;

		setTitle("EXCLUIR INSCRIÇÃO");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("EXCLUIR INSCRIÇÃO", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 60, 400, 40);
		panel.add(titulo);

		JLabel labelEscolher = new JLabel("ESCOLHER INSCRIÇÃO:");
		labelEscolher.setBounds(250, 170, 300, 20);
		panel.add(labelEscolher);

		combo = new JComboBox<Inscricao>();
		combo.setBounds(250, 195, 300, 35);
		combo.addItem(null);

		for (Inscricao inscricao : inscricoes) {
			combo.addItem(inscricao);
		}

		panel.add(combo);

		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setBounds(250, 280, 300, 45);
		btnExcluir.setVisible(false);
		panel.add(btnExcluir);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		panel.add(btnVoltar);

		PadroesView.estilizarBotao(btnExcluir);
		PadroesView.estilizarVoltar(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		combo.addActionListener(e -> {
			Inscricao inscricao = (Inscricao) combo.getSelectedItem();

			btnExcluir.setVisible(inscricao != null);
		});

		btnExcluir.addActionListener(e -> {
			Inscricao inscricao = (Inscricao) combo.getSelectedItem();

			if (inscricao != null) {
				inscricaoController.excluirInscricao(inscricao.getCodigoProcesso());

				inscricaoController.mensagemView("EXCLUÍDO COM SUCESSO", this);
				combo.removeItem(inscricao);
				setVisible(false);
			}
		});

		setVisible(true);
	}
}