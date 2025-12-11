package ClaudioRodriguez.Evaluacion3.Service;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Repository.CotMuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotMuebleService {
    @Autowired
    private CotMuebleRepository cotMuebleRepo;

    public List<CotMueble> getAllCotMuebles() {
        return cotMuebleRepo.findAll();
    }

    public CotMueble saveCotMueble(CotMueble cotMueble) {
        return cotMuebleRepo.save(cotMueble);
    }

    public CotMueble getCotMuebleById(int id) {
        return cotMuebleRepo.findById(id).orElse(null);
    }

    public void deleteCotMueble(int id) {
        cotMuebleRepo.deleteById(id);
    }

    public CotMueble updateCotMueble(int id, CotMueble reemplazo) {
        Optional<CotMueble> optionalCotMueble = cotMuebleRepo.findById(id);
        if (optionalCotMueble.isPresent()) {
            CotMueble existingCotMueble = optionalCotMueble.get();
            existingCotMueble.setCotizacion(reemplazo.getCotizacion());
            existingCotMueble.setCantidad(reemplazo.getCantidad());
            existingCotMueble.setPrecioUnitario(reemplazo.getPrecioUnitario());
            return cotMuebleRepo.save(existingCotMueble);
        } else {
            return null;
        }
    }
}
