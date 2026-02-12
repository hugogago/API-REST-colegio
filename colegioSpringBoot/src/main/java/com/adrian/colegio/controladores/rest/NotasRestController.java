package com.adrian.colegio.controladores.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adrian.colegio.dtos.NotaDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;

@RestController
@RequestMapping("v1")
public class NotasRestController {

    @Autowired
    private INotasService notasService;

    
    @GetMapping("/notas")
    public ResponseEntity<List<NotaDTO>> listarTodas() {

        List<NotaDTO> lista =
                notasService.obtenerNotasPorFiltros(
                        null, null, null, null, null, null
                );

        return ResponseEntity.ok(lista);
    }

   
    @GetMapping("/notas/{id}")
    public ResponseEntity<NotaDTO> obtenerPorId(@PathVariable Integer id) {

        List<NotaDTO> lista =
                notasService.obtenerNotasPorFiltros(
                        null, null, null, null, null, null
                );

        return lista.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/notas/buscar")
    public ResponseEntity<List<NotaDTO>> buscarConFiltros(
            @RequestParam(value = "idAlumno", required = false) Integer idAlumno,
            @RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
            @RequestParam(value = "asignatura", required = false) String asignatura,
            @RequestParam(value = "nota", required = false) Double nota,
            @RequestParam(value = "fecha", required = false) String fecha,
            @RequestParam(value = "activo", required = false) Integer activo) {

       
        if (fecha == null || fecha.isBlank()) {
            fecha = LocalDate.now().toString();
        }

        List<NotaDTO> lista = notasService.obtenerNotasPorFiltros(
                        idAlumno,
                        nombreAlumno,
                        asignatura,
                        nota,
                        fecha,
                        activo
                );

        return ResponseEntity.ok(lista);
    }

    
    @PostMapping("/notas")
    public ResponseEntity<NotaDTO> crear(@RequestBody NotaDTO notaDTO) {

        int idCreado = notasService.insertarNota(
                notaDTO.getIdAlumno(),
                notaDTO.getIdAsignatura(),
                Double.valueOf(notaDTO.getNota()),
                notaDTO.getFecha()
        );

        notaDTO.setId(idCreado);
        return new ResponseEntity<>(notaDTO, HttpStatus.CREATED);
    }

  
    @PutMapping("/notas/{id}")
    public ResponseEntity<NotaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody NotaDTO notaDTO) {

        if (!id.equals(notaDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }

        List<NotaDTO> existente =
                notasService.obtenerNotasPorFiltros(
                        null, null, null, null, null, null
                );

        boolean existe = existente.stream()
                .anyMatch(n -> n.getId().equals(id));

        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        notasService.actualizarNota(
                notaDTO.getId(),
                notaDTO.getIdAlumno(),
                notaDTO.getIdAsignatura(),
                Double.valueOf(notaDTO.getNota()),
                notaDTO.getFecha()
        );

        return ResponseEntity.ok(notaDTO);
    }

    
    @DeleteMapping("/notas/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        int resultado = notasService.borrarNota(id);

        if (resultado == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Nota eliminada correctamente");
    }
}
