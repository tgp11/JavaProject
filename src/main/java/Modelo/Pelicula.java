package Modelo;
public class Pelicula {
    private int idPeli;
    private String titulo;
    private int duracion;
    private String clasificacion;
    private java.sql.Date fEstreno;
    private java.math.BigDecimal presupuesto; // Nuevo campo
    private java.math.BigDecimal recaudacion; // Nuevo campo
	public int getIdPeli() {
		return idPeli;
	}
	public void setIdPeli(int idPeli) {
		this.idPeli = idPeli;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public java.sql.Date getfEstreno() {
		return fEstreno;
	}
	public void setfEstreno(java.sql.Date fEstreno) {
		this.fEstreno = fEstreno;
	}
	public java.math.BigDecimal getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(java.math.BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}
	public java.math.BigDecimal getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(java.math.BigDecimal recaudacion) {
		this.recaudacion = recaudacion;
	}
    
}