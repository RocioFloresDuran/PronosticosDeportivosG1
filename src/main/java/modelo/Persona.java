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
            this.pronosticosPersona= new ArrayList();
	}

        public Persona(String nombre, int dni, ArrayList<Pronostico> pronosticosPersona) {
            this.nombre = nombre;
            this.dni = dni;
            this.pronosticosPersona = pronosticosPersona;
        }
        
	public Persona() {
            //super();
	}

        public Persona(String nombre) {
        this.nombre = nombre;
        this.pronosticosPersona= new ArrayList();
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
    
    public int aciertoPersoRonda(ArrayList<Ronda> arrayRondas){
        
        int aciertosTotalesRondas = 0;
        
        for (Ronda elemento : arrayRondas) { //Recorro las rondas recibidas por parámetro
            
            //Inicializo un array para apartar los pronósticos que sean de la ronda actual
            ArrayList<Pronostico> pronosticosRonda = new ArrayList();
            
            for (Pronostico item : pronosticosPersona) { //Recorro todos los pronósticos de la persona
                if (elemento.getNro().equals(item.getIdRonda())) { //Si pertenecen a la ronda
                    pronosticosRonda.add(item); //Los agrego al array 
                }
            }
            //Llamo al método de la ronda actual con el array resultante, acumulo el resultado
            aciertosTotalesRondas += elemento.aciertosRonda(pronosticosRonda);
        }
        
        return aciertosTotalesRondas;
}


	
	
}
