package ClaudioRodriguez.Evaluacion3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ClaudioRodriguez.Evaluacion3.Entity.Variante;
import ClaudioRodriguez.Evaluacion3.Service.VarianteService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class VariantesServiceTest {

    @Autowired
    private VarianteService servicio;

    protected static Variante buildVariante() {
        Variante retorno = new Variante();
        retorno.setDescripcion("Variante de testeo");
        retorno.setPrecioAdicional(100);
        return retorno;
    }

    @Test
    void crearVariante() {
        Variante var = buildVariante();
        servicio.saveVariante(var);
        assertEquals(var, servicio.getVarianteById(var.getIdVariante()));
    }

    @Test
    void borrarVariante() {
        Variante var = buildVariante();
        servicio.saveVariante(var);
        servicio.deleteVariante(var.getIdVariante());
        assertNull(servicio.getVarianteById(var.getIdVariante()));
    }

    @Test
    void actualizarVariante(){
        Variante var = buildVariante();
        servicio.saveVariante(var);
        Variante var2 = var;
        var2.setDescripcion("nin");
        servicio.updateVariante(var.getIdVariante(), var2);
        assertEquals(var.getDescripcion(), "nin");
    }

}
