package Modelo;
public class TarjetaAfiliado {
    private int codTarjeta;
    private String tipo;
    private java.sql.Date fCaducidad;
    private java.sql.Date fExpedicion;
    private int codCine;

    public int getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(int codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public java.sql.Date getFCaducidad() {
        return fCaducidad;
    }

    public void setFCaducidad(java.sql.Date fCaducidad) {
        this.fCaducidad = fCaducidad;
    }

    public java.sql.Date getFExpedicion() {
        return fExpedicion;
    }

    public void setFExpedicion(java.sql.Date fExpedicion) {
        this.fExpedicion = fExpedicion;
    }

    public int getCodCine() {
        return codCine;
    }

    public void setCodCine(int codCine) {
        this.codCine = codCine;
    }
}