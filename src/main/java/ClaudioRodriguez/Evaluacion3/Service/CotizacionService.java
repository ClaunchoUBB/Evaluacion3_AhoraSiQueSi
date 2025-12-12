package ClaudioRodriguez.Evaluacion3.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {
    @Autowired
    private CotizacionRepository cotizacionRepo;

    public List<Cotizacion> getAllCotizaciones() {
        return cotizacionRepo.findAll();
    }    

    public Cotizacion saveCotizacion(Cotizacion cotizacion){
        List<CotMueble> items = cotizacion.getCotMuebles();
        int tt = 0;
        for (CotMueble cotMueble : items) {
            tt = cotMueble.getCantidad()*cotMueble.getPrecioUnitario();
        }
        cotizacion.setTotal(tt);
        return cotizacionRepo.save(cotizacion);
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
