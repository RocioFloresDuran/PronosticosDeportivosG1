package modelo;

import java.util.ArrayList;

public class Persona {
	// atributos
	private String nombre;
	private int dni;
        private ArrayList<Pronostico> pronosticosPersona; 

	// constructores

	public Persona(String nombre, int dni) {
            this.nombre = nombre;
            this.dni = dni;
	}

        public Persona(String nombre, int dni, ArrayList<Pronostico> pronosticosPersona) {
            this.nombre = nombre;
            this.dni = dni;
            this.pronosticosPersona = pronosticosPersona;
        }
        
	public Persona() {
            //super();
	}

	// getters y setters

	public String getNombre() {
            return nombre;
	}

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

    public ArrayList<Pronostico> getPronosticosPersona() {
        return pronosticosPersona;
    }

    public void setPronosticosPersona(ArrayList<Pronostico> pronosticosPersona) {
        this.pronosticosPersona = pronosticosPersona;
    }


    public int puntoPersoRonda(Ronda ronda){
        ArrayList<Pronostico> pronosticosRonda = new ArrayList();
        for (Pronostico item: pronosticosPersona){
            if(ronda.getNro().equals(item.getIdRonda())){
                pronosticosRonda.add(item);
            } 
        }
        return ronda.puntosRonda(pronosticosRonda);
}


	
	
}
