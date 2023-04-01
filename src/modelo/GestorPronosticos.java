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
		Equipo equipo1 = new Equipo();
		Equipo equipo2 = new Equipo();
		int goles1 = 0;
		int goles2 = 0;
                
                for(String linea : Files.readAllLines(Paths.get(archivoResultados))){
                    
                    idPartido = linea.split(",")[0];
                    equipo1.setNombre(linea.split(",")[1]);
                    equipo2.setNombre(linea.split(",")[2]);
                    goles1 = Integer.parseInt(linea.split(",")[3]);
                    goles2 = Integer.parseInt(linea.split(",")[4]);
                
                    Partido partido = new Partido(idPartido,equipo1,equipo2,goles1,goles2);
                    arrayPartidos.add(partido);
                
          
                }
                
                Ronda ronda = new Ronda("ronda 1",arrayPartidos);
						
				

	}

}
