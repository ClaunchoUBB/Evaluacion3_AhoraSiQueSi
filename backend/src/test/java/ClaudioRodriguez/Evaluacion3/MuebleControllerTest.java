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
public class MuebleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearMueble_ok() throws Exception {
        String json = """
        {
          "nombre_mueble": "Mesa Test",
          "tipo": "Comedor",
          "precio_base": 50000,
          "material": "MADERA",
          "stock": 5,
          "activo": true,
          "tamano": "MEDIANO"
        }
        """;

        mockMvc.perform(post("/api/muebles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_mueble").exists())
                .andExpect(jsonPath("$.nombre_mueble").value("Mesa Test"));
    }

    @Test
    void obtenerTodosMuebles_ok() throws Exception {
        mockMvc.perform(get("/api/muebles"))
                .andExpect(status().isOk());
    }
}
