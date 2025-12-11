package ClaudioRodriguez.Evaluacion3.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ClaudioRodriguez.Evaluacion3.Entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
}
