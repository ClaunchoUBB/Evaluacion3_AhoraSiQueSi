package ClaudioRodriguez.Evaluacion3.Service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ClaudioRodriguez.Evaluacion3.Entity.Variantes;

import ClaudioRodriguez.Evaluacion3.Repository.VariantesRepository;

@Service
public class VariantesService {

    @Autowired
    private VariantesRepository variantesRepo;

    public List<Variantes> getAllVariantes() {
        return variantesRepo.findAll();
    }

    public Variantes saveVariante(Variantes variante) {
        return variantesRepo.save(variante);
    }


    public void deleteVariante(int idVariante) {
        variantesRepo.deleteById(idVariante);
    }

    public Variantes getVarianteById(int idVariante) {
        return variantesRepo.findById(idVariante).orElse(null);
    }

    public Variantes updateVariante(int id, Variantes reemplazo) {
        Optional<Variantes> existente = variantesRepo.findById(id);
        if (existente.isPresent()) {
            Variantes varianteExistente = existente.get();
            varianteExistente.setMueble(reemplazo.getMueble());
            varianteExistente.setDescripcion(reemplazo.getDescripcion());
            varianteExistente.setPrecioAdicional(reemplazo.getPrecioAdicional());
            return variantesRepo.save(varianteExistente);
        } else {
            return null;

        }
    }

}
