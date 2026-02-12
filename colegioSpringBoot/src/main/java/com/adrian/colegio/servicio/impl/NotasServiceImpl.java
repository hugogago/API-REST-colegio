package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.INotasDAO;
import com.adrian.colegio.dtos.NotaDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;

@Service
public class NotasServiceImpl implements INotasService {

    @Autowired
    private INotasDAO notasDAO;

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            Double nota, String fecha, Integer activo) {
        return notasDAO.obtenerNotasPorFiltros(idAlumno, nombreAlumno, nombreAsignatura, nota, fecha, activo);
    }

    @Override
    public int insertarNota(Integer idAlumno, Integer idAsignatura, Double nota, String fecha) {
        return notasDAO.insertarNota(idAlumno, idAsignatura, nota, fecha);
    }

    @Override
    public int actualizarNota(Integer id, Integer idAlumno, Integer idAsignatura, Double nota, String fecha) {
        return notasDAO.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);
    }

    @Override
    public int borrarNota(Integer id) {
        return notasDAO.borrarNota(id);
    }
}
