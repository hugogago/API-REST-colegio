package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotaDTO;

public interface INotasService {
    ArrayList<NotaDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            Double nota, String fecha, Integer activo);

    int insertarNota(Integer idAlumno, Integer idAsignatura, Double nota, String fecha);

    int actualizarNota(Integer id, Integer idAlumno, Integer idAsignatura, Double nota, String fecha);

    int borrarNota(Integer id);
}
