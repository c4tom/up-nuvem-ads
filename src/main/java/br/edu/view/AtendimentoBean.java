package br.edu.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.entidade.Atendimento;
import br.edu.entidade.Operador;
import br.edu.servico.ServicoAtendimento;

@Named
@ViewScoped
public class AtendimentoBean implements Serializable {

	private static final long serialVersionUID = 5844486436653290761L;
	private Atendimento atendimento;
	private boolean emAtendimento = false;
	
	private Operador operadorRelatorio;
	
	private List<Atendimento> atendimentos;
	
	@Inject
	private ServicoAtendimento servicoAtendimento;
	
	// @ManagedProperty(value = "#{loginBean}") -> se fosse JSF 2 e usando outro pacote, nao de CDI apenas
	@Inject
	private LoginBean login;
	
	@PostConstruct
	public void construtor() {
		this.setAtendimento(new Atendimento());
		getLogin().isLogado();
	}
	
	public void iniciaAtendimento() {
		this.getAtendimento().setHoraInicioAtendimento(new Date());
		setEmAtendimento(true);
	}
	
	public List<Atendimento> listar(){
		atendimentos = getServicoAtendimento().listar();
		return atendimentos;
	}
	
	public List<Atendimento> listar(Operador operador) {
		if (operador == null) {
			operador = login.getOperador();
		}
		List<Atendimento> at = new ArrayList<>();
 		for(Atendimento a: getServicoAtendimento().listar()) {
 			if(a.getOperador().getId().equals(operador.getId())) {
 				at.add(a);
 			}
 		}
 		return at;
	}
	
	public int getSomaTempoAtendimentoTotal() {
		int qtidade = 0;
		
		for(Atendimento atendimento: this.listar())
		{
			qtidade += atendimento.getTempoAtendimento();
		}
		return qtidade;
	}
	
	public void trocaOperadorRelatorio(ValueChangeEvent event) {
		
	}
	
	
	public void excluir(Atendimento atendimento) {
		this.servicoAtendimento.delete(atendimento);
	}

	public void cadastrar() {
		atendimento.setDataRegistro(new Date());
		atendimento.setOperador(getLogin().getOperador());
		this.servicoAtendimento.inserir(atendimento);
		this.setAtendimento(new Atendimento());
		setEmAtendimento(false);
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

	public boolean isEmAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(boolean emAtendimento) {
		this.emAtendimento = emAtendimento;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public Operador getOperadorRelatorio() {
		return operadorRelatorio;
	}

	public void setOperadorRelatorio(Operador operadorRelatorio) {
		this.operadorRelatorio = operadorRelatorio;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	
}
