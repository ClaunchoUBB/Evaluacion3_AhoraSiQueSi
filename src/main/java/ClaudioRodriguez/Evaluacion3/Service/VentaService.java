package ClaudioRodriguez.Evaluacion3.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Entity.Venta;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;
import ClaudioRodriguez.Evaluacion3.Repository.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private MuebleRepository muebleRepo;
    @Autowired
    private CotizacionRepository cotizacionRepo;

    public List<Venta> getAllVentas() {
        return ventaRepo.findAll();
    }

    public Venta saveVenta(Venta venta) {
        return ventaRepo.save(venta);
    }

    public Venta getVentaById(int id) {
        return ventaRepo.findById(id).orElse(null);
    }

    public void deleteVenta(int id) {
        ventaRepo.deleteById(id);
    }

    public Venta updateVenta(int id, Venta reemplazo) {
        Optional<Venta> optionalVenta = ventaRepo.findById(id);
        if (optionalVenta.isPresent()) {
            Venta existingVenta = optionalVenta.get();
            existingVenta.setFecha_venta(reemplazo.getFecha_venta());
            existingVenta.setTotal_venta(reemplazo.getTotal_venta());
            existingVenta.setCotizacion(reemplazo.getCotizacion());
            return ventaRepo.save(existingVenta);
        } else {
            return null;
        }
    }

    public Venta confirmarVenta(int idCotizacion) {

        Cotizacion cot = cotizacionRepo.findById(idCotizacion) 
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));
        if (cot.getCotMuebles() == null || cot.getCotMuebles().isEmpty()) { 
            throw new RuntimeException("La cotización no tiene muebles"); 
        }

        for (CotMueble item : cot.getCotMuebles()) { 
            Mueble m = muebleRepo.findById(item.getMueble().getId_mueble()) 
                    .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            if (m.getStock() < item.getCantidad()) { 
                throw new RuntimeException(
                        "Stock insuficiente para mueble '" + m.getNombre_mueble() +
                                "'. Stock disponible: " + m.getStock() +
                                " — solicitado: " + item.getCantidad());
            } else {
                m.setStock(m.getStock() - item.getCantidad());
                muebleRepo.save(m);
            }

        }

        Venta venta = new Venta();
        venta.setFecha_venta(LocalDateTime.now());
        venta.setTotal_venta(cot.getTotal());
        venta.setCotizacion(cot);
        ventaRepo.save(venta);
        return venta;
    }
}
