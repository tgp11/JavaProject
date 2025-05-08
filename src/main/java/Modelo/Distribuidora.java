package Modelo;
public class Distribuidora {
    private int idDistribuidora;
    private String nombre;
    private java.math.BigDecimal patrimonio;

    public int getIdDistribuidora() {
        return idDistribuidora;
    }

    public void setIdDistribuidora(int idDistribuidora) {
        this.idDistribuidora = idDistribuidora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.math.BigDecimal getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(java.math.BigDecimal patrimonio) {
        this.patrimonio = patrimonio;
    }
}