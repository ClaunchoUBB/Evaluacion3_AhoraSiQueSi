package ClaudioRodriguez.Evaluacion3.Service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ClaudioRodriguez.Evaluacion3.Entity.Variante;

import ClaudioRodriguez.Evaluacion3.Repository.VariantesRepository;

@Service
public class VarianteService {

    @Autowired
    private VariantesRepository variantesRepo;

    public List<Variante> getAllVariantes() {
        return variantesRepo.findAll();
    }

    public Variante saveVariante(Variante variante) {
        return variantesRepo.save(variante);
    }

    public void deleteVariante(int idVariante) {
        variantesRepo.deleteById(idVariante);
    }

    public Variante getVarianteById(int idVariante) {
        return variantesRepo.findById(idVariante).orElse(null);
    }

    public Variante updateVariante(int id, Variante reemplazo) {
        Optional<Variante> existente = variantesRepo.findById(id);
        if (existente.isPresent()) {
            Variante varianteExistente = existente.get();
            varianteExistente.setDescripcion(reemplazo.getDescripcion());
            varianteExistente.setPrecioAdicional(reemplazo.getPrecioAdicional());
            return variantesRepo.save(varianteExistente);
        } else {
            return null;

        }
    }

}
