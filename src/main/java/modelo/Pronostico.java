package modelo;

public class Pronostico {
	private Persona persona;
	private Partido partido;
	private Equipo equipo;
	private String resultado;
        private String idRonda;

	public Pronostico(Persona persona, Partido partido, Equipo equipo, String resultado) {
		this.persona = persona;
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}
	
	public Pronostico(Partido partido, Equipo equipo, String resultado) {
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}

    public Pronostico(Partido partido, Equipo equipo, String resultado, String idRonda) {
        this.persona = new Persona();
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
        this.idRonda = idRonda;
    }
	

	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public Pronostico() {
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
        public String getIdRonda() {
        return idRonda;
    }

    public void setIdRonda(String idRonda) {
        this.idRonda = idRonda;
    }
        
	public int aciertos() {
		if (resultado.equals(partido.resultado(equipo))) {
		return 	1; 
                }
		else{
			return 0;
		}
	}

    
}
