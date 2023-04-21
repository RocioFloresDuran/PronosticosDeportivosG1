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

    public static void main(String[] args) {

        //Inicialización de arrays
        ArrayList<Persona> arrayPersonas = new ArrayList();
        ArrayList<Ronda> arrayRondas = new ArrayList();
        
        // Inicialización de variables de partidos
        String idPartido = "";
        int goles1 = 0;
        int goles2 = 0;
        String idRonda = "";


        // Recorro el archivo y creo objetos Partido
        for (String linea : leerResultados()) {

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

        // Inicialización de variables de pronosticos
        String resultado = "";
        String nombrePersona = "";
        String rondaNro = "";

        // Recorro el archivo y creo objetos Pronostico en una colección
        /*for (String linea : leerPronosticos()) {

            Partido partido = new Partido(linea.split(",")[0]);
            Equipo equipo = new Equipo(linea.split(",")[1]);
            resultado = linea.split(",")[2];
            nombrePersona = linea.split(",")[3];
            rondaNro = linea.split(",")[4];
            
            Pronostico pronostico = new Pronostico(partido, equipo, resultado, rondaNro);
            boolean existe = false; // Variable auxiliar
            
            for (Persona elemento : arrayPersonas){ //Recorro array de personas
                if(elemento.getNombre().equals(nombrePersona)){ //Si existe alguna con el mismo nombre leído de archivo
                    elemento.getPronosticosPersona().add(pronostico); //Le añado el pronostico creado
                    existe = true;
                }
            } //Finalizo recorrido del array y ahora sé si la persona ya existe o no
            
            if(existe == false){ //Si NO existe
                Persona personaNueva = new Persona(nombrePersona); //Creo una con el nombre leído
                personaNueva.getPronosticosPersona().add(pronostico); //Le añado el pronostico creado
                arrayPersonas.add(personaNueva); //Agrego la nueva persona al array de persona
            }
        } */

        //Salida
        System.out.println("Persona   Puntos");
        for (Persona pers : arrayPersonas) {
            System.out.println(pers.getNombre() + "      " + pers.puntoPersoRonda(arrayRondas));
        }

        leerPronosticos();
    }
    
    // Métodos de lectura de archivos

    public static List<String> leerResultados() {
        String archivoResultados = "C:\\Users\\Vanesa\\eclipse-workspace\\PronosticosDeportivosG1\\src\\main\\java\\modelo\\Resultados.csv";
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(Paths.get(archivoResultados));
            lineas.remove(0);
        } catch (IOException ex) {
            Logger.getLogger(GestorPronosticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lineas;
    }

    public static void leerPronosticos() {
        /*String archivoPronosticos = "C:\\Users\\lu_el\\IdeaProjects\\Proyectointegrador\\PronosticosDeportivosG1\\src\\main\\java\\modelo\\Pronosticos.csv";
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(Paths.get(archivoPronosticos));
            lineas.remove(0);
        } catch (IOException ex) {
            Logger.getLogger(GestorPronosticos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lineas;*/

        ArrayList<Persona> arrayPersona2 = new ArrayList<>();
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("C:\\Users\\Vanesa\\eclipse-workspace\\PronosticosDeportivosG1\\src\\main\\java\\modelo\\config.ini")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(properties.getProperty("driver"));
            System.out.println(properties.getProperty ("url"));
            System.out.println(properties.getProperty ("user"));
            System.out.println(properties.getProperty("password"));

        //LECTURA BASE DATOS
        String driver = properties.getProperty("driver");
        String url =properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String database = properties.getProperty("database");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url+database,user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from personas");


            while(rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
            Persona persona= new Persona(rs.getString(2), rs.getInt(1));
           arrayPersona2.add(persona);

            int iddd = 246810;
            ResultSet rs2 = stmt.executeQuery("select * from pronosticos where personas_dni =" + iddd);
            while(rs2.next())
                System.out.println(rs2.getString(3)+" "+rs2.getString(4) +" "+ rs2.getInt(5)+" "+rs2.getString(6));

            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
