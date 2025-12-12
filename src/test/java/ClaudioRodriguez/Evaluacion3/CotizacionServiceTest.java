package ClaudioRodriguez.Evaluacion3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Fabricas.CotizacionFactory;
import ClaudioRodriguez.Evaluacion3.Service.CotizacionService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class CotizacionServiceTest {

  @Autowired
  private CotizacionService servicio;



  protected static Cotizacion buildCotizacion() {
    Cotizacion cot = CotizacionFactory.crearCotizacionVacia();
    return cot;
  }

  @Test
  void crearCotizacion() {
    Cotizacion cot = buildCotizacion();

    servicio.saveCotizacion(cot);

    assertNotNull(cot.getIdCotizacion(), "La Cotización debe tener un ID autogenerado.");

    assertEquals(cot.getIdCotizacion(), servicio.getCotizacionById(cot.getIdCotizacion()).getIdCotizacion(),
        "La cotización recuperada debe coincidir con la guardada.");
  }


}
