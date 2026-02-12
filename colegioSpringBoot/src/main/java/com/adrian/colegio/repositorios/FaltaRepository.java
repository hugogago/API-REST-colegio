package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.FaltaDTO;
import com.adrian.colegio.entities.FaltaEntity;

public interface FaltaRepository extends CrudRepository<FaltaEntity, Integer> {
    @Query("SELECT NEW com.adrian.colegio.dtos.FaltaDTO(" +
            "f.id, " +
            "f.alumno.id, " +
            "f.alumno.nombre, " +
            "f.asignatura.id, " +
            "f.asignatura.nombre, " +
            "f.fecha, " +
            "f.justificada) " +
            "FROM com.adrian.colegio.entities.FaltaEntity f " +
            "WHERE (:idAlumno IS NULL OR f.alumno.id = :idAlumno) " +
            "AND (:nombreAlumno IS NULL OR f.alumno.nombre LIKE CONCAT('%', :nombreAlumno, '%')) " +
            "AND (:nombreAsignatura IS NULL OR f.asignatura.nombre LIKE CONCAT('%', :nombreAsignatura, '%')) " +
            "AND (:fecha IS NULL OR f.fecha >= :fecha) " +
            "AND (:justificada IS NULL OR f.justificada = :justificada) " +
            "AND (:activo IS NULL OR f.alumno.activo = :activo)")
    ArrayList<FaltaDTO> buscarFaltas(
            @Param("idAlumno") Integer idAlumno,
            @Param("nombreAlumno") String nombreAlumno,
            @Param("nombreAsignatura") String nombreAsignatura,
            @Param("fecha") String fecha,
            @Param("justificada") Integer justificada,
            @Param("activo") Integer activo);
}
