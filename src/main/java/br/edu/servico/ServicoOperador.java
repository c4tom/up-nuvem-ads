package br.edu.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.entidade.Operador;

@Stateless
public class ServicoOperador {
	@PersistenceContext
	private EntityManager em;
	
	public void inserir(Operador operador) {
		this.em.persist(operador);
	}

	public void delete(Operador operador) {
		this.em.remove(this.em.merge(operador));
	}

	public List<Operador> listar() {
		return this.em.createQuery("SELECT o FROM Operador o", Operador.class).getResultList();
	}

	public void atualiza(Operador operador) {
		this.em.merge(operador);
	}

}
