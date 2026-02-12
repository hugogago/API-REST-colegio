package com.adrian.colegio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faltas")
public class FaltaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfaltas")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "alumno")
	private AlumnoEntity alumno;
	
	@ManyToOne
	@JoinColumn(name = "asignatura")
	private AsignaturaEntity asignatura;
	
	@Column(name = "fecha")
	private String fecha;
	
	@Column(name = "justificada")
	private Integer justificada;

	public FaltaEntity(Integer id, AlumnoEntity alumno, AsignaturaEntity asignatura, String fecha,
			Integer justificada) {
		super();
		this.id = id;
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.fecha = fecha;
		this.justificada = justificada;
	}

	public FaltaEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AlumnoEntity getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoEntity alumno) {
		this.alumno = alumno;
	}

	public AsignaturaEntity getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(AsignaturaEntity asignatura) {
		this.asignatura = asignatura;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getJustificada() {
		return justificada;
	}

	public void setJustificada(Integer justificada) {
		this.justificada = justificada;
	}
	
	

}
