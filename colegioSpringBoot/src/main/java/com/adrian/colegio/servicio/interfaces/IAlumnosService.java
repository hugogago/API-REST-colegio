package com.adrian.colegio.servicio.interfaces;


import java.util.ArrayList;

import com.adrian.colegio.dtos.AlumnoDTO;

public interface IAlumnosService {
	public ArrayList<AlumnoDTO> obtenerAlumnos();
	public  ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(Integer id, String nombre, String apellido, Integer familiaNumerosa, Integer activo);
	public int insertarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa, int activo);
	public int actualizarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa, int activo);
	public int borrarAlumno(int id);
	public AlumnoDTO obtenerAlumnoPorId(Integer id);
}

