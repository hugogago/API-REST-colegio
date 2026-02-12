package com.adrian.colegio.dtos;

public class NotaDTO {
	private Integer id;
    private String nota;
    private Integer idAsignatura;
    private String nombreAsignatura;
    private Integer idAlumno;
    private String nombreAlumno;
    private String fecha;
    
	public NotaDTO(Integer id, String nota, Integer idAsignatura, String nombreAsignatura, Integer idAlumno,
			String nombreAlumno, String fecha) {
		super();
		this.id = id;
		this.nota = nota;
		this.idAsignatura = idAsignatura;
		this.nombreAsignatura = nombreAsignatura;
		this.idAlumno = idAlumno;
		this.nombreAlumno = nombreAlumno;
		this.fecha = fecha;
	}

	public NotaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

    
}
