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
    // 1. Declarar los campos como final (buena práctica)
    private final CotizacionRepository cotizacionRepo;
    private final MuebleRepository muebleRepo;
    private final VariantesRepository variantesRepo;
    private final PrecioStrategy precioStrategy; // Usaremos esta en lugar de crear una nueva en el método

    // 2. CONSTRUCTOR PARA INYECTAR TODAS LAS DEPENDENCIAS (¡ESTO RESUELVE EL NULLPOINTER!)
    @Autowired
    public CotizacionService(CotizacionRepository cotizacionRepo,
                             MuebleRepository muebleRepo,
                             VariantesRepository variantesRepo) {
        this.cotizacionRepo = cotizacionRepo;
        this.muebleRepo = muebleRepo;       // <-- ¡INICIALIZADO!
        this.variantesRepo = variantesRepo; // <-- ¡INICIALIZADO!
        // Inicializar la estrategia
        this.precioStrategy = new PrecioNormalStrategy();
    }

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

        // Ya no necesitamos crear una nueva estrategia aquí, usamos la inyectada:
        // PrecioStrategy strategy = new PrecioNormalStrategy(); 

        int total = 0;

        for (CotMueble item : muebles){
            if (item.getMueble() == null || item.getMueble().getId_mueble() == 0) {
                throw new RuntimeException("Todos lo items deben tener un mueble válido");
            }
            
            // Reemplazamos la lógica del precio base y adicional para usar el objeto completo Mueble
            
            // Línea que causaba el fallo, ahora muebleRepo ya no es null:
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

            // Usamos la lógica de la estrategia (ya corregida en pasos anteriores)
            int precioBase = mueblito.getPrecio_base();
            int precioAdicional = (variante != null) ? variante.getPrecioAdicional() : 0;
            int cantidad = item.getCantidad() <= 0 ? 1 : item.getCantidad();
            
            // Usamos la estrategia inyectada
            int subtotal = this.precioStrategy.calcularPrecio(precioBase, cantidad, precioAdicional); 

            // Establecer precio unitario (para que el getter getSubtotal() funcione correctamente si es necesario)
            item.setPrecioUnitario(precioBase + precioAdicional);
            
            if (item.getCantidad() <= 0) item.setCantidad(1); // Ya manejado arriba
            
            item.setCotizacion(cotizacion);

            total += subtotal;
        }

        cotizacion.setTotal(total);

        Cotizacion saved = cotizacionRepo.save(cotizacion);

        return saved;
    }

    /* 
    private int calcularPrecioUnitario(Mueble mueble, Variantes variante) {
        int precioBase = mueble.getPrecio_base();
        int adicional = (variante != null) ? variante.getPrecioAdicional() : 0;
        return precioBase + adicional;
    }
    */
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

