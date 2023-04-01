package modelo;

public class Partido {
        private String id;
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEquipo1;
	private int golesEquipo2;

//Constructores
	public Partido(String id, Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
                this.id = id;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}

        public Partido(String id) {
                this.id = id;
        }
        
	public Partido() {
	}

//Métodos
	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
        

	public String resultado(Equipo equipo) {
		String resultado = "";

		if (equipo.equals(equipo1)) {
			if (golesEquipo1 > golesEquipo2) {
				resultado = "ganador";
			} else {
				resultado = "perdedor";
			}
		}

		if (equipo.equals(equipo2)) {
			if (golesEquipo2 > golesEquipo1) {
				resultado = "ganador";
			} else {
				resultado = "perdedor";
			}
		}

		if (golesEquipo1 == golesEquipo2) {
			resultado = "empate";
		}

		return resultado;
	}
}


