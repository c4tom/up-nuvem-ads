package br.edu.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

@Entity
public class Atendimento {

	@Id
//	@SequenceGenerator(name = "CONTADOR_ATENDIMENTO", sequenceName = "NUM_SEQ_ATENDIMENTO", allocationSize = 0)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTADOR_ATENDIMENTO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_operador")
	private Operador operador;

	@NotNull
	private Date horaInicioAtendimento;
	
	@NotNull
	private Date dataRegistro;

	@NotBlank
	private String descricao;
	
	//@Formula("DATE_PART('minute', dataregistro::timestamp - horainicioatendimento::timestamp)")
	@Transient
	private int tempoAtendimento;
	
	public Atendimento() {
		super();
	}


	/**
	 * @param id
	 * @param operador
	 * @param horaInicioAtendimento
	 * @param dataRegistro
	 * @param descricao
	 * @param tempoAtendimento
	 */
	public Atendimento(int id, @NotNull Operador operador, @NotNull Date horaInicioAtendimento,
			@NotNull Date dataRegistro, @NotBlank String descricao, int tempoAtendimento) {
		super();
		this.id = id;
		this.operador = operador;
		this.horaInicioAtendimento = horaInicioAtendimento;
		this.dataRegistro = dataRegistro;
		this.descricao = descricao;
		this.tempoAtendimento = tempoAtendimento;
	}


	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRegistro == null) ? 0 : dataRegistro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((horaInicioAtendimento == null) ? 0 : horaInicioAtendimento.hashCode());
		result = prime * result + id;
		result = prime * result + ((operador == null) ? 0 : operador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendimento other = (Atendimento) obj;
		if (dataRegistro == null) {
			if (other.dataRegistro != null)
				return false;
		} else if (!dataRegistro.equals(other.dataRegistro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horaInicioAtendimento == null) {
			if (other.horaInicioAtendimento != null)
				return false;
		} else if (!horaInicioAtendimento.equals(other.horaInicioAtendimento))
			return false;
		if (id != other.id)
			return false;
		if (operador == null) {
			if (other.operador != null)
				return false;
		} else if (!operador.equals(other.operador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atendimento [id=" + id + ", operador=" + operador + ", horaInicioAtendimento=" + horaInicioAtendimento
				+ ", dataRegistro=" + dataRegistro + ", descricao=" + descricao + "]";
	}

	public Date getHoraInicioAtendimento() {
		return horaInicioAtendimento;
	}

	public void setHoraInicioAtendimento(Date horaInicioAtendimento) {
		this.horaInicioAtendimento = horaInicioAtendimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTempoAtendimento() {
		return Minutes.minutesBetween(new DateTime(this.getHoraInicioAtendimento()),new DateTime(this.getDataRegistro())).getMinutes();
	}
}
