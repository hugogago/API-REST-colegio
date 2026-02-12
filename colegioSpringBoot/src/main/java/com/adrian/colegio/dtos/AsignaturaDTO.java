package com.adrian.colegio.dtos;

public class AsignaturaDTO {
	private Integer id;
	private String nombre;
	private Integer curso;
	private double tasa;
	private Integer activo;
	
	public AsignaturaDTO(int id, String nombre, Integer curso, double tasa, int activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.tasa = tasa;
		this.activo = activo;
	}
	
	public AsignaturaDTO(int id, String nombre, Integer curso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
	}
	
	public AsignaturaDTO(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	
	//Getters, setters...

	public AsignaturaDTO() {
		super();
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

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
}
