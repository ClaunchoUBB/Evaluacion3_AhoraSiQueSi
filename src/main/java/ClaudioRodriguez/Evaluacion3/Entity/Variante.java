package ClaudioRodriguez.Evaluacion3.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Variantes")

public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variante")
    private int idVariante;

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
