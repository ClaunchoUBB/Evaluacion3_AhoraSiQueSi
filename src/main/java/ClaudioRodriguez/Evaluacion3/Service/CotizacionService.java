package ClaudioRodriguez.Evaluacion3.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Entity.Variantes;
import ClaudioRodriguez.Evaluacion3.Estrategias.PrecioNormalStrategy;
import ClaudioRodriguez.Evaluacion3.Estrategias.PrecioStrategy;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;
import ClaudioRodriguez.Evaluacion3.Repository.VariantesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    @Autowired
    private CotizacionRepository cotizacionRepo;

    private MuebleRepository muebleRepo;
    private VariantesRepository variantesRepo;

    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionRepo.findAll();
    }

    public Cotizacion createCotizacion(Cotizacion cotizacion) {
        if (cotizacion == null){
            throw new IllegalArgumentException("Cotización nula");
        }

        List<CotMueble> muebles =  cotizacion.getCotMuebles();

        if (muebles == null || muebles.isEmpty()){
    
            throw new IllegalArgumentException("la cotización no tiene items");

        }

        PrecioStrategy strategy = new PrecioNormalStrategy();

        int total = 0;

        for (CotMueble item : muebles){
            if (item.getMueble() == null || item.getMueble().getId_mueble() == 0) {
                throw new RuntimeException("Todos lo items deben tener un mueble válido");
            }

            int precio_base = item.getMueble().getPrecio_base();
            int adicional = 0;

            


            Mueble mueblito = muebleRepo.findById(item.getMueble().getId_mueble())
        .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            Variantes variante = null;
            if (item.getVariante() != null && item.getVariante().getIdVariante() != 0) {
                variante = variantesRepo.findById(item.getVariante().getIdVariante())
                        .orElseThrow(() -> new RuntimeException("Variante no encontrada id=" + item.getVariante().getIdVariante()));
                if (variante.getMueble() == null || variante.getMueble().getId_mueble() != mueblito.getId_mueble()) {
                    throw new RuntimeException("La variante id=" + variante.getIdVariante() + " no pertenece al mueble id=" + mueblito.getId_mueble());
                }
            }

            int precioUnitario = calcularPrecioUnitario(mueblito, variante);
            item.setPrecioUnitario(precioUnitario);

            if (item.getCantidad() <= 0) item.setCantidad(1);


            int subtotal = precioUnitario * item.getCantidad();

            item.setCotizacion(cotizacion);

            total += subtotal;
        }

        cotizacion.setTotal(total);

        Cotizacion saved = cotizacionRepo.save(cotizacion);

        return saved;


    }

    private int calcularPrecioUnitario(Mueble mueble, Variantes variante) {
        int precioBase = mueble.getPrecio_base();
        int adicional = (variante != null) ? variante.getPrecioAdicional() : 0;
        return precioBase + adicional;
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

