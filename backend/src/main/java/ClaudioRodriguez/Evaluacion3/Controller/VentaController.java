package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.Venta;
import ClaudioRodriguez.Evaluacion3.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> getAll() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable int id) {
        Venta v = ventaService.getVentaById(id);
        return (v != null) ? ResponseEntity.ok(v) : ResponseEntity.notFound().build();
    }
}
