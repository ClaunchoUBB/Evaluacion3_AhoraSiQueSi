package ClaudioRodriguez.Evaluacion3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ClaudioRodriguez.Evaluacion3.Entity.Variantes;

@Repository
public interface VariantesRepository extends JpaRepository<Variantes, Integer> {
} 
