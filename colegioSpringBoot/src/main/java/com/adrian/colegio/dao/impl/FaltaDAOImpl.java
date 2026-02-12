package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IFaltasDAO;
import com.adrian.colegio.dtos.FaltaDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.FaltaEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.FaltaRepository;

@Repository
public class FaltaDAOImpl implements IFaltasDAO {

    @Autowired
    private FaltaRepository faltaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            String fecha, Integer justificada, Integer activo) {
        return faltaRepository.buscarFaltas(idAlumno, nombreAlumno, nombreAsignatura, fecha, justificada, activo);
    }

    @Override
    public int insertarFalta(Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada) {
        // Obtener las entidades de alumno y asignatura desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // Si la fecha está vacía, usar la fecha actual
        if (fecha == null || fecha.isEmpty()) {
            fecha = java.time.LocalDate.now().toString();
        }

        // Crear la entidad falta
        FaltaEntity faltaEntity = new FaltaEntity();
        faltaEntity.setAlumno(alumno);
        faltaEntity.setAsignatura(asignatura);
        faltaEntity.setFecha(fecha);
        faltaEntity.setJustificada(justificada);

        // Guardar y retornar el ID
        FaltaEntity faltaGuardada = faltaRepository.save(faltaEntity);
        return faltaGuardada.getId();
    }

    @Override
    public int actualizarFalta(Integer id, Integer idAlumno, Integer idAsignatura, String fecha, Integer justificada) {
        // Obtener las entidades de alumno y asignatura desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // Crear la entidad falta con el ID existente (para actualizar)
        FaltaEntity faltaEntity = new FaltaEntity();
        faltaEntity.setId(id);
        faltaEntity.setAlumno(alumno);
        faltaEntity.setAsignatura(asignatura);
        faltaEntity.setFecha(fecha);
        faltaEntity.setJustificada(justificada);

        // Guardar (actualizar) y retornar el ID
        FaltaEntity faltaActualizada = faltaRepository.save(faltaEntity);
        return faltaActualizada.getId();
    }

    @Override
    public int borrarFalta(Integer id) {
        try {
            faltaRepository.deleteById(id);
            return id;
        } catch (Exception e) {
            return 0;
        }
    }
}
