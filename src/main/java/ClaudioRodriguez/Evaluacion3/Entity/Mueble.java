package ClaudioRodriguez.Evaluacion3.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mueble")

public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Mueble")
    private int id_mueble;

    @OneToMany(mappedBy = "mueble")
    @JsonIgnore
    private List<CotMueble> cotMuebles = new ArrayList<>();

    @Column(name = "nombre_mueble")
    private String nombre_mueble;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "precio_base")
    private int precio_base;

    @Column(name = "material")
    private EnumMaterial material;

    @Column(name = "stock")
    private int stock;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "tamano")
    private EnumTamano tamano;
    
    // Getters and Setters
    public int getId_mueble() {
        return id_mueble;
    }

    public void setId_mueble(int id_mueble) {
        this.id_mueble = id_mueble;
    }

    public String getNombre_mueble() {
        return nombre_mueble;
    }

    public void setNombre_mueble(String nombre_mueble) {
        this.nombre_mueble = nombre_mueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(int precio_base) {
        this.precio_base = precio_base;
    }

    public EnumMaterial getMaterial() {
        return material;
    }

    public void setMaterial(EnumMaterial material) {
        this.material = material;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public EnumTamano getTamano() {
        return tamano;
    }

    public void setTamano(EnumTamano tamano) {
        this.tamano = tamano;
    }

    public List<CotMueble> getCotMuebles() {
        return cotMuebles;
    }

    public void setCotMuebles(List<CotMueble> cotMuebles) {
        this.cotMuebles = cotMuebles;
    }


    
}
