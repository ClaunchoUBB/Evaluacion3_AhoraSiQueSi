package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @GetMapping
    public List<Mueble> getAll() {
        return muebleService.getAllMuebles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mueble> getById(@PathVariable int id) {
        Mueble m = muebleService.getMuebleById(id);
        return (m != null) ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Mueble> create(@RequestBody Mueble mueble) {
        return ResponseEntity.ok(muebleService.saveMueble(mueble));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> update(@PathVariable int id, @RequestBody Mueble reemplazo) {
        Mueble updated = muebleService.updateMueble(id, reemplazo);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        muebleService.deleteMueble(id);
        return ResponseEntity.noContent().build();
    }
}
