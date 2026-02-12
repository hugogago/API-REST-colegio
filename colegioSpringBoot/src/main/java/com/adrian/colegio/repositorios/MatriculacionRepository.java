package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.MatriculacionDTO;
import com.adrian.colegio.entities.MatriculacionEntity;

public interface MatriculacionRepository extends CrudRepository<MatriculacionEntity, Integer> {
	@Query("SELECT NEW com.adrian.colegio.dtos.MatriculacionDTO(" +
	        "m.id, " +
	        "m.asignatura.id, " +
	        "m.asignatura.nombre, " +
	        "m.alumno.id, " +
	        "m.alumno.nombre, " +
	        "m.fecha, " +
	        "m.activo, " +
	        "m.caja.importe) " + 
	        "FROM com.adrian.colegio.entities.MatriculacionEntity m " +
	        "WHERE (:nombreAsignatura IS NULL OR m.asignatura.nombre LIKE CONCAT('%', :nombreAsignatura, '%')) " +
	        "AND (:nombreAlumno IS NULL OR m.alumno.nombre LIKE CONCAT('%', :nombreAlumno, '%')) " +
	        "AND (:fecha IS NULL OR m.fecha >= :fecha) " +
	        "AND (:activo IS NULL OR m.activo = :activo)")
	ArrayList<MatriculacionDTO> buscarMatriculaciones(
	        @Param("nombreAsignatura") String nombreAsignatura,
	        @Param("nombreAlumno") String nombreAlumno,
	        @Param("fecha") String fecha,
	        @Param("activo") Integer activo);
}
