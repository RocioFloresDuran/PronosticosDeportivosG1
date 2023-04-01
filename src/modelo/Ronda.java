package modelo;

import java.util.ArrayList;

public class Ronda {
    
	private String nro;
	private ArrayList<Partido> partidos;
        
	public Ronda(String nro, ArrayList<Partido> partidos) {
		this.nro = nro;
		this.partidos = partidos;
	}
        
	public Ronda() {
	}
        
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public ArrayList<Partido> getPartidos() {
		return partidos;
	}
	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}
        
       public int puntosRonda(Pronostico[] pronosticos) {

            int puntosTotales = 0;

            for (Pronostico pronostico : pronosticos) { //recorro el array de pronósticos que vino por parámetro
                for (Partido partido : partidos) { //recorro el array de partidos que tengo en el objeto ronda
                    
                    if (pronostico.getPartido().getId().equals(partido.getId())) { //si el id del partido en el pronóstico 
                                                                                   //es igual al id del partido en la ronda            
                        pronostico.setPartido(partido); //guardo ese partido en el pronóstico
                        puntosTotales += pronostico.puntos(); //llamo al método puntos y lo guardo en la variable
                    }
                    
                }
            }

            return puntosTotales;
        }

}
