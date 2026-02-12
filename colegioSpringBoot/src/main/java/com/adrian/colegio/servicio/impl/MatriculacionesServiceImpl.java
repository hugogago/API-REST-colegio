package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IMatriculacionesDAO;
import com.adrian.colegio.dtos.MatriculacionDTO;
import com.adrian.colegio.servicio.interfaces.IMatriculacionesService;

@Service
public class MatriculacionesServiceImpl implements IMatriculacionesService {

    @Autowired
    private IMatriculacionesDAO matriculacionesDAO;

    @Override
    public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
            String fecha, Integer activo) {
        return matriculacionesDAO.obtenerMatriculacionesPorFiltros(nombreAsignatura, nombreAlumno, fecha, activo);
    }

    @Override
    public int insertarMatriculacion(Integer idAlumno, Integer idAsignatura, String fecha, Double tasa) {
        return matriculacionesDAO.insertarMatriculacion(idAlumno, idAsignatura, fecha, tasa);
    }

    @Override
    public int actualizarMatriculacion(Integer id, Integer idAlumno, Integer idAsignatura, String fecha, Double tasa) {
        return matriculacionesDAO.actualizarMatriculacion(id, idAlumno, idAsignatura, fecha, tasa);
    }

    @Override
    public int borrarMatriculacion(Integer id) {
        return matriculacionesDAO.borrarMatriculacion(id);
    }
}
