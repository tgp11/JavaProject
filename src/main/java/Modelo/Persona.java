
package Modelo;
public class Persona {
    private int codP;
    private String nombre;
    private String apellidos;
    private java.sql.Date fNacimiento;
    private String nacionalidad;
    private int codTarjeta;

    public int getCodP() {
        return codP;
    }

    public void setCodP(int codP) {
        this.codP = codP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public java.sql.Date getFNacimiento() {
        return fNacimiento;
    }

    public void setFNacimiento(java.sql.Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getCodTarjeta() {
        return codTarjeta;
    }

    public void setCodTarjeta(int codTarjeta) {
        this.codTarjeta = codTarjeta;
    }
}