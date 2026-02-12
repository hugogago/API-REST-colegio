package com.adrian.colegio.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.servicio.interfaces.IAsignaturasService;

@Controller
@RequestMapping("/asignaturas")
public class AsignaturasController {
	@Autowired
	IAsignaturasService asignaturasService;

	// -----------------
	// INSERTAR ASIGNATURA
	// -----------------
	@GetMapping("/insertarAsignatura")
	public String formularioInsertarAsignatura(ModelMap model) {
		return "asignaturas/insertarAsignatura";
	}

	@PostMapping("/insertarAsignatura")
	public String insertarAsignatura(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
			@RequestParam("curso") Integer curso, @RequestParam("tasa") Double tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = activo != null ? 1 : 0;

		Integer resultado = asignaturasService.actualizarAsignatura(id, nombre, curso, tasa, act);

		model.addAttribute("resultado", resultado);

		return "asignaturas/insertarAsignatura";
	}

	// -----------------
	// LISTADO ALUMNOS
	// -----------------
	@GetMapping("/listadoAsignaturas")
	public String formularioListadoAsignaturas() {
		return "asignaturas/listadoAsignaturas";
	}

	@PostMapping("/listadoAsignaturas")
	public String listadoAsignaturas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam("nombre") String nombre, @RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Double tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		int act = (activo != null) ? 1 : 0;

		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturasService.obtenerAsignaturasPorFiltros(id, nombre, curso,
				tasa, act);

		model.addAttribute("lista", listaAsignaturas);

		return "asignaturas/listadoAsignaturas";
	}

	// -------------------------
	// MODIFICAR ALUMNO
	// -------------------------

	// Método que simplemente nos mostrará el formulario
	@GetMapping(value = "/formularioActualizarAsignaturas")
	public String formularioModificarAsignaturas(ModelMap model) {
		return "asignaturas/actualizarAsignaturas";
	}

	// Método que se encarga de la búsqueda de alumnos para actualizar
	@PostMapping(value = "/formularioActualizarAsignaturas")
	public String formularioModificarAsignaturas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam("nombre") String nombre, @RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Double tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = activo != null ? 1 : 0;

		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturasService.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa,
				act);
		model.addAttribute("lista", listaAsignaturas);
		return "asignaturas/actualizarAsignaturas";
	}

	// Método que lleva a cabo la actualización
	@PostMapping(value = "/actualizarAsignatura")
	public String modificarAsignaturas(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
			@RequestParam("curso") Integer curso, @RequestParam("tasa") Double tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = activo != null ? 1 : 0;

		Integer resultado = asignaturasService.actualizarAsignatura(id, nombre, curso, tasa, act);

		model.addAttribute("resultado", resultado);
		return "asignaturas/actualizarAsignaturas";
	}

	// -------------------------
	// BORRAR ALUMNO
	// -------------------------
	// Método que se usa simplemente para mostrar el formulario.
	@GetMapping(value = "/formularioBorrarAsignaturas")
	public String getFormularioEliminarAsignaturas() {
		return "asignaturas/borrarAsignaturas";
	}

	// Método que se usa para buscar los registros a borrar
	@PostMapping(value = "/formularioBorrarAsignaturas")
	public String formularioEliminarAsignaturas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam("nombre") String nombre, @RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Double tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = activo != null ? 1 : 0;

		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturasService.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa, act);

		model.addAttribute("lista", listaAsignaturas);
		return "asignaturas/borrarAsignaturas";
	}

	@PostMapping(value = "/borrarAsignatura")
	public String eliminarAsignaturas(@RequestParam("id") Integer id, ModelMap model) {
		Integer resultado = asignaturasService.borrarAsignatura(id);

		model.addAttribute("resultado", resultado);
		return "asignaturas/borrarAsignaturas";
	}

}
