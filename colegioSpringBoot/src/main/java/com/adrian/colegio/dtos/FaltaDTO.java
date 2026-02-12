package com.adrian.colegio.dtos;

public class FaltaDTO {
	private Integer idFalta;
	private Integer idAlumno;
	private String nombreAlumno;
	private Integer idAsignatura;
	private String nombreAsignatura;
	private String fecha;
	private Integer justificada;

	public FaltaDTO(Integer idFalta, Integer idAlumno, String nombreAlumno, Integer idAsignatura,
			String nombreAsignatura, String fecha, Integer justificada) {
		super();
		this.idFalta = idFalta;
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.idAsignatura = idAsignatura;
		this.nombreAsignatura = nombreAsignatura;
		this.fecha = fecha;
		this.justificada = justificada;
	}

	public FaltaDTO() {
		super();
	}

	public Integer getIdFalta() {
		return idFalta;
	}

	public void setIdFalta(Integer idFalta) {
		this.idFalta = idFalta;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public Integer getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Integer idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
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
