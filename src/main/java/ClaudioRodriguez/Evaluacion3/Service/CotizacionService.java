package ClaudioRodriguez.Evaluacion3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Entity.Variante;
import ClaudioRodriguez.Evaluacion3.Estrategias.PrecioNormalStrategy;
import ClaudioRodriguez.Evaluacion3.Estrategias.PrecioStrategy;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;
import ClaudioRodriguez.Evaluacion3.Repository.VariantesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    private CotizacionRepository cotizacionRepo;
    private MuebleRepository muebleRepo;
    private VariantesRepository variantesRepo;
    private PrecioStrategy precioStrategy;

    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionRepo.findAll();
    }

    public Cotizacion createCotizacion(Cotizacion cotizacion) {
        if (cotizacion == null) {
            throw new IllegalArgumentException("Cotización nula");
        }

        List<CotMueble> muebles = cotizacion.getCotMuebles();

        if (muebles == null || muebles.isEmpty()) {
            throw new IllegalArgumentException("la cotización no tiene items");
        }

        int total = 0;


        //En este bloque se calcula el total de la cotización
        for (CotMueble item : muebles) {

            if (item.getMueble() == null || item.getMueble().getId_mueble() == 0) {
                throw new RuntimeException("Todos lo items deben tener un mueble válido");
            }
            Mueble mueblito = muebleRepo.findById(item.getMueble().getId_mueble())
                    .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            Variante variante = null;
            if (item.getVariante() != null && item.getVariante().getIdVariante() != 0) {
                variante = variantesRepo.findById(item.getVariante().getIdVariante())
                        .orElseThrow(() -> new RuntimeException(
                                "Variante no encontrada id=" + item.getVariante().getIdVariante()));
                if (variante.getMueble() == null || variante.getMueble().getId_mueble() != mueblito.getId_mueble()) {
                    throw new RuntimeException("La variante id=" + variante.getIdVariante()
                            + " no pertenece al mueble id=" + mueblito.getId_mueble());
                }
            }

            int precioBase = mueblito.getPrecio_base();

            int precioAdicional = (variante != null) ? variante.getPrecioAdicional() : 0;

            int cantidad = item.getCantidad() <= 0 ? 1 : item.getCantidad();

            int subtotal = this.precioStrategy.calcularPrecio(precioBase, cantidad, precioAdicional);

            item.setPrecioUnitario(precioBase + precioAdicional);

            if (item.getCantidad() <= 0) {
                item.setCantidad(1);
            } 
            item.setCotizacion(cotizacion);
            total += subtotal;
        }

        cotizacion.setTotal(total);

        Cotizacion saved = cotizacionRepo.save(cotizacion);

        return saved;
    }

    public Cotizacion getCotizacionById(int id) {
        return cotizacionRepo.findById(id).orElse(null);
    }

    public void deleteCotizacion(int id) {
        cotizacionRepo.deleteById(id);
    }

    public Cotizacion updateCotizacion(int id, Cotizacion reemplazo) {
        Optional<Cotizacion> optionalCotizacion = cotizacionRepo.findById(id);
        if (optionalCotizacion.isPresent()) {
            Cotizacion existingCotizacion = optionalCotizacion.get();
            existingCotizacion.setFecha_cotizacion(reemplazo.getFecha_cotizacion());
            existingCotizacion.setTotal(reemplazo.getTotal());
            existingCotizacion.setVenta(reemplazo.getVenta());
            return cotizacionRepo.save(existingCotizacion);
        } else {
            return null;
        }
    }
}
