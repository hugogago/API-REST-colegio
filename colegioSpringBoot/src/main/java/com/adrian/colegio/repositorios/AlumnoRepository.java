package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.entities.AlumnoEntity;

public interface AlumnoRepository extends CrudRepository<AlumnoEntity, Integer> {

	@Query("select new com.adrian.colegio.dtos.AlumnoDTO (a.id, a.nombre, a.apellidos, a.municipio.nombre, a.municipio.idMunicipio, a.famNumerosa, a.activo) "
		    + "FROM AlumnoEntity a "
		    + "WHERE (:id IS NULL OR a.id = :id) "
		    + "AND (:nombre IS NULL OR :nombre = '' OR a.nombre LIKE CONCAT('%', :nombre, '%')) "
		    + "AND (:apellidos IS NULL OR :apellidos = '' OR a.apellidos LIKE CONCAT('%', :apellidos, '%')) "
		    + "AND (:activo IS NULL OR a.activo = :activo) "
		    + "AND (:famNumerosa IS NULL OR a.famNumerosa = :famNumerosa)")
	ArrayList<AlumnoDTO> buscaAlumnoporIDyNombre(@Param("id") Integer id,
			@Param("nombre") String nombre,
			@Param("apellidos") String apellidos,
			@Param("famNumerosa") Integer familiaNumerosa,
			@Param("activo") Integer activo);

	@Query("SELECT a FROM com.adrian.colegio.entities.AlumnoEntity a WHERE a.activo = 1")
	Iterable<AlumnoEntity> buscarAlumnosActivos();
   
	@Query(" SELECT new com.adrian.colegio.dtos.AlumnoDTO (a.id, a.nombre, a.apellidos, a.municipio.nombre, a.municipio.idMunicipio, a.famNumerosa, a.activo) "
            + "FROM AlumnoEntity a "
            + "WHERE a.id = :id ")
    AlumnoDTO buscaAlumnoPorID(@Param("id") Integer id);

}
