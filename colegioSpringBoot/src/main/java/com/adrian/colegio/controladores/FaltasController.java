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
import com.adrian.colegio.dtos.FaltaDTO;
import com.adrian.colegio.servicio.interfaces.IFaltasService;

@Controller
@RequestMapping("/faltas")
public class FaltasController {

    @Autowired
    private IFaltasService faltasService;

    @Autowired
    private IDesplegablesDAO desplegablesDAO;

    // -----------------
    // INSERTAR FALTA
    // -----------------
    @GetMapping("/insertarFalta")
    public String formularioInsertarFalta(ModelMap model) {
        // Cargar desplegables de alumnos y asignaturas activos
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "faltas/insertarFalta";
    }

    @PostMapping("/insertarFalta")
    public String insertarFalta(@RequestParam("alumno") Integer idAlumno,
            @RequestParam("asignatura") Integer idAsignatura,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "justificada", required = false) String justificada, ModelMap model) {

        Integer just = (justificada != null) ? 1 : 0;

        Integer resultado = faltasService.insertarFalta(idAlumno, idAsignatura, fecha, just);

        model.addAttribute("resultado", resultado);

        // Recargar desplegables para el formulario
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "faltas/insertarFalta";
    }

    // -----------------
    // LISTADO FALTAS
    // -----------------
    @GetMapping("/listadoFaltas")
    public String formularioListadoFaltas() {
        return "faltas/listadoFaltas";
    }

    @PostMapping("/listadoFaltas")
    public String listadoFaltas(@RequestParam(value = "idAlumno", required = false) Integer idAlumno,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "justificada", required = false) String justificada,
            @RequestParam(value = "activo", required = false) String activo, ModelMap model) {

        Integer just = (justificada != null) ? 1 : null;
        Integer act = (activo != null) ? 1 : null;
        
        if(fecha == null || fecha.isEmpty() || fecha.isBlank())
        	fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        ArrayList<FaltaDTO> listaFaltas = faltasService.obtenerFaltasPorFiltros(idAlumno, nombreAlumno, asignatura,
                fecha, just, act);

        model.addAttribute("lista", listaFaltas);

        return "faltas/listadoFaltas";
    }

    // -------------------------
    // ACTUALIZAR FALTA
    // -------------------------
    @GetMapping(value = "/formularioActualizarFaltas")
    public String formularioActualizarFaltas(ModelMap model) {
        return "faltas/actualizarFaltas";
    }

    @PostMapping(value = "/formularioActualizarFaltas")
    public String formularioActualizarFaltas(
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "fecha", required = false) String fecha, ModelMap model) {

        if (fecha == null || fecha.isEmpty() || fecha.isBlank())
            fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        ArrayList<FaltaDTO> listaFaltas = faltasService.obtenerFaltasPorFiltros(null, nombreAlumno, asignatura, fecha,
                null, 1);

        model.addAttribute("lista", listaFaltas);

        // Cargar desplegables
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "faltas/actualizarFaltas";
    }

    @PostMapping(value = "/actualizarFalta")
    public String actualizarFalta(@RequestParam("idFalta") Integer id, @RequestParam("alumno") Integer idAlumno,
            @RequestParam("asignatura") Integer idAsignatura, @RequestParam("fecha") String fecha,
            @RequestParam(value = "justificada", required = false) String justificada, ModelMap model) {

        Integer just = (justificada != null) ? 1 : 0;

        Integer resultado = faltasService.actualizarFalta(id, idAlumno, idAsignatura, fecha, just);

        model.addAttribute("resultado", resultado);

        return "faltas/actualizarFaltas";
    }

    // -------------------------
    // BORRAR FALTA
    // -------------------------
    @GetMapping(value = "/formularioBorrarFaltas")
    public String formularioBorrarFaltas() {
        return "faltas/borrarFaltas";
    }

    @PostMapping(value = "/formularioBorrarFaltas")
    public String formularioBorrarFaltas(@RequestParam(value = "idAlumno", required = false) Integer idAlumno,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "justificada", required = false) String justificada,
            @RequestParam(value = "activo", required = false) String activo, ModelMap model) {

        Integer just = (justificada != null) ? 1 : null;
        Integer act = (activo != null) ? 1 : null;

        if (fecha == null || fecha.isEmpty() || fecha.isBlank())
            fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        ArrayList<FaltaDTO> listaFaltas = faltasService.obtenerFaltasPorFiltros(idAlumno, nombreAlumno, asignatura,
                fecha, just, act);

        model.addAttribute("lista", listaFaltas);

        return "faltas/borrarFaltas";
    }

    @PostMapping(value = "/borrarFalta")
    public String borrarFalta(@RequestParam("idFalta") Integer id, ModelMap model) {
        Integer resultado = faltasService.borrarFalta(id);

        model.addAttribute("resultado", resultado);

        return "faltas/borrarFaltas";
    }
}
