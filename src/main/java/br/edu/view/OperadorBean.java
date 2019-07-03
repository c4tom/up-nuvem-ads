package br.edu.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.entidade.Operador;
import br.edu.servico.ServicoOperador;

@Named
@ViewScoped
public class OperadorBean implements Serializable {
	private static final long serialVersionUID = -4453754362005733997L;

	private Operador operador;

	@EJB
	private ServicoOperador servicoOperador;

	@PostConstruct
	public void construtor() {
		this.setOperador(new Operador());
	}

	public void excluir(Operador operador) {
		this.servicoOperador.delete(operador);
	}

	public void cadastrar() {
		this.servicoOperador.inserir(operador);
		this.setOperador(new Operador());
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public ServicoOperador getServicoOperador() {
		return servicoOperador;
	}

	public void setServicoOperador(ServicoOperador servicoOperador) {
		this.servicoOperador = servicoOperador;
	}

	public List<Operador> getListaOperador() {
		return servicoOperador.listar();
	}

}
