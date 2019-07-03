package br.edu.view;



import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.entidade.Operador;

//https://stackoverflow.com/questions/9921324/managedproperty-in-cdi-named-bean-returns-null

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -1222240867599544685L;
	private Operador operador;
	private boolean logado = false;

	public String logoff() {
		setOperador(null);
		setLogado(false);
		return "login";
	}
	
	public String autenticar() {
		setLogado(true);
		return "cadastroAtendimento";
	}
	
	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}
}
