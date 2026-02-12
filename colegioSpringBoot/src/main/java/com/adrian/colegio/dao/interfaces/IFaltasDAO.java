package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.FaltaDTO;

public interface IFaltasDAO {
    ArrayList<FaltaDTO> obtenerFaltasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            String fecha, Integer justificada, Integer activo);

    int insertarFalta(Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada);

    int actualizarFalta(Integer id, Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada);

    int borrarFalta(Integer id);
}
