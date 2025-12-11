package ClaudioRodriguez.Evaluacion3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.EnumMaterial;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Repository.CotMuebleRepository;
import ClaudioRodriguez.Evaluacion3.Repository.CotizacionRepository;
import ClaudioRodriguez.Evaluacion3.Repository.MuebleRepository;
import ClaudioRodriguez.Evaluacion3.Entity.EnumTamano;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VentaControllerTest {

    @Autowired
    private MuebleRepository muebleRepo;
    @Autowired
    private CotizacionRepository cotiRepo;
    @Autowired
    private CotMuebleRepository cmRepo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void confirmarVenta_ok() throws Exception {

        //Primero creamos un mueble con stock apra proar

        Mueble mueble = new Mueble();
        mueble.setNombre_mueble("MuebleTest");
        mueble.setTipo("Test");
        mueble.setStock(2);
        mueble.setPrecio_base(1000);
        mueble.setMaterial(EnumMaterial.MADERA);
        mueble.setTamano(EnumTamano.MEDIANO);
        mueble.setActivo(true);
        muebleRepo.save(mueble);
        

        Cotizacion cot = new Cotizacion();
        cot.setTotal(1000);
        cot = cotiRepo.save(cot);

        CotMueble cm = new CotMueble();
        cm.setCotizacion(cot);
        cm.setMueble(mueble);
        cm.setCantidad(2);
        cm.setPrecioUnitario(1000);
        cmRepo.save(cm);

       mockMvc.perform(post("/api/cotizaciones/{id}/confirmar", cot.getId_cotizacion()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.total_venta").value(1000));

    }

    @Test
    void obtenerVentas_ok() throws Exception {
        mockMvc.perform(get("/api/ventas"))
                .andExpect(status().isOk());
    }
}
