package ClaudioRodriguez.Evaluacion3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ClaudioRodriguez.Evaluacion3.Entity.CotMueble;
import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;
import ClaudioRodriguez.Evaluacion3.Entity.Mueble;
import ClaudioRodriguez.Evaluacion3.Entity.Variante;
import ClaudioRodriguez.Evaluacion3.Service.CotMuebleService;
import ClaudioRodriguez.Evaluacion3.Service.CotizacionService;
import ClaudioRodriguez.Evaluacion3.Service.MuebleService;
import ClaudioRodriguez.Evaluacion3.Service.VarianteService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class CotMuebleServiceTest {

    @Autowired
    private CotMuebleService servicio;

    @Autowired
    private MuebleService muebleServ;

    @Autowired
    private VarianteService varServ;
    
    @Autowired
    private CotizacionService cotServ;

    @Test
    void guardarCotMueble(){
        CotMueble cotMueble = new CotMueble();
        Mueble mueblito = MuebleServiceTest.buildMueble();
        Variante var = VariantesServiceTest.buildVariante();
        Cotizacion cot = CotizacionServiceTest.buildCotizacion();
        cotMueble.setMueble(muebleServ.saveMueble(mueblito));
        cotMueble.setVariante(varServ.saveVariante(var));
        cotMueble.setPrecioUnitario();
        cotMueble.setCantidad(4);
        cotMueble.setCotizacion(cotServ.saveCotizacion(cot));
        assertNotNull(cotMueble.getVariante().getIdVariante());
        servicio.saveCotMueble(cotMueble);
        assertEquals(servicio.getCotMuebleById(cotMueble.getIdCotMueble()), cotMueble);
    }
    
}
