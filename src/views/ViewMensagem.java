package views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewMensagem extends JFrame {

	JFrame frameAnterior;

	public ViewMensagem(String mensagem, JFrame frameAnterior) {
		this.frameAnterior = frameAnterior;
		setTitle("MENSAGEM");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Container panel = getContentPane();
		panel.setLayout(null);

		JLabel lMensagem = new JLabel(mensagem, JLabel.CENTER);
		lMensagem.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lMensagem.setBounds(0, 60, 400, 30);
		panel.add(lMensagem);

		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				frameAnterior.setVisible(true);
			}
		});

		setVisible(true);
	}
}
