package ClaudioRodriguez.Evaluacion3.Controller;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.Venta;
import ClaudioRodriguez.Evaluacion3.Fabricas.CotizacionFactory;
import ClaudioRodriguez.Evaluacion3.Service.CotizacionService;
import ClaudioRodriguez.Evaluacion3.Service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Cotizacion> getAll() {
        return cotizacionService.getAllCotizaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> getById(@PathVariable int id) {
        Cotizacion c = cotizacionService.getCotizacionById(id);
        return (c != null) ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cotizacion> create(@RequestBody Cotizacion input) {

        Cotizacion cotizacion = CotizacionFactory.crearCotizacionVacia();

        List<CotMueble> items = input.getCotMuebles();
        cotizacion.setCotMuebles(items);

        Cotizacion saved = cotizacionService.createCotizacion(cotizacion);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<Venta> confirmar(@PathVariable int id) {
        Venta venta = ventaService.confirmarVenta(id);
        return ResponseEntity.ok(venta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        cotizacionService.deleteCotizacion(id);
        return ResponseEntity.noContent().build();
    }
}
