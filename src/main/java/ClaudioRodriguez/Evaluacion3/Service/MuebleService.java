package ClaudioRodriguez.Evaluacion3.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepo;

    public List<Mueble> getAllMuebles() {
        return muebleRepo.findAll();
    }

    public Mueble saveMueble(Mueble mueble) {
        return muebleRepo.save(mueble);
    }

    public Mueble getMuebleById(int id) {
        return muebleRepo.findById(id).orElse(null);
    }   

    public void deleteMueble(int id) {
        muebleRepo.deleteById(id);
    }

    public Mueble updateMueble(int id, Mueble reemplazo) {
        Optional<Mueble> optionalMueble = muebleRepo.findById(id); 
        if (optionalMueble.isPresent()) {
            Mueble existingMueble = optionalMueble.get(); 

            existingMueble.setNombre_mueble(reemplazo.getNombre_mueble());
            existingMueble.setTipo(reemplazo.getTipo());
            existingMueble.setPrecio_base(reemplazo.getPrecio_base());
            existingMueble.setMaterial(reemplazo.getMaterial());
            existingMueble.setStock(reemplazo.getStock());
            existingMueble.setActivo(reemplazo.isActivo());
            
            return muebleRepo.save(existingMueble);
        } else {
            return null;
        }
    }

}
