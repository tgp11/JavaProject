package Modelo;
public class Ticket {
    private int codTicket;
    private java.sql.Date fecha;
    private java.sql.Time hora; // Nuevo campo
    private java.math.BigDecimal precio; // Nuevo campo
    private int codP;
    private int codSala;
    private int idPeli;
	public int getCodTicket() {
		return codTicket;
	}
	public void setCodTicket(int codTicket) {
		this.codTicket = codTicket;
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
	public java.math.BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(java.math.BigDecimal precio) {
		this.precio = precio;
	}
	public int getCodP() {
		return codP;
	}
	public void setCodP(int codP) {
		this.codP = codP;
	}
	public int getCodSala() {
		return codSala;
	}
	public void setCodSala(int codSala) {
		this.codSala = codSala;
	}
	public int getIdPeli() {
		return idPeli;
	}
	public void setIdPeli(int idPeli) {
		this.idPeli = idPeli;
	}
    
}