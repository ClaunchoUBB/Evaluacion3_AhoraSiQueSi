package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Service.CotMuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cotmueble")
public class CotMuebleController {

    @Autowired
    private CotMuebleService service;

    @GetMapping
    public List<CotMueble> getAll() {
        return service.getAllCotMuebles();
    }

    @PostMapping
    public CotMueble create(@RequestBody CotMueble c) {
        return service.saveCotMueble(c);
    }

    @GetMapping("/{id}")
    public CotMueble get(@PathVariable int id) {
        return service.getCotMuebleById(id);
    }

    @PutMapping("/{id}")
    public CotMueble update(@PathVariable int id, @RequestBody CotMueble c) {
        return service.updateCotMueble(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteCotMueble(id);
    }
}
