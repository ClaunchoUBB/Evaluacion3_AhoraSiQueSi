package ClaudioRodriguez.Evaluacion3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ClaudioRodriguez.Evaluacion3.Entity.EnumMaterial;
import ClaudioRodriguez.Evaluacion3.Entity.EnumTamano;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Entity.Variantes;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;
import ClaudioRodriguez.Evaluacion3.Repository.VariantesRepository;
import static org.hamcrest.Matchers.greaterThan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CotizacionControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private MuebleRepository muebleRepository;
  @Autowired
  private VariantesRepository variantesRepository;
  @Autowired
  private CotizacionRepository cotizacionRepository;

  private Integer muebleId;
  private Integer varianteId;

  private final int PRECIO_BASE_MUEBLE = 10000;
  private final int PRECIO_ADICIONAL_VARIANTE = 5000;
  private final int CANTIDAD_SOLICITADA = 3;
  private final int PRECIO_ESPERADO = (PRECIO_BASE_MUEBLE + PRECIO_ADICIONAL_VARIANTE) * CANTIDAD_SOLICITADA; // (15000
                                                                                                              // * 3) =
                                                                                                              // 45000

  @BeforeEach
  void setup() {
    // 1. CREAR MUEBLE BASE
    Mueble mueble = new Mueble();
    mueble.setNombre_mueble("Silla Ejecutiva");
    mueble.setPrecio_base(PRECIO_BASE_MUEBLE);
    mueble.setStock(10);
    mueble.setActivo(true);
    mueble.setMaterial(EnumMaterial.METAL);
    mueble.setTamano(EnumTamano.MEDIANO);
    mueble.setTipo("Silla");
    Mueble savedMueble = muebleRepository.save(mueble);
    muebleId = savedMueble.getId_mueble();

    // 2. CREAR VARIANTE ASOCIADA
    Variantes variante = new Variantes();
    variante.setMueble(savedMueble);
    variante.setDescripcion("Tapizado de Cuero Sintético");
    variante.setPrecioAdicional(PRECIO_ADICIONAL_VARIANTE);
    Variantes savedVariante = variantesRepository.save(variante);
    varianteId = savedVariante.getIdVariante();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void crearCotizacion_conMuebleYVariante() throws Exception {

    String json = String.format("""
        {
          "cotMuebles": [
            {
              "mueble": { "id_mueble": %d },
              "variante": { "idVariante": %d },
              "cantidad": %d
            }
          ]
        }
        """, muebleId, varianteId, CANTIDAD_SOLICITADA);

    mockMvc.perform(post("/api/cotizaciones")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id_cotizacion").exists())
        .andExpect(jsonPath("$.id_cotizacion", greaterThan(0)))
        .andExpect(jsonPath("$.cotMuebles[0].precioUnitario").value(PRECIO_BASE_MUEBLE + PRECIO_ADICIONAL_VARIANTE))
        .andExpect(jsonPath("$.total").value(PRECIO_ESPERADO));
  }

  @Test
  void crearCotizacion_sinVariante_ok() throws Exception {
    int cantidad = 2;
    int totalEsperado = PRECIO_BASE_MUEBLE * cantidad;

    String json = String.format("""
        {
          "cotMuebles": [
            {
              "mueble": { "id_mueble": %d },
              "cantidad": %d
            }
          ]
        }
        """, muebleId, cantidad);

    mockMvc.perform(post("/api/cotizaciones")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.total").value(totalEsperado))
        .andExpect(jsonPath("$.cotMuebles[0].precioUnitario").value(PRECIO_BASE_MUEBLE));
  }

  @Test
  void obtenerCotizaciones_ok() throws Exception {
    mockMvc.perform(get("/api/cotizaciones"))
        .andExpect(status().isOk());
  }
}
