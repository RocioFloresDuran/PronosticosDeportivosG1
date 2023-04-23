package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class GestorPronosticos {

    static int puntos = 0;
    public static void main(String[] args) throws CantidadDeCamposException {

        //Inicialización de arrays
        ArrayList<Persona> arrayPersonas = cargarPersonasPronosticos();
        ArrayList<Ronda> arrayRondas = new ArrayList();
        
        // Inicialización de variables de partidos
        String idPartido = "";
        int goles1 = 0;
        int goles2 = 0;
        String idRonda = "";


        // Recorro el archivo y creo objetos Partido
        for (String linea : leerResultados()) {

            validar(linea);

            idPartido = linea.split(",")[0];
            Equipo equipo1 = new Equipo(linea.split(",")[1]);
            Equipo equipo2 = new Equipo(linea.split(",")[2]);
            goles1 = Integer.parseInt(linea.split(",")[3]);
            goles2 = Integer.parseInt(linea.split(",")[4]);
            idRonda = linea.split(",")[5];

            Partido partido = new Partido(idPartido, equipo1, equipo2, goles1, goles2);
            
            boolean existe = false; // Variable auxiliar
            
            for (Ronda elemento : arrayRondas){ //Recorro array de rondas
                if(elemento.getNro().equals(idRonda)){ //Si existe alguna con el mismo id leído de archivo
                    elemento.getPartidos().add(partido); //Le añado el partido creado
                    existe = true;
                }
            } //Finalizo recorrido del array y ahora sé si la ronda ya existe o no
            
            if(existe == false){ //Si NO existe
                Ronda rondaNueva = new Ronda(idRonda); //Creo una con el id leído
                rondaNueva.getPartidos().add(partido); //Le añado el partido creado
                arrayRondas.add(rondaNueva); //Agrego la nueva ronda al array de rondas
            }
            
        }

        //Salida
        System.out.println("Persona  Aciertos   Puntos");
        for (Persona pers : arrayPersonas) {
            System.out.println(pers.getNombre() + "      " + pers.aciertoPersoRonda(arrayRondas)
                                                + "       " + pers.aciertoPersoRonda(arrayRondas) * puntos);
        }
    }
    
    // Métodos de lectura de archivos

    public static List<String> leerResultados() {
        String archivoResultados = "C:\\Users\\rochi\\OneDrive\\Documentos\\NetBeansProjects\\TPIntegradorG1\\src\\main\\java\\modelo\\Resultados.csv";
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(Paths.get(archivoResultados));
            lineas.remove(0);
        } catch (IOException ex) {
            Logger.getLogger(GestorPronosticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lineas;
    }

    public static ArrayList<Persona> cargarPersonasPronosticos() {

        ArrayList<Persona> arrayPersona = new ArrayList<>();

        //Lectura de archivo de Datos de Conexión
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("C:\\Users\\rochi\\OneDrive\\Documentos\\NetBeansProjects\\TPIntegradorG1\\src\\main\\java\\modelo\\config.ini")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driver = properties.getProperty("driver");
        String url =properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String database = properties.getProperty("database");
        puntos = Integer.parseInt(properties.getProperty("puntosPartido"));

        //Lectura Base de Datos
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url+database,user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from personas");

            //Cargo array de personas
            while(rs.next()) {
                Persona persona = new Persona(rs.getString(2), rs.getInt(1));
                arrayPersona.add(persona);
            }

            //Cargo pronósticos a cada persona
            for (Persona persona : arrayPersona){
                ResultSet rs2 = stmt.executeQuery("select * from pronosticos where personas_dni ="+persona.getDni());
                while (rs2.next()){
                    Partido partido = new Partido(rs2.getString(2));
                    Equipo equipo = new Equipo(rs2.getString(3));
                    Pronostico pronostico = new Pronostico(partido,equipo,rs2.getString(4),rs2.getString(5));
                    persona.getPronosticosPersona().add(pronostico);
                }
            }

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return arrayPersona;
    }

    public static void validar(String linea) throws CantidadDeCamposException{

        String[] contenidoLinea = linea.split(",");

        //Validar la cantidad de celdas de la linea
        if(contenidoLinea.length !=6) {
            System.out.println("Error - cantidad de columnas incorrecto");
            throw new CantidadDeCamposException();
        }

        //validar que los goles sean enteros
        //Para ello primero los convertimos
        try {
            Integer.parseInt(contenidoLinea[3]); //goles equipo 1
            Integer.parseInt(contenidoLinea[4]); //goles equipo 2
        } catch (Exception e) {
            System.out.println("Error - formato de goles incorrecto");

        }

    }

}
