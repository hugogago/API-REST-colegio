package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.AsignaturaDTO;

public interface IAsignaturasService {
    ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(Integer id, String nombre, Integer curso, Double tasa,
			Integer activo);

    int insertarAsignatura(Integer id, String nombre, Integer curso, Double tasa,
			Integer activo);

    int actualizarAsignatura(Integer id, String nombre, Integer curso, Double tasa,
			Integer activo);

    int borrarAsignatura(Integer id);
}
