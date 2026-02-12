package com.adrian.colegio.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class AlumnoEntity {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "familia_numerosa")
	private int famNumerosa;

	@Column(name = "activo")
	private int activo;

	@ManyToOne
	@JoinColumn(name = "id_municipio")
	private MunicipioEntity municipio;

	@OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
	List<NotaEntity> notas;
	
	@OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
	List<FaltaEntity> faltas;

	public AlumnoEntity(int id, String nombre, String apellidos, int famNumerosa, int activo,
			MunicipioEntity municipio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.famNumerosa = famNumerosa;
		this.activo = activo;
		this.municipio = municipio;
	}

	public AlumnoEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getFamNumerosa() {
		return famNumerosa;
	}

	public void setFamNumerosa(int famNumerosa) {
		this.famNumerosa = famNumerosa;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public MunicipioEntity getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioEntity municipio) {
		this.municipio = municipio;
	}

}
