package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IAlumnosDAO;
import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.servicio.interfaces.IAlumnosService;

@Service
public class AlumnosServiceImpl implements IAlumnosService {
	@Autowired
	IAlumnosDAO alumnosDAO;
	
	@Override
	public int insertarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa,
			int activo) {
		return alumnosDAO.insertarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, activo);
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnos()  {
		return alumnosDAO.obtenerTodosAlumnos();
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(Integer id, String nombre, String apellido,
			Integer familiaNumerosa, Integer activo) {
		return alumnosDAO.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido, familiaNumerosa, activo);
	}



	@Override
	public int actualizarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa,
			int activo) {
		return alumnosDAO.actualizarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, activo);
	}

	@Override
	public int borrarAlumno(int id) {
		return alumnosDAO.borrarAlumno(id);
	}

	@Override
	public AlumnoDTO obtenerAlumnoPorId(Integer id) {
		return alumnosDAO.obtenerAlumnosPorId(id);
	}

}
