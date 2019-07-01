package br.edu.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtils {

	public static void enviarMensagemDeInformacao(String mensagem) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary(mensagem);
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public static void enviarMensagemDeAtencao(String mensagem) {
		FacesMessage msg = new FacesMessage();
		msg.setSummary(mensagem);
		msg.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
