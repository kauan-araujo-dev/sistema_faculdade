package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controllers.InscricaoController;
import models.Inscricao;

public class ViewConsultarInscricao extends JFrame {

	InscricaoController inscricaoController = new InscricaoController();
	JFrame frameAnterior;

	public ViewConsultarInscricao(InscricaoController inscricaoController, JFrame frameAnterior,
			Inscricao[] inscricoes) {
		this.inscricaoController = inscricaoController;
		this.frameAnterior = frameAnterior;

		setTitle("CONSULTAR INSCRIÇÕES");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel titulo = new JLabel("CONSULTAR INSCRIÇÕES", JLabel.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBounds(200, 50, 400, 40);
		panel.add(titulo);

		Object[][] dados = new Object[inscricoes.length][2];

		for (int i = 0; i < inscricoes.length; i++) {
			dados[i][0] = inscricoes[i].getCpf();
			dados[i][1] = inscricoes[i].getCodigoDisciplina();
		}

		String[] colunas = { "CPF PROFESSOR", "CÓDIGO DISCIPLINA" };

		JTable table = new JTable(dados, colunas);
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
		table.getTableHeader().setBorder(null);
		table.getTableHeader().setBackground(Color.decode("#252525"));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

		table.setBorder(null);
		table.setRowHeight(35);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(null);
		scroll.setBounds(50, 120, 700, 380);
		panel.add(scroll);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(20, 20, 100, 30);
		PadroesView.estilizarVoltar(btnVoltar);
		panel.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			dispose();
			frameAnterior.setVisible(true);
		});

		setVisible(true);
	}
}