package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.MatriculacionDTO;

public interface IMatriculacionesDAO {
    ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
            String fecha, Integer activo);

    int insertarMatriculacion(Integer idAlumno, Integer idAsignatura, String fecha, Double tasa);

    int actualizarMatriculacion(Integer id, Integer idAlumno, Integer idAsignatura, String fecha, Double tasa);

    int borrarMatriculacion(Integer id);
}
