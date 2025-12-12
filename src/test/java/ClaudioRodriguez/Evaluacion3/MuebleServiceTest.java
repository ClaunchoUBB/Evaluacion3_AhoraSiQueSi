package ClaudioRodriguez.Evaluacion3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ClaudioRodriguez.Evaluacion3.Entity.EnumMaterial;
import ClaudioRodriguez.Evaluacion3.Entity.EnumTamano;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Service.MuebleService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class MuebleServiceTest {

  @Autowired
  private MuebleService servicio;

  private Mueble buildMueble(){
    Mueble mueblito = new Mueble();
    mueblito.setCotMuebles(null);
    mueblito.setMaterial(EnumMaterial.MADERA);
    mueblito.setNombre_mueble("Silla");
    mueblito.setPrecio_base(20000);
    mueblito.setActivo(true);
    mueblito.setTamano(EnumTamano.GRANDE);
    mueblito.setStock(10);
    return mueblito;
  }

  @Test
    void guardarMueble() {
    Mueble mueblito = buildMueble();

    servicio.saveMueble(mueblito);

    Mueble retornado = servicio.getMuebleById(mueblito.getId_mueble());

    assertEquals(mueblito, retornado);
  }

  @Test
  void borrarMueble(){
    Mueble mueblit = buildMueble();
    servicio.saveMueble(mueblit);
    servicio.deleteMueble(mueblit.getId_mueble());
    assertNull(servicio.getMuebleById(mueblit.getId_mueble()));
  }


  @Test
  void actualizarMueble(){
    Mueble mueblito = buildMueble();
    servicio.saveMueble(mueblito);
    Mueble mueblito2 = mueblito;
    mueblito2.setPrecio_base(50000);
    servicio.updateMueble(mueblito.getId_mueble(), mueblito2);
    assertEquals(mueblito.getPrecio_base(),50000);
  }
}
