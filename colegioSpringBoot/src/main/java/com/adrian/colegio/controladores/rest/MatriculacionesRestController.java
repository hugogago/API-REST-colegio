package com.adrian.colegio.controladores.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adrian.colegio.dtos.MatriculacionDTO;
import com.adrian.colegio.servicio.interfaces.IMatriculacionesService;

@RestController
@RequestMapping("v1")
public class MatriculacionesRestController {

    @Autowired
    private IMatriculacionesService matriculacionesService;

    
    @GetMapping("/matriculaciones")
    public ResponseEntity<List<MatriculacionDTO>> listarTodas() {

        List<MatriculacionDTO> lista =
                matriculacionesService.obtenerMatriculacionesPorFiltros(
                        null, null, null, null
                );

        return ResponseEntity.ok(lista);
    }

   
    @GetMapping("/matriculaciones/{id}")
    public ResponseEntity<MatriculacionDTO> obtenerPorId(@PathVariable Integer id) {

        List<MatriculacionDTO> lista =
                matriculacionesService.obtenerMatriculacionesPorFiltros(
                        null, null, null, null
                );

        return lista.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/matriculaciones/buscar")
    public ResponseEntity<List<MatriculacionDTO>> buscarConFiltros(
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "activo", required = false) Integer activo) {

        
        if (fecha == null || fecha.isBlank()) {
            fecha = LocalDate.now().toString();
        }

        List<MatriculacionDTO> lista =
                matriculacionesService.obtenerMatriculacionesPorFiltros(
                        asignatura,
                        nombreAlumno,
                        fecha,
                        activo
                );

        return ResponseEntity.ok(lista);
    }

    
    @PostMapping("/matriculaciones")
    public ResponseEntity<MatriculacionDTO> crear(
            @RequestBody MatriculacionDTO dto) {

    	int idCreado = matriculacionesService.insertarMatriculacion(
    	        dto.getIdAlumno(),
    	        dto.getIdAsignatura(),
    	        dto.getFecha(),
    	        dto.getTasa()
    	);


        dto.setId(idCreado);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

  
    @PutMapping("/matriculaciones/{id}")
    public ResponseEntity<MatriculacionDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody MatriculacionDTO dto) {

        if (!id.equals(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        List<MatriculacionDTO> existente =
                matriculacionesService.obtenerMatriculacionesPorFiltros(
                        null, null, null, null
                );

        boolean existe = existente.stream()
                .anyMatch(m -> m.getId().equals(id));

        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        matriculacionesService.actualizarMatriculacion(
                dto.getId(),
                dto.getIdAlumno(),
                dto.getIdAsignatura(),
                dto.getFecha(),
                dto.getTasa()
        );


        return ResponseEntity.ok(dto);
    }

    
    @DeleteMapping("/matriculaciones/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        int resultado = matriculacionesService.borrarMatriculacion(id);

        if (resultado == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Matriculación eliminada correctamente");
    }
}
