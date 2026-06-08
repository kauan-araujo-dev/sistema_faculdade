package controllers;

import javax.swing.JFrame;

import views.ViewMensagem;

public abstract class Controller {
	public void mensagemView(String mensagem, JFrame frameAnterior) {
		new ViewMensagem(mensagem, frameAnterior);
	}
}
