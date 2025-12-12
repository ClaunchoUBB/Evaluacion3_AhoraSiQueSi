package ClaudioRodriguez.Evaluacion3.Fabricas;

import java.time.LocalDateTime;

import ClaudioRodriguez.Evaluacion3.Entity.Cotizacion;

public class CotizacionFactory {

    public static Cotizacion crearCotizacionVacia() {
        Cotizacion cot = new Cotizacion();
        cot.setFecha_cotizacion(LocalDateTime.now());
        cot.setTotal(0);
        return cot;
    }
}
