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

import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.servicio.interfaces.IAlumnosService;

@RestController
@RequestMapping("/v2")
public class AlumnosRestControllerV2 {

	@Autowired
	private IAlumnosService alumnoService;
	
	//Insertar alumnos
	@PostMapping("/alumnos")
	public ResponseEntity<String> insertarAlumno(@RequestBody AlumnoDTO alumno) {
		alumnoService.insertarAlumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(),
				alumno.getIdMunicipio(), alumno.getFamiliaNumerosa(), alumno.getActivo());
		return new ResponseEntity<>("Inserción correcta! ", HttpStatus.OK);
	}
	
	
	//Obtener todos los alumnos
	@GetMapping("/alumnos")
	public Iterable<AlumnoDTO> listarTodosAlumnos() {
	    return alumnoService.obtenerAlumnos();
	}
	
	//Obtener alumno por ID
	@GetMapping("/alumnos/{id}")
	public AlumnoDTO buscarAlumnoPorId(@PathVariable("id") Integer id){
	    return alumnoService.obtenerAlumnoPorId(id);
	}
	
	// Obtener alumnos por id, nombre, apellido, famNumerosa, activo y municipios
	@GetMapping(value = "/alumnos", params = { "id", "nombre", "apellidos", "famNumerosa", "activo" })
	public List<AlumnoDTO> listarAlumnosPorIdNombre(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellidos", required = false) String apellidos,
			@RequestParam(value = "famNumerosa", required = false) Integer famNumerosa,
			@RequestParam(value = "activo", required = false) Integer activo) {

		return alumnoService.obtenerAlumnosPorIdNombreApellido(id, nombre, apellidos, famNumerosa, activo);
	}
	
	// Actualizar alumnos
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody AlumnoDTO alumno) {

		if (!id.equals(alumno.getId())) {
			return ResponseEntity.badRequest().body("El ID de la URL no coincide con el del alumno");
		}

		//Aprovechamos que ya lo tenemos implementado
		if (buscarAlumnoPorId(alumno.getId()) == null) {
			return ResponseEntity.notFound().build(); // Devolvera un error 404
		}

		alumnoService.actualizarAlumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getIdMunicipio(), alumno.getFamiliaNumerosa(), alumno.getActivo());
		return ResponseEntity.ok(buscarAlumnoPorId(alumno.getId()));
	}
	

	// Borrar alumnos
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<?> borrarAlumno(@PathVariable Integer id) {

		if (buscarAlumnoPorId(id) == null) {
			return ResponseEntity.notFound().build(); // Devolvera un error 404
		}
		alumnoService.borrarAlumno(id);
		return new ResponseEntity<String>("Borrado correcto!", HttpStatus.OK);
	}

}
