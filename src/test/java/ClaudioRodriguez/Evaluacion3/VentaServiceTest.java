package ClaudioRodriguez.Evaluacion3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import ClaudioRodriguez.Evaluacion3.Service.VentaService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class VentaServiceTest {

    @Autowired
    private VentaService servicio;

    @Autowired
    private MuebleService muebleServ;

    @Autowired
    private VarianteService varServ;

    @Autowired
    private CotizacionService cotServ;

    @Autowired
    private CotMuebleService cmServ;

    @Test
    void confirmarVenta_StockInsuficiente() {

        Mueble muebleOriginal = MuebleServiceTest.buildMueble();
        muebleOriginal.setStock(5);
        muebleOriginal.setNombre_mueble("Sillón Test");
        Mueble muebleGuardado = muebleServ.saveMueble(muebleOriginal);

        Variante varGuardada = varServ.saveVariante(VariantesServiceTest.buildVariante());
        Cotizacion cotGuardada = cotServ.saveCotizacion(CotizacionServiceTest.buildCotizacion());

        int cantidadSolicitada = 10;
        CotMueble item = new CotMueble();
        item.setMueble(muebleGuardado);
        item.setVariante(varGuardada);
        item.setCantidad(cantidadSolicitada);
        item.setPrecioUnitario();
        item.setCotizacion(cotGuardada);

        cotGuardada.getCotMuebles().add(item);

        cmServ.saveCotMueble(item);

        cotServ.calcularTotal(cotGuardada);
        cotServ.saveCotizacion(cotGuardada);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicio.confirmarVenta(cotGuardada.getIdCotizacion());
        });

        String expectedMessage = "Stock insuficiente para mueble 'Sillón Test'. Stock disponible: 5 — solicitado: 10";
        assertEquals(expectedMessage, exception.getMessage(),
                "Debe lanzar una RuntimeException con el mensaje de stock insuficiente que incluye los detalles.");

        Mueble muebleDespuesFallo = muebleServ.getMuebleById(muebleGuardado.getId_mueble());
        assertEquals(5, muebleDespuesFallo.getStock(), "El stock NO debe haber sido modificado tras el fallo.");
    }

    @Test
    void confirmarVenta_CotizacionNoEncontrada() {
        int idInexistente = 9999;

        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicio.confirmarVenta(idInexistente);
        });

        assertEquals("Cotización no encontrada", exception.getMessage(),
                "Debe lanzar la excepción correcta para Cotización no encontrada.");
    }

    @Test
    void confirmarVenta() {
        Mueble muebleOriginal = MuebleServiceTest.buildMueble();
        muebleOriginal.setStock(10);
        muebleOriginal.setNombre_mueble("Sillón Test");
        Mueble muebleGuardado = muebleServ.saveMueble(muebleOriginal);

        Variante varGuardada = varServ.saveVariante(VariantesServiceTest.buildVariante());
        Cotizacion cotGuardada = cotServ.saveCotizacion(CotizacionServiceTest.buildCotizacion());

        int cantidadSolicitada = 1;
        CotMueble item = new CotMueble();
        item.setMueble(muebleGuardado);
        item.setVariante(varGuardada);
        item.setCantidad(cantidadSolicitada);
        item.setPrecioUnitario();
        item.setCotizacion(cotGuardada);

        cotGuardada.getCotMuebles().add(item);

        cmServ.saveCotMueble(item);

        cotServ.calcularTotal(cotGuardada);
        cotServ.saveCotizacion(cotGuardada);
        servicio.confirmarVenta(cotGuardada.getIdCotizacion());
        assertEquals(muebleGuardado.getStock(), 9);
        


    }
}
