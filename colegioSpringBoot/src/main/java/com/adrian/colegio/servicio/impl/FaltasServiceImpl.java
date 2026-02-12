package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IFaltasDAO;
import com.adrian.colegio.dtos.FaltaDTO;
import com.adrian.colegio.servicio.interfaces.IFaltasService;

@Service
public class FaltasServiceImpl implements IFaltasService {

    @Autowired
    private IFaltasDAO faltasDAO;

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            String fecha, Integer justificada, Integer activo) {
        return faltasDAO.obtenerFaltasPorFiltros(idAlumno, nombreAlumno, nombreAsignatura, fecha, justificada, activo);
    }

    @Override
    public int insertarFalta(Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada) {
        return faltasDAO.insertarFalta(idAlumno, idAsignatura, fecha, justificada);
    }

    @Override
    public int actualizarFalta(Integer id, Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada) {
        return faltasDAO.actualizarFalta(id, idAlumno, idAsignatura, fecha, justificada);
    }

    @Override
    public int borrarFalta(Integer id) {
        return faltasDAO.borrarFalta(id);
    }
}
