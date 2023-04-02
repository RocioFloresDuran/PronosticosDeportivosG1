package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GestorPronosticos {

	public static void main(String[] args) throws IOException {
            
                ArrayList arrayPartidos = new ArrayList<Partido>();
            
		String archivoResultados = "C:\\Users\\Vanesa\\eclipse-workspace\\PronosticosDeportivosG1\\src\\modelo\\Resultados.csv";
		String idPartido = "";
		int goles1 = 0;
		int goles2 = 0;
                
                for(String linea : Files.readAllLines(Paths.get(archivoResultados))){
                    
                    idPartido = linea.split(",")[0];
                    Equipo equipo1 = new Equipo(linea.split(",")[1]);
                    Equipo equipo2 = new Equipo(linea.split(",")[2]);
                    goles1 = Integer.parseInt(linea.split(",")[3]);
                    goles2 = Integer.parseInt(linea.split(",")[4]);
                
                    Partido partido = new Partido(idPartido,equipo1,equipo2,goles1,goles2);
                    arrayPartidos.add(partido);
          
                }
                
                Ronda ronda = new Ronda("ronda 1",arrayPartidos);
		

// Agregado 
                ArrayList arrayPronosticos = new ArrayList<Pronostico>();
            
		String archivoPronosticos = "C:\\Users\\luz\\proyecto.java\\PronosticosDeportivosG1\\src\\modelo\\Pronosticos.csv";

                String resultado = "";
				
		for(String linea : Files.readAllLines(Paths.get(archivoPronosticos))){
                    
                    Partido partido = new Partido(linea.split(",")[0]); 
                    Equipo equipo = new Equipo(linea.split(",")[1]);
                    resultado = linea.split(",")[2];
                    
                    Pronostico pronostico = new Pronostico(partido,equipo,resultado);
                    arrayPronosticos.add(pronostico);
                
                }		
              
              System.out.println("Puntos totales " + ronda.puntosRonda(arrayPronosticos));
              
	}

}
