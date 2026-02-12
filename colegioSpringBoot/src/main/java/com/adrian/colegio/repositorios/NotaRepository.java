package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.NotaDTO;
import com.adrian.colegio.entities.NotaEntity;

public interface NotaRepository extends CrudRepository<NotaEntity, Integer>{
	@Query("SELECT NEW com.adrian.colegio.dtos.NotaDTO(" +
		       "n.id, " +
		       "CAST(n.nota AS string), " +
		       "n.asignatura.id, " +
		       "n.asignatura.nombre, " +
		       "n.alumno.id, " +
		       "n.alumno.nombre, " +
		       "n.fecha) " +
		       "FROM com.adrian.colegio.entities.NotaEntity n " +
		       "WHERE (:idAlumno IS NULL OR n.alumno.id = :idAlumno) " +
		       "AND (:nombreAlumno IS NULL OR n.alumno.nombre LIKE CONCAT('%', :nombreAlumno, '%')) " +
		       "AND (:nombreAsignatura IS NULL OR n.asignatura.nombre LIKE CONCAT('%', :nombreAsignatura, '%')) " +
		       "AND (:nota IS NULL OR n.nota = :nota) " +
		       "AND (:fecha IS NULL OR n.fecha >= :fecha)" +
		       "AND (:activo IS NULL OR n.alumno.activo = :activo)")
		ArrayList<NotaDTO> buscarNotas(
		    @Param("idAlumno") Integer idAlumno,
		    @Param("nombreAlumno") String nombreAlumno,
		    @Param("nombreAsignatura") String nombreAsignatura,
		    @Param("nota") Double nota,
		    @Param("fecha") String fecha,
		    @Param("activo") Integer activo);
}
