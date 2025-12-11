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
public class CotizacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crearCotizacionVacia_ok() throws Exception {
        String json = """
        {
          "cotMuebles": []
        }
        """;

        mockMvc.perform(post("/api/cotizaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCotizacion").exists());
    }

    @Test
    void obtenerCotizaciones_ok() throws Exception {
        mockMvc.perform(get("/api/cotizaciones"))
                .andExpect(status().isOk());
    }
}
