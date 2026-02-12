package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IAsignaturasDAO;
import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.repositorios.AsignaturaRepository;

@Repository
public class AsignaturaDAOImpl implements IAsignaturasDAO {

	@Autowired
	AsignaturaRepository asignaturaRepository;

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(Integer id, String nombre, Integer curso, Double tasa,
			Integer activo) {
		return asignaturaRepository.buscarAsignatura(id, nombre, curso, tasa, activo);
	}

	@Override
	public int insertarAsignatura(Integer id, String nombre, Integer curso, Double tasa, Integer activo) {
		AsignaturaEntity asignatura = new AsignaturaEntity(id, nombre, curso, tasa, activo);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

	@Override
	public int actualizarAsignatura(Integer id, String nombre, Integer curso, Double tasa, Integer activo) {
		AsignaturaEntity asignatura = new AsignaturaEntity(id, nombre, curso, tasa, activo);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

	@Override
	public int borrarAsignatura(Integer id) {
		AsignaturaEntity asignatura = asignaturaRepository.findById(id).get();
		asignatura.setActivo(0);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

}
