package br.edu.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.entidade.Atendimento;

@Stateless
public class ServicoAtendimento {
	@PersistenceContext
	private EntityManager em;
	
	public void inserir(Atendimento atendimento) {
		this.em.persist(atendimento);
	}

	public void delete(Atendimento atendimento) {
		this.em.remove(this.em.merge(atendimento));
	}

	public List<Atendimento> listar() {
		return this.em.createQuery("SELECT a FROM Atendimento a", Atendimento.class).getResultList();
	}

	public void atualiza(Atendimento atendimento) {
		this.em.merge(atendimento);
	}

}
