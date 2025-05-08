package Modelo;
public class Proyectar {
    private int idPeli;
    private int codSala;
    private java.sql.Date fecha; // Nuevo campo
    private java.sql.Time hora; // Nuevo campo
	public int getIdPeli() {
		return idPeli;
	}
	public void setIdPeli(int idPeli) {
		this.idPeli = idPeli;
	}
	public int getCodSala() {
		return codSala;
	}
	public void setCodSala(int codSala) {
		this.codSala = codSala;
	}
	public java.sql.Date getFecha() {
		return fecha;
	}
	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}
	public java.sql.Time getHora() {
		return hora;
	}
	public void setHora(java.sql.Time hora) {
		this.hora = hora;
	}
    
}