package ClaudioRodriguez.Evaluacion3.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Variantes")

public class Variantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_variante")
    private int idVariante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_mueble")
    @JsonIgnore
    private Mueble mueble;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio_adicional")
    private int precioAdicional;

    // Getters y Setters
    public int getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(int idVariante) {
        this.idVariante = idVariante;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecioAdicional() {
        return precioAdicional;
    }

    public void setPrecioAdicional(int precioAdicional) {
        this.precioAdicional = precioAdicional;
    }
}
