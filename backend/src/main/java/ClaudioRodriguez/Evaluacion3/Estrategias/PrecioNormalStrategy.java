package ClaudioRodriguez.Evaluacion3.Estrategias;

public class PrecioNormalStrategy implements PrecioStrategy {

    @Override
    public int calcularPrecio(int precioBase, int cantidad, int precioAdicional) {
        return (precioBase + precioAdicional) * cantidad;
    }
}
