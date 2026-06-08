package views;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

public class PadroesView {
	private PadroesView() {
	}

	public static void estilizarBotao(JButton botao) {
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botao.setFocusPainted(false);
		botao.setBorderPainted(false);
		botao.setBackground(Color.decode("#252525"));
		botao.setForeground(Color.WHITE);

	}

	public static void estilizarVoltar(JButton botao) {
		botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		botao.setFocusPainted(false);
		botao.setBorderPainted(false);
		botao.setContentAreaFilled(false);
		botao.setForeground(Color.decode("#C1121F"));

	}
}
