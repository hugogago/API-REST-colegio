package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IAsignaturasDAO;
import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.servicio.interfaces.IAsignaturasService;

@Service
public class AsignaturasServiceImpl implements IAsignaturasService {
	@Autowired
	IAsignaturasDAO asignaturasDAO;

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(Integer id, String nombre, Integer curso, Double tasa,
			Integer activo) {
		return asignaturasDAO.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa, activo);
	}

	@Override
	public int insertarAsignatura(Integer id, String nombre, Integer curso, Double tasa, Integer activo) {
		// TODO Auto-generated method stub
		return asignaturasDAO.insertarAsignatura(id, nombre, curso, tasa, activo);
	}

	@Override
	public int actualizarAsignatura(Integer id, String nombre, Integer curso, Double tasa, Integer activo) {
		// TODO Auto-generated method stub
		return asignaturasDAO.actualizarAsignatura(id, nombre, curso, tasa, activo);
	}

	@Override
	public int borrarAsignatura(Integer id) {
		// TODO Auto-generated method stub
		return asignaturasDAO.borrarAsignatura(id);
	}

}
