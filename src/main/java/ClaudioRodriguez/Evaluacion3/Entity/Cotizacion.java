package ClaudioRodriguez.Evaluacion3.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int id_cotizacion;

    @Column(name = "fecha_cotizacion", insertable = false, updatable =  false)
    private LocalDateTime fecha_cotizacion;

    @Column(name = "total")
    private int total;

    @OneToMany(mappedBy = "cotizacion")
    @JsonIgnore
    private List<CotMueble> cotMueble = new ArrayList<CotMueble>();

    @OneToOne(mappedBy = "cotizacion")
    private Venta venta;

    public int getId_cotizacion() {
        return id_cotizacion;
    }

    public void setId_cotizacion(int id_cotizacion) {
        this.id_cotizacion = id_cotizacion;
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
        return cotMueble;
    }

    public void setCotMuebles(List<CotMueble> cotMueble) {
        this.cotMueble = cotMueble;
    }

    
}
