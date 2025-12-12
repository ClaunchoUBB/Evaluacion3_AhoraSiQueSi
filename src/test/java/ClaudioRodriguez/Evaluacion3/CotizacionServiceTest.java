package ClaudioRodriguez.Evaluacion3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class CotizacionServiceTest {

  @Test
  void conseguirCotizaciones(){

      Mueble mueblito = new Mueble();
      mueblito.setActivo(true);
      mueblito.setId_mueble(1);

  }

  
}
