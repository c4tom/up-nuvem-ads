package br.edu.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.entidade.Atendimento;
import br.edu.servico.ServicoAtendimento;

@Named
@RequestScoped
public class AtendimentoBean {

	private Atendimento atendimento;
	
	@EJB
	private ServicoAtendimento servicoAtendimento;
	
	@PostConstruct
	public void construtor() {
		this.setAtendimento(new Atendimento());
	}
	
	public void excluir(Atendimento atendimento) {
		this.servicoAtendimento.delete(atendimento);
	}

	public void cadastrar() {
		this.servicoAtendimento.inserir(atendimento);
		this.setAtendimento(new Atendimento());
	}	

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public ServicoAtendimento getServicoAtendimento() {
		return servicoAtendimento;
	}

	public void setServicoAtendimento(ServicoAtendimento servicoAtendimento) {
		this.servicoAtendimento = servicoAtendimento;
	}
	
}
