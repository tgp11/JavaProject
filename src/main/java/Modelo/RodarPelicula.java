package Modelo;
public class RodarPelicula {
    private java.sql.Date fInicioRodaje;
    private java.sql.Date fFinalRodaje;
    private int director;
    private int actor;
    private int idPeli;

    public java.sql.Date getFInicioRodaje() {
        return fInicioRodaje;
    }

    public void setFInicioRodaje(java.sql.Date fInicioRodaje) {
        this.fInicioRodaje = fInicioRodaje;
    }

    public java.sql.Date getFFinalRodaje() {
        return fFinalRodaje;
    }

    public void setFFinalRodaje(java.sql.Date fFinalRodaje) {
        this.fFinalRodaje = fFinalRodaje;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }

    public int getIdPeli() {
        return idPeli;
    }

    public void setIdPeli(int idPeli) {
        this.idPeli = idPeli;
    }
}