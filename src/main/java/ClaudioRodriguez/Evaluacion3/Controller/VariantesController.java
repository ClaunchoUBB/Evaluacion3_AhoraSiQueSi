package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.Variantes;
import ClaudioRodriguez.Evaluacion3.Service.VariantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VariantesController {

    @Autowired
    private VariantesService variantesService;

    @GetMapping
    public List<Variantes> getAll() {
        return variantesService.getAllVariantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variantes> getById(@PathVariable int id) {
        Variantes v = variantesService.getVarianteById(id);
        return (v != null) ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Variantes> create(@RequestBody Variantes variante) {
        return ResponseEntity.ok(variantesService.saveVariante(variante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variantes> update(@PathVariable int id, @RequestBody Variantes reemplazo) {
        Variantes updated = variantesService.updateVariante(id, reemplazo);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        variantesService.deleteVariante(id);
        return ResponseEntity.noContent().build();
    }
}
