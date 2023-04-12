package modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PersonaTest {
    
    Equipo equipoA;
    Equipo equipoB;
    Equipo equipoC;
    Equipo equipoD;
    Partido partido1;
    Partido partido2;
    Partido partido3;
    Partido partido4;
    Ronda primeraRonda;
    Ronda segundaRonda;
    Pronostico pronostico1;
    Pronostico pronostico2;
    Pronostico pronostico3;
    Pronostico pronostico4;
    
    public PersonaTest() {
    }
    
    @Before
    public void setUp() {
        equipoA = new Equipo("Boca");
        equipoB = new Equipo("River");
        equipoC = new Equipo("Independiente");
        equipoD = new Equipo("Racing");
        partido1 = new Partido("w",equipoA,equipoB,4,2);
        partido2 = new Partido("x",equipoC,equipoD,0,2);
        partido3 = new Partido("y",equipoB,equipoC,1,0);
        partido4 = new Partido("z",equipoD,equipoA,0,0);
        primeraRonda = new Ronda("1°");
        segundaRonda = new Ronda("2°");
        primeraRonda.getPartidos().add(partido1);
        primeraRonda.getPartidos().add(partido2);
        segundaRonda.getPartidos().add(partido3);
        segundaRonda.getPartidos().add(partido4);
        pronostico1 = new Pronostico(partido1,equipoA,"ganador","1°");
        pronostico2 = new Pronostico(partido2,equipoC,"ganador","1°");
        pronostico3 = new Pronostico(partido3,equipoB,"perdedor","2°");
        pronostico4 = new Pronostico(partido4,equipoA,"ganador","2°");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of puntoPersoRonda method, of class Persona.
     */
    @Test
    public void testPuntoPersoRonda() {
        System.out.println("puntoPersoRonda");
        ArrayList<Ronda> arrayRondas = new ArrayList();
        arrayRondas.add(primeraRonda);
        arrayRondas.add(segundaRonda);
        ArrayList<Pronostico> arrayPronosticos = new ArrayList();
        arrayPronosticos.add(pronostico1);
        arrayPronosticos.add(pronostico2);
        arrayPronosticos.add(pronostico3);
        arrayPronosticos.add(pronostico4);
        Persona instance = new Persona("Juan",12345678,arrayPronosticos);
        int expResult = 1;
        int result = instance.puntoPersoRonda(arrayRondas);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of puntoPersoRonda method, of class Persona.
     */
    @Test
    public void testPuntoPersoRondaAciertos() {
        System.out.println("puntoPersoRonda con todos los pronósticos acertados");
        ArrayList<Ronda> arrayRondas = new ArrayList();
        arrayRondas.add(primeraRonda);
        arrayRondas.add(segundaRonda);
        ArrayList<Pronostico> arrayPronosticos = new ArrayList();
        arrayPronosticos.add(pronostico1);
        arrayPronosticos.add(pronostico2);
        arrayPronosticos.add(pronostico3);
        arrayPronosticos.add(pronostico4);
        pronostico2.setResultado("perdedor");
        pronostico3.setResultado("ganador");
        pronostico4.setResultado("empate");
        Persona instance = new Persona("Mónica",87654321,arrayPronosticos);
        int expResult = 4;
        int result = instance.puntoPersoRonda(arrayRondas);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of puntoPersoRonda method, of class Persona.
     */
    @Test
    public void testPuntoPersoRondaInvertido() {
        System.out.println("puntoPersoRonda orden de rondas invertido");
        ArrayList<Ronda> arrayRondas = new ArrayList();
        arrayRondas.add(segundaRonda);
        arrayRondas.add(primeraRonda);
        ArrayList<Pronostico> arrayPronosticos = new ArrayList();
        arrayPronosticos.add(pronostico1);
        arrayPronosticos.add(pronostico2);
        arrayPronosticos.add(pronostico3);
        arrayPronosticos.add(pronostico4);
        pronostico2.setResultado("perdedor");
        pronostico3.setResultado("ganador");
        pronostico4.setResultado("empate");
        Persona instance = new Persona("Osvaldo",24681012,arrayPronosticos);
        int expResult = 4;
        int result = instance.puntoPersoRonda(arrayRondas);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of puntoPersoRonda method, of class Persona.
     */
    @Test
    public void testPuntoPersoRondaUna() {
        System.out.println("puntoPersoRonda con una sola ronda");
        ArrayList<Ronda> arrayRondas = new ArrayList();
        arrayRondas.add(segundaRonda);
        ArrayList<Pronostico> arrayPronosticos = new ArrayList();
        arrayPronosticos.add(pronostico1);
        arrayPronosticos.add(pronostico2);
        arrayPronosticos.add(pronostico3);
        arrayPronosticos.add(pronostico4);
        pronostico2.setResultado("perdedor");
        pronostico3.setResultado("ganador");
        pronostico4.setResultado("empate");
        Persona instance = new Persona("Dago",13579112,arrayPronosticos);
        int expResult = 2;
        int result = instance.puntoPersoRonda(arrayRondas);
        assertEquals(expResult, result);
    }
    
}
