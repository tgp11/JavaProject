package Modelo;
public class Actor {

    private int codP;
	private String Nombre;
	private String Apellido;
    private int nPremios;
    private int nActuaciones;
	public int getCodP() {
		return codP;
	}
	public void setCodP(int codP) {
		this.codP = codP;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public int getnPremios() {
		return nPremios;
	}
	public void setnPremios(int nPremios) {
		this.nPremios = nPremios;
	}
	public int getnActuaciones() {
		return nActuaciones;
	}
	public void setnActuaciones(int nActuaciones) {
		this.nActuaciones = nActuaciones;
	}

    
}