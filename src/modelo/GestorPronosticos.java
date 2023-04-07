package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorPronosticos {

    public static void main(String[] args) {

        // Inicialización de variables de partidos
        ArrayList<Partido> arrayPartidos = new ArrayList();

        String idPartido = "";
        int goles1 = 0;
        int goles2 = 0;

        // Recorro el archivo y creo objetos Partido en una colección
        for (String linea : leerResultados()) {

            idPartido = linea.split(",")[0];
            Equipo equipo1 = new Equipo(linea.split(",")[1]);
            Equipo equipo2 = new Equipo(linea.split(",")[2]);
            goles1 = Integer.parseInt(linea.split(",")[3]);
            goles2 = Integer.parseInt(linea.split(",")[4]);

            Partido partido = new Partido(idPartido, equipo1, equipo2, goles1, goles2);
            arrayPartidos.add(partido);

        }

        // Creo la ronda con la colección de partidos
        Ronda ronda = new Ronda("ronda 1", arrayPartidos);

        // Inicialización de variables de pronosticos
        ArrayList<Pronostico> arrayPronosticos = new ArrayList();

        String resultado = "";

        // Recorro el archivo y creo objetos Pronostico en una colección
        for (String linea : leerPronosticos()) {

            Partido partido = new Partido(linea.split(",")[0]);
            Equipo equipo = new Equipo(linea.split(",")[1]);
            resultado = linea.split(",")[2];

            Pronostico pronostico = new Pronostico(partido, equipo, resultado);
            arrayPronosticos.add(pronostico);

        }

        // Imprimo resultado de puntos con la colección de pronosticos como argumento
        System.out.println("Puntos totales de la ronda: " + ronda.puntosRonda(arrayPronosticos));

    }
    
    // Métodos de lectura de archivos

    public static List<String> leerResultados() {
        String archivoResultados = "C:\\Users\\Seba\\eclipse-workspace\\"
        		+ "Pronostico_Deportivo_GIT\\src\\modelo\\Resultados.csv";
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(Paths.get(archivoResultados));
            lineas.remove(0);
        } catch (IOException ex) {
            Logger.getLogger(GestorPronosticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lineas;
    }

    public static List<String> leerPronosticos() {
        String archivoPronosticos = "C:\\Users\\Seba\\eclipse-workspace\\Pronostico_Deportivo_GIT\\src\\modelo\\Pronosticos.csv";
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(Paths.get(archivoPronosticos));
            lineas.remove(0);
        } catch (IOException ex) {
            Logger.getLogger(GestorPronosticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lineas;
    }

}
