package ClaudioRodriguez.Evaluacion3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ClaudioRodriguez.Evaluacion3.Entity.Mueble;


@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Integer> {

    
}
