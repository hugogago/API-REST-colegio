package com.adrian.colegio.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.dtos.MatriculacionDTO;
import com.adrian.colegio.servicio.interfaces.IMatriculacionesService;

@Controller
@RequestMapping("/matriculaciones")
public class MatriculacionesController {

	@Autowired
	private IMatriculacionesService matriculacionesService;

	@Autowired
	private IDesplegablesDAO desplegablesDAO;

	// -----------------
	// INSERTAR MATRICULACION
	// -----------------
	@GetMapping("/insertarMatriculacion")
	public String formularioInsertarMatriculacion(ModelMap model) {
		// Cargar desplegables de alumnos y asignaturas activos
		ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
		ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", desplegableAlumnos);
		model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

		return "matriculaciones/insertarMatriculacion";
	}

	@PostMapping("/insertarMatriculacion")
	public String insertarMatriculacion(@RequestParam("alumno") Integer idAlumno,
			@RequestParam("asignatura") Integer idAsignatura,
			@RequestParam(value = "fecha", required = false) String fecha, @RequestParam(value = "tasa") Double tasa,
			ModelMap model) {

		Integer resultado = matriculacionesService.insertarMatriculacion(idAlumno, idAsignatura, fecha, tasa);

		model.addAttribute("resultado", resultado);

		// Recargar desplegables para el formulario
		ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
		ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", desplegableAlumnos);
		model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

		return "matriculaciones/insertarMatriculacion";
	}

	// -----------------
	// LISTADO MATRICULACIONES
	// -----------------
	@GetMapping("/listadoMatriculaciones")
	public String formularioListadoMatriculaciones() {
		return "matriculaciones/listadoMatriculaciones";
	}

	@PostMapping("/listadoMatriculaciones")
	public String listadoMatriculaciones(@RequestParam(value = "asignatura", required = false) String asignatura,
			@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = (activo != null) ? 1 : null;

		if (fecha == null || fecha.isEmpty() || fecha.isBlank())
			fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		ArrayList<MatriculacionDTO> listaMatriculaciones = matriculacionesService
				.obtenerMatriculacionesPorFiltros(asignatura, nombreAlumno, fecha, act);

		model.addAttribute("lista", listaMatriculaciones);

		return "matriculaciones/listadoMatriculaciones";
	}

	// -------------------------
	// ACTUALIZAR MATRICULACION
	// -------------------------
	@GetMapping(value = "/formularioActualizarMatriculaciones")
	public String formularioActualizarMatriculaciones(ModelMap model) {
		return "matriculaciones/actualizarMatriculaciones";
	}

	@PostMapping(value = "/formularioActualizarMatriculaciones")
	public String formularioActualizarMatriculaciones(
			@RequestParam(value = "asignatura", required = false) String asignatura,
			@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
			@RequestParam(value = "fecha", required = false) String fecha, ModelMap model) {

		if (fecha == null || fecha.isEmpty() || fecha.isBlank())
			fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		ArrayList<MatriculacionDTO> listaMatriculaciones = matriculacionesService
				.obtenerMatriculacionesPorFiltros(asignatura, nombreAlumno, fecha, 1);

		model.addAttribute("lista", listaMatriculaciones);

		// Cargar desplegables
		ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
		ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", desplegableAlumnos);
		model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

		return "matriculaciones/actualizarMatriculaciones";
	}

	@PostMapping(value = "/actualizarMatriculacion")
	public String actualizarMatriculacion(@RequestParam("id") Integer id, @RequestParam("alumno") Integer idAlumno,
			@RequestParam("asignatura") Integer idAsignatura, @RequestParam("fecha") String fecha,
			@RequestParam("tasa") Double tasa, ModelMap model) {

		Integer resultado = matriculacionesService.actualizarMatriculacion(id, idAlumno, idAsignatura, fecha, tasa);

		model.addAttribute("resultado", resultado);

		return "matriculaciones/actualizarMatriculaciones";
	}

	// -------------------------
	// BORRAR MATRICULACION
	// -------------------------
	@GetMapping(value = "/formularioBorrarMatriculaciones")
	public String formularioBorrarMatriculaciones() {
		return "matriculaciones/borrarMatriculaciones";
	}

	@PostMapping(value = "/formularioBorrarMatriculaciones")
	public String formularioBorrarMatriculaciones(
			@RequestParam(value = "asignatura", required = false) String asignatura,
			@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		Integer act = (activo != null) ? 1 : null;

		if (fecha == null || fecha.isEmpty() || fecha.isBlank())
			fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		ArrayList<MatriculacionDTO> listaMatriculaciones = matriculacionesService
				.obtenerMatriculacionesPorFiltros(asignatura, nombreAlumno, fecha, act);

		model.addAttribute("lista", listaMatriculaciones);

		return "matriculaciones/borrarMatriculaciones";
	}

	@PostMapping(value = "/borrarMatriculacion")
	public String borrarMatriculacion(@RequestParam("id") Integer id, ModelMap model) {
		Integer resultado = matriculacionesService.borrarMatriculacion(id);

		model.addAttribute("resultado", resultado);

		return "matriculaciones/borrarMatriculaciones";
	}
}
