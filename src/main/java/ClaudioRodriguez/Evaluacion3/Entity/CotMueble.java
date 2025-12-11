package ClaudioRodriguez.Evaluacion3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cot_mueble")

public class CotMueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cot_Mueble")
    private int idCotMueble;

    @ManyToOne
    @JoinColumn(name = "ID_Cotizacion")
    @JsonIgnore
    private Cotizacion cotizacion;

    @ManyToOne
    @JoinColumn(name = "ID_Mueble")
    private Mueble mueble;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private int precioUnitario;

    @ManyToOne
    @JoinColumn (name = "ID_Variante")
    private Variantes variante;


    public int getIdCotMueble() {
        return idCotMueble;
    }

    public void setIdCotMueble(int idCotMueble) {
        this.idCotMueble = idCotMueble;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getSubtotal() {
        return cantidad * precioUnitario;
    }

    public Variantes getVariante() {
        return variante;
    }


    public void setVariante(Variantes v){
        this.variante = v;
    }
}
