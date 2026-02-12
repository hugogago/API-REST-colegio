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
import com.adrian.colegio.dtos.NotaDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;

@Controller
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private INotasService notasService;

    @Autowired
    private IDesplegablesDAO desplegablesDAO;

    // -----------------
    // INSERTAR NOTA
    // -----------------
    @GetMapping("/insertarNota")
    public String formularioInsertarNota(ModelMap model) {
        // Cargar desplegables de alumnos y asignaturas activos
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "notas/insertarNota";
    }

    @PostMapping("/insertarNota")
    public String insertarNota(@RequestParam("alumno") Integer idAlumno,
            @RequestParam("asignatura") Integer idAsignatura, @RequestParam("nota") Double nota,
            @RequestParam(value = "fecha", required = false) String fecha, ModelMap model) {

        Integer resultado = notasService.insertarNota(idAlumno, idAsignatura, nota, fecha);

        model.addAttribute("resultado", resultado);

        // Recargar desplegables para el formulario
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "notas/insertarNota";
    }

    // -----------------
    // LISTADO NOTAS
    // -----------------
    @GetMapping("/listadoNotas")
    public String formularioListadoNotas() {
        return "notas/listadoNotas";
    }

    @PostMapping("/listadoNotas")
    public String listadoNotas(@RequestParam(value = "idAlumno", required = false) Integer idAlumno,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "nota", required = false) Double nota,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "activo", required = false) String activo, ModelMap model) {

        Integer act = (activo != null) ? 1 : null;
        
        if(fecha == null || fecha.isEmpty() || fecha.isBlank())
        	fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        ArrayList<NotaDTO> listaNotas = notasService.obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota,
                fecha, act);

        model.addAttribute("lista", listaNotas);

        return "notas/listadoNotas";
    }

    // -------------------------
    // ACTUALIZAR NOTA
    // -------------------------
    @GetMapping(value = "/formularioActualizarNotas")
    public String formularioActualizarNotas(ModelMap model) {
        return "notas/actualizarNotas";
    }

    @PostMapping(value = "/formularioActualizarNotas")
    public String formularioActualizarNotas(@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "fecha", required = false) String fecha, ModelMap model) {

    	 if(fecha == null || fecha.isEmpty() || fecha.isBlank())
         	fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        ArrayList<NotaDTO> listaNotas = notasService.obtenerNotasPorFiltros(null, nombreAlumno, asignatura, null,
                fecha, 1);

        model.addAttribute("lista", listaNotas);

        // Cargar desplegables
        ArrayList<DesplegableDTO> desplegableAlumnos = desplegablesDAO.desplegableAlumnos();
        ArrayList<DesplegableDTO> desplegableAsignaturas = desplegablesDAO.desplegableAsignaturas();

        model.addAttribute("desplegableAlumnos", desplegableAlumnos);
        model.addAttribute("desplegableAsignaturas", desplegableAsignaturas);

        return "notas/actualizarNotas";
    }

    @PostMapping(value = "/actualizarNota")
    public String actualizarNota(@RequestParam("id") Integer id, @RequestParam("alumno") Integer idAlumno,
            @RequestParam("asignatura") Integer idAsignatura, @RequestParam("nota") Double nota,
            @RequestParam("fecha") String fecha, ModelMap model) {

        Integer resultado = notasService.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);

        model.addAttribute("resultado", resultado);

        return "notas/actualizarNotas";
    }

    // -------------------------
    // BORRAR NOTA
    // -------------------------
    @GetMapping(value = "/formularioBorrarNotas")
    public String formularioBorrarNotas() {
        return "notas/borrarNotas";
    }

    @PostMapping(value = "/formularioBorrarNotas")
    public String formularioBorrarNotas(@RequestParam(value = "idAlumno", required = false) Integer idAlumno,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "nota", required = false) Double nota,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "activo", required = false) String activo, ModelMap model) {

        Integer act = (activo != null) ? 1 : null;
        if(fecha == null || fecha.isEmpty() || fecha.isBlank())
        	fecha = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        ArrayList<NotaDTO> listaNotas = notasService.obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota,
                fecha, act);

        model.addAttribute("lista", listaNotas);

        return "notas/borrarNotas";
    }

    @PostMapping(value = "/borrarNota")
    public String borrarNota(@RequestParam("id") Integer id, ModelMap model) {
        Integer resultado = notasService.borrarNota(id);

        model.addAttribute("resultado", resultado);

        return "notas/borrarNotas";
    }
}
