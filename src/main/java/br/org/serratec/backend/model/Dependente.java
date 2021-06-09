package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Dependente extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dependente")
	@ApiModelProperty(value="Identificador único do cliente")
	private Long id;
	@ApiModelProperty(value="Enum que define o parentesco do dependente  ")
	private Parentesco parentesco;
	@ApiModelProperty("Calcula idade do dependenteee")
	private Period period;

	public Dependente() {
		
	}

	public Dependente(@Size(max = 30) String nome, @CPF String cpf, LocalDate dataNascimento, Long id,
			Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.id = id;
		this.parentesco = parentesco;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
