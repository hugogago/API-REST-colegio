package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adrian.colegio.dao.interfaces.IMatriculacionesDAO;
import com.adrian.colegio.dtos.MatriculacionDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.CajaEntity;
import com.adrian.colegio.entities.MatriculacionEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.CajaRepository;
import com.adrian.colegio.repositorios.MatriculacionRepository;

@Repository
public class MatriculacionDAOImpl implements IMatriculacionesDAO {

    @Autowired
    private MatriculacionRepository matriculacionRepository;

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
            String fecha, Integer activo) {
        return matriculacionRepository.buscarMatriculaciones(nombreAsignatura, nombreAlumno, fecha, activo);
    }

    @Override
    @Transactional // Transacción automática: si falla algo, se hace rollback
    public int insertarMatriculacion(Integer idAlumno, Integer idAsignatura, String fecha, Double tasa) {
        // Obtener las entidades desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // Si la fecha está vacía, usar la fecha actual
        if (fecha == null || fecha.isEmpty()) {
            fecha = java.time.LocalDate.now().toString();
        }

        // 1. Crear y guardar la matriculación
        MatriculacionEntity matriculacion = new MatriculacionEntity();
        matriculacion.setAlumno(alumno);
        matriculacion.setAsignatura(asignatura);
        matriculacion.setFecha(fecha);
        matriculacion.setActivo(1);

        MatriculacionEntity matriculacionGuardada = matriculacionRepository.save(matriculacion);

        // 2. Crear y guardar el registro en caja con la tasa de la asignatura
        CajaEntity caja = new CajaEntity();
        caja.setMatricula(matriculacionGuardada);
        caja.setImporte(tasa);

        cajaRepository.save(caja);

        // Si todo va bien, retorna el ID de la matriculación
        return matriculacionGuardada.getId();
    }

    @Override
    @Transactional // Transacción automática
    public int actualizarMatriculacion(Integer id, Integer idAlumno, Integer idAsignatura, String fecha,Double tasa ) {
        // Obtener las entidades desde los desplegables
        AlumnoEntity alumno = alumnoRepository.findById(idAlumno).get();
        AsignaturaEntity asignatura = asignaturaRepository.findById(idAsignatura).get();

        // 1. Actualizar la matriculación
        MatriculacionEntity matriculacion = matriculacionRepository.findById(id).get();
        matriculacion.setAlumno(alumno);
        matriculacion.setAsignatura(asignatura);
        matriculacion.setFecha(fecha);

        matriculacionRepository.save(matriculacion);

        // 2. Actualizar el importe en caja con la nueva tasa
        CajaEntity caja = matriculacion.getCaja();
            caja.setImporte(tasa);
            cajaRepository.save(caja);
        

        return id;
    }

    @Override
    @Transactional // Transacción automática
    public int borrarMatriculacion(Integer id) {
        try {
        	
        	MatriculacionEntity matriculacion = matriculacionRepository.findById(id).get();
            cajaRepository.deleteById(matriculacion.getCaja().getId());

            matriculacionRepository.deleteById(id);

            return id;
        } catch (Exception e) {
            return 0;
        }
    }
}
