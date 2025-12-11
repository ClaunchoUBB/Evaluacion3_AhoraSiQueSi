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
public class VariantesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearVariante_ok() throws Exception {
        String json = """
        {
          "descripcion": "Color negro",
          "precio_adicional": 10000,
          "mueble": {
              "id_mueble": 1
          }
        }
        """;

        mockMvc.perform(post("/api/variantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerVariantes_ok() throws Exception {
        mockMvc.perform(get("/api/variantes"))
                .andExpect(status().isOk());
    }
}
