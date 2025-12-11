package ClaudioRodriguez.Evaluacion3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CotMuebleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearCotMueble_ok() throws Exception {
        String json = """
        {
          "cotizacion": { "idCotizacion": 1 },
          "mueble": { "id_mueble": 1 },
          "variante": { "id_variante": 1 },
          "cantidad": 2,
          "precio_unitario": 120000
        }
        """;

        mockMvc.perform(post("/cotmueble")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void listarCotMuebles_ok() throws Exception {
        mockMvc.perform(get("/cotmueble"))
                .andExpect(status().isOk());
    }
}
