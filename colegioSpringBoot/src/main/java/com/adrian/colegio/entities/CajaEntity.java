package com.adrian.colegio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="caja")
public class CajaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "idmatricula")
	private MatriculacionEntity matricula;
	
	@Column(name  = "importe")
	private Double importe;

	public CajaEntity(Integer id, MatriculacionEntity matricula, Double importe) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.importe = importe;
	}

	public CajaEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MatriculacionEntity getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculacionEntity matricula) {
		this.matricula = matricula;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	
}
