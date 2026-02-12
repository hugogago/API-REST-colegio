package com.adrian.colegio.controladores.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.servicio.interfaces.IAsignaturasService;

@RestController
@RequestMapping("v1")
public class AsingaturasRestController {

    @Autowired
    private IAsignaturasService asignaturasService;

    
    @GetMapping("/asignaturas")
    public ResponseEntity<List<AsignaturaDTO>> listarTodas() {
        List<AsignaturaDTO> lista =
                asignaturasService.obtenerAsignaturasPorFiltros(null, null, null, null, null);
        return ResponseEntity.ok(lista);
    }

    
    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<AsignaturaDTO> obtenerPorId(@PathVariable Integer id) {
        List<AsignaturaDTO> lista = asignaturasService.obtenerAsignaturasPorFiltros(id, null, null, null, null);

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista.get(0));
    }

    
    @GetMapping("/asignaturas/buscar")
    public ResponseEntity<List<AsignaturaDTO>> buscarConFiltros(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "curso", required = false) Integer curso,
            @RequestParam(value = "tasa", required = false) Double tasa,
            @RequestParam(value = "activo", required = false) Integer activo) {

        List<AsignaturaDTO> lista =
                asignaturasService.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa, activo);
        return ResponseEntity.ok(lista);
    }

  
    @PostMapping("/asignaturas")
    public ResponseEntity<AsignaturaDTO> crear(@RequestBody AsignaturaDTO asignatura) {

        int idCreado = asignaturasService.insertarAsignatura(
                asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getCurso(),
                asignatura.getTasa(),
                asignatura.getActivo()
        );

        asignatura.setId(idCreado);
        return new ResponseEntity<>(asignatura, HttpStatus.CREATED);
    }

   
    @PutMapping("/asignaturas/{id}")
    public ResponseEntity<AsignaturaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody AsignaturaDTO asignatura) {

        if (!id.equals(asignatura.getId())) {
            return ResponseEntity.badRequest().build();
        }

        List<AsignaturaDTO> existente =
                asignaturasService.obtenerAsignaturasPorFiltros(id, null, null, null, null);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        asignaturasService.actualizarAsignatura(
                asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getCurso(),
                asignatura.getTasa(),
                asignatura.getActivo()
        );

        return ResponseEntity.ok(asignatura);
    }

   
    @DeleteMapping("/asignaturas/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        List<AsignaturaDTO> existente =
                asignaturasService.obtenerAsignaturasPorFiltros(id, null, null, null, null);

        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        asignaturasService.borrarAsignatura(id);
        return ResponseEntity.ok("Asignatura eliminada correctamente");
    }
}
