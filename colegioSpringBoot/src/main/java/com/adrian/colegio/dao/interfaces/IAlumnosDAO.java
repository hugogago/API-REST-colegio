package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.AlumnoDTO;


public interface IAlumnosDAO {
	
	ArrayList<AlumnoDTO> obtenerTodosAlumnos();
	ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(Integer id, String nombre, String apellido,  Integer familiaNumerosa, Integer activo);
	int insertarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa, int activo);
	int actualizarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa, int activo);
	int borrarAlumno(int id);
	AlumnoDTO obtenerAlumnosPorId(Integer id);
	
    boolean esFamiliaNumerosa(String idAlumno);
	int contarAsignaturasMatriculadas(String idAlumno);


}
