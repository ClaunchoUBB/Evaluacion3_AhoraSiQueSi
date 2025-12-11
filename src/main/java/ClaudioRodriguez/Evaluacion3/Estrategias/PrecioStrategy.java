package ClaudioRodriguez.Evaluacion3.Estrategias;


//Finalmente un patrón de diseño, porque había olvodado hacerlo
public interface PrecioStrategy {
    int calcularPrecio(int precioBase, int cantidad, int precioAdicional);
}