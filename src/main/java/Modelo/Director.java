package Modelo;
public class Director {
    private int codP;
    private String Nombre;
	private String Apellido;
    private int nPremios;
    private int nDirecciones;
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
	public int getnDirecciones() {
		return nDirecciones;
	}
	public void setnDirecciones(int nDirecciones) {
		this.nDirecciones = nDirecciones;
	}

}