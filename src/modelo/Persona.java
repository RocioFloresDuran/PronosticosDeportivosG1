package modelo;

public class Persona {
	// atributos
	private String nombre;
	private int dni;

	// constructores

	public Persona(String nombre, int dni) {
		//super();
		this.nombre = nombre;
		this.dni = dni;
	}
	
	
	public Persona() {
		//super();
	}

	// getters y setters

	public String getNombre() {
		return nombre;
	}

	
	

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}





	
	
}
