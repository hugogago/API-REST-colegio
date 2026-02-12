package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.INotasDAO;
import com.adrian.colegio.dtos.NotaDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.NotaEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.NotaRepository;

@Repository
public class NotaDAOImpl implements INotasDAO {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
            Double nota, String fecha, Integer activo) {
        return notaRepository.buscarNotas(idAlumno, nombreAlumno, nombreAsignatura, nota, fecha, activo);
    }

    @Override
    public int insertarNota(Integer idAlumno, Integer idAsignatura, Double nota, String fecha) {
        // Obtener las entidades de alumno y asignatura desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // Si la fecha está vacía, usar la fecha actual
        if (fecha == null || fecha.isEmpty()) {
            fecha = java.time.LocalDate.now().toString();
        }

        // Crear la entidad nota
        NotaEntity notaEntity = new NotaEntity();
        notaEntity.setAlumno(alumno);
        notaEntity.setAsignaturaEntity(asignatura);
        notaEntity.setNota(nota);
        notaEntity.setFecha(fecha);

        // Guardar y retornar el ID
        NotaEntity notaGuardada = notaRepository.save(notaEntity);
        return notaGuardada.getId();
    }

    @Override
    public int actualizarNota(Integer id, Integer idAlumno, Integer idAsignatura, Double nota, String fecha) {
        // Obtener las entidades de alumno y asignatura desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // Crear la entidad nota con el ID existente (para actualizar)
        NotaEntity notaEntity = new NotaEntity();
        notaEntity.setId(id);
        notaEntity.setAlumno(alumno);
        notaEntity.setAsignaturaEntity(asignatura);
        notaEntity.setNota(nota);
        notaEntity.setFecha(fecha);

        // Guardar (actualizar) y retornar el ID
        NotaEntity notaActualizada = notaRepository.save(notaEntity);
        return notaActualizada.getId();
    }

    @Override
    public int borrarNota(Integer id) {
        try {
            notaRepository.deleteById(id);
            return id; // Retornar el ID si se borró correctamente
        } catch (Exception e) {
            return 0; // Error al borrar
        }
    }
}
