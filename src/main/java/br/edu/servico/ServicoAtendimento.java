package br.edu.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.entidade.Atendimento;
import br.edu.entidade.Operador;

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
	
	public List<Atendimento> listarPorOperador(Operador operador){
		TypedQuery<Atendimento> qry = this.em.createQuery("SELECT a FROM Atendimento a INNER JOIN a.operador o WHERE o.id = :idOperador", Atendimento.class);
		qry.setParameter("idOperador", operador.getId());
		return qry.getResultList();
	}

	public void atualiza(Atendimento atendimento) {
		this.em.merge(atendimento);
	}

}
