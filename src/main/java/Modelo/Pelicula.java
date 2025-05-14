package Modelo;
public class Pelicula {
    private int id_peli;
    private String titulo;
    private int duracion;
    private String clasificacion;
    private java.sql.Date f_estreno;
    private java.math.BigDecimal presupuesto; // Nuevo campo
    private java.math.BigDecimal recaudacion; // Nuevo campo
	public int getIdPeli() {
		return id_peli;
	}
	public void setIdPeli(int idPeli) {
		this.id_peli = idPeli;
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
		return f_estreno;
	}
	public void setfEstreno(java.sql.Date fEstreno) {
		this.f_estreno = fEstreno;
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