package Modelo;
public class Sala {
    private int codSala;
    private String nombre;
    private int capacidad;
    private int codTarjeta;

    public int getCodSala() {
        return codSala;
    }

    public void setCodSala(int codSala) {
        this.codSala = codSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(int codTarjeta) {
        this.codTarjeta = codTarjeta;
    }
}