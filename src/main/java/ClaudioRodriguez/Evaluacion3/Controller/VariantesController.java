package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.Variante;
import ClaudioRodriguez.Evaluacion3.Service.VarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VariantesController {

    @Autowired
    private VarianteService variantesService;

    @GetMapping
    public List<Variante> getAll() {
        return variantesService.getAllVariantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variante> getById(@PathVariable int id) {
        Variante v = variantesService.getVarianteById(id);
        return (v != null) ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Variante> create(@RequestBody Variante variante) {
        return ResponseEntity.ok(variantesService.saveVariante(variante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variante> update(@PathVariable int id, @RequestBody Variante reemplazo) {
        Variante updated = variantesService.updateVariante(id, reemplazo);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        variantesService.deleteVariante(id);
        return ResponseEntity.noContent().build();
    }
}
