package ClaudioRodriguez.Evaluacion3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    
}
