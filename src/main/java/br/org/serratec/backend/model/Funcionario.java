package br.org.serratec.backend.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Funcionario extends Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcionario")
	@ApiModelProperty(value = "Identificador único do funcionario")
	private Long id;

	@ApiModelProperty(value = "Salário bruto do funcionario")
	private double salarioBruto;

	@ApiModelProperty(value = "Desconto Inss do funcionario")
	private double descontoInss;

	@ApiModelProperty(value = "Salário liquido do funcionario")
	private double salarioLiquido;

	@ApiModelProperty(value = "Deconto IR do funcionario")
	private double descontoIR;

	@ApiModelProperty(value = "Taxas Inss do funcionario")
	private TaxasInss taxasInss;

	@ApiModelProperty(value = "Taxa IR do funcionario")
	private TaxaIR taxaIR;

	@ApiModelProperty(value = "Calcula idade do funcionario")
	private Period idade;

	@JsonManagedReference
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	@Size(max = 3, message = "Um funcionario nao poder ter mais de 3 dependentes")
	private List<Dependente> dependentes = new ArrayList<Dependente>();

	public Funcionario() {

	}

	public Funcionario(@Size(max = 30) String nome, String cpf, LocalDate dataNascimento, Long id, double salarioBruto,
			double descontoInss, double salarioLiquido, double descontoIR, TaxasInss taxasInss, TaxaIR taxaIR,
			Period idade, @Size(max = 3) List<Dependente> dependentes) {
		super(nome, cpf, dataNascimento);
		this.id = id;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.salarioLiquido = salarioLiquido;
		this.descontoIR = descontoIR;
		this.taxasInss = taxasInss;
		this.taxaIR = taxaIR;
		this.idade = idade;
		this.dependentes = dependentes;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TaxasInss getTaxasInss() {
		return taxasInss;
	}

	public void setTaxasInss(TaxasInss taxasInss) {
		this.taxasInss = taxasInss;
	}

	public TaxaIR getTaxaIR() {
		return taxaIR;
	}

	public void setTaxaIR(TaxaIR taxaIR) {
		this.taxaIR = taxaIR;
	}

	public Period getIdade() {
		return idade;
	}

	public void setIdade(Period idade) {
		this.idade = idade;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
