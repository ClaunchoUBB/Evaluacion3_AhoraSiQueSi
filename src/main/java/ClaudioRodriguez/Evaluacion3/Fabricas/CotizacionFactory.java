package ClaudioRodriguez.Evaluacion3.Fabricas;

import java.time.LocalDateTime;

import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;

public class CotizacionFactory {

    public static Cotizacion crearCotizacionVacia() {
        Cotizacion c = new Cotizacion();
        c.setFecha_cotizacion(LocalDateTime.now());
        c.setTotal(0);
        return c;
    }
}
