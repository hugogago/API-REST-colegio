package com.adrian.colegio.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.colegio.dao.impl.AlumnosDAOImpl;
import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;

@RestController
@RequestMapping("/v1")
public class AlumnosRestControllerV1 {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private AlumnosDAOImpl alumnoDAO;

	@PostMapping("/alumnos")
	public ResponseEntity<String> insertarAlumno(@RequestBody AlumnoEntity alumno) {
		alumnoRepository.save(alumno);
		return new ResponseEntity<>("Inserción correcta! ", HttpStatus.OK);
	}

	// Obtener todos los alumnos
	@GetMapping("/alumnos")
	public Iterable<AlumnoEntity> listarTodosAlumnos() {
		return alumnoRepository.findAll();
	}

	// Obtener alumno por ID
	@GetMapping("/alumnos/{id}")
	public AlumnoDTO buscarAlumnoPorId(@PathVariable("id") Integer id) {
		return alumnoRepository.buscaAlumnoPorID(id);
	}

	// Obtener alumnos por id, nombre, apellido, famNumerosa, activo y municipios
	@GetMapping(value = "/alumnos", params = { "id", "nombre", "apellidos", "famNumerosa", "activo" })
	public List<AlumnoDTO> listarAlumnosPorIdNombre(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellidos", required = false) String apellidos,
			@RequestParam(value = "famNumerosa", required = false) Integer famNumerosa,
			@RequestParam(value = "activo", required = false) Integer activo) {

		return alumnoDAO.obtenerAlumnosPorIdNombreApellido(id, nombre, apellidos, famNumerosa, activo);
	}

	// Actualizar alumnos
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody AlumnoEntity alumno) {

		if (!id.equals(alumno.getId())) {
			return ResponseEntity.badRequest().body("El ID de la URL no coincide con el del alumno");
		}

		if (!alumnoRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // Devolvera un error 404
		}

		AlumnoEntity alumnoActualizado = alumnoRepository.save(alumno);
		return ResponseEntity.ok(alumnoActualizado);
	}

	// Borrar alumnos
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<?> borrarAlumno(@PathVariable Integer id) {

		if (!alumnoRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // Devolvera un error 404
		}
		 alumnoDAO.borrarAlumno(id);
		return new ResponseEntity<String>("Borrado correcto!", HttpStatus.OK);
	}
}
