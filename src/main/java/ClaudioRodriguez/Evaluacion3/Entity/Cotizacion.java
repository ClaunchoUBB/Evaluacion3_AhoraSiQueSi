package ClaudioRodriguez.Evaluacion3.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotizacion")

public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCotizacion;

    @Column(name = "fecha_cotizacion", insertable = false, updatable =  false)
    private LocalDateTime fecha_cotizacion;

    @Column(name = "total")
    private int total;

    @OneToMany(mappedBy = "cotizacion", cascade =CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CotMueble> items = new ArrayList<CotMueble>();

    @OneToOne(mappedBy = "cotizacion")
    private Venta venta;

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int id_cotizacion) {
        this.idCotizacion = id_cotizacion;
    }

    public LocalDateTime getFecha_cotizacion() {
        return fecha_cotizacion;
    }

    public void setFecha_cotizacion(LocalDateTime fecha_cotizacion) {
        this.fecha_cotizacion = fecha_cotizacion;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<CotMueble> getCotMuebles() {
        return items;
    }

    public void setCotMuebles(List<CotMueble> cotMueble) {
        this.items = cotMueble;
    }

    
}
